package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hotel")
public class HotelManagementController {

   
    private Map<String, Hotel> hotelDb = new HashMap<>();
    private Map<Integer, Booking> bookingDb = new HashMap<>();
    private Map<Integer, User> userDb = new HashMap<>();
    private int bookingIdCounter = 1;

    @PostMapping("/add-hotel")
    public String addHotel(@RequestBody Hotel hotel) {
        if (hotel == null || hotel.getHotelName() == null) {
            return "FAILURE";
        }

        if (hotelDb.containsKey(hotel.getHotelName())) {
            return "FAILURE";
        }

        hotelDb.put(hotel.getHotelName(), hotel);
        return "SUCCESS";
    }


    @PostMapping("/add-user")
    public Integer addUser(@RequestBody User user) {
        int aadharCardNo = user.hashCode();
        user.setAadharCardNo(aadharCardNo);
        userDb.put(aadharCardNo, user);
        return aadharCardNo;
    }

    @GetMapping("/get-hotel-with-most-facilities")
public Hotel getHotelWithMostFacilities() {
    if (hotelDb.isEmpty()) {
        return null;
    }

    Hotel hotelWithMostFacilities = hotelDb.values().stream()
            .filter(hotel -> !hotel.getFacilities().isEmpty())
            .max(Comparator.comparingInt(hotel -> hotel.getFacilities().size()))
            .orElse(null);

    return hotelWithMostFacilities;
}


@PostMapping("/book-a-room")
public int bookARoom(@RequestBody Booking booking) {
    Hotel hotel = hotelDb.get(booking.getHotelName());
    if (hotel == null || hotel.getAvailableRooms() < booking.getNumberOfRooms()) {
        return -1;
    }

    String bookingId = UUID.randomUUID().toString();
    booking.setBookingId(bookingId);
    int totalAmountPaid = booking.getNumberOfRooms() * hotel.getPricePerNight();
    booking.setAmountToBePaid(totalAmountPaid);

    bookingDb.put(bookingIdCounter, booking);
    bookingIdCounter++;

    hotel.setAvailableRooms(hotel.getAvailableRooms() - booking.getNumberOfRooms());
    return totalAmountPaid;
}

    
@GetMapping("/get-bookings-by-a-person/{aadharCard}")
    public List<Booking> getBookings(@PathVariable("aadharCard") Integer aadharCard) {
        List<Booking> bookingsByPerson = bookingDb.values().stream()
                .filter(booking -> booking.getBookingAadharCard() == aadharCard)
                .collect(Collectors.toList());
        return bookingsByPerson;
    }



    @PutMapping("/update-facilities")
    public Hotel updateFacilities(@RequestBody List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hotelDb.get(hotelName);

        if (hotel != null) {
            List<Facility> existingFacilities = hotel.getFacilities();

            // Add new facilities that the hotel is planning to bring
            for (Facility facility : newFacilities) {
                if (!existingFacilities.contains(facility)) {
                    existingFacilities.add(facility);
                }
            }

            // Update the hotel's facilities in the database
            hotelDb.put(hotelName, hotel);
        }

        return hotel;
    }


}
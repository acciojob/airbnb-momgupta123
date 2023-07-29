package com.driver.model;

public class Booking {

    private String bookingId; // This will be a random UUID generated String
    private int bookingAadharCard;
    private int numberOfRooms; // Added field for the number of rooms
    private String bookingPersonName;
    private String hotelName;
    private int amountToBePaid;

    public Booking(String bookingId, int bookingAadharCard, int numberOfRooms, String bookingPersonName, String hotelName) {
        this.bookingId = bookingId;
        this.bookingAadharCard = bookingAadharCard;
        this.numberOfRooms = numberOfRooms;
        this.bookingPersonName = bookingPersonName;
        this.hotelName = hotelName;
    }

    public Booking(int bookingAadharCard, int numberOfRooms, String bookingPersonName, String hotelName) {
        this.bookingAadharCard = bookingAadharCard;
        this.numberOfRooms = numberOfRooms;
        this.bookingPersonName = bookingPersonName;
        this.hotelName = hotelName;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public int getBookingAadharCard() {
        return bookingAadharCard;
    }

    public void setBookingAadharCard(int bookingAadharCard) {
        this.bookingAadharCard = bookingAadharCard;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getBookingPersonName() {
        return bookingPersonName;
    }

    public void setBookingPersonName(String bookingPersonName) {
        this.bookingPersonName = bookingPersonName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getAmountToBePaid() {
        return amountToBePaid;
    }

    public void setAmountToBePaid(int amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }
}

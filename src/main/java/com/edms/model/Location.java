package com.edms.model;

import java.sql.Timestamp;

public class Location {
    private int locationId;
    private String locationName;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String locationType;
    private int capacity;
    private int currentOccupancy;
    private String facilities;
    private String contactPerson;
    private String contactPhone;
    private double latitude;
    private double longitude;
    private String status;
    private int addedBy;
    private Timestamp createdAt;
    
    // Additional field for display
    private String addedByUsername;
    
    // Constructors
    public Location() {}
    
    public Location(String locationName, String address, String locationType, int capacity) {
        this.locationName = locationName;
        this.address = address;
        this.locationType = locationType;
        this.capacity = capacity;
    }
    
    // Getters and Setters
    public int getLocationId() {
        return locationId;
    }
    
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    
    public String getLocationName() {
        return locationName;
    }
    
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getPincode() {
        return pincode;
    }
    
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    
    public String getLocationType() {
        return locationType;
    }
    
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public int getCurrentOccupancy() {
        return currentOccupancy;
    }
    
    public void setCurrentOccupancy(int currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }
    
    public String getFacilities() {
        return facilities;
    }
    
    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }
    
    public String getContactPerson() {
        return contactPerson;
    }
    
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    
    public String getContactPhone() {
        return contactPhone;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
    public double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getAddedBy() {
        return addedBy;
    }
    
    public void setAddedBy(int addedBy) {
        this.addedBy = addedBy;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getAddedByUsername() {
        return addedByUsername;
    }
    
    public void setAddedByUsername(String addedByUsername) {
        this.addedByUsername = addedByUsername;
    }
    
    // Helper method to calculate available space
    public int getAvailableSpace() {
        return capacity - currentOccupancy;
    }
    
    // Helper method to get occupancy percentage
    public double getOccupancyPercentage() {
        if (capacity == 0) return 0;
        return ((double) currentOccupancy / capacity) * 100;
    }
}
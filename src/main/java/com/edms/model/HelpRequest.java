package com.edms.model;

import java.sql.Timestamp;

public class HelpRequest {
    private int requestId;
    private int userId;
    private String requestType;
    private String description;
    private String location;
    private String urgency;
    private String status;
    private String contactPhone;
    private double latitude;
    private double longitude;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    // Additional fields for display
    private String username;
    private String userFullName;
    
    // Constructors
    public HelpRequest() {}
    
    public HelpRequest(int userId, String requestType, String description, String location, String urgency) {
        this.userId = userId;
        this.requestType = requestType;
        this.description = description;
        this.location = location;
        this.urgency = urgency;
    }
    
    // Getters and Setters
    public int getRequestId() {
        return requestId;
    }
    
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getRequestType() {
        return requestType;
    }
    
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getUrgency() {
        return urgency;
    }
    
    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUserFullName() {
        return userFullName;
    }
    
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
}
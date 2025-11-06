package com.edms.model;

import java.sql.Timestamp;

public class Contribution {
    private int contributionId;
    private int userId;
    private String contributionType;
    private double amount;
    private String description;
    private String location;
    private String quantity;
    private String deliveryStatus;
    private Timestamp createdAt;
    
    // Additional fields for display
    private String username;
    private String userFullName;
    
    // Constructors
    public Contribution() {}
    
    public Contribution(int userId, String contributionType, String description) {
        this.userId = userId;
        this.contributionType = contributionType;
        this.description = description;
    }
    
    // Getters and Setters
    public int getContributionId() {
        return contributionId;
    }
    
    public void setContributionId(int contributionId) {
        this.contributionId = contributionId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getContributionType() {
        return contributionType;
    }
    
    public void setContributionType(String contributionType) {
        this.contributionType = contributionType;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
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
    
    public String getQuantity() {
        return quantity;
    }
    
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    public String getDeliveryStatus() {
        return deliveryStatus;
    }
    
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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
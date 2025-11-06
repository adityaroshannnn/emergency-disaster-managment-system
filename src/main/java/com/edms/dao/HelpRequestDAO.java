package com.edms.dao;

import com.edms.model.HelpRequest;
import com.edms.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelpRequestDAO {
    
    // Create help request
    public boolean createHelpRequest(HelpRequest request) {
        String sql = "INSERT INTO help_requests (user_id, request_type, description, " +
                    "location, urgency, contact_phone) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, request.getUserId());
            pstmt.setString(2, request.getRequestType());
            pstmt.setString(3, request.getDescription());
            pstmt.setString(4, request.getLocation());
            pstmt.setString(5, request.getUrgency());
            pstmt.setString(6, request.getContactPhone());
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get all help requests
    public List<HelpRequest> getAllHelpRequests() {
        List<HelpRequest> requests = new ArrayList<>();
        String sql = "SELECT h.*, u.username, u.full_name FROM help_requests h " +
                    "JOIN users u ON h.user_id = u.user_id " +
                    "ORDER BY h.created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                HelpRequest request = extractHelpRequestFromResultSet(rs);
                requests.add(request);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
    
    // Get pending help requests
    public List<HelpRequest> getPendingHelpRequests() {
        List<HelpRequest> requests = new ArrayList<>();
        String sql = "SELECT h.*, u.username, u.full_name FROM help_requests h " +
                    "JOIN users u ON h.user_id = u.user_id " +
                    "WHERE h.status = 'pending' ORDER BY h.urgency DESC, h.created_at ASC";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                HelpRequest request = extractHelpRequestFromResultSet(rs);
                requests.add(request);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
    
    // Get help requests by user ID
    public List<HelpRequest> getHelpRequestsByUserId(int userId) {
        List<HelpRequest> requests = new ArrayList<>();
        String sql = "SELECT h.*, u.username, u.full_name FROM help_requests h " +
                    "JOIN users u ON h.user_id = u.user_id " +
                    "WHERE h.user_id = ? ORDER BY h.created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                HelpRequest request = extractHelpRequestFromResultSet(rs);
                requests.add(request);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
    
    // Update help request status
    public boolean updateHelpRequestStatus(int requestId, String status) {
        String sql = "UPDATE help_requests SET status = ? WHERE request_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, requestId);
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get help request by ID
    public HelpRequest getHelpRequestById(int requestId) {
        String sql = "SELECT h.*, u.username, u.full_name FROM help_requests h " +
                    "JOIN users u ON h.user_id = u.user_id WHERE h.request_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, requestId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractHelpRequestFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Helper method to extract HelpRequest from ResultSet
    private HelpRequest extractHelpRequestFromResultSet(ResultSet rs) throws SQLException {
        HelpRequest request = new HelpRequest();
        request.setRequestId(rs.getInt("request_id"));
        request.setUserId(rs.getInt("user_id"));
        request.setRequestType(rs.getString("request_type"));
        request.setDescription(rs.getString("description"));
        request.setLocation(rs.getString("location"));
        request.setUrgency(rs.getString("urgency"));
        request.setStatus(rs.getString("status"));
        request.setContactPhone(rs.getString("contact_phone"));
        request.setCreatedAt(rs.getTimestamp("created_at"));
        request.setUpdatedAt(rs.getTimestamp("updated_at"));
        request.setUsername(rs.getString("username"));
        request.setUserFullName(rs.getString("full_name"));
        return request;
    }
}
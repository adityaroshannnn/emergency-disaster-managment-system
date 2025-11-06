package com.edms.dao;

import com.edms.model.Location;
import com.edms.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {
    
    // Add new location
    public boolean addLocation(Location location) {
        String sql = "INSERT INTO locations (location_name, address, city, state, pincode, " +
                    "location_type, capacity, facilities, contact_person, contact_phone, added_by) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, location.getLocationName());
            pstmt.setString(2, location.getAddress());
            pstmt.setString(3, location.getCity());
            pstmt.setString(4, location.getState());
            pstmt.setString(5, location.getPincode());
            pstmt.setString(6, location.getLocationType());
            pstmt.setInt(7, location.getCapacity());
            pstmt.setString(8, location.getFacilities());
            pstmt.setString(9, location.getContactPerson());
            pstmt.setString(10, location.getContactPhone());
            pstmt.setInt(11, location.getAddedBy());
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get all locations
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT l.*, u.username FROM locations l " +
                    "LEFT JOIN users u ON l.added_by = u.user_id " +
                    "ORDER BY l.created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Location location = extractLocationFromResultSet(rs);
                locations.add(location);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }
    
    // Get locations by type
    public List<Location> getLocationsByType(String locationType) {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT l.*, u.username FROM locations l " +
                    "LEFT JOIN users u ON l.added_by = u.user_id " +
                    "WHERE l.location_type = ? ORDER BY l.created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, locationType);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Location location = extractLocationFromResultSet(rs);
                locations.add(location);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }
    
    // Get location by ID
    public Location getLocationById(int locationId) {
        String sql = "SELECT l.*, u.username FROM locations l " +
                    "LEFT JOIN users u ON l.added_by = u.user_id " +
                    "WHERE l.location_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, locationId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractLocationFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Update location occupancy
    public boolean updateOccupancy(int locationId, int newOccupancy) {
        String sql = "UPDATE locations SET current_occupancy = ? WHERE location_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, newOccupancy);
            pstmt.setInt(2, locationId);
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Update location status
    public boolean updateLocationStatus(int locationId, String status) {
        String sql = "UPDATE locations SET status = ? WHERE location_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, locationId);
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get available shelters (not full)
    public List<Location> getAvailableLocations() {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT l.*, u.username FROM locations l " +
                    "LEFT JOIN users u ON l.added_by = u.user_id " +
                    "WHERE l.status = 'active' AND l.current_occupancy < l.capacity " +
                    "ORDER BY l.city, l.location_name";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Location location = extractLocationFromResultSet(rs);
                locations.add(location);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }
    
    // Helper method to extract Location from ResultSet
    private Location extractLocationFromResultSet(ResultSet rs) throws SQLException {
        Location location = new Location();
        location.setLocationId(rs.getInt("location_id"));
        location.setLocationName(rs.getString("location_name"));
        location.setAddress(rs.getString("address"));
        location.setCity(rs.getString("city"));
        location.setState(rs.getString("state"));
        location.setPincode(rs.getString("pincode"));
        location.setLocationType(rs.getString("location_type"));
        location.setCapacity(rs.getInt("capacity"));
        location.setCurrentOccupancy(rs.getInt("current_occupancy"));
        location.setFacilities(rs.getString("facilities"));
        location.setContactPerson(rs.getString("contact_person"));
        location.setContactPhone(rs.getString("contact_phone"));
        location.setStatus(rs.getString("status"));
        location.setAddedBy(rs.getInt("added_by"));
        location.setCreatedAt(rs.getTimestamp("created_at"));
        location.setAddedByUsername(rs.getString("username"));
        return location;
    }
}
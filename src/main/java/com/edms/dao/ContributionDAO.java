package com.edms.dao;

import com.edms.model.Contribution;
import com.edms.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContributionDAO {
    
    // Add contribution
    public boolean addContribution(Contribution contribution) {
        String sql = "INSERT INTO contributions (user_id, contribution_type, amount, " +
                    "description, location, quantity) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, contribution.getUserId());
            pstmt.setString(2, contribution.getContributionType());
            pstmt.setDouble(3, contribution.getAmount());
            pstmt.setString(4, contribution.getDescription());
            pstmt.setString(5, contribution.getLocation());
            pstmt.setString(6, contribution.getQuantity());
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get all contributions
    public List<Contribution> getAllContributions() {
        List<Contribution> contributions = new ArrayList<>();
        String sql = "SELECT c.*, u.username, u.full_name FROM contributions c " +
                    "JOIN users u ON c.user_id = u.user_id " +
                    "ORDER BY c.created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Contribution contribution = extractContributionFromResultSet(rs);
                contributions.add(contribution);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contributions;
    }
    
    // Get contributions by user ID
    public List<Contribution> getContributionsByUserId(int userId) {
        List<Contribution> contributions = new ArrayList<>();
        String sql = "SELECT c.*, u.username, u.full_name FROM contributions c " +
                    "JOIN users u ON c.user_id = u.user_id " +
                    "WHERE c.user_id = ? ORDER BY c.created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Contribution contribution = extractContributionFromResultSet(rs);
                contributions.add(contribution);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contributions;
    }
    
    // Update delivery status
    public boolean updateDeliveryStatus(int contributionId, String status) {
        String sql = "UPDATE contributions SET delivery_status = ? WHERE contribution_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, contributionId);
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get total money contributions
    public double getTotalMoneyContributions() {
        String sql = "SELECT SUM(amount) as total FROM contributions WHERE contribution_type = 'money'";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getDouble("total");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    
    // Helper method to extract Contribution from ResultSet
    private Contribution extractContributionFromResultSet(ResultSet rs) throws SQLException {
        Contribution contribution = new Contribution();
        contribution.setContributionId(rs.getInt("contribution_id"));
        contribution.setUserId(rs.getInt("user_id"));
        contribution.setContributionType(rs.getString("contribution_type"));
        contribution.setAmount(rs.getDouble("amount"));
        contribution.setDescription(rs.getString("description"));
        contribution.setLocation(rs.getString("location"));
        contribution.setQuantity(rs.getString("quantity"));
        contribution.setDeliveryStatus(rs.getString("delivery_status"));
        contribution.setCreatedAt(rs.getTimestamp("created_at"));
        contribution.setUsername(rs.getString("username"));
        contribution.setUserFullName(rs.getString("full_name"));
        return contribution;
    }
}
package com.edms.dao;

import com.edms.model.Post;
import com.edms.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    
    // Create new post
    public boolean createPost(Post post) {
        String sql = "INSERT INTO posts (user_id, title, content, post_type, location, priority) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, post.getUserId());
            pstmt.setString(2, post.getTitle());
            pstmt.setString(3, post.getContent());
            pstmt.setString(4, post.getPostType());
            pstmt.setString(5, post.getLocation());
            pstmt.setString(6, post.getPriority());
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get all posts with user information
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT p.*, u.username, u.full_name FROM posts p " +
                    "JOIN users u ON p.user_id = u.user_id " +
                    "ORDER BY p.created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Post post = extractPostFromResultSet(rs);
                posts.add(post);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    
    // Get posts by user ID
    public List<Post> getPostsByUserId(int userId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT p.*, u.username, u.full_name FROM posts p " +
                    "JOIN users u ON p.user_id = u.user_id " +
                    "WHERE p.user_id = ? ORDER BY p.created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Post post = extractPostFromResultSet(rs);
                posts.add(post);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    
    // Get post by ID
    public Post getPostById(int postId) {
        String sql = "SELECT p.*, u.username, u.full_name FROM posts p " +
                    "JOIN users u ON p.user_id = u.user_id WHERE p.post_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, postId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractPostFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Delete post
    public boolean deletePost(int postId, int userId) {
        String sql = "DELETE FROM posts WHERE post_id = ? AND user_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, postId);
            pstmt.setInt(2, userId);
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Update post status
    public boolean updatePostStatus(int postId, String status) {
        String sql = "UPDATE posts SET status = ? WHERE post_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, postId);
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get posts by type
    public List<Post> getPostsByType(String postType) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT p.*, u.username, u.full_name FROM posts p " +
                    "JOIN users u ON p.user_id = u.user_id " +
                    "WHERE p.post_type = ? ORDER BY p.created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, postType);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Post post = extractPostFromResultSet(rs);
                posts.add(post);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    
    // Helper method to extract Post from ResultSet
    private Post extractPostFromResultSet(ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setPostId(rs.getInt("post_id"));
        post.setUserId(rs.getInt("user_id"));
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        post.setPostType(rs.getString("post_type"));
        post.setLocation(rs.getString("location"));
        post.setImageUrl(rs.getString("image_url"));
        post.setPriority(rs.getString("priority"));
        post.setStatus(rs.getString("status"));
        post.setCreatedAt(rs.getTimestamp("created_at"));
        post.setUsername(rs.getString("username"));
        post.setUserFullName(rs.getString("full_name"));
        return post;
    }
}
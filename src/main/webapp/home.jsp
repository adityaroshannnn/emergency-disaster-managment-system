<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.edms.model.*, com.edms.dao.*" %>
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    User user = (User) session.getAttribute("user");
    String username = (String) session.getAttribute("username");
    
    // Fetch recent data
    PostDAO postDAO = new PostDAO();
    LocationDAO locationDAO = new LocationDAO();
    HelpRequestDAO helpRequestDAO = new HelpRequestDAO();
    
    List<Post> recentPosts = postDAO.getAllPosts();
    List<Location> locations = locationDAO.getAvailableLocations();
    List<HelpRequest> helpRequests = helpRequestDAO.getPendingHelpRequests();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - EDMS</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    
    <div class="container">
        <div class="dashboard">
            <h1>Welcome, <%= user != null ? user.getFullName() : username %>!</h1>
            
            <!-- Quick Actions -->
            <section class="quick-actions">
                <h2>Quick Actions</h2>
                <div class="action-grid">
                    <a href="add-post.jsp" class="action-card">
                        <h3>ðŸ“¢ Post Update</h3>
                        <p>Share disaster updates</p>
                    </a>
                    <a href="ask-help.jsp" class="action-card">
                        <h3>ðŸ†˜ Ask for Help</h3>
                        <p>Request emergency assistance</p>
                    </a>
                    <a href="shelters.jsp" class="action-card">
                        <h3>ðŸ¥ Find Shelter</h3>
                        <p>Locate safe zones</p>
                    </a>
                    <a href="add-contribution.jsp" class="action-card">
                        <h3>ðŸ¤ Contribute</h3>
                        <p>Donate resources</p>
                    </a>
                </div>
            </section>
            
            <!-- Statistics -->
            <section class="stats">
                <h2>System Overview</h2>
                <div class="stats-grid">
                    <div class="stat-card">
                        <h3><%= locations != null ? locations.size() : 0 %></h3>
                        <p>Active Shelters</p>
                    </div>
                    <div class="stat-card">
                        <h3><%= helpRequests != null ? helpRequests.size() : 0 %></h3>
                        <p>Pending Requests</p>
                    </div>
                    <div class="stat-card">
                        <h3><%= recentPosts != null ? recentPosts.size() : 0 %></h3>
                        <p>Recent Updates</p>
                    </div>
                </div>
            </section>
            
            <!-- Recent Help Requests -->
            <section class="recent-requests">
                <h2>Recent Help Requests</h2>
                <div class="requests-list">
                    <% if (helpRequests != null && !helpRequests.isEmpty()) { 
                        int count = 0;
                        for (HelpRequest hr : helpRequests) { 
                            if (count >= 5) break;
                            count++;
                    %>
                        <div class="request-item urgency-<%= hr.getUrgency() %>">
                            <h4><%= hr.getRequestType().toUpperCase() %> - <%= hr.getUrgency() %></h4>
                            <p><%= hr.getDescription() %></p>
                            <p class="location">ðŸ“ <%= hr.getLocation() %></p>
                            <p class="requester">By: <%= hr.getUserFullName() %></p>
                        </div>
                    <% 
                        } 
                    } else { 
                    %>
                        <p>No pending help requests.</p>
                    <% } %>
                </div>
            </section>
        </div>
    </div>
    
    <script src="js/script.js"></script>
</body>
</html>
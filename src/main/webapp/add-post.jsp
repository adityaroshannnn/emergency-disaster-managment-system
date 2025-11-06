<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Post - EDMS</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    
    <div class="container">
        <div class="form-container">
            <h2>ðŸ“¢ Post Disaster Update</h2>
            
            <% if(request.getAttribute("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
            
            <form action="post" method="post" class="edms-form">
                <div class="form-group">
                    <label for="title">Title *</label>
                    <input type="text" id="title" name="title" required 
                           placeholder="Brief title of the update">
                </div>
                
                <div class="form-group">
                    <label for="postType">Post Type *</label>
                    <select id="postType" name="postType" required>
                        <option value="">Select Type</option>
                        <option value="update">General Update</option>
                        <option value="alert">Alert</option>
                        <option value="news">News</option>
                        <option value="announcement">Announcement</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="priority">Priority *</label>
                    <select id="priority" name="priority" required>
                        <option value="low">Low</option>
                        <option value="medium" selected>Medium</option>
                        <option value="high">High</option>
                        <option value="critical">Critical</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="location">Location</label>
                    <input type="text" id="location" name="location" 
                           placeholder="Location related to this update">
                </div>
                
                <div class="form-group">
                    <label for="content">Content *</label>
                    <textarea id="content" name="content" rows="6" required 
                              placeholder="Detailed information about the situation..."></textarea>
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn-primary">Post Update</button>
                    <a href="home.jsp" class="btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
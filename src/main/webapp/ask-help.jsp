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
    <title>Ask for Help - EDMS</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    
    <div class="container">
        <div class="form-container">
            <h2>ðŸ†˜ Request Emergency Help</h2>
            
            <% if(request.getAttribute("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
            
            <form action="help" method="post" class="edms-form">
                <div class="form-row">
                    <div class="form-group">
                        <label for="requestType">Type of Help Needed *</label>
                        <select id="requestType" name="requestType" required>
                            <option value="">Select Type</option>
                            <option value="medical">Medical Emergency</option>
                            <option value="food">Food & Water</option>
                            <option value="shelter">Shelter</option>
                            <option value="rescue">Rescue</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="urgency">Urgency Level *</label>
                        <select id="urgency" name="urgency" required>
                            <option value="low">Low</option>
                            <option value="medium">Medium</option>
                            <option value="high">High</option>
                            <option value="critical">Critical</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="location">Current Location *</label>
                    <input type="text" id="location" name="location" required 
                           placeholder="Your current location">
                </div>
                
                <div class="form-group">
                    <label for="contactPhone">Contact Phone *</label>
                    <input type="tel" id="contactPhone" name="contactPhone" required>
                </div>
                
                <div class="form-group">
                    <label for="description">Describe Your Situation *</label>
                    <textarea id="description" name="description" rows="6" required 
                              placeholder="Please provide details about your situation and what help you need..."></textarea>
                </div>
                
                <div class="alert alert-info">
                    <strong>Note:</strong> Your request will be visible to volunteers and emergency responders. 
                    Please provide accurate information to receive timely assistance.
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn-primary">Submit Request</button>
                    <a href="home.jsp" class="btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
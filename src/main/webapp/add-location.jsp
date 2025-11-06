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
    <title>Add Location - EDMS</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    
    <div class="container">
        <div class="form-container">
            <h2>ðŸ“ Add Safe Location/Shelter</h2>
            
            <% if(request.getAttribute("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
            
            <form action="location" method="post" class="edms-form">
                <div class="form-row">
                    <div class="form-group">
                        <label for="locationName">Location Name *</label>
                        <input type="text" id="locationName" name="locationName" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="locationType">Type *</label>
                        <select id="locationType" name="locationType" required>
                            <option value="">Select Type</option>
                            <option value="shelter">Shelter</option>
                            <option value="hospital">Hospital</option>
                            <option value="relief_camp">Relief Camp</option>
                            <option value="safe_zone">Safe Zone</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="address">Address *</label>
                    <textarea id="address" name="address" rows="3" required></textarea>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="city">City *</label>
                        <input type="text" id="city" name="city" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="state">State *</label>
                        <input type="text" id="state" name="state" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="pincode">Pincode</label>
                        <input type="text" id="pincode" name="pincode">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="capacity">Capacity *</label>
                        <input type="number" id="capacity" name="capacity" required min="1">
                    </div>
                    
                    <div class="form-group">
                        <label for="contactPerson">Contact Person</label>
                        <input type="text" id="contactPerson" name="contactPerson">
                    </div>
                    
                    <div class="form-group">
                        <label for="contactPhone">Contact Phone *</label>
                        <input type="tel" id="contactPhone" name="contactPhone" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="facilities">Facilities Available</label>
                    <textarea id="facilities" name="facilities" rows="3" 
                              placeholder="e.g., Food, Water, Medical Aid, Bedding"></textarea>
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn-primary">Add Location</button>
                    <a href="shelters.jsp" class="btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
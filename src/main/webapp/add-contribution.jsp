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
    <title>Add Contribution - EDMS</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    
    <div class="container">
        <div class="form-container">
            <h2>ðŸ¤ Make a Contribution</h2>
            
            <% if(request.getAttribute("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
            
            <form action="contribution" method="post" class="edms-form">
                <div class="form-group">
                    <label for="contributionType">Contribution Type *</label>
                    <select id="contributionType" name="contributionType" required 
                            onchange="toggleAmountField(this.value)">
                        <option value="">Select Type</option>
                        <option value="money">Money</option>
                        <option value="food">Food</option>
                        <option value="clothes">Clothes</option>
                        <option value="medical">Medical Supplies</option>
                        <option value="volunteer">Volunteer Service</option>
                        <option value="other">Other</option>
                    </select>
                </div>
                
                <div class="form-row">
                    <div class="form-group" id="amountField" style="display:none;">
                        <label for="amount">Amount (â‚¹)</label>
                        <input type="number" id="amount" name="amount" min="0" step="0.01">
                    </div>
                    
                    <div class="form-group">
                        <label for="quantity">Quantity/Duration</label>
                        <input type="text" id="quantity" name="quantity" 
                               placeholder="e.g., 50 kg, 100 items, 5 hours">
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="location">Delivery/Service Location</label>
                    <input type="text" id="location" name="location" 
                           placeholder="Where will you deliver/provide this?">
                </div>
                
                <div class="form-group">
                    <label for="description">Description *</label>
                    <textarea id="description" name="description" rows="5" required 
                              placeholder="Describe your contribution in detail..."></textarea>
                </div>
                
                <div class="alert alert-success">
                    <strong>Thank you!</strong> Your contribution will help people in need during emergencies.
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn-primary">Submit Contribution</button>
                    <a href="home.jsp" class="btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
    
    <script>
        function toggleAmountField(type) {
            const amountField = document.getElementById('amountField');
            if (type === 'money') {
                amountField.style.display = 'block';
                document.getElementById('amount').required = true;
            } else {
                amountField.style.display = 'none';
                document.getElementById('amount').required = false;
            }
        }
    </script>
</body>
</html>
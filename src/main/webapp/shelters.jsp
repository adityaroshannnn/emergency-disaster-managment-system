<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.edms.model.*"%>
<%@ page import="com.edms.dao.*"%>
<%
if (session.getAttribute("userId") == null) {
response.sendRedirect("login.jsp");
return;
}
LocationDAO locationDAO = new LocationDAO();
List<Location> locations = locationDAO.getAvailableLocations();
%>
<%-- <% if (locations != null && !locations.isEmpty()) {
for (Location loc : locations) { %> --%>
<div class="container">
	<div class="shelters-page">
		<div class="page-header">
			<h1>🏥 Shelters & Safe Zones</h1>
			<a href="add-location.jsp" class="btn-primary">+ Add Location</a>
		</div>

		<div class="locations-grid">
			<% if (locations != null && !locations.isEmpty()) { %>
			<% for (Location loc : locations) {
                       double occupancyPercent = (loc.getCapacity() == 0) ? 0.0
                           : (loc.getCurrentOccupancy() * 100.0 / loc.getCapacity());
                       String statusClass = occupancyPercent >= 90 ? "almost-full"
                           : (occupancyPercent >= 70 ? "filling" : "available");
                %>
			<div class="location-card <%= statusClass %>">
				<div class="location-type-badge">
					<%= (loc.getLocationType() != null ? loc.getLocationType().replace("_"," ").toUpperCase() : "LOCATION") %>
				</div>
				<h3><%= loc.getLocationName() %></h3>
				<p class="address">
					📍
					<%= loc.getAddress() %><%= (loc.getCity() != null ? ", " + loc.getCity() : "") %></p>

				<div class="capacity-info">
					<div class="capacity-bar">
						<div class="capacity-fill" style="width: <%= occupancyPercent %>%"></div>
					</div>
					<p><%= loc.getCurrentOccupancy() %>
						/
						<%= loc.getCapacity() %>
						occupied
					</p>
				</div>

				<%
                            String facilities = loc.getFacilities();
                            if (facilities != null && !facilities.isEmpty()) {
                        %>
				<p class="facilities">
					<strong>Facilities:</strong>
					<%= facilities %></p>
				<%
                            }
                        %>

				<p class="contact">
					📞
					<%= (loc.getContactPhone() != null ? loc.getContactPhone() : "-") %></p>

				<div class="card-actions">
					<a href="location?action=view&id=<%= loc.getLocationId() %>"
						class="btn-secondary">View Details</a>
				</div>
			</div>
			<% } %> 
			<% } else { %>
			<p>No shelters available at the moment.</p>
			<% } %>
		</div>
	</div>
</div>
</div>
</div>
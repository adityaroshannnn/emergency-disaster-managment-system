package com.edms.servlet;

import com.edms.dao.LocationDAO;
import com.edms.model.Location;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/location")
public class LocationServlet extends HttpServlet {
    private LocationDAO locationDAO = new LocationDAO();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listLocations(request, response);
                break;
            case "view":
                viewLocation(request, response);
                break;
            case "shelters":
                listShelters(request, response);
                break;
            default:
                listLocations(request, response);
                break;
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String locationName = request.getParameter("locationName");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String pincode = request.getParameter("pincode");
        String locationType = request.getParameter("locationType");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        String facilities = request.getParameter("facilities");
        String contactPerson = request.getParameter("contactPerson");
        String contactPhone = request.getParameter("contactPhone");
        
        Location location = new Location(locationName, address, locationType, capacity);
        location.setCity(city);
        location.setState(state);
        location.setPincode(pincode);
        location.setFacilities(facilities);
        location.setContactPerson(contactPerson);
        location.setContactPhone(contactPhone);
        location.setAddedBy(userId);
        
        boolean isAdded = locationDAO.addLocation(location);
        
        if (isAdded) {
            request.setAttribute("success", "Location added successfully!");
            response.sendRedirect("location?action=shelters");
        } else {
            request.setAttribute("error", "Failed to add location");
            request.getRequestDispatcher("add-location.jsp").forward(request, response);
        }
    }
    
    private void listLocations(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Location> locations = locationDAO.getAllLocations();
        request.setAttribute("locations", locations);
        request.getRequestDispatcher("shelters.jsp").forward(request, response);
    }
    
    private void listShelters(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Location> shelters = locationDAO.getAvailableLocations();
        request.setAttribute("locations", shelters);
        request.getRequestDispatcher("shelters.jsp").forward(request, response);
    }
    
    private void viewLocation(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int locationId = Integer.parseInt(request.getParameter("id"));
        Location location = locationDAO.getLocationById(locationId);
        request.setAttribute("location", location);
        request.getRequestDispatcher("location-profile.jsp").forward(request, response);
    }
}
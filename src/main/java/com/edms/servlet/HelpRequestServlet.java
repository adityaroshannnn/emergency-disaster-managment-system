package com.edms.servlet;

import com.edms.dao.HelpRequestDAO;
import com.edms.model.HelpRequest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/help")
public class HelpRequestServlet extends HttpServlet {
    private HelpRequestDAO helpRequestDAO = new HelpRequestDAO();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listHelpRequests(request, response);
                break;
            case "my-requests":
                myRequests(request, response);
                break;
            case "update-status":
                updateStatus(request, response);
                break;
            default:
                listHelpRequests(request, response);
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
        
        String requestType = request.getParameter("requestType");
        String description = request.getParameter("description");
        String location = request.getParameter("location");
        String urgency = request.getParameter("urgency");
        String contactPhone = request.getParameter("contactPhone");
        
        HelpRequest helpRequest = new HelpRequest(userId, requestType, description, location, urgency);
        helpRequest.setContactPhone(contactPhone);
        
        boolean isCreated = helpRequestDAO.createHelpRequest(helpRequest);
        
        if (isCreated) {
            request.setAttribute("success", "Help request submitted successfully!");
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("error", "Failed to submit help request");
            request.getRequestDispatcher("ask-help.jsp").forward(request, response);
        }
    }
    
    private void listHelpRequests(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<HelpRequest> requests = helpRequestDAO.getPendingHelpRequests();
        request.setAttribute("helpRequests", requests);
        request.getRequestDispatcher("help-requests.jsp").forward(request, response);
    }
    
    private void myRequests(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId != null) {
            List<HelpRequest> requests = helpRequestDAO.getHelpRequestsByUserId(userId);
            request.setAttribute("helpRequests", requests);
            request.getRequestDispatcher("my-help-requests.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    
    private void updateStatus(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int requestId = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");
        
        helpRequestDAO.updateHelpRequestStatus(requestId, status);
        response.sendRedirect("help?action=list");
    }
}
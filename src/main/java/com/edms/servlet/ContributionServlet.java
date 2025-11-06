package com.edms.servlet;

import com.edms.dao.ContributionDAO;
import com.edms.model.Contribution;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/contribution")
public class ContributionServlet extends HttpServlet {
    private ContributionDAO contributionDAO = new ContributionDAO();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listContributions(request, response);
                break;
            case "my-contributions":
                myContributions(request, response);
                break;
            default:
                listContributions(request, response);
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
        
        String contributionType = request.getParameter("contributionType");
        String description = request.getParameter("description");
        String location = request.getParameter("location");
        String quantity = request.getParameter("quantity");
        
        String amountStr = request.getParameter("amount");
        double amount = 0.0;
        if (amountStr != null && !amountStr.isEmpty()) {
            amount = Double.parseDouble(amountStr);
        }
        
        Contribution contribution = new Contribution(userId, contributionType, description);
        contribution.setAmount(amount);
        contribution.setLocation(location);
        contribution.setQuantity(quantity);
        
        boolean isAdded = contributionDAO.addContribution(contribution);
        
        if (isAdded) {
            request.setAttribute("success", "Thank you for your contribution!");
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("error", "Failed to add contribution");
            request.getRequestDispatcher("add-contribution.jsp").forward(request, response);
        }
    }
    
    private void listContributions(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Contribution> contributions = contributionDAO.getAllContributions();
        request.setAttribute("contributions", contributions);
        request.getRequestDispatcher("contributions.jsp").forward(request, response);
    }
    
    private void myContributions(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId != null) {
            List<Contribution> contributions = contributionDAO.getContributionsByUserId(userId);
            request.setAttribute("contributions", contributions);
            request.getRequestDispatcher("my-contributions.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
package com.edms.servlet;

import com.edms.dao.UserDAO;
import com.edms.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        User user = userDAO.getUserById(userId);
        request.setAttribute("user", user);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        
        User user = new User();
        user.setUserId(userId);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        
        boolean isUpdated = userDAO.updateUser(user);
        
        if (isUpdated) {
            // Update session
            User updatedUser = userDAO.getUserById(userId);
            session.setAttribute("user", updatedUser);
            
            request.setAttribute("success", "Profile updated successfully!");
            request.setAttribute("user", updatedUser);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to update profile");
            User currentUser = userDAO.getUserById(userId);
            request.setAttribute("user", currentUser);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    }
}
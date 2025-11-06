<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String currentPage = request.getRequestURI();
String navUsername = (String) session.getAttribute("username");
if (navUsername == null || navUsername.trim().isEmpty()) navUsername = "Guest";
%>
<nav class="navbar">
    <div class="nav-container">
        <a href="home.jsp" class="logo">
            <h2>🚨 EDMS</h2>
        </a>
        
        <ul class="nav-menu">
            <li><a href="home.jsp" class="<%= currentPage.contains("home") ? "active" : "" %>">Home</a></li>
            <li><a href="social.jsp" class="<%= currentPage.contains("social") ? "active" : "" %>">Social</a></li>
            <li><a href="shelters.jsp" class="<%= currentPage.contains("shelters") ? "active" : "" %>">Shelters</a></li>
            <li><a href="ask-help.jsp" class="<%= currentPage.contains("ask-help") ? "active" : "" %>">Ask Help</a></li>
            <li><a href="add-contribution.jsp" class="<%= currentPage.contains("contribution") ? "active" : "" %>">Contribute</a></li>
        </ul>
        
        <div class="nav-user">
            <span>Welcome, <%= navUsername %></span>
            <a href="profile" class="btn-secondary">Profile</a>
            <a href="logout" class="btn-outline">Logout</a>
        </div>
    </div>
</nav>
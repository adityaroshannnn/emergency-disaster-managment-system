# emergency-disaster-managment-system
Emergency Disaster Management System (EDMS) is a Java web application that helps communities coordinate during disasters by centralizing shelters, help requests, social updates, and resource contributions in one place. Built with JSP and Servlets using a clean DAO–Model–Controller architecture, EDMS focuses on fast access to critical information,
Emergency Disaster Management System (EDMS)A Java web application to help communities coordinate during disasters by centralizing shelters, help requests, community updates, and contributions. Built with a clean DAO–Model–Servlet architecture and responsive UI.
Tech stack
.Language:Java 8+
.Web: JSP, Servlets
.Build/Run: Maven, Tomcat 9
.Database: MySQL, JDBC (DAO pattern)
.UI: HTML, CSS (responsive, dark theme)
Features
.Authentication: Register, login, logout, session management
.Shelters: Grid with capacity bars and status badges; view details
.Help Requests: Submit and track requests (food, medical, rescue)
.Social Feed: Community posts with type/priority labels
.Contributions: Donate resources or volunteer time
.Admin: Registered users list (role-based access)
Project structure
com.edms.model — POJOs (User, Location, Post, HelpRequest, Contribution)
com.edms.dao — JDBC DAOs (CRUD and specialized queries)
com.edms.servlet — Controllers (LoginServlet, RegisterServlet, LocationServlet, PostServlet, HelpRequestsServlet, etc.)
src/main/webapp — JSP views, CSS, images
Getting started
 Prerequisites
 JDK 8+
 Maven 3+
 MySQLTomcat 9
 Database
 .Create schema and update DBConnection with credentials.
 .Run SQL to create tables (users, locations, posts, help_requests, contributions).
 Build and run
   mvn clean package
   Deploy target/*.war to Tomcat 9 or Run on Server from IDE
   Visit http://localhost:8080/edms
   Default access
   Register a user from register.jsp
   For admin pages, set userType = 'ADMIN' in DB for your user
   Screenshots
   <img width="1890" height="901" alt="Screenshot 2025-11-07 023512" src="https://github.com/user-attachments/assets/8d805e53-6ac8-40aa-adff-501f9ce1d9ac" />
<img width="1894" height="900" alt="Screenshot 2025-11-07 023528" src="https://github.com/user-attachments/assets/8550d0a3-50d2-48a5-8595-f769993e886d" />
<img width="1893" height="899" alt="Screenshot 2025-11-07 023538" src="https://github.com/user-attachments/assets/57455a1d-ef50-4712-b3ed-d6e6abac2f65" />

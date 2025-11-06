<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EDMS - Emergency Disaster Management System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="landing-container">
        <header class="header">
            <nav class="navbar">
                <div class="logo">
                    <h1>🚨 EDMS</h1>
                </div>
                <ul class="nav-links">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="login.jsp" class="btn-primary">Login</a></li>
                    <li><a href="register.jsp" class="btn-secondary">Register</a></li>
                </ul>
            </nav>
        </header>

        <section class="hero">
            <div class="hero-content">
                <h1>Emergency Disaster Management System</h1>
                <p>Real-time disaster response and community support platform</p>
                <div class="hero-buttons">
                    <a href="register.jsp" class="btn-large btn-primary">Get Started</a>
                    <a href="#features" class="btn-large btn-outline">Learn More</a>
                </div>
            </div>
        </section>

        <section id="features" class="features">
            <h2>Key Features</h2>
            <div class="feature-grid">
                <div class="feature-card">
                    <h3>🏥 Shelter Management</h3>
                    <p>Find and manage emergency shelters and safe zones</p>
                </div>
                <div class="feature-card">
                    <h3>📢 Real-time Updates</h3>
                    <p>Post and receive critical disaster updates</p>
                </div>
                <div class="feature-card">
                    <h3>🆘 Help Requests</h3>
                    <p>Request immediate assistance during emergencies</p>
                </div>
                <div class="feature-card">
                    <h3>🤝 Contributions</h3>
                    <p>Donate resources and volunteer services</p>
                </div>
            </div>
        </section>

        <footer class="footer">
            <p>&copy; 2025 EDMS - Emergency Disaster Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
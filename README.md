# 🚨 EMERGENCY–DISASTER MANAGEMENT SYSTEM  
**Empowering Communities, Saving Lives in Crisis**

---

<div align="center">

![Last Commit](https://img.shields.io/badge/last%20commit-november-blue)
![Java](https://img.shields.io/badge/java-91.7%25-orange)
![Languages](https://img.shields.io/badge/languages-3-blue)

### Built with the tools and technologies:

![Markdown](https://img.shields.io/badge/-Markdown-000000?logo=markdown)
![JavaScript](https://img.shields.io/badge/-JavaScript-F7DF1E?logo=javascript&logoColor=black)
![XML](https://img.shields.io/badge/-XML-0C54C2)

</div>

---

## 📌 **Project Overview**

The **Emergency–Disaster Management System** is a web-based platform built using **Advanced Java (JSP/Servlets), Apache Tomcat, and MySQL**.  
It helps government agencies and communities to efficiently handle:

- Emergency alerts  
- Disaster reporting  
- Rescue operations  
- Resource allocation  
- Admin & User coordination  

The goal is **fast response, real-time tracking**, and **smooth communication during crisis situations**.

---

## 🛠️ **Tech Stack**

| Technology | Usage |
|-----------|--------|
| **Advanced Java (JSP, Servlets)** | Backend Logic |
| **Apache Tomcat** | Server Deployment |
| **MySQL** | Database |
| **HTML / CSS / JS** | Frontend |
| **JDBC** | Database Connectivity |
| **XML** | Configuration Files |

---

## 📁 **Project Folder Structure**
Emergency-Disaster-Management-System/
│
├── src/
│ ├── com.edms.controller/
│ │ ├── LoginServlet.java
│ │ ├── RegisterServlet.java
│ │ ├── ReportDisasterServlet.java
│ │ ├── AdminOperationsServlet.java
│ │
│ ├── com.edms.dao/
│ │ ├── UserDAO.java
│ │ ├── DisasterDAO.java
│ │
│ ├── com.edms.model/
│ ├── User.java
│ ├── Disaster.java
│
├── WebContent/
│ ├── index.jsp
│ ├── login.jsp
│ ├── register.jsp
│ ├── reportDisaster.jsp
│ ├── adminDashboard.jsp
│ ├── styles/
│ │ ├── style.css
│ ├── scripts/
│ ├── app.js
│
├── lib/ (JAR Files)
│
├── database/
│ ├── edms.sql
│
├── README.md
├── pom.xml (if Maven used)

> 🔽 

---

## 🚀 **Features**

### 👨‍💼 Admin Panel
- Manage disaster alerts  
- View reported emergencies  
- Update rescue status  
- Manage users  
- Resource assignment  

### 👤 User Features
- Register / Login  
- Report disaster with details  
- Track disaster status  
- Real-time notification alerts  

### 🗄️ Database Module
- User table  
- Disaster reports  
- Rescue unit allocation  
- Feedback logs  

---

## 🔧 **Setup Instructions**

### **1️⃣ Install Required Software**
- JDK 8+  
- Apache Tomcat 8.5 / 9  
- MySQL Server  
- Any IDE (Eclipse / IntelliJ / VS Code with extensions)

---

### **2️⃣ Import the Project**
1. Download or clone the repository  
   ```bash
   git clone https://github.com/your-username/Emergency-Disaster-Management-System.git
Import into Eclipse/IntelliJ

Select Dynamic Web Project
3️⃣ Configure Database

Create database:

CREATE DATABASE edms;


Import:

SOURCE database/edms.sql;


Update DB configuration in:

/src/com/edms/dao/DBConnection.java

4️⃣ Deploy on Tomcat

Add project to server

Run → Start Tomcat

Visit:

http://localhost:8080/Emergency-Disaster-Management-System/

<img width="1901" height="909" alt="Screenshot 2025-11-17 164718" src="https://github.com/user-attachments/assets/bc5b3511-aae7-485e-a2f9-229ebf18e9ac" />

<img width="1899" height="899" alt="Screenshot 2025-11-17 164731" src="https://github.com/user-attachments/assets/164def9a-cdf0-4280-82fe-de433341cb65" />
<img width="1900" height="915" alt="Screenshot 2025-11-17 164758" src="https://github.com/user-attachments/assets/5a913188-a6d3-4ce4-8f8b-aee9b0576eca" />
🤝 Contribution

Contributions are welcome!
Feel free to submit Issues or Pull Requests.
⭐ Show Your Support

If this project helped you, please ⭐ star the repository!


---



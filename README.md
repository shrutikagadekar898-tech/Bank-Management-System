# 🏦 Bank Management System

A full-stack banking application built using **Spring Boot, Hibernate (JPA), Spring Security, JWT, React.js, and PostgreSQL**. The system provides secure account management, money transfers, loan processing, beneficiary services, notifications, and PDF statement generation with role-based access control.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- User Registration and Login
- JWT Authentication
- Spring Security Integration
- Role-Based Access Control (User, Bank, Admin)

### 💳 Account Management
- Create Account
- View Account Details
- Check Balance
- Multiple Account Support

### 💰 Transactions
- Deposit Money
- Withdraw Money
- Transfer Money
- Transaction History
- Download Transaction Statement in PDF

### 👥 Beneficiary Management
- Add Beneficiaries
- View Beneficiaries
- Secure Fund Transfers

### 🏦 Loan Management
- Apply for Loans
- Loan Approval and Rejection
- EMI Calculation
- EMI Payment
- Loan Payment History
- Auto Loan Closure

### 🔔 Notifications
- Loan Approval Notifications
- Balance Update Notifications
- EMI Payment Notifications

### 📊 Dashboards
- User Dashboard
- Bank Dashboard
- Admin Dashboard

---

## 🛠️ Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Hibernate (JPA)
- Maven

### Frontend
- React.js
- React Router
- Axios
- CSS

### Database
- PostgreSQL

### Tools
- Postman
- IntelliJ IDEA
- VS Code
- Git & GitHub

---

## 📂 Project Structure

```
Bank-Management-System
│
├── Backend
│   ├── Controller
│   ├── Service
│   ├── Repository
│   ├── Entity
│   ├── DTO
│   ├── Security
│   └── Config
│
├── Frontend
│   ├── Components
│   ├── Pages
│   ├── Services
│   └── CSS
│
└── Database
    └── PostgreSQL
```

---

## ⚙️ Installation

### Clone Repository

```bash
git clone https://github.com/shrutikagadekar898-tech/Bank-Management-System.git
```

### Backend Setup

Configure PostgreSQL database in:

```properties
application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bankdb
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

### Frontend Setup

```bash
npm install
npm run dev
```

Frontend:

```
http://localhost:5173
```

Backend:

```
http://localhost:8080
```

---

## 📌 REST APIs

### User APIs
- Register User
- Login User
- Profile Management

### Account APIs
- Create Account
- View Accounts
- Check Balance

### Transaction APIs
- Deposit Money
- Withdraw Money
- Transfer Money
- Transaction History
- Download PDF Statement

### Beneficiary APIs
- Add Beneficiary
- View Beneficiaries

### Loan APIs
- Apply Loan
- Approve Loan
- Reject Loan
- EMI Payment

### Notification APIs
- View Notifications

---

## 🔒 Security

- JWT Authentication
- Spring Security
- Role-Based Authorization
- Protected REST APIs

---

## 📸 Screenshots

Add screenshots of:

- Login Page
- User Dashboard
- Bank Dashboard
- Transactions
- Loan Management

---

## 👩‍💻 Author

**Shrutika Gadekar**

- GitHub: https://github.com/shrutikagadekar898-tech

---

## ⭐ Key Highlights

- Full-Stack Banking Application
- Spring Boot + React.js Architecture
- Hibernate ORM with PostgreSQL
- JWT-Based Authentication
- Role-Based Access Control
- PDF Statement Generation
- Loan and EMI Management
- Transaction Tracking and Notifications

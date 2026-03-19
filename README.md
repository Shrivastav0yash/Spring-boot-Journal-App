# 📝 Journal App (Spring Boot Learning Project)

This project is a **hands-on learning application** built while studying **Spring Boot, Spring Security, and backend development concepts**.

The goal of this project is to **practice and understand real-world backend development**, including authentication, REST APIs, and application architecture.

---

## 🎯 Learning Objectives

Through this project, I learned:

* ✅ Building REST APIs using Spring Boot
* ✅ Understanding Spring Security
* ✅ Implementing JWT Authentication
* ✅ Integrating Google OAuth2 Login
* ✅ Structuring a scalable backend project
* ✅ Working with databases (MongoDB / MySQL)
* ✅ Writing clean and maintainable code

---

## 🚀 Features Implemented

* 🔐 User Authentication (Spring Security)
* 🔑 JWT-based Login System
* 🌐 Google OAuth2 Login
* 📝 CRUD Operations for Journal Entries
* 👤 User Management
* 📦 RESTful API Design

---

## 🛠️ Tech Stack

* **Backend:** Spring Boot, Spring Security
* **Database:** MySQL 
* **Authentication:** JWT + Google OAuth2
* **Build Tool:** Maven
* **Other Tools:** Lombok

---

## 📂 Project Structure

```id="s4kz9u"
src/main/java/com/springboot/journal_app
│
├── controller       # Handles API requests
├── service          # Business logic
├── repository       # Database operations
├── entity           # Data models
├── config           # Security & app configuration
└── util             # Helper classes
```

---

## 🔑 Authentication Flow (Learning Implementation)

### 🔹 JWT Login

1. User registers/logs in
2. JWT token is generated
3. Token is used to access protected APIs

### 🔹 Google Login

1. User accesses `/oauth2/authorization/google`
2. Redirected to Google login
3. On success → user is created/logged in

---

## ⚙️ Configuration

Update `application.properties`:

```id="ql4hr5"
spring.security.oauth2.client.registration.google.client-id=YOUR_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_CLIENT_SECRET
spring.security.oauth2.client.registration.google.scope=email,profile
```

---

## ▶️ How to Run

```id="h9du8s"
git clone https://github.com/your-username/journal-app.git
cd journal-app
mvn spring-boot:run
```

---

## 📌 Note

⚠️ This project is created **for learning purposes** and may not follow all production-level best practices.

However, it demonstrates a strong understanding of:

* Backend development
* Authentication systems
* Spring ecosystem

---

## 🔥 Future Improvements

* Add frontend (React / Angular)
* Improve security practices
* Add refresh tokens
* Deploy using Docker & AWS

---

## 👨‍💻 Author

YASH SHRIVASTAV

---

## ⭐ Acknowledgement

This project is part of my journey to becoming a **better backend developer** 🚀

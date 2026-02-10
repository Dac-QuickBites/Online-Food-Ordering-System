
# 🍔 Online Food Ordering System

## 📌 Project Overview

The **Online Food Ordering System** is a full-stack web application designed to provide a seamless platform for customers to browse restaurants, order food online, and make secure payments. The system supports multiple user roles including **Customers**, **Restaurant Owners**, and **Admins**, each with role-based access and features.

The application is built using **React.js** for the frontend and **Spring Boot** for the backend, following a **RESTful architecture** with **JWT-based authentication** to ensure secure and scalable communication.

---

## 🎯 Objectives

* Provide a user-friendly interface for online food ordering
* Enable restaurant owners to manage menus and orders
* Allow admins to monitor and control the platform
* Implement secure authentication and authorization
* Ensure scalable and maintainable system architecture

---

## 🛠️ Tech Stack

### Frontend

* React.js
* JavaScript (ES6+)
* HTML5, CSS3
* Axios
* React Router

### Backend

* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Hibernate / JPA
* REST APIs

### Database

* MySQL

### Tools & Technologies

* Maven
* Git & GitHub
* Postman
* IntelliJ / VS Code

---

## 🔐 Authentication & Security

* JWT-based stateless authentication
* Role-based authorization (USER, ADMIN, RESTAURANT_OWNER)
* Secure password storage using BCrypt
* Protected APIs using Spring Security filters
* Centralized exception handling using `@ControllerAdvice`

---

## 👥 User Roles & Features

### 👤 Customer

* Register & login
* Browse restaurants and menus
* Place food orders
* View order history

### 🏪 Restaurant Owner

* Manage restaurant profile
* Add, update, and delete menu items
* View and manage orders

### 🛡️ Admin

* Manage users and restaurants
* Monitor platform activity
* Control application access

---

## 🔁 Application Flow

1. User logs in using credentials
2. Backend validates credentials and generates JWT
3. JWT is stored on the client side
4. JWT is sent with every secured API request
5. Backend validates JWT using a security filter
6. Authorized request reaches the controller
7. Response is sent back to the client

---

## 🧠 System Architecture

* Follows **Layered Architecture**

  * Controller Layer
  * Service Layer
  * Repository Layer
* Uses **Spring Security Filter Chain**
* Central entry point using **DispatcherServlet**
* Stateless backend with JWT authentication

---

## 🚀 How to Run the Project

### Backend

1. Clone the repository
2. Open backend project in IntelliJ / Eclipse
3. Configure MySQL database
4. Update `application.properties`
5. Run the Spring Boot application

### Frontend

1. Open frontend folder
2. Install dependencies:

   ```bash
   npm install
   ```
3. Start the application:

   ```bash
   npm start
   ```

---

## 📂 Project Structure (Backend)

```
com.foodiesapi
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── security
 ├── exception
 └── FoodiesApiApplication.java
```

---

## 📌 Key Highlights

* Clean separation of concerns
* Secure and scalable authentication
* Real-world project structure
* Industry-standard best practices
* Interview-ready implementation

---

## 📈 Future Enhancements

* Online payment gateway integration
* Order tracking system
* Notification service
* Dockerization
* Cloud deployment



## 📝 Conclusion

This project demonstrates the practical implementation of a real-world food ordering platform using modern web technologies. It showcases full-stack development skills, secure backend design, and scalable application architecture.


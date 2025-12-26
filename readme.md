# Bookstore API

## Hani Ismail 

Simple REST API for a bookstore application built with **Spring Boot**.

## Requirements
- Java 21
- Maven
- MySQL

## Run the project

```bash
mvn spring-boot:run
```

The application starts on:  
http://localhost:8080

## Configuration

Sensitive configuration (database, JWT secrets) is provided using **environment variables**.

Example:
```bash
DB_URL=jdbc:mysql://localhost:3306/bookstore
DB_USERNAME=root
DB_PASSWORD=your_password
JWT_SECRET=your_jwt_secret_key_32_chars_min
JWT_ACCESS_EXPIRATION=900000
JWT_REFRESH_EXPIRATION=2592000000
```

## Authentication

- Register a user
- Login to obtain a **JWT token**
- Use the token

## Swagger (API documentation)

Swagger UI is available at:
http://localhost:8080/swagger-ui/index.html

You can:
- View all endpoints
- Inspect DTOs
- Authenticate using JWT via the **Authorize** button

## Features
- User authentication (JWT)
- Role management (USER / ADMIN)
- Book management
- Reviews
- Secured endpoints

---

I didn’t have time to finish this project, so this is an incomplete but functional version.


## Frontend Application

This project includes a separate frontend application built with **React + Vite**, which provides a user interface to interact with the Bookstore API (authentication, books listing, admin actions, etc.).

The frontend source code is available in a dedicated Git repository:

🔗 **Frontend repository:**  
https://github.com/Ismail-Hani/bookstore-frontend.git

### Running the frontend locally

The frontend application runs on the following address:

http://localhost:5173

It communicates with the backend API running on:

http://localhost:8080


Make sure the backend server is started before launching the frontend to ensure proper API communication.

The frontend handles:
- User registration and authentication (JWT-based)
- Book listing
- Admin-only book creation and deletion
- Basic UI interactions with the REST API

## Improvements Between Assignment 2 and Final Submission

Between **Assignment 2** and the **final project submission**, several enhancements and additional features were implemented to improve both functionality and usability of the application.

On the backend side, missing REST endpoints were completed in order to fully cover the required use cases, including improved CRUD operations, pagination support, and stricter validation using DTOs and Bean Validation. Security was reinforced with role-based access control (USER / ADMIN) and JWT authentication, ensuring that sensitive operations such as book creation and deletion are restricted to administrators.

On the frontend side, a complete **React-based web interface** was added. This frontend allows users to register, authenticate, browse the book catalog, and (for admin users) manage books directly from the UI. The frontend communicates with the backend through the REST API and properly handles authentication tokens.

Additionally, API documentation was improved using Swagger/OpenAPI, and a Postman collection was prepared to demonstrate and test all available endpoints.

These additions significantly enhance the overall completeness of the project compared to Assignment 2 and provide a fully usable, end-to-end web application.

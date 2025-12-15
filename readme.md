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

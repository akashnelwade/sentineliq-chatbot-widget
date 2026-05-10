# SentinelIQ Chatbot Widget

## 1. Project Overview

SentinelIQ Chatbot Widget is a production-ready full-stack web application designed to provide a secure and scalable chatbot integration platform. The project enables user registration, authentication, and management of chatbot-related data through RESTful APIs.

The application is built using a modern enterprise technology stack:

- Frontend: React.js
- Backend: Spring Boot
- Database: MySQL
- Caching: Redis
- Authentication: JWT with Spring Security
- Email Notifications: JavaMailSender
- Containerization: Docker and Docker Compose
- API Testing: Postman

### Key Features

- User registration and login
- JWT-based authentication and authorization
- Role-based access control (ADMIN and USER)
- CRUD operations for users
- Pagination and search APIs
- Redis caching with 10-minute TTL
- Email notifications and scheduled jobs
- Global exception handling
- Unit and integration testing
- Dockerized deployment

---

## 2. Architecture Diagram

```text
┌─────────────────────────────┐
│       React Frontend        │
│    (Chatbot Widget UI)      │
└──────────────┬──────────────┘
               │
               │ HTTP/REST API
               ▼
┌─────────────────────────────┐
│     Spring Boot Backend     │
│ Controllers → Services →    │
│ Repositories → Security     │
└───────┬─────────┬───────────┘
        │         │
        │         │
        ▼         ▼
┌─────────────┐ ┌─────────────┐
│    MySQL    │ │    Redis    │
│  Database   │ │   Caching   │
└─────────────┘ └─────────────┘
        │
        ▼
┌─────────────────────────────┐
│ SMTP Mail Server (Gmail)    │
│ Email Notifications         │
└─────────────────────────────┘

## 3. Prerequisites

Before running the project, ensure the following software is installed:

- JDK 17 or later
- Maven 3.9+
- MySQL 8.0+
- Redis 7+
- Docker Desktop (optional)
- Git
- Postman
- Node.js 18+ (for the React frontend)

---

## 4. Setup Steps

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/sentineliq-chatbot-widget.git
cd sentineliq-chatbot-widget

### 2. Create the Database
```sql
CREATE DATABASE chatbotdb;

### 3. Configure Environment Variables
Create a .env file in the project root and define all required variables as shown in the Environment Variables table.

### 4. Start Redis
Make sure Redis is running on port 6379.

### 5. Run the Backend
cd backendmvn clean spring-boot:run

### 6. Run the Frontend
cd frontendnpm installnpm start

### 7. Access the Application
Frontend: http://localhost:3000
Backend API: http://localhost:8080

### 8. Test APIs Using Postman
POST /api/auth/register
POST /api/auth/login
GET /api/users/all
POST /api/users/create



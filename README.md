# Distributed Notification Service

A production-style **event-driven notification microservice** built using **Spring Boot, Kafka, Redis, and PostgreSQL**.
The service handles **multi-channel notifications (Email, SMS, Push, In-App)** with reliable delivery, retry mechanisms, and scalable worker processing.

---

# Architecture Overview

The service is designed using **Clean Architecture and SOLID principles**.

Client services publish events or call the Notification API, which processes and dispatches notifications asynchronously through Kafka and channel workers.

```
Client Services
        |
        |
Notification API (Spring Boot)
        |
        |
Kafka Event Bus
        |
Notification Processor
        |
------------------------------------
| Email Worker | SMS Worker | Push |
------------------------------------
        |
Internal Delivery Engines
        |
PostgreSQL + Redis
```

---

# Features

* Event-driven notification processing
* Multi-channel notification delivery
* Categorized notifications
* Unread notification tracking
* Redis caching for latest notifications
* Retry & failure handling
* Dead-letter queue support
* JWT based authentication
* Clean architecture with SOLID principles
* Scalable worker-based processing

---

# Tech Stack

| Technology      | Purpose               |
| --------------- | --------------------- |
| Java 21         | Programming Language  |
| Spring Boot     | Backend Framework     |
| Kafka           | Event streaming       |
| Redis           | Caching & idempotency |
| PostgreSQL      | Persistent storage    |
| Maven           | Build tool            |
| Lombok          | Boilerplate reduction |
| Spring Security | Authentication        |
| Docker          | Local infrastructure  |

---

# Project Structure

```
notification-service
│
├── api
│     NotificationController
│
├── application
│     NotificationService
│
├── domain
│
│   ├── entity
│   │     Notification
│   │
│   └── enums
│         NotificationCategory
│         NotificationChannel
│         NotificationStatus
│
├── infrastructure
│
│   ├── repository
│   │     NotificationRepository
│   │
│   ├── redis
│   │
│   └── kafka
│
├── worker
│     EmailWorker
│     SmsWorker
│     PushWorker
│
├── security
│     JwtFilter
│     JwtUtil
│
├── common
│
│   ├── response
│   │     ApiResponse
│   │
│   ├── exception
│   │
│   └── util
│
└── config
      KafkaConfig
      RedisConfig
      SecurityConfig
```

---

# Database Schema

### notifications

| Column      | Description                 |
| ----------- | --------------------------- |
| id          | Unique notification ID      |
| user_id     | User receiving notification |
| title       | Notification title          |
| message     | Notification message        |
| category    | Notification category       |
| channel     | Delivery channel            |
| status      | Delivery status             |
| is_read     | Read/unread flag            |
| payload     | Additional metadata         |
| retry_count | Retry attempts              |
| created_at  | Created timestamp           |
| updated_at  | Updated timestamp           |

---

# Notification Categories

```
SYSTEM
PAYMENT
ORDER
SOCIAL
MARKETING
REMINDER
```

---

# Notification Channels

```
EMAIL
SMS
PUSH
IN_APP
```

---

# API Response Format

All APIs return a consistent response format.

Example:

```json
{
  "success": true,
  "message": "Notification created",
  "data": {
    "id": "notification_id"
  }
}
```

---

# Security

The service uses **JWT authentication** to protect APIs.

Headers required:

```
Authorization: Bearer <JWT_TOKEN>
x-user-id: user_123
```

---

# Redis Usage

Redis is used for fast operations.

Examples:

```
notif:latest:{userId}
notif:unread:{userId}
notif:category:{userId}:{category}
```

---

# Development Setup

## Prerequisites

* Java 21
* Maven
* Docker
* PostgreSQL
* Redis
* Kafka

---

## Run Application

```
mvn spring-boot:run
```

Server starts on:

```
http://localhost:8080
```

---

# Roadmap

Project will be developed incrementally.

### Phase 1

* Domain models
* Repository layer
* Notification API

### Phase 2

* JWT authentication
* Redis caching
* Unread notifications

### Phase 3

* Kafka event streaming
* Notification processor

### Phase 4

* Worker services
* Email / SMS / Push delivery

### Phase 5

* Retry mechanism
* Dead letter queue

### Phase 6

* Monitoring and metrics
* Integration tests

---

# Design Principles

This project follows:

* SOLID principles
* Clean architecture
* Event-driven design
* Scalable worker architecture

---

# Author

Ritesh Roushan

Backend Engineer
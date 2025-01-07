# Microservices Architecture with Eureka, Spring Cloud Gateway, JWT Security and Prometheus Monitoring

## Overview

This project implements a microservices architecture with four core services: `OrderService`, `PaymentService`,`GatewayService`, and `IdentityService`. The system uses **Spring Cloud Netflix Eureka** for service registration and discovery, **Spring Cloud Gateway** as an API Gateway, and **Prometheus** and **Grafana** for monitoring and visualizing metrics.

### Technologies Used:
- **Spring Boot**: For developing microservices.
- **Eureka Server**: For service registration and discovery.
- **Spring Cloud Gateway**: For routing and proxying API requests.
- **Prometheus**: For monitoring and scraping application metrics.
- **Spring Security and JWT**: For securing endpoints and authenticating requests.

## Architecture

- **OrderService**: Provides order management functionality.
- **PaymentService**: Manages payment processing and integrates with `OrderService`.
- **IdentityService**: Handles user authentication and issues secure tokens for client requests.
- **Eureka Server**: Acts as the service registry, where all services register themselves.
- **Spring Cloud Gateway**: The API Gateway that routes incoming client requests to the appropriate microservices.
- **Prometheus and Grafana**: Used for monitoring service health, request statistics, JVM metrics, and more.

```

                                +---------------------+
                                |   Eureka Server     |
                                | (Service Registry)  |
                                +---------------------+
                                            ^
                                            |
                                            |  Service Discovery
                    +-----------------------+-----------------------+
                    |                                               |
                    |                                               |
            +---------------+                               +----------------+
            | OrderService  |<----------------------------->| PaymentService |
            +---------------+                               +----------------+
                    ^                                               ^
                    |                                               |
                    +---------------+                   +-----------+
                                    |                   |
                            +----------------+      +---------------+
                            | IdentityService |<----|     Clients   |
                            +----------------+      +---------------+
                                    ^
                                    |
                            +--------------------------------+
                            |        Spring Cloud Gateway    |
                            |     (API Gateway - Proxy)      |
                            +--------------------------------+
                                    ^
                                    |
                                    | Routes client requests
                                    |
                                +----------------+       
                                |    Prometheus  | 
                                | (Scrapes metrics from  |      
                                | Gateway and Services)  |           
                                +------------------------+       
```


## Services

### 1. **Eureka Server (Service Registry)**
The **Eureka Server** is responsible for managing service registration and discovery. It allows the microservices to register themselves and discover other services dynamically.

**Running the Eureka Server:**

- mvn spring-boot:run
- Port: 8761
- Browser: http://localhost:8761

### 2. **API Gateway**
The Spring Cloud Gateway acts as a reverse proxy and routes external API requests to the appropriate microservices (`OrderService`, `PaymentService`, and `IdentityService`).

Gateway Routes:
- **/api/order/**: Routes to OrderService
- **/api/payment/**: Routes to PaymentService
- **/api/auth/**: Routes to IdentityService
- Port: 7070

### 3. **IdentityService**
The IdentityService is responsible for authenticating users and securing communication between services. It provides the following features:
- **Authentication**: Validates user credentials and issues JWTs (JSON Web Tokens) for secure communication.
- **Token Validation**: Verifies the integrity and validity of incoming JWTs.
- **Integration**: Works with the API Gateway to authenticate requests and authorize access to microservices.

Endpoints:
- POST /api/auth/login: Authenticates the user and issues a JWT.
- GET /api/auth/validate-token: Validates a given JWT.
- Port: 9292

**How It Works:**
- Clients authenticate with IdentityService to obtain a JWT.
- JWTs are included in the `Authorization` header of subsequent requests.
- The API Gateway verifies JWTs with IdentityService before routing requests to `OrderService` or `PaymentService`.
- This ensures only authenticated and authorized requests can access the system.

### 4. **OrderService**
The OrderService handles order-related business logic, such as placing, updating, and viewing orders.

Endpoints:
- POST /api/order: Create a new order
- GET /api/order: Get all orders
- Port: 9090

### 5. **PaymentService**
The PaymentService manages payment processing and tracks the payment status of orders.

Endpoints:
- POST /api/payment: Process a payment
- GET /api/payment/{orderId}: Get payment details by order ID
- Port: 9191

### Monitoring
**This project uses Prometheus to scrape metrics from each service and Grafana to visualize these metrics.**

#### Prometheus Setup
Prometheus is used to scrape metrics from the ***/actuator/prometheus*** endpoint exposed by each service.

### Available Metrics:
- HTTP Requests: http_server_requests_seconds_count
- Gateway Requests: gateway.requests
- JVM Memory Usage: jvm_memory_used_bytes
- Custom Metrics (e.g., number of orders placed, payments processed)

### License
This project is licensed under the MIT License.


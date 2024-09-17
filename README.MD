# Microservices Architecture with Eureka, Spring Cloud Gateway, and Prometheus Monitoring

## Overview

This project implements a microservices architecture with two core services: `OrderService` and `PaymentService`. The system uses **Spring Cloud Netflix Eureka** for service registration and discovery, **Spring Cloud Gateway** as an API Gateway, and **Prometheus** and **Grafana** for monitoring and visualizing metrics.

### Technologies Used:
- **Spring Boot**: For developing microservices.
- **Eureka Server**: For service registration and discovery.
- **Spring Cloud Gateway**: For routing and proxying API requests.
- **Prometheus**: For monitoring and scraping application metrics.

## Architecture

- **OrderService**: Provides order management functionality.
- **PaymentService**: Manages payment processing and integrates with `OrderService`.
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
                        +----------------+                  +-----------+
                                         |                  |
                                +--------------------------------+
                                |        Spring Cloud Gateway    |
                                |     (API Gateway - Proxy)      |
                                +--------------------------------+
                                         ^
                                         |
                                         | Routes client requests
                                         |
                                    +----------------+
                                    |   Clients      |
                                    +----------------+
                                            ^
                                            |
                                            |
                                +------------------------+       
                                |    Prometheus          | 
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

### 2. API Gateway
   The Spring Cloud Gateway acts as a reverse proxy and routes external API requests to the appropriate microservices (OrderService and PaymentService).

Gateway Routes:
 - **/api/order/**: Routes to OrderService
 - **/api/payment/**: Routes to PaymentService
 - Port: 7070

### 3. OrderService
The OrderService handles order-related business logic, such as placing, updating, and viewing orders.

Endpoints:

 - POST /api/order: Create a new order
 - GET /api/order: Get all orders
 - Port: 9090

### 4. PaymentService
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

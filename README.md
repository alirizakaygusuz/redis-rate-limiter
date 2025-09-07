# ⏳ Redis Rate Limiter

## 🚀 Project Overview
Redis Rate Limiter is a **Spring Boot + Redis** application that demonstrates how to apply **API request limiting** (rate limiting) per user and per endpoint.  

It implements a **fixed-window algorithm** using Redis commands (`INCR`, `EXPIRE`) to ensure that users cannot exceed a defined number of requests within a specific time window.  

The purpose of this project is to provide a simple reference for how Redis can be integrated with Spring Boot to build **distributed and scalable rate limiting systems**.  

> **Note:** This project contains **dummy endpoints** (`/api/v1/balance`, `/api/v1/transfer`) created only for demonstration purposes.  

---

## ✨ Features
- Redis-backed **fixed window** rate limiting  
- Configurable rules via `application.yml`  
- Per-user + per-endpoint isolation  
- Returns **429 Too Many Requests** when limit exceeded  
- Dummy REST endpoints for testing (`/api/v1/balance`, `/api/v1/transfer`)  
- Easy Docker setup with Redis  

---

## 🛠 Tech Stack
- **Java 17**  
- **Spring Boot 3.x**  
- **Spring Data Redis (Lettuce)**  
- **Docker & Docker Compose**  
- **Maven**  

---

## ⚙️ Setup Instructions

```bash
# 1. Clone the project
git clone https://github.com/alirizakaygusuz/redis-rate-limiter.git
cd redis-rate-limiter

# 2. Start Redis with Docker
docker-compose up -d
```

### 3. Configure Rate Limits
Set limits in `application.yml`:  

```yml
rate:
  limiter:
    balance:
      limit: 10
      window-seconds: 60
    transfer:
      limit: 3
      window-seconds: 60
```

- `/api/v1/balance` → 10 requests per minute  
- `/api/v1/transfer` → 3 requests per minute  

### 4. Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

---

## 📂 Dummy API Endpoints

### `GET /api/v1/balance`
Returns dummy account balance.

**Response:**
```json
{
  "balance": 1500,
  "currency": "USD"
}
```

---

### `POST /api/v1/transfer`
Performs dummy transfer.

**Request:**
```json
{
  "to": "alibaba",
  "amount": "500"
}
```

**Response:**
```json
{
  "transactionId": "857bd5c0-b690-4a35-9d67-e060bbc83cae",
  "to": "alibaba",
  "amount": 500,
  "status": "SUCCESS"
}
```

If the limit is exceeded:

```json
{
  "error": "Too Many Requests"
}
```

---

## 🏗️ Architecture
- **Controller** → defines dummy REST APIs  
- **Interceptor** → checks limits before request reaches controller  
- **Service** → interacts with Redis (INCR + EXPIRE)  
- **ConfigurationProperties** → loads rules from `application.yml`  
- **WebConfig** → registers the interceptor  

---

## ✍️ Author
**Ali Rıza Kaygusuz** – 🛠 Java Backend Developer  

🌍 [GitHub Profile](https://github.com/alirizakaygusuz)  
💼 [LinkedIn Profile](https://www.linkedin.com/in/alirizakaygusuz)  

---

## 📄 License
This project is licensed under the **MIT License**.  

📜 [MIT License Link](https://opensource.org/licenses/MIT)  

Feel free to fork, contribute, or use it freely in your own applications.  

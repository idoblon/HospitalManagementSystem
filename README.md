<div align="center">

# 🏥 Hospital Management System

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven&logoColor=white"/>
  <img src="https://img.shields.io/badge/Lombok-Enabled-blueviolet?style=for-the-badge"/>
</p>

<p align="center">
  A robust, RESTful <strong>Hospital Management System</strong> built with <strong>Spring Boot</strong> and <strong>MySQL</strong>.
  <br/>
  Streamline hospital operations — manage patients, doctors, appointments, and billing with ease.
</p>

---

</div>

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [API Reference](#-api-reference)
- [Configuration](#-configuration)

---

## 🌟 Overview

The **Hospital Management System** is a backend REST API application designed to digitize and simplify hospital workflows. It provides a clean, layered architecture (Controller → Service → Repository) for managing the core hospital entities: **Patients**, **Doctors**, **Appointments**, and **Bills**.

---

## ✨ Features

| Module         | Capabilities                                      |
|----------------|---------------------------------------------------|
| 🧑‍⚕️ **Patients**     | Register, view, update, and delete patient records |
| 👨‍⚕️ **Doctors**      | Add, fetch, update, and remove doctor profiles     |
| 📅 **Appointments** | Schedule, retrieve, and cancel appointments        |
| 🧾 **Bills**        | Create, manage, and track patient billing          |
| 📄 **Pagination**   | All list endpoints support page & size parameters  |

---

## 🛠 Tech Stack

| Technology         | Purpose                         |
|--------------------|---------------------------------|
| **Java 17**        | Programming Language            |
| **Spring Boot 4**  | Application Framework           |
| **Spring Data JPA**| ORM & Database Abstraction      |
| **Hibernate**      | JPA Implementation              |
| **MySQL**          | Relational Database             |
| **Lombok**         | Boilerplate Code Reduction      |
| **Maven**          | Build & Dependency Management   |

---

## 📁 Project Structure

```
HospitalManagementSystem/
├── src/
│   └── main/
│       ├── java/com/javaspring/hospitalmanagementsystem/
│       │   ├── HospitalManagementSystemApplication.java   # App entry point
│       │   ├── controllers/
│       │   │   ├── PatientControllers.java
│       │   │   ├── DoctorControllers.java
│       │   │   ├── AppointmentControllers.java
│       │   │   └── BillControllers.java
│       │   ├── entity/
│       │   │   ├── Patient.java
│       │   │   ├── Doctor.java
│       │   │   ├── Appointment.java
│       │   │   └── Bill.java
│       │   ├── repository/
│       │   │   ├── PatientRepository.java
│       │   │   ├── DoctorRepository.java
│       │   │   ├── AppointmentRepository.java
│       │   │   └── BillRepository.java
│       │   └── service/
│       │       ├── PatientService.java
│       │       ├── DoctorService.java
│       │       ├── AppointmentService.java
│       │       └── BillService.java
│       └── resources/
│           └── application.properties
├── pom.xml
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- ☕ **Java 17** or higher
- 🐬 **MySQL 8.x**
- 📦 **Maven 3.x**

### 1. Clone the Repository

```bash
git clone https://github.com/idoblon/HospitalManagementSystem.git
cd HospitalManagementSystem
```

### 2. Configure the Database

Create a MySQL database named `hms`:

```sql
CREATE DATABASE hms;
```

Then update `src/main/resources/application.properties` with your credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hms
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

> The server starts at **`http://localhost:8080`**

---

## 📡 API Reference

> Base URL: `http://localhost:8080/api/v1`
>
> All list endpoints support pagination via `?page=0&size=10` query parameters.

---

### 🧑‍⚕️ Patients — `/api/v1/patients`

| Method   | Endpoint         | Description              |
|----------|------------------|--------------------------|
| `GET`    | `/`              | Get all patients (paged) |
| `POST`   | `/`              | Register a new patient   |
| `GET`    | `/{id}`          | Get patient by ID        |
| `PUT`    | `/{id}`          | Update patient details   |
| `DELETE` | `/{id}`          | Delete a patient         |

**Patient Model:**
```json
{
  "id": 1,
  "name": "John Doe",
  "gender": "Male",
  "age": 30
}
```

---

### 👨‍⚕️ Doctors — `/api/v1/doctors`

| Method   | Endpoint         | Description             |
|----------|------------------|-------------------------|
| `GET`    | `/`              | Get all doctors (paged) |
| `POST`   | `/create`        | Add a new doctor        |
| `GET`    | `/{id}`          | Get doctor by ID        |
| `PUT`    | `/{id}`          | Update doctor details   |
| `DELETE` | `/{id}`          | Remove a doctor         |

---

### 📅 Appointments — `/api/v1/appointments`

| Method   | Endpoint         | Description                  |
|----------|------------------|------------------------------|
| `GET`    | `/`              | Get all appointments (paged) |
| `POST`   | `/`              | Schedule a new appointment   |
| `GET`    | `/{id}`          | Get appointment by ID        |
| `DELETE` | `/{id}`          | Cancel an appointment        |

---

### 🧾 Bills — `/api/v1/bills`

| Method   | Endpoint         | Description          |
|----------|------------------|----------------------|
| `GET`    | `/`              | Get all bills (paged)|
| `POST`   | `/`              | Create a new bill    |
| `GET`    | `/{id}`          | Get bill by ID       |
| `PUT`    | `/{id}`          | Update a bill        |
| `DELETE` | `/{id}`          | Delete a bill        |

---

## ⚙️ Configuration

Key properties in `application.properties`:

```properties
# Application
spring.application.name=Hospital Management System
server.port=8080

# Database
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/hms
spring.datasource.username=root
spring.datasource.password=your_password

# JPA / Hibernate
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

> ⚠️ **Note:** Never commit your database credentials to version control. Use environment variables or a secrets manager in production.

---

<div align="center">

Made with ❤️ using **Spring Boot** & **Java**

</div>

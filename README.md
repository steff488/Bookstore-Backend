<h1 align="center">Welcome to The Cozy Corner</h1>

This project was generated using [Spring Boot](https://spring.io) version 3.5.3 with Java 17.

<p>
</p>

> Backend for a cozy online bookstore built with Spring Boot and using a MySQL database for inventory and order management. Provides the frontend with REST APIs that manage books, users, orders and more.

## Installation

Clone the repository and run:

```sh
./mvnw clean install
```

## Database Setup

1. Open MySQL and create a database for the project. For example:

```sh
CREATE DATABASE cozy_corner;
```

2. Update the `application.properties` (found in `src/main/resources/`) file with your databae credential:

```sh
# Replace '3306' with your MySQL port if it's different
spring.datasource.url=jdbc:mysql://localhost:3306/cozy_corner
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. The application will automatically create tables on startup using JPA/Hibernate.

## Getting Started

Make sure to have MySQL running and properly configued.<br>
Open a terminal and start the server with:

```sh
./mvnw spring-boot:run
```

The API will be available at http://localhost:8080

## Frontend

The frontend for **The Cozy Corner** is in a separate repository.<br>
You can find it here: [Cozy Corner Frontend](https://github.com/steefy02/Bookstore-Frontend)

Make sure to set up and run the backend first, then follow the frontend repository instructions to start the user interface.

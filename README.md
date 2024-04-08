# StayEase

StayEase is a Java-based web application designed to facilitate hotel bookings. It integrates various components including security configuration, user authentication, and CRUD operations for bookings and hotels.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java JDK 11 or higher
- Maven 3.6.3 or higher

### Installing

1. Clone the repository to your local machine.
```bash
git clone https://github.com/shubhranka/stayease-main.git
```

2. Navigate to the project directory.
```bash
cd stayease-main
```

3. Use the Maven wrapper to build the project.
```bash
./mvnw clean install
```

### Running the Application

Run the application using Maven.
```bash
./mvnw spring-boot:run
```

The application should now be running and accessible via `http://localhost:8080`.

## Structure

The project is structured as follows:

- **src/main/java/com/shubhranka/stayease**
  - **config/**: Contains security configuration classes.
  - **controllers/**: REST controllers for handling requests.
  - **dto/**: Data Transfer Objects for request and response handling.
  - **entities/**: JPA entity classes.
  - **exceptionHandlers/**: Exception handlers for custom exceptions.
  - **exceptions/**: Custom exception classes.
  - **filter/**: JWT authentication filter.
  - **repositories/**: Spring Data JPA repositories.
  - **service/**: Service layer classes.

- **src/main/resources**
  - **application.properties**: Application configuration properties.

- **src/test/java/com/shubhranka/stayease**
  - Test cases for validating the application's functionality.

## Contributing

Please read [CONTRIBUTING.md](https://github.com/shubhranka/stayease-main/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

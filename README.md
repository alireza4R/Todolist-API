# Todo List API

A RESTful API built with Spring Boot for managing personal tasks, with JWT authentication and MySQL persistence.

Built for the [Todo List API project on roadmap.sh](https://roadmap.sh/projects/todo-list-api).

## Features

- User registration and login with hashed passwords
- JWT authentication using Spring Security
- Create, update, delete, and list tasks
- Ownership checks: users can only access their own tasks
- Paginated task lists
- Request validation and custom exception handling
- MySQL storage using Spring Data JPA and Hibernate

## Requirements

- Java 21 or higher
- MySQL Server 8.0 or higher

Maven is included through the Maven Wrapper.

## How to run

1. Create a MySQL database:

   ```sql
   CREATE DATABASE todolist;
   ```

2. Set these environment variables in your terminal or IDE run configuration:

   ```text
   DB_USERNAME=your_mysql_username
   DB_PASSWORD=your_mysql_password
   ```

3. The application connects to `localhost:3306/todolist` by default. Change `spring.datasource.url` in `src/main/resources/application.properties` if needed.

4. From the project directory, run:

   **macOS / Linux:**

   ```bash
   ./mvnw spring-boot:run
   ```

   **Windows PowerShell:**

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

Hibernate creates missing tables on startup using `ddl-auto=update` for local development.

## Endpoints

Base URL: `http://localhost:8080`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Register a user and receive a token |
| POST | `/login` | Log in and receive a token |
| POST | `/todos` | Create a task |
| GET | `/todos?page=1&limit=10` | List your tasks |
| PUT | `/todos/{id}` | Update a task |
| DELETE | `/todos/{id}` | Delete a task; returns `204 No Content` |



## Database Tables


<img width="1282" height="514" alt="image" src="https://github.com/user-attachments/assets/c2f4423b-46a0-4707-9aa2-f762b68649f4" />

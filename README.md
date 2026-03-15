# java-starter

A beginner-friendly **Spring Boot** REST API starter project.  
Use it as a learning resource or as a foundation for your own applications.

---

## What's inside?

| Layer | Class | Purpose |
|---|---|---|
| Controller | `HelloController` | Simple "Hello, World!" endpoint |
| Controller | `TaskController` | Full CRUD REST API for a task list |
| Service | `TaskService` | Business logic with in-memory storage |
| Model | `Task` | Plain Java object (POJO) representing a task |

---

## Prerequisites

| Tool | Minimum version |
|---|---|
| [Java (JDK)](https://adoptium.net/) | 17 |
| [Apache Maven](https://maven.apache.org/download.cgi) | 3.6 |

Verify your installation:

```bash
java -version
mvn -version
```

---

## Quick start

```bash
# 1. Clone the repository
git clone https://github.com/dhtml/java-starter.git
cd java-starter

# 2. Build and start the server (port 8080)
mvn spring-boot:run
```

The server is ready when you see:
```
Started JavaStarterApplication in X.XXX seconds
```

---

## Endpoints

### Hello World

```
GET http://localhost:8080/hello
```

```json
{"message": "Hello, World!"}
```

---

### Task list (CRUD)

#### List all tasks

```bash
curl http://localhost:8080/tasks
```

#### Get a task by id

```bash
curl http://localhost:8080/tasks/1
```

#### Create a task

```bash
curl -X POST http://localhost:8080/tasks \
     -H "Content-Type: application/json" \
     -d '{"title": "Learn Spring Boot", "completed": false}'
```

#### Update a task

```bash
curl -X PUT http://localhost:8080/tasks/1 \
     -H "Content-Type: application/json" \
     -d '{"title": "Learn Spring Boot", "completed": true}'
```

#### Delete a task

```bash
curl -X DELETE http://localhost:8080/tasks/1
```

---

## Run the tests

```bash
mvn test
```

All tests use **JUnit 5** and Spring's **MockMvc** test support — no running server required.

---

## Project structure

```
src/
├── main/
│   ├── java/com/example/javastarter/
│   │   ├── JavaStarterApplication.java   ← entry point
│   │   ├── controller/
│   │   │   ├── HelloController.java
│   │   │   └── TaskController.java
│   │   ├── model/
│   │   │   └── Task.java
│   │   └── service/
│   │       └── TaskService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/javastarter/
        ├── JavaStarterApplicationTest.java
        ├── controller/
        │   ├── HelloControllerTest.java
        │   └── TaskControllerTest.java
        └── service/
            └── TaskServiceTest.java
```

---

## Next steps

Once you are comfortable with this starter, explore these topics:

* **Spring Data JPA** – replace the in-memory list with a real database
* **Spring Security** – add authentication and authorisation
* **Spring Boot Actuator** – production-ready health checks and metrics
* **Docker** – package the app as a container image

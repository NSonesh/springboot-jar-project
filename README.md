# Bajaj Spring Boot JAR Project



## Features
- Build a fat JAR with `spring-boot-maven-plugin`
- Health endpoint: `GET /api/health`
- Greeting endpoint: `GET /api/greet?name=YourName`
- Simple in-memory CRUD for tasks under `/api/tasks`
- Bean validation and unit test

## Quickstart
```bash
# 1) Build (skip tests if needed)
mvn -q -DskipTests package

# 2) Run the jar
java -jar target/bajaj-springboot-jar-0.0.1-SNAPSHOT.jar

# 3) Try endpoints
curl http://localhost:8080/api/health
curl "http://localhost:8080/api/greet?name=Sonesh"

# Create a task
curl -X POST -H "Content-Type: application/json" -d '{"title":"Read JD"}' http://localhost:8080/api/tasks

# List tasks
curl http://localhost:8080/api/tasks

# Update a task (replace {id})
curl -X PUT -H "Content-Type: application/json" -d '{"title":"Read JD fully","completed":true}' http://localhost:8080/api/tasks/{id}

# Delete a task
curl -X DELETE http://localhost:8080/api/tasks/{id}
```

## Project Structure
```
bajaj-springboot-jar
├── pom.xml
├── Dockerfile
├── README.md
├── src
│   ├── main
│   │   ├── java/com/bajaj/jar
│   │   │   ├── Application.java
│   │   │   ├── controller
│   │   │   │   ├── HealthController.java
│   │   │   │   └── TaskController.java
│   │   │   ├── model
│   │   │   │   ├── Task.java
│   │   │   │   └── CreateTaskRequest.java
│   │   │   └── service
│   │   │       └── TaskService.java
│   │   └── resources/application.properties
│   └── test/java/com/bajaj/jar/TaskControllerTest.java
└── .gitignore
```

## Docker (optional)
```bash
# Build image
docker build -t bajaj-springboot-jar:latest .

# Run container
docker run -p 8080:8080 bajaj-springboot-jar:latest
```

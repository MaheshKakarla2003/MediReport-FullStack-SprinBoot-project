# ---------- Stage 1: Build the app with Maven + JDK 17 ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy pom.xml and source code into the image
COPY pom.xml .
COPY src ./src

# Build the Spring Boot jar (skip tests to speed up)
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run the built jar on a light JRE ----------
FROM eclipse-temurin:17-jre-alpine

# Set working directory for runtime container
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/employee-management-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app will listen on INSIDE the container
EXPOSE 8080

# Start the app
ENTRYPOINT ["java","-jar","app.jar"]

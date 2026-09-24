# ---- Stage 1: Build the application ----
FROM maven:3.8.5-eclipse-temurin-17 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---- Stage 2: Create the runtime image ----
FROM eclipse-temurin:17-jdk-alpine

LABEL maintainer="akram123@gmail.com"

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/patient_service_pdp3-0.0.1-SNAPSHOT.jar patient-service.jar

EXPOSE 8080

COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
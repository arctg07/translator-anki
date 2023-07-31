# Use the official Maven image as the base image
FROM maven:3.8.3-openjdk-17-slim AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and project files to the container
COPY pom.xml .
COPY src ./src
COPY /frontend ./frontend/

# Build the application using Maven
RUN mvn clean package -Pproduction

# Use a lightweight container with OpenJDK 17 to run the application
FROM openjdk:17-jdk AS runtime

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage to the runtime stage
COPY --from=builder /app/target/translator-anki-1.jar .
COPY /frontend .

# Expose the port on which your Spring Boot app listens
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "translator-anki-1.jar"]

# Use a base image with JDK
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file to the container
COPY target/import-export-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on (change if your application runs on a different port)
EXPOSE 9090

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]

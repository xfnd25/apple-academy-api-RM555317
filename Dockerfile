# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-jammy AS build

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the application
RUN ./mvnw package -DskipTests

# Stage 2: Create the final image
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/apple-academy-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8083

# Run a diagnostic command and then the application
ENTRYPOINT ["/bin/sh", "-c", "printenv && java -jar app.jar"]

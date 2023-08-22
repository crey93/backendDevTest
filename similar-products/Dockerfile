# Use the official Gradle image as the build environment.
FROM gradle:7.6.2-jdk17 AS build

# Set the working directory
WORKDIR /workspace/app

# Set the default value for CLIENT_URL
ENV CLIENT_URL=http://host.docker.internal:3001/product/

# Copy the gradle configuration files first to leverage Docker cache
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Download all required dependencies into one layer
RUN ./gradlew dependencies

# Copy source code and build our application
COPY src src
RUN ./gradlew bootJar

# Use the official OpenJDK base image
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /workspace/app/build/libs/*.jar app.jar

# Specify the entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]

# Expose the port your app runs on
EXPOSE 5000

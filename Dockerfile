# Use the official OpenJDK 17 image
FROM openjdk:17

# Create a new app directory for the application files
WORKDIR /app

# Copy the JAR file into the app directory
COPY target/FoodPay-0.0.1-SNAPSHOT.jar /app/FoodPay.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/FoodPay.jar"]
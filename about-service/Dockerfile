# 1. build
FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. runtime
FROM eclipse-temurin:23-jdk
WORKDIR /app
COPY --from=build /app/target/about-service-*-runner.jar app.jar

EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "app.jar" ]
# Этап 1: Сборка приложения
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Этап 2: Запуск приложения
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

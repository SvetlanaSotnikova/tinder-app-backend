FROM openjdk:22-jdk-apline

WORKDIR /app

COPY target/tinder-app-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
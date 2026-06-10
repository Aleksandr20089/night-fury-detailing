# Шаг 1: Собираем проект с помощью Maven и Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Даем права на выполнение скрипта сборщика в Linux
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Шаг 2: Запускаем готовый jar-файл на чистой Java 21
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
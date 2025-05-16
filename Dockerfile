# build stage
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /edu.unipr.eshendetsia
COPY . .
RUN mvn clean install

# runtime stage
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /edu.unipr.eshendetsia
COPY --from=build /app/target/*.jar e-shendetsia.jar

EXPOSE 8080
ENTRYPOINT ["java", "--enable-preview", "-jar", "e-shendetsia.jar"]

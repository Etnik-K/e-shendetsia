# java development kit - lightweight
FROM openjdk:17-jdk-slim

# workdir
WORKDIR /app

# kopjo maven build jar
COPY target/*.jar e-shendetsia.jar

# porti ku run
EXPOSE 8080

# run jar
ENTRYPOINT ["java", "-jar", "e-shendetsia.jar"]


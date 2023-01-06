FROM openjdk:17-alpine
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
FROM openjdk:17-jdk-alpine
COPY build/libs/company-api-1.0.0.jar company-api.jar
ENTRYPOINT ["java", "-jar", "/company-api.jar"]
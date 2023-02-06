FROM openjdk:17-jdk-alpine
COPY build/libs/CompanyApi-1.0.0.jar CompanyApi.jar
ENTRYPOINT ["java", "-jar", "/CompanyApi.jar"]
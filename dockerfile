FROM openjdk:17-alpine
COPY build/libs/shiok-jobs-user-ms.jar /shiok-jobs-user-ms.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/shiok-jobs-user-ms.jar"]

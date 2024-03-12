FROM openjdk:23-slim
COPY build/libs/shiok_jobs_user_ms.jar /shiok_jobs_user_ms.jar
COPY src/main/resources/application.properties /application.properties
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/shiok_jobs_user_ms.jar"]

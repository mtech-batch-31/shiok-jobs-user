FROM openjdk:23-ea-17-jdk-oraclelinux8
COPY build/libs/shiok_jobs_user_ms.jar /shiok_jobs_user_ms.jar
COPY src/main/resources/application.properties /application.properties
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/shiok_jobs_user_ms.jar"]

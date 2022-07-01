FROM openjdk:11
EXPOSE 8084
VOLUME /main-app
ADD target/logger-rest-web-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
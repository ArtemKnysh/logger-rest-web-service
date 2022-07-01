# Logger Rest Web Service

This is a RESTfull Web Service to consume, store, and process logs from a frontend application and to make the stored logs retrievable for batch processing.

## API documentation
This example uses Swagger. Access http://localhost:8080/swagger-ui.html to check the documentation.

## Build and run application
The file [docker-compose.yml](docker-compose.yml) contains all the necessary settings to configure the environment. As this is a very simple application, there are only two containers/services - the rest api and the database. For building or rebuilding services run `docker-compose build`. To start all the services, you need to run `docker-compose up` (use `-d` to run in detached mode). The docker image for the rest api application service is defined by the file [dockerfile](dockerfile).

Apart from that, you can manually build and generate the executable file with `mvn clean package` and then run the maven spring boot plugin (`mvn spring-boot:run`) or directly the java command `java -jar <jar file location>`. You must be running an instance of MongoDB server.

The rest api application will be available at http://localhost:8080.

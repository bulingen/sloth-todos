# Sloth To-Do:s server

### To Run

This project uses Java with Spring Boot and Maven as the build and package manager.

First of all, run `mvn install` do download all dependencies.

Make sure to update the `spring.datasource.url`-property in `application.properties` to a file that the server can access.

Then, run `mvn spring-boot:run` to start the server.

The server starts on port 8088 out of the box. To change the port edit the `application.properties`-file.

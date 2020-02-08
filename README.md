# e57a-section-boot-jpa

# Contents
This  project demos a very Spring Boot project with the the following features:
1. H2 in mememory database integration
2. JPA
3. Spring @Repository
3. Thymeleaf
4. Spring MVC
5. Spring @RestConstroller
6. Spring devtools

## H2
An H2 embedded (in project) in-memory simple relational database that developers use for demoing/trying out code that requires a database.  Spring Boot takes the H2 DB to the next level with its opinionated nature of configuration for H2.  With only adding the H2 and JPA Spring dependencies add,  Spring will generate/load DDL from the scanned Entities in your project to create the DB tables.  It also takes care of the connection setup.  You only need to be concerned with writing your JPA entities and Spring Repositories.

Here is a [good overview of H2](http://www.springboottutorial.com/spring-boot-and-h2-in-memory-database)

### Packaging the application
- mvn clean package

### Running the applicaiton
- mvn spring-boot:run

### Application URL
##### Web app: 
1. [http://localhost:8080](http://localhost:8080)

##### Rest APIs: 
1. GET http://localhost:8080/cars?color=red 
2. GET http://localhost:8080/cars
3. DELETE http://localhost:8080/cars/100
4. PUT http://localhost:8080/cars
5. POST http://localhost:8080/cars

### Enabling H2 console
1. Add the following to your applicaiton.properties: spring.h2.console.enabled=true 
2. Navigate to: http://localhost:8080/h2-console
3. Set the JDBC URL to: jdbc:h2:mem:testdb
4. Click 'Connect'

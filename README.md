# cyangate-spring-case
 
## Requirements

For building and running the application you need:

- JDK 20
- Spring Boot 3.2.0
- Maven 3
- Apache POI 5.2.2
- H2 Database (A fast in-memory relational database)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.ali.cyangatespringcase.CyangateSpringCaseApplication` class from your IDE.

## Encoding your XLSX file to a Base64 String

You can execute the `main` method in the `com.ali.cyangatespringcase.util.FileToBase64Converter` class from your IDE. You'll receive an output of a Base64 String


#### REST API

|HTTP Method|URL|Description|
|---|---|---|
|`POST`|http://localhost:8080/api/employees/createRecord | Create the Employee DB |
|`PUT`|http://localhost:8080/api/employees/updateRecord | Update Employee by ID |
|`GET`|http://localhost:8080/api/employees/readRecord?id={id} | Get Employee by ID |
|`DELETE`|http://localhost:8080/api/employees/deleteRecord?id={id} | Delete Employee by ID |

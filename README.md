
Readme: example-spring-rest

An example of a RESTful WebServer developed using Spring & SpringBoot.

This simple server acts a Location-Service - it will return details of a physical location given a valid identifier (in the form of a UUID).
Requirements

The fully fledged server uses the following:

    Spring Framework
    SpringBoot
    A CloudFoundry account (to deploy the server)

Dependencies

There are a number of third-party dependencies used in the project. Browse the Maven pom.xml file for details of libraries and versions used.
Building the project

You will need:

    Java JDK 17 or higher
    Maven 3.1.2 or higher
    Git

Clone the project and use Maven to build the server

$ mvn clean install

Deploying to the cloud

You can deploy the server to CloudFoundry using:

$ cf push UUIDLocator -p <jar>

Browser URL

Open your browser at the following URL for Swagger UI (giving REST interface details):

http://localhost:8080/swagger-ui/index.html#/ or just click here

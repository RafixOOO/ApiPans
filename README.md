The fully fledged server uses the following:

    Spring Framework
    SpringBoot

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

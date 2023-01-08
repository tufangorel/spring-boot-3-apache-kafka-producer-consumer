## kafka-producer-rest-api

Purpose : Use programmatic transaction management instead of @Transactional annotation. <br/>
Reason : Get rid of annotation processing cost. <br/>

### Local run steps <br/>
1- Call customer/saveprogrammatic rest end-point to save customer record. </br>
2- Call customer/list rest end-point to list all customer records. </br>
3- Before starting the application run <b>mvn clean install</b> to generate mapstruct mapper class. </br>
4- If generated mapstruct mapper class is not recognized by IntelliJ IDE then reload all maven projects. </br>
5- Start Spring Boot REST API by running main method containing class CustomerInfoApplication.java in your IDE. <br/>
6- Alternatively you can start your Docker container by following the commands below. <br/>
NOT : Execute maven command from where the pom.xml is located in the project directory to create Spring Boot executable jar. <br/>
<pre> 
$ mvn clean install -U -X <br/>
$ mvn spring-boot:run <br/>
</pre>

swagger_ui can be accessed via https secure port 8443 from localhost : <br/>
https://localhost:8443/customer-info/swagger-ui/index.html <br/><br/>
![https_swagger_ui](doc/https_swagger_ui.png) <br/><br/>

Database ER diagram : <br/>
![https_swagger_ui](doc/cust_db_er_diagram.png) <br/><br/>

### Tech Stack
<pre>
Java 11
H2 Database Engine
spring boot
spring boot starter data jpa
spring boot starter web
spring boot starter test
spring boot starter aop
spring boot starter actuator
spring security web
springdoc openapi ui
springfox swagger ui
querydsl-jpa
querydsl-apt
hibernate
logback
maven
mapstruct
mapstruct-processor
hikari connection pool
mockito-core
mockito-junit-jupiter
mockito-inline
Docker
</pre>
### Docker build run steps
NOT : Execute docker commands from where the DockerFile is located. <br/>
NOT : Tested on Windows 10 with Docker Desktop Engine Version : 20.10.11 <br/>
<pre>
$ docker system prune -a --volumes <br/>
$ docker build . --tag demo  <br/>
$ docker images <br/>
  REPOSITORY   TAG       IMAGE ID       CREATED         SIZE <br/>
  demo         latest    9d4a0ec3294e   6 minutes ago   288MB <br/>
$ docker run -p 8443:8443 -e "SPRING_PROFILES_ACTIVE=dev" demo:latest <br/>
</pre>

## API OPERATIONS
### Save a new customer to database

Method : HTTP.POST <br/>
URL : https://localhost:8443/customer-info/customer/saveprogrammatic <br/>
HTTP Request Body : <br/>
<pre>
{
    "name": "name1",
    "age": 1,
    "shippingAddress": {
        "address": {
            "streetName": "software",
            "city": "ankara",
            "country": "TR"
        }
    }
}
</pre>

Curl Request : <br/>
<pre>
curl --location --request POST 'https://localhost:8443/customer-info/customer/saveprogrammatic' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "name1",
    "age": 1,
    "shippingAddress": {
        "address": {
            "streetName": "software",
            "city": "ankara",
            "country": "TR"
        }
    }
}'
</pre><br/>

Response : 

HTTP response code 200 <br/>
<pre>
{
    "id": 1,
    "name": "name1",
    "age": 1,
    "shippingAddress": {
        "id": 1,
        "address": {
            "id": 1,
            "streetName": "software",
            "city": "ankara",
            "country": "TR"
        }
    }
}
</pre>

HTTP Response Headers : </br>
<pre>
request-id: 68182bbf-996d-4732-a6ff-2c49a90012d1
correlation-id: 68182bbf-996d-4732-a6ff-2c49a90012d1
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
</pre>

### List all customers saved to database

Method : HTTP.GET <br/>
URL : https://localhost:8443/customer-info/customer/list <br/>
Request Body : <br/>
<pre>
{}
</pre>
Curl Request : <br/>
<pre>
curl --location --request GET 'https://localhost:8443/customer-info/customer/list' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5E6B21C9533643F4A7EE462DCBB3B312' \
--data-raw '{}'
</pre>
<br/>

Response : 

HTTP response code 200 <br/>
<pre>
[
    {
        "id": 1,
        "name": "name1",
        "age": 1,
        "shippingAddress": {
            "id": 1,
            "address": {
                "id": 1,
                "streetName": "software",
                "city": "ankara",
                "country": "TR"
            }
        }
    }
]
</pre>
<br/>
HTTP Response Headers : </br>
<pre>
request-id: 411b4b33-6af5-4f78-b185-4171e779222d
correlation-id: 411b4b33-6af5-4f78-b185-4171e779222d
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
</pre>

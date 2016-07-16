# Microservices introduction: Spring cloud + Netflix stack
This is an introduction to Spring cloud which provides tools building some of the common patterns in distributed systems. This program allow you to create and retrieve a list of users.

## Technologies
This Java 8 project is an introduction to microservices where the usage of the following frameworks/services is shown:
* Spring cloud
* Eureka
* Hystrix
* Feign
* Gradle

### Eureka
From https://github.com/Netflix/eureka/wiki/Eureka-at-a-glance:

>Eureka is a REST (Representational State Transfer) based service that is primarily used in the AWS cloud for locating services for the purpose of load balancing and failover of middle-tier servers. We call this service, the Eureka Server. Eureka also comes with a Java-based client component,the Eureka Client, which makes interactions with the service much easier. The client also has a built-in load balancer that does basic round-robin load balancing. At Netflix, a much more sophisticated load balancer wraps Eureka to provide weighted load balancing based on several factors like traffic, resource usage, error conditions etc to provide superior resiliency.

### Hystrix: Latency and Fault Tolerance for Distributed Systems
Hystrix is a latency and fault tolerance library designed to isolate points of access to remote systems, services and 3rd party libraries, stop cascading failure and enable resilience in complex distributed systems where failure is inevitable.

### Feign HTTP clients
Feign is a java to http client binder inspired by Retrofit, JAXRS-2.0, and WebSocket. Feign's first goal was reducing the complexity of binding Denominator uniformly to http apis regardless of restfulness.
Feign works by processing annotations into a templatized request. Just before sending it off, arguments are applied to these templates in a straightforward fashion. While this limits Feign to only supporting text-based apis, it dramatically simplified system aspects such as replaying requests. It is also stupid easy to unit test your conversions knowing this.

## Components
The project is made up of 3 small services and allow you to create a user or retrieve a list of users through REST API.
* *users-services* : This service is responsible of user creation.
* *eureka-server*: This service is responsible of the discovery service.
* *api-service*: This is the entry point/proxy that manages and forward the requests to the services. It runs on port 9000

## Execution
In order to compile and run the program you can execute the *startup.sh* script.
To test the functionalities you can send the following REST requests:

*CREATE A USER*
```
curl -H "Content-Type: application/json" -X POST -d '{"name": "Andres","lastName": "Iniesta","fullName": "Andres Iniesta","city": "Barcelona"}' http://localhost:9000/api/users/addUser
```
*GET USERS*
```
curl -X GET localhost:9000/api/users
```

Eureka dashboard: http://localhost:8761/

Hystrix page: http://localhost:9000/hystrix with http://localhost:9000/hystrix.stream as stream

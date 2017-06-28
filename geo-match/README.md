#Welcome to Clean Code Bay!

This is our Clean Architecture Solution by Example: Offline Phone Number Geolocation


**The back-end:**

The back-end, is a micro-service solution with String Boot and a REST web service endpoint. Therefore, it is simple to combine with Docker and Spring Cloud for an elegant cloud solution.


**The back-end design:**

The design is applying principles of: S.O.L.I.D, T.R.U.E, F.I.R.S.T, [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html).

So, result is a business focused component which, at its core, captures the problem domain essence and pushes mechanisms to the periphery, where they belong.
This has the consequence of enabling its reuse in different products with distinct deployment scenarios.


**The front-end:**

It is a Java command-line app, build in Java 8 and Jersey client API.


_Tecnologies Highlights:_
- Maven
- Java 8
- Google libphonenumber
- Google libphonenumber - geocoder
- Spring
- Spring Data (JPA)
- Spring Boot
- Jersey
- RESTful
- Mockito
- JUnit

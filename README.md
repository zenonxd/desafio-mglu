# About

We'll do a backend challenge of a company named: Magalu.

# What will we learn

- How to create a microservice with Spring Boot; 


- How to do schedule task with Spring Scheduler; 
    

- How to communicate with MySQL using Docker;


- Custom queries with Spring Data JPA;


- ManyToOne relations with JoinColumn


- Doing logs with SL4J

# About the challenge

The application will be part of a communication platform, so, we need:

- Receive a request to schedule notification sending


- Consult the situation of the notification schedule (to know if it was sended, is pending or has some error)


- Cancel a notification that has been schedule


# Technical requirements

- This API has to be Restfull


- The endpoint responsible for the schedule needs to have, at minimum, this attributes:
  - Date/Time that's going to be sent;
  - Receiver
  - Message to be delivered


- The possible ways to communicate (email, SMS, push and Whatsapp)


- As soon as we get the request of schedule, we have to save it in the database.

# Structure

```textmate
src/main/java/com/seuprojeto
├── application
│   ├── scheduler        # Lógica para agendamentos
│   └── usecases         # Casos de uso da aplicação
│       └── SendNotificationUseCase.java
├── domain
│   ├── entities         # Modelos centrais do domínio
│   │   └── Notification.java
│   ├── repositories     # Interfaces dos repositórios
│   │   └── NotificationRepository.java
│   └── services         # Regras de domínio puras
│       └── NotificationService.java
├── infrastructure
│   ├── config           # Configurações do projeto (Spring, Scheduler)
│   │   └── SchedulerConfig.java
│   ├── persistence      # Implementações do banco de dados (MySQL)
│   │   ├── JpaNotificationRepository.java
│   │   └── entities     # Representações das tabelas do banco
│   │       └── NotificationEntity.java
│   └── rabbitmq         # Comunicação com outras partes (opcional)
│       └── NotificationPublisher.java
└── interfaces
    ├── controllers      # Endpoints RESTful
    │   └── NotificationController.java
    ├── mappers          # Convertem entre entidades, DTOs e ViewModels
    └── dtos             # Objetos de entrada e saída da API
        ├── NotificationRequestDTO.java
        └── NotificationResponseDTO.java
```


## Initiating the project

- Spring Web
- Spring Data JPA
- Lombok for boilerplate code
- MySQL driver
- Docker compose support
- Spring Scheduled Tasks

# Initial Tasks

## MySQL and Docker configuration

```dockerfile
services:
  mysql:
    container_name: 'mysql-magalu'
    image: 'mysql:latest'
    environment:
      - 'MYSQL_DATABASE=magaludb'
      - 'MYSQL_PASSWORD=123'
      - 'MYSQL_ROOT_PASSWORD=123'
      - 'MYSQL_USER=admin'
    ports:
      - '3307:3306'
```

## Spring Boot with MySQL configuration

```properties
spring.application.name=desafio-mglu

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3307/magaludb
spring.datasource.username=root
spring.datasource.password=123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
```

To connect on the database, we can use Workbench or Beekeeper.

<hr>

# Functionalities

## Scheduling notification with requests

### Mapping the entities (Notification, Channel and Status)

What do we need? First, we need to have the Notification. It'll be responsible to manage the date/time, recipient and the
message.

This notification is going to be sent through a Channel (another entity). This channel may be (email, SMS, push or Whatsapp).

And by last, we need to have the register status, if the notification was sent, we have to change the Status (ENUM).

#### Notification

Needs to have at least 3 attributes: date/time, recipient, message.

```java
```

#### Channel

```java
```

#### Status

```java
```

### Creating config to initialize the tables (Channel and Status)

A class can have predefined values, using ENUMS. On the bottom of the class, we can create a subclass ENUM.

It's going to be public and will return an ``enum``. It can be called "values".

Inside of it, we can return some ENUM values like: EMAIL, SMS, etc.

And we can also, create attributes on it like id, description and even constructors.

After that we can pass specific values to the ENUMS(id, description).

Finally, we can create inside this class a method to convert from ENUM to the class we are working with.

```java
```

To insert this predefined values, we can create class in the package config, named DataLoader.

#### DataLoader

This class will be responsible to initialize the database, so we need to create the repositories.

Since it's a configuration class, we use the @Configuration.

The class will implements CommandLineRunner, and we will implement the interface method. 

This method will be initialized as soon as we start our project. We need to guarantee that our tables has the values
predefined.

To do that, we have to insert our repositories (Channel & Status) and use the constructor.

Inside the method we have to persist the values using ``Entitie.Values.values()``. This will return an Array of values,
that we're going to convert to Stream. ``Arrays.stream(repository::save)``.

But this is still an ENUM, we need to convert to entity, so we use ``.map(Entitie.Values::METHOD)``.

```java
```


### Creating the API to request the notification schedule

### Create persistence flux of request

### Testing the flux (api → service → mysql)

<hr>

## Consulting the schedule notification situation

### Create notification schedule request query API

### Create request service

### Testing the API

<hr>

## Canceling the notification schedule

### Create schedule notification cancellation API

### Create service cancellation

### Test the API

<hr>

## Checking if the notification was sended

### Create checking routine via Spring Scheduler

### Testing the routine

### Create Service to consult the available notifications to be sent

### Create a mock that's able to send the notification with logging

### Create service that updates the nofification status

### Test the complete flux (api -> schedule -> routine -> send the notification - updates the databse)


#Estate Management Web Service
##Core Idea
This project Provides an API for the owners of estates registered in the system where they can store
and sell those estates

It also provides an API for the system administrator where one can do administrative tasks

##Used Technologies
* Spring Boot
* Spring MVC
* Spring Data JPA
* MySQL Database
* Spring Security
* NGINX Web Server

##Main Features
1. Authentication & Authorization using JWT Tokens standard
2. Estate owner registration in the system using an email and a password
2. Estates CRUD API for the owners to manage their estates
3. Esate Sale Transaction CR API for the owners to sell their estates
4. Some default values for certain estate attributes in case the owner does not provide the system with them, they are stored in the database
and they have a RU API for the system administrator
5. Resource RUD(Read, Update, Delete) operation details logger for estates and sale transactions, they are logged in a file as JSON, implemented using AOP
and they have a Read API for the system administrator
6. Asynchronous Messaging using RabbitMQ, where the system administrator can send a message to another application, the other end just receives the message
7. Load balancing using Robin Round algorithm to distribute the load between the system instances (using NGINX as a load balancer)

# BoardgamesRental

## About

Project was created for hosting event based on board games. \
It allows to create event adding boardgames to them\
renting them to participants and returning them after the event.\

## Requirements

Java 21 or higher\
Maven 3.8.7 or higher\
Postgres 17.0 or higher




## How To Run 

### to build the project

```bash
mvn clean package
```
then build the docker containers
```bash
docker-compose build
```
 run the migrations
```bash
mvn flyway:migrate
```
### to run the project

```bash
docker-compose up
```




## How To Use
# task-manager-backend

## Install requires software
------
Before to get start, make sure that you have installed the next software for correct application work:

* Install [JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install [Maven](https://maven.apache.org/)
* Install [PostgreSQL](https://www.postgresql.org/)

## How to run

Following steps helps you to get the app and run it

* Download the application or clone it
* Create a PostgreSQL database and a user via following commands:
```
 psql postgres
CREATE USER app_user with encrypted password 'app_password';
CREATE DATABASE localdb;
GRANT ALL PRIVILEGES ON DATABASE localdb TO app_user;
\q 
```
* To start the application, just run following commands in root project directory
mvn package
`java -jar task-manager-1.0-SNAPSHOT.jar`

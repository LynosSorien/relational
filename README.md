# Relational
Spring framework based application that contains REST-Service which communicates with database that is used to relationate concepts between them (initially used to hierachically relationate people for study purposes)

## Technologies
- Java 8
- Spring Framework (Spring-boot 2.1.2).
- MySql
- H2 (for local environment)
- Thymeleaf

## Libraries
- Lombok
- Mapstructs

## Webjars
 - JQuery
 - Thymeleaf templates


## How to run
First of all we must ensure that we have installed Java 8 (at least).

Go to the working copy and execute:
```shell
./run.sh <profile>
```
or
```shell
mvn clean package
mvn spring-boot:run -Drun.spring-boot.profiles=<profiles>
```

Change **<profile>** by any usable profiles.

# Profiles
 - local

### Local
The local profile use H2 database (hypersonic) and is used commontly for test and development (local). This makes easy to try the application because we don't need to configure any database and don't need to put any needed data to start to use it. It's because the bootstrapping at this profile is enabled and the default admin user is admin@test.com:test.

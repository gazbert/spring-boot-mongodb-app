# Spring Boot MongoDB App

## What is this?
A [Spring Boot](http://projects.spring.io/spring-boot/) sample app that integrates 
[MongoDB](https://www.mongodb.com/) using [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb).

MongoDB is a document database, which means it stores data in JSON-like documents.

It could be used as boilerplate for developing microservices that require a persistent document store.

## Install MongoDB
The fastest way to get up and running is to use the [Docker image](https://hub.docker.com/_/mongo):

```bash
docker pull mongo;
docker run -d -p 27017-27019:27017-27019 --name my-mongodb mongo:latest

```

Once it's up and running, you can attach to it to run MongoDB commands: 
```bash
docker exec -it mongodb bash
```

Launch the [Mongo Shell](https://docs.mongodb.com/manual/reference/mongo-shell/) client:
```bash
mongo
```

Check it's working ok, e.g. list the dbs:
```bash
show dbs
```

The alternative is to install it natively as per the [official guide](https://docs.mongodb.com/manual/installation/).

## Build Guide
You'll need JDK 11 installed on your dev box.

You can use [Gradle](https://gradle.org/) or [Maven](https://maven.apache.org) to build the app.

Both [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) and 
[Maven Wrapper](https://github.com/takari/maven-wrapper) are included in the project.

### Gradle
1. From the project root, run `./gradlew build`
1. To generate the Javadoc, run `./gradlew javadoc` and look in the `./build/docs/javadoc` folder.

### Maven
1. From the project root, run `./mvnw clean install`
1. Take a look at the Javadoc in the `./target/apidocs` folders after the build completes.

### Checkstyle
The app uses the [Google Style Guide](https://google.github.io/styleguide/javaguide.html)
which is enforced during both the Gradle and Maven build - see the [build.gradle](./build.gradle) 
and [pom.xml](./pom.xml) files respectively. The Checkstyle report locations are:

* Gradle - `./build/reports/checkstyle/main.html`
* Maven - `./target/checkstyle-result.xml`

### Code Coverage
Code coverage is provided by [JaCoCo](https://www.eclemma.org/jacoco/) and is enforced at build time.
It's currently set to 80% line coverage. See the build files. The coverage report locations are:

* Gradle - `./build/report/jacoco/test/html/index.html`
* Maven - `./target/jacoco-report/index.html`

### Code Quality
[SpotBugs](https://spotbugs.github.io/) is run at build time. Any bugs found will fail the build. 
The bug report locations are:

* Gradle - `./build/report/jacoco/test/html/index.html`
* Maven - `./target/spotbugsXml.xml`

### Tests
Unit tests are run as part of both Gradle and Maven builds.

[JUnit 4](https://junit.org/junit4/) and [Mockito](https://site.mockito.org/) is used to unit test
the code.

The unit test report locations are:
* Gradle - `build/reports/tests/test/index.html`
* Maven - `./target/surefire-reports`
 
## Configuration
The configuration is held in the [./config/application.properties](./config/application.properties) 
file.

You'll need to change the MongoDB connection details of you are running the database on a different
machine:

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=sample-app
```

MongoDB authentication is not enabled for this demo - see the
[MongoDB Manual](https://docs.mongodb.com/manual/tutorial/enable-authentication/) and the
[Spring Data Docs](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.mongo-db-factory-java)
if you need to enable it.

## User Guide

You can view the exchanges, queues, and messages in the Rabbit UI: 
[http://localhost:15672](http://localhost:15672)

### Running the App
The [Application](./src/main/java/com/gazbert/mongosample/Application.java) app does...

From the the app from the project root folder using:

* Gradle - `./gradlew bootRun`
* Maven - `./mvnw spring-boot:run`

Once you've run the app, you can take a look at the collections in the database using the Mongo Shell.

To see all the Users and Registrations:
```
show dbs
use sample-app
db.user.find()
db.registration.find()
```

Take a look at the [Mongo Shell Manual](https://docs.mongodb.com/manual/crud/) for more commands.

### Interesting things to note

#### The MongoTemplate
Spring Data MongoDB uses the MongoTemplate to execute the queries behind your find* methods.
You can use the template yourself for more complex queries, but this guide does not cover that.
(see the Spring Data MongoDB Reference Guide)

#### Indexing
To see the indexes on the collections:
```
db.user.getIndexes()
db.registration.getIndexes()
```

## Logging
Logging for the app is provided by [log4j](http://logging.apache.org/log4j). 
The log file is written to `logs/app.log` using a rolling policy. When a log file size reaches 
100 MB or a new day is started, it is archived and a new log file is created. 

The app will create up to 7 archives on the same day; these are stored in a directory based on the 
current year and month. Only the last 90 archives are kept. Each archive is compressed using gzip.

The logging level is set at `info`. You can change this default logging configuration in 
the [`config/log4j2.xml`](./config/log4j2.xml) file.

## Issue & Change Management
Issues and new features are managed using the project 
[Issue Tracker](https://github.com/gazbert/spring-boot-mongodb-app/issues) - submit bugs here.
 
You are welcome to take on new features or fix bugs! See [here](CONTRIBUTING.md) for how to get involved. 

## References
1. [MongoDB Manual](https://docs.mongodb.com/manual/reference/).
1. [Spring Data MongoDB Reference Docs](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference).



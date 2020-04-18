# Spring Boot MongoDB App

## MongoDB

https://www.mongodb.com/

### Docker

https://hub.docker.com/_/mongo

https://docs.mongodb.com/manual/installation/

https://docs.mongodb.com/manual/reference/

Download and run it:
```bash
docker pull mongo;
docker run -d -p 27017-27019:27017-27019 --name mongodb mongo
```

Attach to it to run MongoDB commands:
```bash
docker exec -it mongodb bash
```

Launch the MongoDB shell client:
```bash
mongo
```

List dbs:
```bash
show dbs
```

Mongo shell Quick Reference: https://docs.mongodb.com/manual/reference/mongo-shell/


Spring Data MongoDB uses the MongoTemplate to execute the queries behind your find* methods. 
You can use the template yourself for more complex queries, but this guide does not cover that.
(see the Spring Data MongoDB Reference Guide)

# References
1. [Spring Data MongoDB Reference Docs](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference).


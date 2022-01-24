http://localhost:8080/swagger-ui/index.html

## Build app in the Vagrant

```shell
$ cd /vagrant
```

```shell
$ ./mvnw package && java -jar target/api-0.0.1-SNAPSHOT.jar
```

```shell
$ ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=bluebank/java-api -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```
## Base image
FROM eclipse-temurin:21

## Meta data for the image
LABEL mentainer="ash001x@gmail.com"


## Creating a working directory in the container
WORKDIR /app
# in the root path a folder will be created and inside the our jar
 # will be placed inside
## Copying the jar from target folder(Source) to (destination) container path
COPY target/sb3-j21-db-docker-demo-0.0.1-SNAPSHOT.jar  /app/springbootapp.jar


##to start the jar file command
ENTRYPOINT ["java", "-jar", "springbootapp.jar"]
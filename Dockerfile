# from java 17
FROM openjdk:17-jdk-alpine3.13

# set environment variable
ARG MYSQL_DBNAME
ARG MYSQL_USERNAME
ARG MYSQL_PASSWORD
ARG MYSQL_URL
ARG MYSQL_PORT

# copy the jar file to the container
COPY target/*.jar app.jar

# expose the port
EXPOSE 8080

# run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
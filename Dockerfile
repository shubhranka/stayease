FROM maven:3.8.5-openjdk-17 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ ./src/
RUN mvn clean package -DskipTests=true

# from java 17
FROM openjdk:17-jdk-alpine3.13

# set environment variable
ARG MYSQL_DBNAME
ARG MYSQL_USERNAME
ARG MYSQL_PASSWORD
ARG MYSQL_URL
ARG MYSQL_PORT

# copy the jar file to the container
COPY --from=builder /app/target/*.jar app.jar

# expose the port
EXPOSE 8080

# run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
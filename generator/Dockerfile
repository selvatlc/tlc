FROM openjdk:19-jdk-slim
WORKDIR /app
RUN apt-get -y update; apt-get -y install curl
COPY target/*-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
FROM maven:3.5-jdk-8-alpine
VOLUME /messenger_tmp
COPY target/messenger-1.0.jar app.jar
RUN mvn install
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-Djava.security.egd=file:/dev/./urandom","-jar", "/app.jar"]
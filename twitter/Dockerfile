FROM maven:3.5-jdk-8-alpine
VOLUME /tmp
WORKDIR /app
ADD . /app
RUN mvn install -amd
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-Djava.security.egd=file:/dev/./urandom","-jar", "target/twitter-1.0.jar"]


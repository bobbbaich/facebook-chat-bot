FROM maven:3.5-jdk-8-alpine
VOLUME /tmp
WORKDIR /app
ADD /target/twitter-1.0.jar /app/
RUN mvn install
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-Djava.security.egd=file:/dev/./urandom","-jar", "twitter-1.0.jar"]
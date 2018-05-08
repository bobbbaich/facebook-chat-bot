FROM maven:3.5-jdk-8-alpine
VOLUME /tmp
WORKDIR /twitter
ADD . /twitter
RUN mvn install
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-Djava.security.egd=file:/dev/./urandom","-jar", "/twitter-1.0.jar"]


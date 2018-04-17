FROM maven:3.5-jdk-8-alpine
VOLUME /tmp
WORKDIR /app
ADD . /app
RUN mvn install
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "target/twitter-stream-analysis-1.0.jar","-Dspring.profiles.active=docker"]
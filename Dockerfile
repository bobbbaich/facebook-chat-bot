FROM maven:3.5-jdk-8-alpine
VOLUME /tmp
WORKDIR /app
ADD . /app
RUN mvn install -DskipTests=true
#TODO: add use of 'docker' profile, when docker run;
#TODO: specify appropriate properties in application-docker.yml
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "target/twitter-stream-analysis-1.0.jar"]
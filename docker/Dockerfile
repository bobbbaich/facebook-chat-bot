FROM openjdk:8
VOLUME /tmp
ARG ARTIFACT_NAME
ARG EXPOSED_PORT
ARG DOCKER_USERNAME
ARG DOCKER_PASSWORD
ADD ${ARTIFACT_NAME}.jar /app.jar
ENV SPRING_PROFILES_ACTIVE docker
RUN bash -c 'touch /app.jar'
CMD echo ${DOCKER_PASSWORD} | docker login -u ${DOCKER_USERNAME} --password-stdin
EXPOSE ${EXPOSED_PORT}
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
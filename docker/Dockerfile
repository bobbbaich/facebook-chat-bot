FROM openjdk:8
VOLUME /tmp
ARG ARTIFACT_NAME
ADD ${ARTIFACT_NAME}.jar /app.jar
ENV SPRING_PROFILES_ACTIVE docker
ENV DOCKER_HOST=unix:///private/var/tmp/docker.sock
RUN bash -c 'touch /app.jar'
EXPOSE ${EXPOSED_PORT}
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

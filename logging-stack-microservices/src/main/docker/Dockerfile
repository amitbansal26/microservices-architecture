FROM openjdk:8-jre-alpine

VOLUME /tmp
ADD logging-stack-microservices.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
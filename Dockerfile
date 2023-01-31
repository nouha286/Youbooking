FROM openjdk:11
ARG WAR_FILE=target/*.war
COPY target/demo-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java", "-jar","app.war"]


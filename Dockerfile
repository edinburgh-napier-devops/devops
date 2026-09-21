FROM amazoncorretto:26
COPY ./target/devops-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "devops-1.0-SNAPSHOT-jar-with-dependencies.jar"]
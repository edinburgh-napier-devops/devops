FROM amazoncorretto:26
COPY ./target/DevOps-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar","DevOps-0.1.0.2-jar-with-dependencies.jar"]
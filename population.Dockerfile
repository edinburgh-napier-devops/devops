FROM amazoncorretto:26
COPY population/target/population-0.1.0.12-jar-with-dependencies.jar /tmp/app.jar
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "app.jar"]
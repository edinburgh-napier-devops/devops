FROM amazoncorretto:26
COPY hr/target/hr-0.1.0.5-jar-with-dependencies.jar /tmp/app.jar
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "app.jar"]
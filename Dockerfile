FROM openjdk
COPY ./target/*.jar netWorkers.jar
EXPOSE 3333
ENTRYPOINT ["java","-jar", "/netWorkers.jar"]
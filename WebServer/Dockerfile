
FROM openjdk:11
COPY target/WebServer-0.0.1-SNAPSHOT.jar WebServer-0.0.1-SNAPSHOT.jar
CMD ["rabbit:5672","--","java","-jar","/WebServer-0.0.1-SNAPSHOT.jar"]



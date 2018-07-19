FROM openjdk:8-jdk-alpine
WORKDIR /home
COPY target/registry.jar registry.jar
CMD ["java", "-jar", "registry.jar"]
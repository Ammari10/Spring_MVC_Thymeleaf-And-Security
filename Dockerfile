FROM openjdk:17-jdk-alpine

EXPOSE 8084

ADD target/*.jar gestion-scolarite.jar


CMD ["java", "-jar", "/gestion-scolarite.jar"]
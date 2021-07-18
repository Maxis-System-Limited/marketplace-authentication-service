FROM openjdk:8-jre-alpine

EXPOSE 8080

WORKDIR /app

COPY target/marketplace-authentication-service-0.0.1-SNAPSHOT.jar .

COPY src/main/resources/keystore.jks .


COPY certstore-db.jks .
COPY keystore-db.jks .

ENTRYPOINT [ "java", "-jar", "marketplace-authentication-service-0.0.1-SNAPSHOT.jar" ]
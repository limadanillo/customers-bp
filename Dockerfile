FROM adoptopenjdk/openjdk11:latest

COPY build/libs /app/

WORKDIR /app

CMD java -Dspring.profiles.active=prod -jar /app/customers-bp-0.0.1-SNAPSHOT.jar

EXPOSE 9090
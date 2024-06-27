FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . /app
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar location-attitude-longitude.jar
ENTRYPOINT ["java", "-jar", "location-attitude-longitude.jar"]

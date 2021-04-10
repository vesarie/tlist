FROM maven:3.6-openjdk-8 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve dependency:resolve-plugins
COPY ./src ./src
RUN mvn package

FROM openjdk:8-jre-buster
WORKDIR /app
COPY --from=build /app/target/tlist.war .
COPY --from=build /app/target/dependency/webapp-runner.jar .
CMD java $JAVA_OPTS -jar ./webapp-runner.jar --port $PORT ./tlist.war

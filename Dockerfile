FROM maven:3.6-openjdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn package

FROM tomcat:9.0-jdk8-openjdk
COPY --from=build /app/target/tlist /usr/local/tomcat/webapps/ROOT
CMD sed -i \
        "s/port=\"[0-9]\+\" protocol=\"HTTP\/1.1\"/port=\"${PORT}\" protocol=\"HTTP\/1.1\"/" \
        /usr/local/tomcat/conf/server.xml && \
    catalina.sh run;

FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR application

COPY ./ ./
RUN mvn clean package

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

RUN java -Djarmode=layertools -jar application.jar extract

COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application.jar ./
ENTRYPOINT exec java -Xmx400m -Xms400m -Xss256k -jar /application/application.jar
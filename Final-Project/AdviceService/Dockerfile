FROM openjdk:21-jdk AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package


FROM openjdk:21-jdk
WORKDIR adviceservice
COPY --from=build target/*.jar adviceservice.jar
ENTRYPOINT ["java", "-jar", "adviceservice.jar"]
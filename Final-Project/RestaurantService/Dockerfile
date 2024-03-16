FROM openjdk:17-jdk AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package


FROM openjdk:17-jdk
WORKDIR restaurantservice
COPY --from=build target/*.jar restaurantservice.jar
ENTRYPOINT ["java", "-jar", "restaurantservice.jar"]
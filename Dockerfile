FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

WORKDIR /build

WORKDIR /build/user-transfer-service
COPY /pom.xml /build/user-transfer-service
COPY /src /build/user-transfer-service/src
RUN mvn -B -f pom.xml clean package -DskipTests -Dspring.profiles.active=dev

FROM eclipse-temurin:17-jre-alpine

COPY --from=build /build/user-transfer-service/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
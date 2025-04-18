FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -Dspring.profiles.active=test

FROM openjdk:17-jdk-slim
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/target/classes/*.key .
COPY --from=build /app/target/classes/*.pub .


ENTRYPOINT ["java","-jar","/app.jar"]

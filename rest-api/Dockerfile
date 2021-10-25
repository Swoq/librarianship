FROM amazoncorretto:11-alpine-jdk
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} rest-api.jar
ENTRYPOINT ["java","-jar","/rest-api.jar"]

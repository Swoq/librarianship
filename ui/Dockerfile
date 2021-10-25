FROM amazoncorretto:11-alpine-jdk
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ui.jar
ENTRYPOINT ["java","-jar","/ui.jar"]

FROM amazoncorretto:11-alpine-jdk
WORKDIR "/librarianship"
COPY ./application/target/*.jar ./app.jar
CMD ["java","-jar","./app.jar"]
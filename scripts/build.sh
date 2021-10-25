#!/bin/sh

mvn clean install -DskipTests
docker build -t swoqe/librarianship-ui ui/
docker build -t swoqe/librarianship-rest-api rest-api/

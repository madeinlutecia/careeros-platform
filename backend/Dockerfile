FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY careeros-shared careeros-shared
COPY careeros-auth careeros-auth
COPY careeros-profile careeros-profile
COPY careeros-linkedin careeros-linkedin
COPY careeros-resume careeros-resume
COPY careeros-jobs careeros-jobs
COPY careeros-recruiter careeros-recruiter
COPY careeros-ai careeros-ai
COPY careeros-statistics careeros-statistics
COPY careeros-notification careeros-notification
COPY careeros-diary careeros-diary
COPY careeros-achievements careeros-achievements
COPY careeros-skills careeros-skills
COPY careeros-app careeros-app
RUN apk add --no-cache maven && mvn -q -pl careeros-app -am package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN apk add --no-cache curl
COPY --from=build /app/careeros-app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

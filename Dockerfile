FROM openjdk:17.0-jdk
COPY "./target/Neoristest-pichincha-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
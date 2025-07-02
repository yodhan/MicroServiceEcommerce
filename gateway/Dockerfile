# ------------ Stage 1: Build ------------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests
# or use: RUN mvn clean package -DskipTests

# ------------ Stage 2: Runtime ------------
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/api-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]

# ---------- Stage 1: Build ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build

# Copy only pom.xml first so Docker can cache dependency downloads
COPY 01-source-code/demo/pom.xml .
RUN mvn -B dependency:go-offline

# Now copy the actual source code and build the jar
COPY 01-source-code/demo/src ./src
RUN mvn -B clean package -DskipTests

# ---------- Stage 2: Run ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Run as a non-root user for better security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=build /build/target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

HEALTHCHECK --interval=30s --timeout=3s CMD wget -q --spider http://localhost:8081/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
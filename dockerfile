# Étape 1 : build avec Maven
FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /app

# Copier les fichiers Maven
COPY pom.xml .
COPY src ./src

# Build sans les tests
RUN mvn clean package -DskipTests

# Étape 2 : image runtime légère
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copier le jar généré
COPY --from=build /app/target/*.jar app.jar

# Port exposé par Spring Boot
EXPOSE 8080

# Lancement de l'application
ENTRYPOINT ["java", "-jar", "app.jar"]

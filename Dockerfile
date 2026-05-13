# Image de base : Java 21 version légère
FROM eclipse-temurin:21-jdk-jammy

# Dossier de travail dans le conteneur
WORKDIR /app

# Copie le jar compilé dans le conteneur
COPY target/*.jar app.jar

# Commande lancée au démarrage du conteneur
ENTRYPOINT ["java", "-jar", "app.jar"]
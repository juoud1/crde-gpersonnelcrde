# Gestion du personnel de la CRDE

Application web de gestion du personnel de la CRDE en République centrafricaine.

## Fonctionnalités

- Gestion des employés, de leurs adresses et de leurs affectations
- Suivi des congés et des missions
- Planification des visites médicales
- Historique des affectations, congés et missions
- Gestion des utilisateurs et authentification

## Technologies

- Java 21
- Spring Boot 3.5.7 et Maven
- Spring MVC, Thymeleaf, Spring Data JPA et Spring Security
- H2 comme base de données par défaut

Le pilote PostgreSQL est également présent dans le projet. La configuration par défaut utilise toutefois H2.

## Prérequis

- JDK 21

Le wrapper Maven est inclus dans le dépôt ; une installation séparée de Maven n'est pas nécessaire.

## Démarrage

Depuis la racine du projet, lancer l'application avec le wrapper Maven :

```bash
bash ./mvnw spring-boot:run
```

Sous Windows, utiliser `mvnw.cmd spring-boot:run`.

L'application est accessible à l'adresse suivante :

```text
http://localhost:8181/crde/gpsnel/accueil.html
```

La configuration de la base de données et du serveur se trouve dans `src/main/resources/application.yml`. Par défaut, H2 utilise une base fichier dans `~/crde/crdedb` et les scripts d'initialisation sont dans `src/main/resources/db/sql/`.

## Tests

Exécuter les tests avec :

```bash
bash ./mvnw test
```

Sous Windows :

```bat
mvnw.cmd test
```

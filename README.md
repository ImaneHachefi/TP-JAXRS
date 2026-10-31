# TP JAX-RS : API REST de gestion de comptes bancaires

API REST développée avec **Spring Boot**, **Jersey (JAX-RS)** et **Spring Data JPA**. Elle expose un CRUD sur des comptes bancaires, en JSON et en XML, avec une base **H2** lancée dans un conteneur **Docker**.

## Technologies

- Java 17
- Spring Boot 3.5
- Jersey (JAX-RS) + JAXB (XML)
- Spring Data JPA / Hibernate
- H2 (serveur TCP sous Docker)
- Lombok
- Postman (tests)

## Structure du projet

```
src/main/java/ma/ws/tpjaxrs
├── config          → MyConfiguration, AccountRestJaxAPI
├── entities        → Account
├── enumeration     → AccountType (Courant, Epargne)
├── repositories    → AccountRepository
└── TpJaxrsApplication.java
```

## Prérequis

- JDK 17
- Maven (ou `mvnw` fourni)
- Docker Desktop
- Postman

## Lancement

### 1. Démarrer la base de données

```bash
docker compose up -d
```

La base H2 est accessible sur le port `9092` (console web sur `http://localhost:81`).

<img width="1909" height="594" alt="Capture d&#39;écran 2026-10-29 123110" src="https://github.com/user-attachments/assets/1de82554-67e6-4195-927e-0e08ed014eb7" />
<img width="450" height="316" alt="Capture d&#39;écran 2026-10-29 123348" src="https://github.com/user-attachments/assets/f122ab60-7986-4655-b231-c4cc3d623168" />

### 2. Démarrer l'application

```bash
mvnw.cmd clean spring-boot:run
```

L'application démarre sur le port `8082`. Au démarrage, 3 comptes sont insérés automatiquement.

<img width="1810" height="758" alt="Capture d&#39;écran 2026-10-29 125124" src="https://github.com/user-attachments/assets/da4576a6-5ab3-43c7-8950-6fbe483b5c20" />
<img width="1813" height="810" alt="Capture d&#39;écran 2026-10-29 125146" src="https://github.com/user-attachments/assets/b17326f1-6d74-4eee-a618-e670a0d0a602" />
<img width="449" height="458" alt="Capture d&#39;écran 2026-10-29 125220" src="https://github.com/user-attachments/assets/f4c6a96c-e6e4-4404-aa12-99d56a894ff0" />
<img width="1094" height="709" alt="Capture d&#39;écran 2026-10-29 125243" src="https://github.com/user-attachments/assets/0e321a54-8a2c-4c88-b2d7-1d3621b88b90" />

## Configuration

`application.properties` :

```properties
spring.application.name=TP-jaxrs
server.port=8082

spring.datasource.url=jdbc:h2:tcp://localhost:9092/dbbanque
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jersey.application-path=/api
```

## Endpoints

Base URL : `http://localhost:8082/api/banque`

| Action | Méthode | URL |
|---|---|---|
| Lister les comptes | GET | `/comptes` |
| Récupérer un compte | GET | `/comptes/{id}` |
| Créer un compte | POST | `/comptes` |
| Modifier un compte | PUT | `/comptes/{id}` |
| Supprimer un compte | DELETE | `/comptes/{id}` |

Chaque endpoint accepte et produit du **JSON** et du **XML** (header `Accept` / `Content-Type`).

## Tests avec Postman

### 1. GET : liste des comptes (JSON)

<img width="1379" height="885" alt="Capture d&#39;écran 2026-10-31 020520" src="https://github.com/user-attachments/assets/7feea664-8a5c-47b1-a953-9ebdd90b1b3a" />

### 2. GET : liste des comptes (XML)

<img width="1377" height="861" alt="Capture d&#39;écran 2026-10-31 021201" src="https://github.com/user-attachments/assets/4011327b-81b5-4507-8c71-0fc9f67d1d06" />

### 3. GET : un compte par id

<img width="1373" height="753" alt="Capture d&#39;écran 2026-10-31 020552" src="https://github.com/user-attachments/assets/9c51e061-f316-4c00-8edf-b456e3bccf70" />

### 4. POST : création d'un compte

`POST http://localhost:8082/api/banque/comptes` avec `Content-Type: application/json`

```json
{
  "solde": 5000.0,
  "dateCreation": "2026-10-29",
  "type": "Epargne"
}
```
<img width="1376" height="725" alt="Capture d&#39;écran 2026-10-31 020655" src="https://github.com/user-attachments/assets/9d806777-898e-4ba8-b856-8c7835262049" />

Version XML (`Content-Type: application/xml`) :

```xml
<account>
  <solde>5000.0</solde>
  <dateCreation>2026-10-29T12:00:00+01:00</dateCreation>
  <type>Epargne</type>
</account>
```
<img width="1374" height="750" alt="Capture d&#39;écran 2026-10-31 020731" src="https://github.com/user-attachments/assets/c7750820-0a9a-44c9-87af-77425081f7f8" />

### 5. PUT : modification d'un compte

`PUT http://localhost:8082/api/banque/comptes/1` avec `Content-Type: application/json`

```json
{
  "solde": 7500.0,
  "dateCreation": "2026-10-29",
  "type": "Courant"
}
```
<img width="1384" height="768" alt="Capture d&#39;écran 2026-10-31 020928" src="https://github.com/user-attachments/assets/bdb68c4c-3a81-4aa8-afdf-82e5e5ada1c9" />

Version XML (`Content-Type: application/xml`) :

```xml
<account>
  <solde>7500.0</solde>
  <dateCreation>2026-10-29T12:00:00+01:00</dateCreation>
  <type>Courant</type>
</account>
```
<img width="1370" height="739" alt="Capture d&#39;écran 2026-10-31 021000" src="https://github.com/user-attachments/assets/dcb66271-72ba-4f55-a341-74bed5e39d7f" />

### 6. DELETE : suppression d'un compte

<img width="1377" height="675" alt="Capture d&#39;écran 2026-10-31 021109" src="https://github.com/user-attachments/assets/180e3fce-4c2a-4a95-a892-f1d702af9b3b" />

### 7. Vérification finale

`GET http://localhost:8082/api/banque/comptes`

<img width="1377" height="861" alt="Capture d&#39;écran 2026-10-31 021201" src="https://github.com/user-attachments/assets/77c95aa6-f0b6-44b2-b949-63ce1311407e" />

## Auteur

Imane Hachefi, EMSI Marrakech

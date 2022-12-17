# README #

### Fleet Manager API ###

Demo API for Clever Shuttle

* Version 0.0.1

### Set Up ###
* This project uses [Java 17] and [Spring boot] with a [Postgres Database]

### Running the project locally ###
* Run locally the project into your IDE with the lombok plugin

### Build and Run ###
Go to the project folder and run:
* ./gradlew clean build -x test
* docker build . -t fleet-manager:latest
* docker-compose up -d

### Postrgres+Docker string connection: ###
##### in case if you want to connect to the database console #####
* psql -U fleet-manager-usr -d fleet-manager-db

[//]: # (* more instructions to come TODO )

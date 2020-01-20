# Semesterarbeit
Kalkulation für die Berechtigung eines SBB-Generalabonnements für Swisscom-Mitarbeiter
## Einleitung
In dieser Applikation kann ein Swisscom Mitarbeiter die Kosten seiner Geschäftsreisen, 
abgehend von Chur, erfassen und die Kostenrelation für ein General-Abonnement im 
Verhältnis zu einzeln gekauften Reisen nachweisen. 
## Vorbereitung und Applikationsstart
Folgende Schritte müssen zur Vorbereitung und zum Starten der Applikation durchgeführt werden:
### Erstellung der benötigten lokalen Datenbank
Verwendung einer MariaDB oder MySQL Datenbank.
Folgende Schritte müssen als root (oder als user mit entsprechenden Rechten) ausgeführt werden:
1. Erstellung einer Datenbank mit DB Name: calculator
```
create database calculator;
```
2. Erstellen der Tabelle und inkl. vorabgefüllter Daten in die DB mit dem File calculator.sql (File abgelegt in Verzeichnis src/Main/GA_Trial/calculator.sql)
```
source <Pfad zur Datei>
```
3. User für Datenbankzugriff aus der Applikation erstellen:
```
CREATE USER 'java'@'localhost' IDENTIFIED BY 'java';
```
```
GRANT ALL PRIVILEGES ON calculator . * TO 'java'@'localhost';
```
### Projekt in intelliJ öffnen
1.	Add as Maven Project wenn danach gefragt wird
2.	Project Structure öffnen und folgende Einstellungen vornehmen:
- Tab Project: 
  - SDK: Java Version 1.8
  - Project language level: SDK default
  - Project compiler output: gewünschten Ordner wählen/erstellen
- Tab Modules: 
  - im Ordner src den Ordner Main als Sources markieren
  - im Ordner src den Ordner Test als Tests markieren
### Starten der Applikation
Main Methoden in der Klasse Application starten.

Examen Décembre 2013 – Janvier 2014
===================================

Auteurs
-------
Benjamin BONNETTO
Jean-Michel POTHELUNE

Installation (Eclipse)
----------------------
### Clonage du repository git
https://github.com/bennetto/

### Importation dans Eclipse
Importez le projet maven (File -> Import -> Maven -> Existing Maven Projects)
Cliquez sur [Browse...] et séléctionnez le dossier dans lequel vous venez de cloner le repository.
Cliquez sur [Finish]

### Lancement de la base de données sous eclipse. (Nouvelle BDD)
Créez une nouvelle configuration (Run -> Run configuration...)
Dans le volet de gauche, effectuez un clic droit sur Java Application -> New.
Selectionnez la configuration ainsi créee
Dans le volet de droite, remplissez le champ Name par 'LaunchDB'
Sous l'onglet Main:
* Selectionnez le projet importé (Browse...)
* Remplissez le champ Main class par 'org.hsqldb.server.Server'
Sous l'onglet Arguments:
Remplissez le champ Program arguments avec: '--database.0 file:musicdb --dbname.0 musicdb'

Pour lancer la base de donnée, executez la configuration LaunchDB via la flèche verte située dans la barre d'action.
**La base de donnée doit être lancée pour que le programme fonctionne**
 
### Lancement du gestionnaire de bases de données sous eclipse. (Nouvelle BDD, faculatatif)
Créez une nouvelle configuration (Run -> Run configuration...)
Dans le volet de gauche, effectuez un clic droit sur Java Application -> New.
Selectionnez la configuration ainsi créee
Dans le volet de droite, remplissez le champ Name par 'LaunchDBManager'
Sous l'onglet Main:
* Selectionnez le projet importé (Browse...)
* Remplissez le champ Main class par 'org.hsqldb.util.DatabaseManagerSwing'

Pour lancer le gestionnaire, executez la configuration LaunchDBManager via la flèche verte située dans la barre d'action.

Une fois le gestionnaire lancé, entrez les paramètres suivant pour vous connecter a la base de donnée:
* Setting Name: HSQL MusicDB Engine
* Type: HSQL Database Engine In-Memory
* Driver: org.hsqldb.jdbc.JDBCDriver
* URL: jdbc:hsqldb:hsql://localhost/musicdb
* User:  SA
* Password: (laisser vide)
 
### Initialisation de la base de données. (Nouvelle BDD)
Une fois la base de donnée lancée, nous pouvons créer les tables.
Dans le gestionnaire de base de données, copier le contenu du fichier 'createTableMusicDB.sql' dans le champ en haut a droite, puis cliquez sur Execute SQL.

### Configuration d'une base de données existante.
Dans src/main/resources, ouvrez le fichier hibernate.cfg.xml avec un éditeur de texte.
Si vous utilisez une base de donnée HSQLDB, vous n'avez qu'a personaliser les champs 'hibernate.connection.url', 'hibernate.connection.username', 'hibernate.connection.password' avec vos informations
Si vous utilisez un autre type de base de données, changez le dialect, et importez votre propre librairie dans le pom.xml (utilisateur avancés)


### Configuration des fichiers de mise a jour, et des dossiers de sortie
Dans src/main/resources, ouvrez le fichier confCSV.properties avec un éditeur de texte.
dans inputPath, renseignez le chemin du dossier où se situent les fichiers de mise a jour
dans successOutputPath, renseignez le chemin du dossier où seront déplacés les fichiers correctements executés
dans failOutputPath, renseignez le chemin du dossier où seront déplacés les fichiers qui n'auront pas correctement été éxécutés.

### Lancement de l'application
Placez vous dans la classe Main.java, et appuyer sur le bouton Run (Fleche verte).



Descriptif des packages
-----------------------
### org.jacademie.examenDecembre.bo (Buiseness object)
Contient les classes métiers.

### org.jacademie.examenDecembre.dao
Contient les interfaces des Data Access Objects

### org.jacademie.examenDecembre.dao.impl
Implementation des DAO (Hibernate)

### org.jacademie.examenDecembre.dao.impl.mapping
Fichiers de mapping Objets métiers - DB (Hibernate)

### org.jacademie.examenDecembre.services
Contient les interfaces des differents services:
*FileMoverService : * Permet le déplacement d'un fichier.
*MusicDataExtractorService : * Permet l'extraction d'informations (MusicData) sur les musiques a partir d'un lecteur (Reader).
*MusicDataUpdaterService : * Permet la mise a jour des objets métiers à partir d'informations (MusicData) extraites ou fournies.
*MusicData : * Permet le stockage temporaire d'informations sur les musiques.
*MusicDataException : * Soulevée par MusicDataExtractorService et MusicDataUpdaterService lorsque les informations lues ou enregistrées sont éronnées.

### org.jacademie.examenDecembre.services.impl
Implementation des services:

### org.jacademie.examenDecembre.utils
Rassemble diférents outils utilisée par les services.
*PersistenceManager : * Interface les outils traditionnels de gestion de base SQL.
*HibernateManage : * Implémentation de l'interface PersitenceManager pour hibernate.

### org.jacademie.examenDecembre.tests
Classes de test des Services et DAO de l'application.
*DAOsTest : * Test la bonne sauvegarde et récupération de tous les objets.
*MusicDataUpdaterService : * Test unitaire (rudimentaire) de la classe MusicDataUpdater


### Ressources:
*confCSV.properties : *
*hibernate.cfg.xml : * Configuration de la base de données et du mapping Hibernate
*log4j.properties : * Configuration de la journalisation.


Librairies utilisées
--------------------
*log4j : * Pour la journalisation.
*org.hsqldb : * Gestion de base de données.
*org.hibernate : * Gestion de la persistence (Connection a la base de donnée).
*net.sf.opencsv : * Lecteur et parseur de fichiers CSV.
*junit (4.11) : * Tests unitaires.
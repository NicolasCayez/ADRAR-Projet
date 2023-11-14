
CREATE DATABASE IF NOT EXISTS MyComics;
-- ---------------------------------------------------------
-- Utilisation de la BDD
-- ---------------------------------------------------------
USE MyComics;
-- ---------------------------------------------------------
-- Création des tables
-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS profils(
	profil_id INT AUTO_INCREMENT NOT NULL,
	profil_nom VARCHAR(50) NOT NULL,
	PRIMARY KEY(profil_id)
) Engine=InnoDB;

CREATE TABLE IF NOT EXISTS editeurs(
	editeur_id INT AUTO_INCREMENT NOT NULL,
	editeur_nom VARCHAR(50) NOT NULL,
	PRIMARY KEY(editeur_id)
) Engine=InnoDB;

CREATE TABLE IF NOT EXISTS series(
	serie_id INT AUTO_INCREMENT NOT NULL,
	serie_nom VARCHAR(50) NOT NULL,
	PRIMARY KEY(serie_id)
) Engine=InnoDB;

CREATE TABLE IF NOT EXISTS auteurs(
	auteur_id INT AUTO_INCREMENT NOT NULL,
	auteur_nom VARCHAR(50),
	auteur_prenom VARCHAR(50),
	auteur_pseudo VARCHAR(50) NOT NULL,
	auteur_photo VARCHAR(100),
	PRIMARY KEY(auteur_id)
) Engine=InnoDB;

CREATE TABLE IF NOT EXISTS tomes(
	tome_id INT AUTO_INCREMENT NOT NULL,
    tome_numero INT NOT NULL,
	tome_titre VARCHAR(50) NOT NULL,
	tome_prix_achat DECIMAL(5,2),
	tome_valeur_connue DECIMAL(5,2),
    tome_date_edition DATE,
    tome_isbn VARCHAR(13) NOT NULL,
	tome_image VARCHAR(100),
	tome_dedicace BOOLEAN,
	tome_edition_speciale BOOLEAN,
	tome_edition_speciale_libelle VARCHAR(50) ,
	serie_id INT,
	PRIMARY KEY(tome_id),
	FOREIGN KEY(serie_id) REFERENCES series(serie_id)
) Engine=InnoDB;

CREATE TABLE IF NOT EXISTS detenir(
	tome_id INT,
	profil_id INT,
	PRIMARY KEY(tome_id, profil_id),
	FOREIGN KEY(tome_id) REFERENCES tomes(tome_id),
    FOREIGN KEY(profil_id) REFERENCES profils(profil_id)
) Engine=InnoDB;

CREATE TABLE IF NOT EXISTS editer(
	tome_id INT,
	editeur_id INT,
	PRIMARY KEY(tome_id, editeur_id),
	FOREIGN KEY(tome_id) REFERENCES tomes(tome_id),
    FOREIGN KEY(editeur_id) REFERENCES editeurs(editeur_id)
) Engine=InnoDB;

CREATE TABLE IF NOT EXISTS ecrire(
	tome_id INT,
	auteur_id INT,
	PRIMARY KEY(tome_id, auteur_id),
	FOREIGN KEY(tome_id) REFERENCES tomes(tome_id),
    FOREIGN KEY(auteur_id) REFERENCES auteurs(auteur_id)
) Engine=InnoDB;
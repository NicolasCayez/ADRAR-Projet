-- ---------------------------------------------------------
-- Utilisation de la BDD
-- ---------------------------------------------------------
USE MyComics;

-- ---------------------------------------------------------
-- Création des requêtes
-- ---------------------------------------------------------



-- INSERTIONS ----------------------------------------------
-- Insertion d'un profil
INSERT INTO profils (profil_nom)
VALUES
    ('Philibert') -- collection_id 2
;

-- Insertion d'un editeur
INSERT INTO editeurs (editeur_nom)
VALUES
    ('Le Lombard') -- editeur_id 3
;

-- Insertion d'une serie
INSERT INTO series (serie_nom)
VALUES
    ('Clifton')-- serie_id 4
;

-- Insertion d'un auteur (ici deux pour compléter le tome)
INSERT INTO auteurs (auteur_nom, auteur_prenom, auteur_pseudo, auteur_photo)
VALUES
    ('', '', 'Turk', ''), -- auteur_id 6
	('Bob', '', 'De Groot', '') -- auteur_id 7
;

-- Insertion d'un tome
INSERT INTO tomes (tome_numero, tome_titre, tome_prix_achat, tome_valeur_connue, tome_date_edition, tome_isbn, tome_image, tome_dedicace, tome_edition_speciale, tome_edition_speciale_libelle, serie_id)
VALUES
    (3, '7 jours pour mourir', 8.50, 0, 199404, '2803603160', './images/test.jpg', 0, 0, '', 4)-- tome_id 18
;

-- ASSOCIATIONS ----------------------------------------------
-- lier tome et profil
INSERT INTO detenir (tome_id, profil_id)
VALUES
    (18, 1)
;

-- lier tome et editeur
INSERT INTO editer (tome_id, editeur_id)
VALUES
    (18, 3)
;

-- lier tome et auteur (ici les deux auteurs)
INSERT INTO ecrire (tome_id, auteur_id)
VALUES
    (18, 6),
    (18, 7)
;

-- MODIFICATIONS SIMPLES--------------------------------------
-- Modification d'un profil : "Philibert" renommée en "Pacôme Hégésippe Adélard Ladislas De Champignac"
UPDATE profils
SET profil_nom = 'Pacôme Hégésippe Adélard Ladislas De Champignac'
WHERE profil_id = 2;

-- Modification d'un editeur : "Soleil" renommé en "Soleil Editions"
UPDATE editeurs
SET editeur_nom = 'Soleil Editions'
WHERE editeur_id = 1;

-- Modification d'une serie : "Lanfeust" renommé en "Lanfeust de Troy"
UPDATE series
SET serie_nom = 'Lanfeust de Troy'
WHERE serie_id = 1;

-- Modification d'un auteur : "Soleil" renommé en "Soleil Editions"
UPDATE auteurs
SET auteur_nom = 'De Groot'
WHERE auteur_id = 7;

-- Modification d'un tome : ici changement du prix
UPDATE tomes
SET tome_prix_achat = 9.50
WHERE tome_id = 18;

-- MODIFICATIONS ASSOCIATIONS---------------------------------
-- Modification de la série d'un tome

-- Modification de l'auteur d'un tome

-- Modification de l'éditeur d'un tome

-- Modification de la collection d'un tome


-- SUPPRESSIONS --------------------------------------------------
-- Suppression d'un profil

-- Suppression d'un editeur

-- Suppression d'une serie

-- Suppression d'un auteur

-- Suppression d'un tome

-- Suppression de l'association du profil et d'un tome

-- Suppression de la série d'un tome

-- Suppression de l'auteur d'un tome

-- Suppression de l'éditeur d'un tome


-- AFFICHAGE SIMPLE ----------------------------------------------
-- Affichage d'un profil

-- Affichage d'un editeur

-- Affichage d'une serie

-- Affichage d'un auteur

-- Affichage d'un tome


-- AFFICHAGE D'ASSOCIATIONS TOMES --------------------------------
-- A CHAQUE FOIS AVEC LE PROFIL COMME REFERENCE
-- Affichage des tomes de la collection

-- Affichage des tomes d'un éditeur

-- Affichage des tomes d'une serie

-- Affichage des tomes d'un auteur


-- AFFICHAGE D'ASSOCIATIONS EDITEURS --------------------------------
-- Affichage de l'éditeur d'un tome

-- Affichage des editeurs d'une série

-- Affichage des editeurs d'un auteur


-- AFFICHAGE D'ASSOCIATIONS AUTEURS --------------------------------
-- Affichage des auteurs d'un tome

-- Affichage des auteurs d'une série

-- Affichage des auteurs d'un editeur


-- AFFICHAGE D'ASSOCIATIONS AUTEURS --------------------------------
-- Affichage de la série d'un tome

-- Affichage des séries d'un auteur

-- Affichage des séries d'un editeur
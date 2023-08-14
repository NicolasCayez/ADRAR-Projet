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
    (NULL, NULL, 'Turk', './images/test.jpg'), -- auteur_id 6
	('Bob', NULL, 'De Groot', './images/test.jpg') -- auteur_id 7
;

-- Insertion d'un tome
INSERT INTO tomes (tome_numero, tome_titre, tome_prix_achat, tome_valeur_connue, tome_date_edition, tome_isbn, tome_image, tome_dedicace, tome_edition_speciale, tome_edition_speciale_libelle, serie_id)
VALUES
    (3, '7 jours pour mourir', 8.50, NULL, 199404, '2803603160', './images/test.jpg', 0, 0, NULL, 1)-- tome_id 18
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
    (18, 5)
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

-- Modification d'un auteur
UPDATE auteurs
SET auteur_nom = 'De Groot', auteur_prenom = 'Bob'
WHERE auteur_id = 7;

-- Modification d'un tome : ici changement du prix
UPDATE tomes
SET tome_prix_achat = 9.50
WHERE tome_id = 18;

-- MODIFICATIONS ASSOCIATIONS---------------------------------
-- Modification de la série d'un tome
UPDATE tomes
SET serie_id = 4
WHERE tome_id = 18;

-- Modification de l'auteur d'un tome
UPDATE ecrire
SET auteur_id = 7
WHERE tome_id = 18 AND auteur_id = 5;
-- Modification de l'éditeur d'un tome
UPDATE editer
SET editeur_id = 3
WHERE tome_id = 18 AND editeur_id = 1;
-- Modification du profil d'un tome
UPDATE detenir
SET profil_id = 1
WHERE tome_id = 18 AND profil_id = 2;

-- SUPPRESSIONS --------------------------------------------------
-- Suppression d'un profil
DELETE FROM profils
WHERE profil_id = 2;
-- Suppression d'un editeur
DELETE FROM editeurs
WHERE editeur_id = 3; -- Contrainte à lever dans la table editer
-- Suppression d'une serie
DELETE FROM series
WHERE serie_id = 3; -- Contrainte à lever clé étrangère dans la table tome (suppression de la série d'un tome)
-- Suppression d'un auteur
DELETE FROM auteurs
WHERE auteur_id = 3; -- Contrainte à lever clé étrangère dans la table ecrire (suppression de l'auteur d'un tome)
-- Suppression d'un tome
DELETE FROM tomes
WHERE tome_id = 3; -- Contrainte à lever clé étrangère dans la table detenir (Suppression de l'association du profil et d'un tome)
-- Suppression de l'association du profil et d'un tome
DELETE FROM detenir
WHERE profil_id = 2 AND tome_id = 2;
-- Suppression de la série d'un tome
UPDATE tomes
SET serie_id = NULL
WHERE tome_id = 18;

-- Suppression de l'auteur d'un tome
DELETE FROM ecrire
WHERE tome_id = 4 AND auteur_id = 5;

-- Suppression de l'éditeur d'un tome
DELETE FROM editer
WHERE tome_id = 4 AND editeur_id = 5;

-- AFFICHAGE SIMPLE ----------------------------------------------
-- Affichage d'un profil
SELECT * FROM profils
WHERE profil_id = 1;

-- Affichage d'un editeur
SELECT * FROM editeurs
WHERE editeur_id = 1;

-- Affichage d'une serie
SELECT * FROM series
WHERE serie_id = 1;

-- Affichage d'un auteur
SELECT * FROM auteurs
WHERE auteur_id = 1;

-- Affichage d'un tome
SELECT * FROM tomes
WHERE tome_id = 1;

-- AFFICHAGE D'ASSOCIATIONS TOMES --------------------------------
-- A CHAQUE FOIS AVEC LE PROFIL COMME REFERENCE
-- Affichage des tomes de la collection
SELECT T.tome_id, T.tome_numero, T.tome_titre, T.tome_prix_achat, T.tome_valeur_connue, T.tome_date_edition, T.tome_isbn, T.tome_image, T.tome_dedicace, T.tome_edition_speciale, T.tome_edition_speciale_libelle, T.serie_id
FROM tomes AS T
RIGHT JOIN detenir AS D
ON T.tome_id = D.tome_id
WHERE D.profil_id = 1;

-- Affichage des tomes d'un éditeur
SELECT T.tome_id, T.tome_numero, T.tome_titre, T.tome_prix_achat, T.tome_valeur_connue, T.tome_date_edition, T.tome_isbn, T.tome_image, T.tome_dedicace, T.tome_edition_speciale, T.tome_edition_speciale_libelle, T.serie_id
FROM tomes AS T
RIGHT JOIN editer AS E
ON T.tome_id = E.tome_id
RIGHT JOIN detenir AS D
ON T.tome_id = D.tome_id
WHERE D.profil_id = 1 AND E.editeur_id = 2;

-- Affichage des tomes d'une serie
SELECT T.tome_id, T.tome_numero, T.tome_titre, T.tome_prix_achat, T.tome_valeur_connue, T.tome_date_edition, T.tome_isbn, T.tome_image, T.tome_dedicace, T.tome_edition_speciale, T.tome_edition_speciale_libelle, T.serie_id
FROM tomes AS T
RIGHT JOIN detenir AS D
ON T.tome_id = D.tome_id
WHERE D.profil_id = 1 AND serie_id = 3;

-- Affichage des tomes d'un auteur
SELECT T.tome_id, T.tome_numero, T.tome_titre, T.tome_prix_achat, T.tome_valeur_connue, T.tome_date_edition, T.tome_isbn, T.tome_image, T.tome_dedicace, T.tome_edition_speciale, T.tome_edition_speciale_libelle, T.serie_id
FROM tomes AS T
RIGHT JOIN ecrire AS E
ON T.tome_id = E.tome_id
RIGHT JOIN detenir AS D
ON T.tome_id = D.tome_id
WHERE D.profil_id = 1 AND E.auteur_id = 2;

-- AFFICHAGE D'ASSOCIATIONS EDITEURS --------------------------------
-- Affichage de l'éditeur d'un tome
SELECT E1.editeur_id, E1.editeur_nom
FROM editeurs AS E1
RIGHT JOIN editer AS E2
ON E1.editeur_id = E2.editeur_id
WHERE E2.tome_id = 1;

-- Affichage des editeurs d'une série
SELECT DISTINCT E1.editeur_id, E1.editeur_nom
FROM editeurs AS E1
RIGHT JOIN editer AS E2
ON E1.editeur_id = E2.editeur_id
RIGHT JOIN tomes AS T
ON E2.tome_id = T.tome_id
WHERE T.serie_id = 1;

-- Affichage des editeurs d'un auteur
SELECT DISTINCT E1.editeur_id, E1.editeur_nom
FROM editeurs AS E1
RIGHT JOIN editer AS E2
ON E1.editeur_id = E2.editeur_id
RIGHT JOIN tomes AS T
ON E2.tome_id = T.tome_id
RIGHT JOIN ecrire AS E3
ON T.tome_id = E3.tome_id
WHERE E3.auteur_id = 3;

-- AFFICHAGE D'ASSOCIATIONS AUTEURS --------------------------------
-- Affichage des auteurs d'un tome
SELECT A.auteur_id, A.auteur_nom, A.auteur_prenom, A.auteur_pseudo, A.auteur_photo
FROM auteurs AS A
RIGHT JOIN ecrire AS E
ON A.auteur_id = E.auteur_id
WHERE E.tome_id = 1;

-- Affichage des auteurs d'une série
SELECT DISTINCT A.auteur_id, A.auteur_nom, A.auteur_prenom, A.auteur_pseudo, A.auteur_photo
FROM auteurs AS A
RIGHT JOIN ecrire AS E
ON A.auteur_id = E.auteur_id
RIGHT JOIN tomes AS T
ON E.tome_id = T.tome_id
WHERE T.serie_id = 2;

-- Affichage des auteurs d'un editeur
SELECT DISTINCT A.auteur_id, A.auteur_nom, A.auteur_prenom, A.auteur_pseudo, A.auteur_photo
FROM auteurs AS A
RIGHT JOIN ecrire AS E1
ON A.auteur_id = E1.auteur_id
RIGHT JOIN tomes AS T
ON E1.tome_id = T.tome_id
RIGHT JOIN editer AS E2
ON T.tome_id = E2.tome_id
WHERE E2.editeur_id = 3;


-- AFFICHAGE D'ASSOCIATIONS SERIES --------------------------------
-- Affichage de la série d'un tome
SELECT S.serie_id, s.serie_nom
FROM series AS S
RIGHT JOIN tomes AS T
ON S.serie_id = T.serie_id
WHERE T.tome_id = 12;

-- Affichage des séries d'un auteur
SELECT DISTINCT S.serie_id, s.serie_nom
FROM series AS S
RIGHT JOIN tomes AS T
ON S.serie_id = T.serie_id
RIGHT JOIN ecrire AS E
ON E.tome_id = T.tome_id
WHERE E.auteur_id = 1;

-- Affichage des séries d'un editeur
SELECT DISTINCT S.serie_id, s.serie_nom
FROM series AS S
RIGHT JOIN tomes AS T
ON S.serie_id = T.serie_id
RIGHT JOIN editer AS E
ON E.tome_id = T.tome_id
WHERE E.editeur_id = 1;

-- AFFICHAGE DES TOMES A COMPLETER ------------------------------------
-- Tomes sans profil associé
SELECT *
FROM tomes AS T
LEFT JOIN detenir AS D
ON T.tome_id = D.tome_id
WHERE T.tome_id NOT IN (
	SELECT detenir.tome_id FROM detenir
);

-- Tomes sans auteur associé
SELECT *
FROM tomes AS T
LEFT JOIN ecrire AS E
ON T.tome_id = E.tome_id
WHERE T.tome_id NOT IN (
	SELECT ecrire.tome_id FROM ecrire
);

-- Tomes sans éditeur associé
SELECT *
FROM tomes AS T
LEFT JOIN editer AS E
ON T.tome_id = E.tome_id
WHERE T.tome_id NOT IN (
	SELECT editer.tome_id FROM editer
);


-- AFFICHAGE D'ASSOCIATIONS MANQUANTES --------------------------------
-- Serie orpheline
SELECT *
FROM series AS S
LEFT JOIN tomes AS T
ON S.serie_id = T.serie_id
WHERE S.serie_id NOT IN (
	SELECT tomes.serie_id FROM tomes
);

-- Auteur orphelin
SELECT *
FROM auteurs AS A
LEFT JOIN ecrire AS E
ON A.auteur_id = E.auteur_id
WHERE A.auteur_id NOT IN (
	SELECT ecrire.auteur_id FROM ecrire
);

-- Editeur Orphelin
SELECT *
FROM editeurs AS E1
LEFT JOIN editer AS E2
ON E1.editeur_id = E2.editeur_id
WHERE E1.editeur_id NOT IN (
	SELECT editer.editeur_id FROM editer
);
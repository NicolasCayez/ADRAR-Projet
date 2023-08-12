-- ---------------------------------------------------------
-- Utilisation de la BDD
-- ---------------------------------------------------------
USE MyComics;

-- ---------------------------------------------------------
-- Création des tables
-- ---------------------------------------------------------

-- Table collections
INSERT INTO profils (profil_nom)
VALUES
	-- ('profil_nom');
    ('Nicolas') -- collection_id 1
;

-- Table editeurs
INSERT INTO editeurs (editeur_nom)
VALUES
	-- ('editeur_nom');
    ('Soleil'), -- editeur_id 1
    ('Delcourt') -- editeur_id 2
;

-- Table auteurs
INSERT INTO auteurs (auteur_nom, auteur_prenom, auteur_pseudo, auteur_photo)
VALUES
    -- ('auteur_nom', 'auteur_prenom', 'auteur_pseudo', 'auteur_photo');
    ('', '', 'Arleston', './images/test.jpg'), -- auteur_id 1
	('', '', 'Tarquin', './images/test.jpg'),-- auteur_id 2
    ('Morvan', 'Jean David', 'Morvan', './images/test.jpg'),-- auteur_id 3
    ('Buchet', 'Philippe', 'Buchet', './images/test.jpg'),-- auteur_id 4
    ('', '', 'Mourier', './images/test.jpg')-- auteur_id 5
;


-- Table series
INSERT INTO series (serie_nom)
VALUES
	-- ('serie_nom');
    ('Lanfeust'),-- serie_id 1
    ('Sillage'),-- serie_id 2
    ('Trolls de Troy')-- serie_id 3
;

-- Table tomes -- Warning lors de l'écriture car les dates sont saisies en YYYYMM et non pas YYYYMMDD
INSERT INTO tomes (tome_numero, tome_titre, tome_prix_achat, tome_valeur_connue, tome_date_edition, tome_isbn, tome_image, tome_dedicace, tome_edition_speciale, tome_edition_speciale_libelle, serie_id)
VALUES
	-- (tome_numero, 'tome_titre', tome_prix_achat, tome_valeur_connue, tome_date_edition, 'tome_isbn', 'tome_image', tome_dedicace, tome_edition_speciale , 'tome_edition_speciale_libelle' , serie_id);
    (1, 'L\'ivoire du Magohamoth', 8.50, 0, 199610, '2877642577', './images/test.jpg', 0, 0, '', 1),-- tome_id 1
    (2, 'thanos l\'incongru', 8.50, 0, 199610, '2877673069', './images/test.jpg', 0, 0, '', 1),-- tome_id 2
    (3, 'Castel Or-Azur', 8.50, 0, 199610, '2877643948', './images/test.jpg', 0, 0, '', 1),-- tome_id 3
    (4, 'Le paladin d\'Eckmül', 8.50, 0, 199611, '2877645665', './images/test.jpg', 0, 0, '', 1),-- tome_id 4
    (5, 'Le frisson de l\'haruspice', 8.50, 0, 199710, '2877646467', './images/test.jpg', 0, 0, '', 1),-- tome_id 5
    (6, 'Cixi Impératrice', 8.50, 0, 199810, '2877647951', './images/test.jpg', 0, 0, '', 1),-- tome_id 6
    (7, 'Les pétaures se cachent pour mourir', 8.50, 0, 199910, '2877649237', './images/test.jpg', 0, 0, '', 1),-- tome_id 7
	(7, 'Les pétaures se cachent pour mourir', 8.50, 60.00, 199912, '287764989X', './images/test.jpg', '0', '1', 'Edition du 31 décembre 1999 Bronze', '1'),-- tome_id 8
	(8, 'La bête fabuleuse', 8.50, 0, 200012, '2845650337', './images/test.jpg', 0, 0, '', 1),-- tome_id 9
    (1, 'A feu et à cendres', 10.50, 0, 199802, '2840551772', './images/test.jpg', 0, 0, '', 2),-- tome_id 10
    (2, 'Collection privée', 10.50, 0, 199905, '2840552604', './images/test.jpg', 0, 0, '', 2),-- tome_id 11
    (3, 'Engrenages', 10.50, 0, 200008, '2840554488', './images/test.jpg', 0, 0, '', 2),-- tome_id 12
    (4, 'Le signe des démons', 10.50, 0, 200109, '2840556707', './images/test.jpg', 0, 0, '', 2),-- tome_id 13
    (1, 'Histoires trolles', 8.50, 0, 199706, '2877645916', './images/test.jpg', 0, 0, '', 3),-- tome_id 14
    (2, 'Le scalp du vénérable', 8.50, 0, 199806, '2877647129', './images/test.jpg', 0, 0, '', 3),-- tome_id 15
    (3, 'Comme un vol de pétaures', 8.50, 0, 199906, '2877648494', './images/test.jpg', 0, 0, '', 3),-- tome_id 16
    (4, 'Le feu occulte', 8.50, 0, 200006, '2845650000', './images/test.jpg', 0, 0, '', 3)-- tome_id 17
;

-- Table contenir pour lier profil et tomes. Ici un seul profil
INSERT INTO detenir (tome_id, profil_id)
VALUES
	-- (tome_id, profil_id);
    (1, 1),-- Lanfeust
	(2, 1),-- Lanfeust
    (3, 1),-- Lanfeust
    (4, 1),-- Lanfeust
    (5, 1),-- Lanfeust
    (6, 1),-- Lanfeust
    (7, 1),-- Lanfeust
    (8, 1),-- Lanfeust
    (9, 1),-- Lanfeust
    (10, 1),-- Sillage
    (11, 1),-- Sillage
    (12, 1),-- Sillage
    (13, 1),-- Sillage
    (14, 1),-- Trolls
    (15, 1),-- Trolls
    (16, 1),-- Trolls
    (17, 1)-- Trolls
;

-- Table editer pour lier tomes et editeurs.
INSERT INTO editer (tome_id, editeur_id)
VALUES
	-- (tome_id, editeur_id);
    (1, 1),-- Lanfeust
	(2, 1),-- Lanfeust
    (3, 1),-- Lanfeust
    (4, 1),-- Lanfeust
    (5, 1),-- Lanfeust
    (6, 1),-- Lanfeust
    (7, 1),-- Lanfeust
    (8, 1),-- Lanfeust
    (9, 1),-- Lanfeust
    (10, 2),-- Sillage
    (11, 2),-- Sillage
    (12, 2),-- Sillage
    (13, 2),-- Sillage
    (14, 1),-- Trolls
    (15, 1),-- Trolls
    (16, 1),-- Trolls
    (17, 1)-- Trolls
;

-- Table ecrire pour lier tomes et auteurs.
INSERT INTO ecrire (tome_id, auteur_id)
VALUES
	-- (tome_id, auteur_id);
    (1, 1),-- Lanfeust
    (1, 2),-- Lanfeust
	(2, 1),-- Lanfeust
    (2, 2),-- Lanfeust
    (3, 1),-- Lanfeust
    (3, 2),-- Lanfeust
    (4, 1),-- Lanfeust
    (4, 2),-- Lanfeust
    (5, 1),-- Lanfeust
    (5, 2),-- Lanfeust
    (6, 1),-- Lanfeust
    (6, 2),-- Lanfeust
    (7, 1),-- Lanfeust
    (7, 2),-- Lanfeust
    (8, 1),-- Lanfeust
    (8, 2),-- Lanfeust
    (9, 1),-- Lanfeust
    (9, 2),-- Lanfeust
    (10, 3),-- Sillage
    (10, 4),-- Sillage
    (11, 3),-- Sillage
    (11, 4),-- Sillage
    (12, 3),-- Sillage
    (12, 4),-- Sillage
    (13, 3),-- Sillage
    (13, 4),-- Sillage
    (14, 1),-- Trolls
    (14, 5),-- Trolls
    (15, 1),-- Trolls
    (15, 5),-- Trolls
    (16, 1),-- Trolls
    (16, 5),-- Trolls
    (17, 1),-- Trolls
    (17, 5)-- Trolls
;
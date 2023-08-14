-- ---------------------------------------------------------
-- Utilisation de la BDD
-- ---------------------------------------------------------
USE MyComics;


-- ---------------------------------------------------------
-- RECHERCHE------------------------------------------------
-- ---------------------------------------------------------
-- Création de la variable pour la recherche----------------
SET @search = '%ou%';

-- Requête de recherche-------------------------------------
SELECT T.tome_id AS 'Id', T.tome_titre AS 'Nom'
FROM tomes AS T
WHERE
T.tome_id IN(
	SELECT T.tome_id
FROM tomes AS T
WHERE T.tome_titre like @search
OR T.tome_isbn like @search
OR T.tome_edition_speciale_libelle like @search
)
UNION
SELECT S.serie_id, S.serie_nom
FROM series AS S
HAVING
S.serie_nom like @search
UNION
SELECT E.editeur_id, E.editeur_nom
FROM editeurs AS E
HAVING
E.editeur_nom like @search
UNION
SELECT DISTINCT A.auteur_id, A.auteur_pseudo
FROM auteurs AS A
HAVING
A.auteur_pseudo like @search
;
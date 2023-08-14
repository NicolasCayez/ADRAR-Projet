-- ---------------------------------------------------------
-- Utilisation de la BDD
-- ---------------------------------------------------------
USE MyComics;


-- ---------------------------------------------------------
-- RECHERCHE------------------------------------------------
-- ---------------------------------------------------------
-- Création de la variable pour la recherche----------------
SET @search = '%an%';

-- Requête de recherche-------------------------------------
SELECT 'Tome' AS 'Type', T.tome_id AS 'Id', S.serie_nom AS 'Série', T.tome_numero AS 'N°', T.tome_titre AS 'Nom', T.tome_isbn
FROM tomes AS T
LEFT JOIN series AS S
ON T.serie_id = S.serie_id
WHERE
T.tome_id IN(
	SELECT T.tome_id
FROM tomes AS T
WHERE T.tome_titre like @search
OR T.tome_isbn like @search
OR T.tome_edition_speciale_libelle like @search
)
UNION
SELECT 'Série', S.serie_id, S.serie_nom, NULL, NULL, NULL
FROM series AS S
HAVING S.serie_nom like @search
UNION
SELECT 'Editeur', E.editeur_id, NULL, NULL, E.editeur_nom, NULL
FROM editeurs AS E
HAVING E.editeur_nom like @search
UNION
SELECT DISTINCT 'Auteur', A.auteur_id, NULL, NULL, A.auteur_pseudo, NULL
FROM auteurs AS A
HAVING A.auteur_pseudo like @search
ORDER BY TYPE
;
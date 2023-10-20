
//********************************************/
//* PAGE PRESENTATION                        */
//********************************************/
// récupération corps de la page sous le titre
const pageContent = document.getElementById("pageContent");

// Récupération des cadres
const cadreAndroidPresentation = document.getElementById("cadreAndroidPresentation");
const cadreAndroidPresentation2 = document.getElementById("cadreAndroidPresentation2");
// images des cadres
const imagecadreAndroidPresentation = document.createElement("img");
const imagecadreAndroidPresentation2 = document.createElement("img");
// Création du menu gauche
const menuLateral = document.querySelectorAll(".menuLateral");
const menuLateralPresentation = document.getElementById("menuLateralPresentation");
// Titre du menu
let titreMenuLateralPresentation = document.createElement("p");
titreMenuLateralPresentation.innerText = "Visite \nMyComics";
titreMenuLateralPresentation.classList.add("titreMenuLateral");
titreMenuLateralPresentation.classList.add("mt-2");
titreMenuLateralPresentation.classList.add("text-center");
titreMenuLateralPresentation.classList.add("text-wrap");
menuLateralPresentation.append(titreMenuLateralPresentation);
//Bouton Accueil
creerElementMenuLateral("btnLienAccueil","Accueil","./img/LogoAccueil.png", menuLateralPresentation);
//Bouton Collection 
creerElementMenuLateral("btnLienCollection","Collection", "./img/LogoCollection.png", menuLateralPresentation);
//Bouton rechercher
creerElementMenuLateral("btnLienRecherche","Recherche", "./img/LogoRecherche.png", menuLateralPresentation);
//Bouton Series
creerElementMenuLateral("btnLienSeries","Séries", "./img/LogoSeries.png", menuLateralPresentation);
//Bouton Tomes
creerElementMenuLateral("btnLienTomes","Tomes", "./img/LogoTomes.png", menuLateralPresentation);
//Bouton Auteurs
creerElementMenuLateral("btnLienAuteurs","Auteurs", "./img/LogoAuteurs.png", menuLateralPresentation);
//Bouton Editeurs
creerElementMenuLateral("btnLienEditeurs","Editeurs", "./img/LogoEditeur.png", menuLateralPresentation);
//Bouton Réglages
creerElementMenuLateral("btnLienReglages","Réglages", "./img/LogoReglages.png", menuLateralPresentation);
//Bouton A propos
creerElementMenuLateral("btnLienAPropos","A Propos", "./img/LogoAPropos.png", menuLateralPresentation);
// récupération textes page Présentation
const sousTitrePresentation = document.getElementById("sousTitre");
sousTitrePresentation.innerText = "Accueil";
const texteMaquetteAccueil = document.getElementById("texteMaquetteAccueil");
const texteMaquetteCollection = document.getElementById("texteMaquetteCollection");
const texteMaquetteRecherche = document.getElementById("texteMaquetteRecherche");
const texteMaquetteSeries = document.getElementById("texteMaquetteSeries");
const texteMaquetteTomes = document.getElementById("texteMaquetteTomes");
const texteMaquetteAuteurs = document.getElementById("texteMaquetteAuteurs");
const texteMaquetteEditeurs = document.getElementById("texteMaquetteEditeurs");
const texteMaquetteReglages = document.getElementById("texteMaquetteReglages");
const texteMaquetteApropos = document.getElementById("texteMaquetteAPropos");
// récupération boutons
let boutonMaquetteAccueil = document.getElementById("btnLienAccueil");
let boutonMaquetteCollection = document.getElementById("btnLienCollection");
let boutonMaquetteRecherche = document.getElementById("btnLienRecherche");
let boutonMaquetteSeries = document.getElementById("btnLienSeries");
let boutonMaquetteTomes = document.getElementById("btnLienTomes");
let boutonMaquetteAuteurs = document.getElementById("btnLienAuteurs");
let boutonMaquetteEditeurs = document.getElementById("btnLienEditeurs");
let boutonMaquetteReglages = document.getElementById("btnLienReglages");
let boutonMaquetteAPropos = document.getElementById("btnLienAPropos");

// let imagecadreAndroidPresentation = document.createElement("img");
imagecadreAndroidPresentation.src = "./img/TMaquetteAccueil.png";
imagecadreAndroidPresentation2.src = "./img/TMaquetteMenuHamburger.png";
cadreAndroidPresentation.appendChild(imagecadreAndroidPresentation);
cadreAndroidPresentation2.appendChild(imagecadreAndroidPresentation2);
cadreAndroidPresentation2.style.display = "flex";
cadreAndroidPresentation.style.marginLeft = "auto";
texteMaquetteAccueil.style.display = "flex";

//Gestion des éléments actifs selon le bouton menu gauche cliqué
// Clic bouton Menu Latéral Accueil
boutonMaquetteAccueil.addEventListener('click', () => {
    sousTitrePresentation.innerText = "Accueil";
    texteMaquetteAccueil.style.display = "flex";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteRecherche.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/TMaquetteAccueil.png";
    cadreAndroidPresentation2.style.display = "flex";
    cadreAndroidPresentation.style.marginLeft = "auto";
    imagecadreAndroidPresentation2.src = "./img/TMaquetteMenuHamburger.png";
    cadreAndroidPresentation2.style.height = "60vh";
});
// Clic bouton Menu Latéral Collection
boutonMaquetteCollection.addEventListener('click', () => {
    sousTitrePresentation.innerText = "Collection";
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "flex";
    texteMaquetteRecherche.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/TMaquetteCollection.png";
    cadreAndroidPresentation2.style.display = "none";
    cadreAndroidPresentation.style.marginLeft = "";
});
// Clic bouton Menu Latéral Recherche
boutonMaquetteRecherche.addEventListener('click', () => {
    sousTitrePresentation.innerText = "Recherche";
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteRecherche.style.display = "flex";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/TMaquetteRecherche.png";
    cadreAndroidPresentation2.style.display = "none";
    cadreAndroidPresentation.style.marginLeft = "";
});
// Clic bouton Menu Latéral Series
boutonMaquetteSeries.addEventListener('click', () => {
    sousTitrePresentation.innerText = "Séries";
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteRecherche.style.display = "none";
    texteMaquetteSeries.style.display = "flex";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/TMaquetteSeries.png";
    cadreAndroidPresentation2.style.display = "flex";
    cadreAndroidPresentation.style.marginLeft = "auto";
    cadreAndroidPresentation2.style.height = "60vh";
    imagecadreAndroidPresentation2.src = "./img/TMaquetteSeriesDetail.png";
});
// Clic bouton Menu Latéral Tomes
boutonMaquetteTomes.addEventListener('click', () => {
    sousTitrePresentation.innerText = "Tomes";
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteRecherche.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "flex";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/TMaquetteTomes.png";
    cadreAndroidPresentation2.style.display = "flex";
    cadreAndroidPresentation.style.marginLeft = "auto";
    cadreAndroidPresentation2.style.height = "fit-content";
    imagecadreAndroidPresentation2.src = "./img/TMaquetteTomesDetail.png";
});
// Clic bouton Menu Latéral Auteurs
boutonMaquetteAuteurs.addEventListener('click', () => {
    sousTitrePresentation.innerText = "Auteurs";
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteRecherche.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "flex";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/TMaquetteAuteurs.png";
    cadreAndroidPresentation2.style.display = "flex";
    cadreAndroidPresentation.style.marginLeft = "auto";
    cadreAndroidPresentation2.style.height = "60vh";
    imagecadreAndroidPresentation2.src = "./img/TMaquetteAuteurDetail.png";
});
// Clic bouton Menu Latéral Editeurs
boutonMaquetteEditeurs.addEventListener('click', () => {
    sousTitrePresentation.innerText = "Editeurs";
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteRecherche.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "flex";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/TMaquetteEditeurs.png";
    cadreAndroidPresentation2.style.display = "flex";
    cadreAndroidPresentation.style.marginLeft = "auto";
    cadreAndroidPresentation2.style.height = "60vh";
    imagecadreAndroidPresentation2.src = "./img/TMaquetteEditeursDetail.png";
});
// Clic bouton Menu Latéral Réglages
boutonMaquetteReglages.addEventListener('click', () => {
    sousTitrePresentation.innerText = "Réglages";
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteRecherche.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "flex";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/TMaquetteReglages.png";
    cadreAndroidPresentation2.style.display = "none";
    cadreAndroidPresentation.style.marginLeft = "";
});
// Clic bouton Menu Latéral A Propos
boutonMaquetteAPropos.addEventListener('click', () => {
    sousTitrePresentation.innerText = "A Propos";
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteRecherche.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "flex";
    imagecadreAndroidPresentation.src = "./img/TMaquetteAPropos.png";
    cadreAndroidPresentation2.style.display = "none";
    cadreAndroidPresentation.style.marginLeft = "";
});


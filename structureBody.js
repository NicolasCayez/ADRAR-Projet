// Création du menu gauche
const menuLateral = document.querySelector(".menuLateral");
// Titre du menu
let titreMenuLateral = document.createElement("h3");
if (menuLateral.id = "menuLateralPresentation") {
    titreMenuLateral.innerText = "Pages de l'application";
}
menuLateral.append(titreMenuLateral);
// éléments du menu
let menuLateralElements = document.createElement("ul");
menuLateralElements.classList.add("menu-lateral-elements");
menuLateral.appendChild(menuLateralElements);

function creerElementMenuLateral (idChild, libelle) {
    let lienLi = document.createElement("li");
    menuLateralElements.appendChild(lienLi);
    let btnLien = document.createElement("p");
    btnLien.classList.add("btnMenuLateral");
    btnLien.id = idChild;
    btnLien.innerText = libelle;
    lienLi.appendChild(btnLien);
}

if (menuLateral.id = "menuLateralPresentation") {
    //Bouton Accueil
    creerElementMenuLateral("btnLienAccueil","Accueil");
    //Bouton Collection 
    creerElementMenuLateral("btnLienCollection","Collection");
    //Bouton Series
    creerElementMenuLateral("btnLienSeries","Séries");
    //Bouton Tomes
    creerElementMenuLateral("btnLienTomes","Tomes");
    //Bouton Auteurs
    creerElementMenuLateral("btnLienAuteurs","Auteurs");
    //Bouton Editeurs
    creerElementMenuLateral("btnLienEditeurs","Editeurs");
    //Bouton Réglages
    creerElementMenuLateral("btnLienReglages","Réglages");
    //Bouton A propos
    creerElementMenuLateral("btnLienAPropos","A Propos");
};

//variables de repères sur la page




// Chargement de la page par défaut
let cadreAndroid = document.querySelector(".cadreAndroid");
// textes
const texteMaquetteAccueil = document.getElementById("texteAccueil");
const texteMaquetteCollection = document.getElementById("texteCollection");
const texteMaquetteSeries = document.getElementById("texteSeries");
const texteMaquetteTomes = document.getElementById("texteTomes");
const texteMaquetteAuteurs = document.getElementById("texteAuteurs");
const texteMaquetteEditeurs = document.getElementById("texteEditeurs");
const texteMaquetteReglages = document.getElementById("texteReglages");
const texteMaquetteApropos = document.getElementById("texteAPropos");
// boutons
let boutonMaquetteAccueil = document.getElementById("btnLienAccueil");
let boutonMaquetteCollection = document.getElementById("btnLienCollection");
let boutonMaquetteSeries = document.getElementById("btnLienSeries");
let boutonMaquetteTomes = document.getElementById("btnLienTomes");
let boutonMaquetteAuteurs = document.getElementById("btnLienAuteurs");
let boutonMaquetteEditeurs = document.getElementById("btnLienEditeurs");
let boutonMaquetteReglages = document.getElementById("btnLienReglages");
let boutonMaquetteAPropos = document.getElementById("btnLienAPropos");
// images des cadres
const imagecadreAndroidAccueil = document.createElement("img");
const imagecadreAndroidPresentation = document.createElement("img");


switch (cadreAndroid.id) {
    case "cadreAndroidAccueil" :
        imagecadreAndroidAccueil.src = "./img/MaquetteAccueil.png";
        cadreAndroid.appendChild(imagecadreAndroidAccueil);
    break;
    case "cadreAndroidPresentation" :
        // let imagecadreAndroidPresentation = document.createElement("img");
        imagecadreAndroidPresentation.src = "./img/MaquetteCollection.png";
        cadreAndroid.appendChild(imagecadreAndroidPresentation);
        texteMaquetteAccueil.style.display = "inline-block";
    break;
};


// Clic bouton Menu Latéral Accueil
boutonMaquetteAccueil.addEventListener('click', () => {
    texteMaquetteAccueil.style.display = "inline-block";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/MaquetteAccueil.png";

});
// Clic bouton Menu Latéral Collection
boutonMaquetteCollection.addEventListener('click', () => {
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "inline-block";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/MaquetteCollection.png";

});
// Clic bouton Menu Latéral Series
boutonMaquetteSeries.addEventListener('click', () => {
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteSeries.style.display = "inline-block";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/MaquetteSeries.png";

});
// Clic bouton Menu Latéral Tomes
boutonMaquetteTomes.addEventListener('click', () => {
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "inline-block";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/MaquetteTomes.png";

});
// Clic bouton Menu Latéral Auteurs
boutonMaquetteAuteurs.addEventListener('click', () => {
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "inline-block";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/MaquetteAuteurs.png";

});
// Clic bouton Menu Latéral Editeurs
boutonMaquetteEditeurs.addEventListener('click', () => {
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "inline-block";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/MaquetteEditeurs.png";
});
// Clic bouton Menu Latéral Réglages
boutonMaquetteReglages.addEventListener('click', () => {
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "inline-block";
    texteMaquetteApropos.style.display = "none";
    imagecadreAndroidPresentation.src = "./img/MaquetteReglages.png";

});
// Clic bouton Menu Latéral A Propos
boutonMaquetteAPropos.addEventListener('click', () => {
    texteMaquetteAccueil.style.display = "none";
    texteMaquetteCollection.style.display = "none";
    texteMaquetteSeries.style.display = "none";
    texteMaquetteTomes.style.display = "none";
    texteMaquetteAuteurs.style.display = "none";
    texteMaquetteEditeurs.style.display = "none";
    texteMaquetteReglages.style.display = "none";
    texteMaquetteApropos.style.display = "inline-block";
    imagecadreAndroidPresentation.src = "./img/MaquetteAPropos.png";

});
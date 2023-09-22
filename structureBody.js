// Création du menu gauche
const menuLateral = document.querySelector(".menuLateral");
// Titre du menu
let titreMenuLateral = document.createElement("h3");
if (menuLateral.id = "menuLateralPresentation") {
    titreMenuLateral.innerText = "Pages de l'application";
    menuLateral.append(titreMenuLateral);
}
// éléments du menu
let menuLateralElements = document.createElement("ul");
menuLateralElements.classList.add("menu-lateral-elements");
menuLateral.appendChild(menuLateralElements);

function creerElementMenuLateral (idChild, libelle, urlLogo) {
    let lienLi = document.createElement("li");
    menuLateralElements.appendChild(lienLi);
    let liDiv = document.createElement("div");
    liDiv.classList.add("btnMenuLateral");
    lienLi.appendChild(liDiv);
    let logo = document.createElement("img");
    logo.src = urlLogo;
    logo.classList.add("logoBouton");
    liDiv.appendChild(logo);
    let btnLien = document.createElement("p");
    btnLien.id = idChild;
    btnLien.innerText = libelle;
    liDiv.appendChild(btnLien);
}

if (menuLateral.id = "menuLateralPresentation") {
    //Bouton Accueil
    creerElementMenuLateral("btnLienAccueil","Accueil","./img/LogoAccueil.png");
    //Bouton Collection 
    creerElementMenuLateral("btnLienCollection","Collection", "./img/LogoCollection.png");
    //Bouton Series
    creerElementMenuLateral("btnLienSeries","Séries", "./img/LogoSeries.png");
    //Bouton Tomes
    creerElementMenuLateral("btnLienTomes","Tomes", "./img/LogoTomes.png");
    //Bouton Auteurs
    creerElementMenuLateral("btnLienAuteurs","Auteurs", "./img/LogoAuteurs.png");
    //Bouton Editeurs
    creerElementMenuLateral("btnLienEditeurs","Editeurs", "./img/LogoEditeur.png");
    //Bouton Réglages
    creerElementMenuLateral("btnLienReglages","Réglages", "./img/LogoReglages.png");
    //Bouton A propos
    creerElementMenuLateral("btnLienAPropos","A Propos", "./img/LogoAPropos.png");
};

//variables de repères sur la page




// Chargement de la page par défaut
const cadreAndroid = document.querySelector(".cadreAndroid");
const cadreAndroidPresentation = document.getElementById("cadreAndroidPresentation");
const cadreAndroidPresentation2 = document.getElementById("cadreAndroidPresentation2");

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
const imagecadreAndroidPresentation2 = document.createElement("img");


switch (cadreAndroid.id) {
    case "cadreAndroidAccueil" :
        imagecadreAndroidAccueil.src = "./img/MaquetteAccueil.png";
        cadreAndroid.appendChild(imagecadreAndroidAccueil);
    break;
    case "cadreAndroidPresentation" :
        // let imagecadreAndroidPresentation = document.createElement("img");
        imagecadreAndroidPresentation.src = "./img/MaquetteAccueil.png";
        cadreAndroidPresentation.appendChild(imagecadreAndroidPresentation);
        cadreAndroidPresentation2.appendChild(imagecadreAndroidPresentation2)
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
    cadreAndroidPresentation2.style.display = "inline-block";
    cadreAndroidPresentation.style.marginLeft = "auto";
    imagecadreAndroidPresentation2.src = "./img/MaquetteMenuHamburger.png";
    cadreAndroidPresentation2.style.height = "60vh";
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
    cadreAndroidPresentation2.style.display = "none";
    cadreAndroidPresentation.style.marginLeft = "";
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
    cadreAndroidPresentation2.style.display = "inline-block";
    cadreAndroidPresentation.style.marginLeft = "auto";
    cadreAndroidPresentation2.style.height = "60vh";
    imagecadreAndroidPresentation2.src = "./img/MaquetteSeriesDetail.png";
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
    cadreAndroidPresentation2.style.display = "inline-block";
    cadreAndroidPresentation.style.marginLeft = "auto";
    cadreAndroidPresentation2.style.height = "fit-content";
    imagecadreAndroidPresentation2.src = "./img/MaquetteTomesDetail.png";
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
    cadreAndroidPresentation2.style.display = "inline-block";
    cadreAndroidPresentation.style.marginLeft = "auto";
    cadreAndroidPresentation2.style.height = "60vh";
    imagecadreAndroidPresentation2.src = "./img/MaquetteAuteurDetail.png";
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
    cadreAndroidPresentation2.style.display = "inline-block";
    cadreAndroidPresentation.style.marginLeft = "auto";
    cadreAndroidPresentation2.style.height = "60vh";
    imagecadreAndroidPresentation2.src = "./img/MaquetteEditeursDetail.png";
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
    cadreAndroidPresentation2.style.display = "none";
    cadreAndroidPresentation.style.marginLeft = "";

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
    cadreAndroidPresentation2.style.display = "none";
    cadreAndroidPresentation.style.marginLeft = "";

});
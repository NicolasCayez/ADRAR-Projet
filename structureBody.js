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
if (menuLateral.id = "menuLateralPresentation") {
    //Bouton Accueil
    let liLienAccueil = document.createElement("li");
    menuLateralElements.appendChild(liLienAccueil);
    let btnLienAccueil = document.createElement("p");
    btnLienAccueil.classList.add("btnMenuLateral");
    btnLienAccueil.id = "btnLienAccueil";
    btnLienAccueil.innerText = "Accueil";
    liLienAccueil.appendChild(btnLienAccueil);
    //Bouton Collection 
    let liLienCollection = document.createElement("li");
    menuLateralElements.appendChild(liLienCollection);
    let btnLienCollection = document.createElement("p");
    btnLienCollection.classList.add("btnMenuLateral");
    btnLienCollection.id = "btnLienCollection";
    btnLienCollection.innerText = "Collection";
    liLienCollection.appendChild(btnLienCollection);
    //Bouton Series
    let liLienSeries = document.createElement("li");
    menuLateralElements.appendChild(liLienSeries);
    let btnLienSeries = document.createElement("p");
    btnLienSeries.classList.add("btnMenuLateral");
    btnLienSeries.id = "btnLienSeries";
    btnLienSeries.innerText = "Séries";
    liLienSeries.appendChild(btnLienSeries);
    //Bouton Tomes
    let liLienTomes = document.createElement("li");
    menuLateralElements.appendChild(liLienTomes);
    let btnLienTomes = document.createElement("p");
    btnLienTomes.classList.add("btnMenuLateral");
    btnLienTomes.id = "btnLienTomes";
    btnLienTomes.innerText = "Tomes";
    liLienTomes.appendChild(btnLienTomes);
    //Bouton Auteurs
    let liLienAuteurs = document.createElement("li");
    menuLateralElements.appendChild(liLienAuteurs);
    let btnLienAuteurs = document.createElement("p");
    btnLienAuteurs.classList.add("btnMenuLateral");
    btnLienAuteurs.id = "btnLienAuteurs";
    btnLienAuteurs.innerText = "Auteurs";
    liLienAuteurs.appendChild(btnLienAuteurs);
    //Bouton Editeurs
    let liLienEditeurs = document.createElement("li");
    menuLateralElements.appendChild(liLienEditeurs);
    let btnLienEditeurs = document.createElement("p");
    btnLienEditeurs.classList.add("btnMenuLateral");
    btnLienEditeurs.id = "btnLienSerie";
    btnLienEditeurs.innerText = "Editeurs";
    liLienEditeurs.appendChild(btnLienEditeurs);
    //Bouton Réglages
    let liLienReglages = document.createElement("li");
    menuLateralElements.appendChild(liLienReglages);
    let btnLienReglages = document.createElement("p");
    btnLienReglages.classList.add("btnMenuLateral");
    btnLienReglages.id = "btnLienReglages";
    btnLienReglages.innerText = "Collection";
    liLienReglages.appendChild(btnLienReglages);
    //Bouton A propos
    let liLienAPropos = document.createElement("li");
    menuLateralElements.appendChild(liLienAPropos);
    let btnLienAPropos = document.createElement("p");
    btnLienbtnLienAProposSerie.classList.add("btnMenuLateral");
    btnLienAPropos.id = "btnLienSerie";
    btnLienAPropos.innerText = "A Propos";
    liLienAPropos.appendChild(btnLienAPropos);
};


// Cadre android page Présentation

// defaut accueil
// if sur clic bouton, changer image(s)





let cadreAndroidPresentation = document.getElementById("cadreAndroidPresentation");
let imagecadreAndroidPresentation = document.createElement("img");
imagecadreAndroidPresentation.src = "./img/MaquetteAccueil.png";
cadreAndroidPresentation.appendChild(imagecadreAndroidPresentation);
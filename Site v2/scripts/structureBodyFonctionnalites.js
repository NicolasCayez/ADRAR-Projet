//********************************************/
//* PAGE FONCTIONNALITES                     */
//********************************************/
// récupération corps de la page sous le titre
const pageContent = document.getElementById("pageContent");
// Création du menu gauche
const menuLateral = document.querySelectorAll(".menuLateral");
const menuLateralFonctionnalites = document.getElementById("menuLateralFonctionnalites");

//Bouton Première Sonctionnalité
creerElementMenuLateral("btnLienFonction1","La Saisie","", menuLateralFonctionnalites);
creerElementMenuLateral("btnLienFonction2","Recherche","", menuLateralFonctionnalites);
creerElementMenuLateral("btnLienFonction3","Profils","", menuLateralFonctionnalites);
creerElementMenuLateral("btnLienFonction4","Plus tard","", menuLateralFonctionnalites);

// récupération textes page Présentation
const sectionFonction1 = document.getElementById("sectionSaisie");
const sectionFonction2 = document.getElementById("sectionRecherche");
const sectionFonction3 = document.getElementById("sectionProfils");
const sectionFonction4 = document.getElementById("sectionAddOn");
// récupération boutons
let boutonFonction1 = document.getElementById("btnLienFonction1");
let boutonFonction2 = document.getElementById("btnLienFonction2");
let boutonFonction3 = document.getElementById("btnLienFonction3");
let boutonFonction4 = document.getElementById("btnLienFonction4");
//Gestion des éléments actifs selon clic bouton
sectionFonction2.style.display = "none";
sectionFonction3.style.display = "none";
sectionFonction4.style.display = "none";
// Clic bouton Menu Latéral Accueil
boutonFonction1.addEventListener('click', () => {
    sectionFonction1.style.display = "flex";
    sectionFonction2.style.display = "none";
    sectionFonction3.style.display = "none";
    sectionFonction4.style.display = "none";
});
boutonFonction2.addEventListener('click', () => {
    sectionFonction1.style.display = "none";
    sectionFonction2.style.display = "flex";
    sectionFonction3.style.display = "none";
    sectionFonction4.style.display = "none";
});
boutonFonction3.addEventListener('click', () => {
    sectionFonction1.style.display = "none";
    sectionFonction2.style.display = "none";
    sectionFonction3.style.display = "flex";
    sectionFonction4.style.display = "none";
});
boutonFonction4.addEventListener('click', () => {
    sectionSaisie.style.display = "none";
    sectionFonction2.style.display = "none";
    sectionFonction3.style.display = "none";
    sectionFonction4.style.display = "flex";
});


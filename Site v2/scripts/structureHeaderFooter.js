//********************************************/
//* HEADER                                   */
//********************************************/
// Recherche de l'id #header
const divHeader = document.getElementById("header");
// Création navbar
let navbarHeader = document.createElement("nav");
navbarHeader.id = "navbarHeader";
navbarHeader.classList.add("navbar");
navbarHeader.classList.add("row");
// intégration navbar dans le header
divHeader.append(navbarHeader);
    // Création logo
    let logo = document.createElement("img");
    logo.src = "./img/logoBeige.png";
    logo.alt = "logoBeige";
    logo.classList.add("logo");
    logo.classList.add("col");
    navbarHeader.appendChild(logo);
    // Création div titre et menu
    let divTitreMenu = document.createElement("div");
    divTitreMenu.classList.add("row");
    divTitreMenu.classList.add("row-cols-1");
    divTitreMenu.classList.add("text-center");
    divTitreMenu.classList.add("m-0");
    navbarHeader.appendChild(divTitreMenu);
        // Création titre
        let titreDiv = document.createElement("div");
        divTitreMenu.appendChild(titreDiv);
        let titre = document.createElement("h1");
        titre.innerText = "MyComics";
        titre.classList.add("fs-3");
        titre.classList.add("pb-2");
        titre.classList.add("custom-font-title");
        titre.id = "titreMyComics";
        titreDiv.appendChild(titre);
        // Création menuHeader
        let menuHeader = document.createElement("div");
        menuHeader.id = "menuHeader";
        menuHeader.classList.add("m-0");
        divTitreMenu.appendChild(menuHeader);
            //Bouton Accueil
            creerElementMenuHeader("btnAccueil","Accueil","./index.html", menuHeader);
            //Bouton Presentation
            creerElementMenuHeader("btnPresentation","Présentation","./presentation.html", menuHeader);
            //Bouton Fonctionnalités
            creerElementMenuHeader("btnFonctionnalites","Fonctionnalités","./fonctionnalites.html", menuHeader);
            //Bouton Structure
            creerElementMenuHeader("btnContact","Contact","./contact.html", menuHeader);
    // Création checkbox
    let checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    navbarHeader.appendChild(checkbox);
    // Création lignes hamburger
    let hamburgerLines = document.createElement("div");
    hamburgerLines.classList.add("hamburger-lines");
    navbarHeader.appendChild(hamburgerLines);
    for (let i=1; i<=3; i++) {
        let line = document.createElement("span");
        line.classList.add("line");
        line.classList.add("line"+i);
        hamburgerLines.appendChild(line);
    };
    //création menu caché hamburger
    let hiddenMenu = document.createElement("div");
    hiddenMenu.classList.add("hiddenMenu");
    hiddenMenu.classList.add("text-center");

    navbarHeader.appendChild(hiddenMenu);
        //Bouton Accueil
        creerElementMenuHeader("btnAccueil","Accueil","./index.html", hiddenMenu);
        //Bouton Presentation
        creerElementMenuHeader("btnPresentation","Présentation","./presentation.html", hiddenMenu);
        //Bouton Fonctionnalités
        creerElementMenuHeader("btnFonctionnalites","Fonctionnalités","./fonctionnalites.html", hiddenMenu);
        //Bouton Structure
        creerElementMenuHeader("btnContact","Contact","./contact.html", hiddenMenu);

//********************************************/
//* FOOTER                                   */
//********************************************/
// Recherche de l'id #footer
const divFooter = document.getElementById("footer");
// Création navbar
let navbarFooter = document.createElement("nav");
navbarFooter.id = "navFooter";
navbarFooter.classList.add("navbar");
navbarFooter.classList.add("navbar-container");
navbarFooter.classList.add("container-fluid");
navbarFooter.classList.add("px-0");
navbarFooter.classList.add("row");
// intégration navbar dans le header
divFooter.append(navbarFooter);
//création div réseaux sociaux
let logosFooter = document.createElement("div");
logosFooter.classList.add("align-top");
navbarFooter.append(logosFooter);
//Lien gitHub avec image
let lienGitHub = document.createElement("a");
lienGitHub.href = "https://github.com/NicolasCayez/ADRAR-Projet.git";
lienGitHub.target = "_blank";
logosFooter.append(lienGitHub);
let logoGitHub = document.createElement("img");
logoGitHub.src="./img/logoGitHub.png";
logoGitHub.classList.add("align-top");
logoGitHub.classList.add("float-end");
lienGitHub.append(logoGitHub);
//Lien facebook avec image
let lienFacebook = document.createElement("a");
lienFacebook.href = "https://github.com/NicolasCayez/ADRAR-Projet.git";
lienFacebook.target = "_blank";
logosFooter.append(lienFacebook);
let logofacebook = document.createElement("img");
logofacebook.src="./img/logofacebook.png";
logofacebook.classList.add("align-top");
logofacebook.classList.add("float-end");
lienFacebook.append(logofacebook);


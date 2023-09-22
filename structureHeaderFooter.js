// Recherche de l'id #header
const divHeader = document.getElementById("header");
// Création navbar
let navbar = document.createElement("nav");
navbar.classList.add("navbar");
// intégration navbar dans le header
divHeader.append(navbar);
    // création navbar container
    let navbarContainer = document.createElement("div");
    navbarContainer.classList.add("navbar-container");
    navbarContainer.classList.add("container");
    navbar.appendChild(navbarContainer);
        // Création logo
        let logo = document.createElement("img");
        logo.src = "./img/logoBeige.png";
        logo.alt = "logoBeige";
        logo.classList.add("logo");
        navbarContainer.appendChild(logo);
        // Création div titre et menu
        let divTitreMenu = document.createElement("div");
        divTitreMenu.classList.add("col");
        navbarContainer.appendChild(divTitreMenu);
        // Création checkbox
        let checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        navbarContainer.appendChild(checkbox);
        // Création lignes hamburger
        let hamburgerLines = document.createElement("div");
        hamburgerLines.classList.add("hamburger-lines");
        navbarContainer.appendChild(hamburgerLines);
        for (let i=1; i<=3; i++) {
            let line = document.createElement("span");
            line.classList.add("line");
            line.classList.add("line"+i);
            hamburgerLines.appendChild(line);
        };
        // Création titre
        let titre = document.createElement("h1");
        titre.innerText = "MyComics";
        titre.classList.add("fs-3");
        titre.classList.add("pb-2");
        titre.id = "titreMyComics";
        navbarContainer.appendChild(titre);
        // éléments du menu
        let menuElements = document.createElement("ul");
        menuElements.classList.add("menu-elements");
        navbarContainer.appendChild(menuElements);
            //Bouton Accueil
            let liAccueil = document.createElement("li");
            menuElements.appendChild(liAccueil);
            let btnAccueil = document.createElement("a");
            btnAccueil.classList.add("btnMenu");
            btnAccueil.id = "btnAccueil";
            btnAccueil.href = "./index.html";
            btnAccueil.innerText = "Accueil";
            liAccueil.appendChild(btnAccueil);
            //Bouton Presentation
            let liPresentation = document.createElement("li");
            menuElements.appendChild(liPresentation);
            let btnPresentation = document.createElement("a");
            btnPresentation.classList.add("btnMenu");
            btnPresentation.id = "btnPresentation";
            btnPresentation.href = "./presentation.html";
            btnPresentation.innerText = "Présentation";
            liPresentation.appendChild(btnPresentation);
            //Bouton Fonctionnalités
            let liFonctionnalites = document.createElement("li");
            menuElements.appendChild(liFonctionnalites);
            let btnFonctionnalites = document.createElement("a");
            btnFonctionnalites.classList.add("btnMenu");
            btnFonctionnalites.id = "btnFonctionnalites";
            btnFonctionnalites.href = "./fonctionnalites.html";
            btnFonctionnalites.innerText = "Fonctionnalités";
            liFonctionnalites.appendChild(btnFonctionnalites);
            //Bouton Structure
            let liStructure = document.createElement("li");
            menuElements.appendChild(liStructure);
            let btnStructure = document.createElement("a");
            btnStructure.classList.add("btnMenu");
            btnStructure.id = "btnStructure";
            btnStructure.href = "./structure.html";
            btnStructure.innerText = "Structure";
            liStructure.appendChild(btnStructure);






// Recherche de l'id #footer
const divFooter = document.getElementById("footer");
// Création navbar
let navbarFooter = document.createElement("nav");
navbarFooter.classList.add("navbar");
// intégration navbar dans le header
divFooter.append(navbarFooter);
    // création navbar container
    let navbarFooterContainer = document.createElement("div");
    navbarFooterContainer.classList.add("navbar-container");
    navbarFooterContainer.classList.add("container");
    navbarFooterContainer.id = "navFooter";
    navbarFooter.appendChild(navbarFooterContainer);
let logoGitHub = document.createElement("img");
logoGitHub.src="./img/logoGitHub.png";
navbarFooterContainer.append(logoGitHub);
let logofacebook = document.createElement("img");
logofacebook.src="./img/logofacebook.png";
navbarFooterContainer.append(logofacebook);




// <!-- NavBar footer -->
// <nav class="navbar navbar-expand couleur2">
//     FOOTER
// </nav>
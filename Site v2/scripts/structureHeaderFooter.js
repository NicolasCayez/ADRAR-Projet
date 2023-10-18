// Recherche de l'id #header
const divHeader = document.getElementById("header");
// Création navbar
let navbarHeader = document.createElement("nav");
navbarHeader.id = "navHeader";
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
                creerElementMenuHeader("btnAccueil","Accueil","./index.html");
                //Bouton Presentation
                creerElementMenuHeader("btnPresentation","Présentation","./presentation.html");
                //Bouton Fonctionnalités
                creerElementMenuHeader("btnFonctionnalites","Fonctionnalités","./fonctionnalites.html");
                //Bouton Structure
                creerElementMenuHeader("btnStructure","Structure","./structure.html");
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
let logoGitHub = document.createElement("img");
logoGitHub.src="./img/logoGitHub.png";
logoGitHub.classList.add("align-top");
logoGitHub.classList.add("float-end");
logosFooter.append(logoGitHub);
let logofacebook = document.createElement("img");
logofacebook.src="./img/logofacebook.png";
logofacebook.classList.add("align-top");
logofacebook.classList.add("float-end");
logosFooter.append(logofacebook);





    // création navbar container
    let navbarFooterContainer = document.createElement("div");
    navbarFooterContainer.classList.add("navbar-container");
    navbarFooterContainer.classList.add("container");
    navbarFooterContainer.classList.add("px-0");
    navbarFooterContainer.id = "navFooter";
    navbarFooter.appendChild(navbarFooterContainer);


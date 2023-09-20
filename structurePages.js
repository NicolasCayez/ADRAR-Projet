// Recherche de l'id #header
const divHeader = document.getElementById("header");
// // Création navbar
//     let headerNav = document.createElement("nav");
//     headerNav.classList.add("navbar");
//     // intégration navbar dans le header
//     divHeader.append(headerNav);
//         // Création logo
//         let logo = document.createElement("img");
//         logo.src = "./img/logoBeige.png";
//         logo.alt = "logoBeige";
//         logo.classList.add("logo")
//         headerNav.appendChild(logo);
//         // Création div titre et menu
//         let divTitreMenu = document.createElement("div");
//         divTitreMenu.classList.add("col")
//         headerNav.appendChild(divTitreMenu);
//             // Création titre
//             let titre = document.createElement("h1");
//             titre.innerText = "MyComics";
//             titre.classList.add("fs-3");
//             titre.classList.add("pb-2");
//             titre.id = "titreMyComics";
//             divTitreMenu.appendChild(titre);
//             // Création Menu
//             let menuClassique = document.createElement("div");
//             menuClassique.id = "menu";
//             menuClassique.classList.add("btnContainer");
//             divTitreMenu.appendChild(menuClassique);
//                 //Bouton Accueil
//                 let btnAccueil = document.createElement("a");
//                 btnAccueil.classList.add("btnMenu");
//                 btnAccueil.id = "btnAccueil";
//                 btnAccueil.href = "./index.html"
//                 btnAccueil.innerText = "Accueil"
//                 menuClassique.appendChild(btnAccueil);
//                 //Bouton Presentation
//                 let btnPresentation = document.createElement("a");
//                 btnPresentation.classList.add("btnMenu");
//                 btnPresentation.id = "btnPresentation";
//                 btnPresentation.href = "./presentation.html"
//                 btnPresentation.innerText = "Présentation"
//                 menuClassique.appendChild(btnPresentation);
//                 //Bouton Fonctionnalités
//                 let btnFonctionnalites = document.createElement("a");
//                 btnFonctionnalites.classList.add("btnMenu");
//                 btnFonctionnalites.id = "btnFonctionnalites";
//                 btnFonctionnalites.href = "./fonctionnalites.html"
//                 btnFonctionnalites.innerText = "Fonctionnalités"
//                 menuClassique.appendChild(btnFonctionnalites);
//                 //Bouton Structure
//                 let btnStructure = document.createElement("a");
//                 btnStructure.classList.add("btnMenu");
//                 btnStructure.id = "btnStructure";
//                 btnStructure.href = "./structure.html"
//                 btnStructure.innerText = "Structure"
//                 menuClassique.appendChild(btnStructure);
//         // Création div menu Hamburger
//         let divMenuHamburger = document.createElement("div");
//         divMenuHamburger.classList.add("col")
//         headerNav.appendChild(divMenuHamburger);
//             //Création menu Hamburger

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
            btnAccueil.href = "./index.html"
            btnAccueil.innerText = "Accueil"
            liAccueil.appendChild(btnAccueil);
            //Bouton Presentation
            let liPresentation = document.createElement("li");
            menuElements.appendChild(liPresentation);
            let btnPresentation = document.createElement("a");
            btnPresentation.classList.add("btnMenu");
            btnPresentation.id = "btnPresentation";
            btnPresentation.href = "./presentation.html"
            btnPresentation.innerText = "Présentation"
            liPresentation.appendChild(btnPresentation);
            //Bouton Fonctionnalités
            let liFonctionnalites = document.createElement("li");
            menuElements.appendChild(liFonctionnalites);
            let btnFonctionnalites = document.createElement("a");
            btnFonctionnalites.classList.add("btnMenu");
            btnFonctionnalites.id = "btnFonctionnalites";
            btnFonctionnalites.href = "./fonctionnalites.html"
            btnFonctionnalites.innerText = "Fonctionnalités"
            liFonctionnalites.appendChild(btnFonctionnalites);
            //Bouton Structure
            let liStructure = document.createElement("li");
            menuElements.appendChild(liStructure);
            let btnStructure = document.createElement("a");
            btnStructure.classList.add("btnMenu");
            btnStructure.id = "btnStructure";
            btnStructure.href = "./structure.html"
            btnStructure.innerText = "Structure"
            liStructure.appendChild(btnStructure);






// création footer
const divFooter = document.getElementById('footer');
let footer = document.createElement('p');
footer.innerText = "prout voici le pied";
divFooter.append(footer);


// <!-- NavBar footer -->
// <nav class="navbar navbar-expand couleur2">
//     FOOTER
// </nav>
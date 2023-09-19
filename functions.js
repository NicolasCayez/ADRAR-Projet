// Recherche de l'id #header
const divHeader = document.getElementById("header");
// Création navbar
    let headerNav = document.createElement("nav");
    headerNav.classList.add("navbar");
    // intégration navbar dans le header
    divHeader.append(headerNav);
        // Création logo
        let logo = document.createElement("img");
        logo.src = "./img/logoBeige.png";
        logo.alt = "logoBeige";
        logo.classList.add("logo")
        headerNav.appendChild(logo);
        // Création div titre et menu
        let divTitreMenu = document.createElement("div");
        divTitreMenu.classList.add("col")
        headerNav.appendChild(divTitreMenu);
            // Création titre
            let titre = document.createElement("h1");
            titre.innerText = "MyComics";
            titre.classList.add("fs-3");
            titre.classList.add("pb-2");
            titre.id = "titreMyComics";
            divTitreMenu.appendChild(titre);
            // Création Menu
    




// Logo
// let logo = headerNav.createElement('img');
// logo.src = "img/logoBeige.png";
// logo.classList.add(`logo`);
// headerNav.append(logo);

// <!-- NavBar header -->
//         <nav class="navbar">
//             <!-- logo MyComics -->
//             <img src="img/logoBeige.png" alt="logoBleu" class="logo">
//             <div class="col">
//                 <!-- Titre -->
//                 <h1 id="titreMyComics fs-3 pb-2">MyComics</h1>
//TODO                 <!-- Menu -->
//                 <div id="menu" class="">
//?                     <div class="btnContainer">
//                         <a class="btnMenu" id="btnAccueil" href="./index.html">Accueil</a>
//                         <a class="btnMenu" id="btnPresentation" href="./presentation.html">Présentation</a>
//                         <a class="btnMenu" id="btnFonctionnalites" href="./fonctionnalites.html">Fonctionnalités</a>
//                         <a class="btnMenu" id="btnStructure" href="./structure.html">Structure</a>
//?                     </div>
//                 </div>
//             </div>
//         </nav>






// création footer
const divFooter = document.getElementById('footer');
let footer = document.createElement('p');
footer.innerText = "prout voici le pied";
divFooter.append(footer);


// <!-- NavBar footer -->
// <nav class="navbar navbar-expand couleur2">
//     FOOTER
// </nav>
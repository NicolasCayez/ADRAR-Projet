// Création du menu gauche
const menuLateral = document.getElementById("menuLateral");
// éléments du menu
let menuLateralElements = document.createElement("ul");
menuLateralElements.classList.add("menu-lateral-elements");
menuLateral.appendChild(menuLateralElements);
    //Bouton 1
    let liLien1 = document.createElement("li");
    menuLateralElements.appendChild(liLien1);
    let btnLien1 = document.createElement("a");
    btnLien1.classList.add("btnMenuLateral");
    btnLien1.id = "btnLien1";
    btnLien1.href = "./index.html";
    btnLien1.innerText = "btnLien1";
    liLien1.appendChild(btnLien1);
    //Bouton 2
    let liLien2 = document.createElement("li");
    menuLateralElements.appendChild(liLien2);
    let btnLien2 = document.createElement("a");
    btnLien2.classList.add("btnMenuLateral");
    btnLien2.id = "btnLien2";
    btnLien2.href = "./presentation.html";
    btnLien2.innerText = "btnLien2";
    liLien2.appendChild(btnLien2);
    //Bouton 3
    let liLien3 = document.createElement("li");
    menuLateralElements.appendChild(liLien3);
    let btnLien3 = document.createElement("a");
    btnLien3.classList.add("btnMenuLateral");
    btnLien3.id = "btnLien3";
    btnLien3.href = "./fonctionnalites.html";
    btnLien3.innerText = "btnLien3";
    liLien3.appendChild(btnLien3);
    //Bouton 4
    let liLien4 = document.createElement("li");
    menuLateralElements.appendChild(liLien4);
    let btnLien4 = document.createElement("a");
    btnLien4.classList.add("btnMenuLateral");
    btnLien4.id = "btnLien4";
    btnLien4.href = "./structure.html";
    btnLien4.innerText = "btnLien4";
    liLien4.appendChild(btnLien4);
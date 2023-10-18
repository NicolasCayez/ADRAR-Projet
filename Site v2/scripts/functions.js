// Création d'un bouton du menu du header
function creerElementMenuHeader (idChild, libelle, lien) {
    let btn = document.createElement("a");
    btn.id = idChild
    btn.innerText = libelle;
    btn.href = lien;
    btn.classList.add("btnMenu");
    btn.classList.add("bg-custom-beige");
    btn.classList.add("custom-bleu");
    btn.classList.add("text-decoration-none");
    btn.classList.add("mx-2");
    btn.classList.add("py-1");
    btn.style.display = "inline-block";
    menuHeader.appendChild(btn);
}

// Création d'un bouton du menu latéral
function creerElementMenuLateral (idChild, libelle, urlLogo) {
    let lienLi = document.createElement("li");
    menuLateralElements.appendChild(lienLi);
    let liDiv = document.createElement("div");
    liDiv.classList.add("btnMenuLateral");
    liDiv.id = idChild
    lienLi.appendChild(liDiv);
    let logo = document.createElement("img");
    logo.src = urlLogo;
    logo.classList.add("logoBouton");
    liDiv.appendChild(logo);
    let btnLien = document.createElement("p");
    btnLien.innerText = libelle;
    liDiv.appendChild(btnLien);
}
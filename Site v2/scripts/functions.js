//********************************************/
//* Création d'un bouton du menu du header   */
//********************************************/
function creerElementMenuHeader (idChild, libelle, lien, cible) {
    let btn = document.createElement("a");
    btn.id = idChild;
    btn.innerText = libelle;
    btn.href = lien;
    btn.classList.add("btnMenu");
    btn.classList.add("bg-custom-beige");
    btn.classList.add("custom-bleu");
    btn.classList.add("text-decoration-none");
    btn.classList.add("mx-2");
    btn.classList.add("py-1");
    btn.style.display = "inline-block";
    cible.appendChild(btn);
}

//********************************************/
//* Création d'un bouton du menu latéral     */
//********************************************/
function creerElementMenuLateral (idChild, libelle, urlLogo, cible) {
    //création div bouton
    let btn = document.createElement("div");
    btn.id = idChild;
    btn.classList.add("btnMenuLateral");
    btn.classList.add("bg-custom-beige");
    btn.classList.add("custom-bleu");
    btn.classList.add("text-decoration-none");
    btn.classList.add("mx-3");
    btn.classList.add("mt-3");
    btn.classList.add("py-1");
    btn.classList.add("ps-2");
        //création logo dans la div
        if (urlLogo != ""){
            let logo = document.createElement("img");
            logo.src = urlLogo;
            logo.classList.add("logoBouton");
            btn.appendChild(logo);
        }
        //création texte du bouton
        let btnLien = document.createElement("span");
        btnLien.innerText = libelle;
        btnLien.classList.add("ps-md-1");
        btnLien.classList.add("ps-0");
        btn.appendChild(btnLien);
    cible.appendChild(btn);
}

//********************************************/
//* Envoi du formulaire                      */
//********************************************/
//TODO
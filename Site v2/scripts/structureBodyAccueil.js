//****************************************************/
//* Structure dans le corps de la page sous le titre */
//****************************************************/
// récupération corps de la page sous le titre
const pageContent = document.getElementById("pageContent");

// Récupération des cadres
const cadreAndroid = document.querySelector(".cadreAndroid");

// images des cadres
const imagecadreAndroidAccueil = document.createElement("img");

imagecadreAndroidAccueil.src = "./img/TMaquetteAccueil.png";
cadreAndroid.appendChild(imagecadreAndroidAccueil);

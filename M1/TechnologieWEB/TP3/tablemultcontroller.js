"use strict";

var Controller = Controller || {};

(function (exports){
    
    //on initialise l'évènement "onchange" au chargement de la page
    //on attache à l'élément sélectionnée au chargement de la page
    function onLoad(){
        nouvelleTable();
        document.getElementById("selectnum").addEventListener("change",onChangeTableNumber,false);
        document.body.addEventListener("click", onClick, false);
    }
    
    //détache l'évènement "onchange" quand on quitte la page
    function unLoad(){
        document.getElementById("selectnum").removeEventListener("change",onChangeTableNumber,false);
        document.body.removeListener("click", onClick, false);
    }
    
    //à appeler quand on clique sur un contrôle, et afficher les résultats de la table quand on clique sur le bouton "Résultats"
    function onClick(event){
        if(event.target.id === "bresult"){
            displayResults();
        }
    }
    
    //fait en sorte que la vue et les données soient ré-initilisées avec la table de multiplication du numéro sélectionné
    //càd que ce numéro soit affiché dans la vue et soit celui de la table actuelle dans les données.
    function nouvelleTable(){
        var selectedValue = View.selectedTableNumber();
        View.writeTableNumber(selectedValue);
        Data.changeTableNumber(selectedValue);
    }
    
    //
    function onChangeTableNumber(event) {
        nouvelleTable();
    }
    
    //affiche les résultats de la table courante dans les champs texte mis en lecture seule
    function displayResults(){
        View.displayResults(Data.tableValues());
    }
    
    exports.nouvelleTable = nouvelleTable;
    
    window.addEventListener("load", onLoad, false);
    window.addEventListener("unload", unLoad, false);
    
})(Controller);
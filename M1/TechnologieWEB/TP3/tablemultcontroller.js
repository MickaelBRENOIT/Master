"use strict";

var Controller = Controller || {};

(function (exports){
    
    //on initialise l'évènement "onchange" au chargement de la page
    //on attache à l'élément sélectionnée au chargement de la page
    function onLoad(){
        nouvelleTable();
        document.getElementById("selectnum").addEventListener("change",onChangeTableNumber,false);
    }
    
    //détache l'évènement "onchange" quand on quitte la page
    function unLoad(){
        document.getElementById("selectnum").removeEventListener("change",onChangeTableNumber,false);
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
    
    exports.nouvelleTable = nouvelleTable;
    
    window.addEventListener("load", onLoad, false);
    window.addEventListener("unload", unLoad, false);
    
})(Controller);
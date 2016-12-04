"use strict";

var View = View || {};

(function (exports){
    
    var numClassElements = null;
    var selectNumberElement = null;
    
    //méthode lancée au démarrage de la page
    function onLoad(){
        numClassElements = document.getElementsByClassName("num");
        selectNumberElement = document.getElementById("selectnum");
    }
    
    //méthode lancée avant le fermeture de la page
    function unLoad(){
        
    }
    
    //écrit le "num" de la table de multiplication dans tous les éléments qui ont une classe "num"
    function writeTableNumber(num){
        for(var i=0; i < numClassElements.length; i++){
            numClassElements[i].textContent = num;
        }
    }
    
    //retourne le numéro sélectionné
    function selectedTableNumber(){
        //return selectNumberElement.options[selectNumberElement.selectedIndex].value;
        return selectNumberElement.value;
    }
    
    //getter selectNumberElement
    function getSelectNumberElement(){
        return selectNumberElement;
    }
    
    window.addEventListener("load", onLoad, false);
    window.addEventListener("unload", unLoad, false);
    
    exports.getSelectNumberElement = getSelectNumberElement;
    exports.writeTableNumber = writeTableNumber;
    exports.selectedTableNumber = selectedTableNumber;
    
})(View);
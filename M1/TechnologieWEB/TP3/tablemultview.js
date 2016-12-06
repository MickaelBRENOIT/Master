"use strict";

var View = View || {};

(function (exports){
    
    var numClassElements = null;
    var selectNumberElement = null;
    var inputresultClassElements = null;
    
    //méthodes lancées au démarrage de la page
    function onLoad(){
        numClassElements = document.getElementsByClassName("num");
        selectNumberElement = document.getElementById("selectnum");
        inputresultClassElements = document.getElementsByClassName("inputresult");
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
    
    //affiche en lecture seule dans les champs texte de classe "inputresults" les valeurs du tableau passé en paramètre.
    function displayResults(results){
        var len = results.length;
        //len contiendra la longueur du tableau le plus grand
        if(len > inputresultClassElements.length){
            len = inputresultClassElements.length;
        }
        for(var i=0; i < len; i++){
            inputresultClassElements[i].value = results[i];
            inputresultClassElements[i].readOnly = true;
        }
    }
    
    //efface les champs texte de classe "inputresults" et les remet en écriture;
    function eraseResult(){
        for(var i=0; i < inputresultClassElements.length; i++){
            inputresultClassElements[i].value = "";
            inputresultClassElements[i].readOnly = false;
        }
    }
    
    window.addEventListener("load", onLoad, false);
    window.addEventListener("unload", unLoad, false);
    
    exports.getSelectNumberElement = getSelectNumberElement;
    exports.writeTableNumber = writeTableNumber;
    exports.selectedTableNumber = selectedTableNumber;
    exports.displayResults = displayResults;
    exports.eraseResult = eraseResult;
    
})(View);
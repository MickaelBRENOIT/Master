"use strict";

var View = View || {};

(function (exports){
    
    var numClassElements = null;
    var selectNumberElement = null;
    var inputresultClassElements = null;
    var checkClassElements = null;
    
    //méthodes lancées au démarrage de la page
    function onLoad(){
        numClassElements = Array.prototype.slice.call(document.getElementsByClassName("num"),0);
        selectNumberElement = document.getElementById("selectnum");
        inputresultClassElements = Array.prototype.slice.call(document.getElementsByClassName("inputresult"),0);
        checkClassElements = Array.prototype.slice.call(document.getElementsByClassName("check"),0);
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
    
    //affiche le message "msg" dans ce paragraphe
    function displayMessage(msg){
        document.getElementById("resultmsg").textContent = msg;
    }
    
    //efface le contenu des éléments "check"
    function eraseCheck(){
        for(var i = 0; i < checkClassElements.length; i++){
            checkClassElements[i].textContent = "";
        }
    }
    
    //vérifie la validité des éléments "check",
    //si vide, afficher "entrez une valeur",
    //s'il ne contient pas un nombre, afficher "entrez unn nombre"
    //sinon effacer l'ancien message
    //s'il y a des champs textes invalides, afficher un message "données incorectes" et renvoyer faux sinon vrai 
    function checkAnswersValidity(){
        var isValid = false;
        var tmp = "";
        for(var i = 0; i < inputresultClassElements.length; i++){
            isValid = false;
            tmp = inputresultClassElements[i].value;
            if(tmp === ""){
                checkClassElements[i].textContent = "entrez une valeur";
            }else if(isNaN(+tmp)){
                checkClassElements[i].textContent = "entrez un nombre";
            }else{
                checkClassElements[i].textContent = "";
                isValid = true;
            }
        }
        if(!isValid){
            alert("Données incorrectes");
        }
        return isValid;
    }
    
    //vérifie si les champs textes contiennent les valeurs du tableau "results"
    //si un champ texte contient la bonne valeur alors afficher "bonne réponse"
    //sinon afficher "réponse erronée"
    //si toutes les valeurs sont bonnes afficher "Bravo tu as trouvé toutes les bonnes réponses"
    //sinon afficher un message général indiquant le nombre de bonnes réponses
    function checkAnswersCorrectness(results){
        var answer = 0;
        var goodAnswers = 0;
        for(var i = 0; inputresultClassElements.length; i++){
            answer = +inputresultClassElements[i].value;
            if(answer === results[i]){
                checkClassElements[i].textContent = "bonne réponse";
                ++goodAnswers;
            }else{
                checkClassElements[i].textContent = "réponse erronée";
            }
            
            if(goodAnswers === inputresultClassElements.length){
                displayMessage("Bravo tu as trouvé toutes les bonnes réponses.");
            }else{
                displayMessage(goodAnswers+"/"+inputresultClassElements.length+" bonnes réponses");
            }
        }
    }
    
    window.addEventListener("load", onLoad, false);
    window.addEventListener("unload", unLoad, false);
    
    exports.getSelectNumberElement = getSelectNumberElement;
    exports.writeTableNumber = writeTableNumber;
    exports.selectedTableNumber = selectedTableNumber;
    exports.displayResults = displayResults;
    exports.eraseResult = eraseResult;
    exports.displayMessage = displayMessage;
    exports.eraseCheck = eraseCheck;
    exports.checkAnswersValidity = checkAnswersValidity;
    exports.checkAnswersCorrectness = checkAnswersCorrectness;
    
})(View);
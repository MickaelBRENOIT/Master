"use strict";

var TableMultiplication = TableMultiplication || {};

function ecrireTable(n){
    var replaceContent = document.getElementById('tablemult');
    var nblignes = nblignes || 10;
    replaceContent.appendChild(TableMultiplication.DOMTableMultiplicationListe(n, nblignes));
}

(function (){
    function init(event){
        ecrireTable(5);
    }
    window.addEventListener("load", init, false);
})();
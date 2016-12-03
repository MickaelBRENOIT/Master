"use strict";

var TableMultiplication = TableMultiplication || {};

(function (exports){
    
    //renvoie le code HTML du début de la table de multiplication de n
    function tableMultiplicationHTMLDebut(n){
        return '<p>Table de multiplication <span class="num">'+n+'</span></p><ul>';
    }
    
    //revoie le code HTML de la ligne numéro 'numligne' de la table de multiplication de n
    function tableMultiplicationHTMLLigne(n, numligne){
        return '<li><span class="lig">'+numligne+'</span> * <span class="num">'+n+'</span> = <span class="result">'+numligne*n+'</span></li>';
    }
    
    //revoie le code HTML de la fin de la table de multiplication de n
    function tableMultiplicationHTMLFin(n){
        return '</ul>';
    }
    
    //renvoie le code HTML complet de la table de multiplication de n contenant le lognes 1 à nblignes (sans utilisez la concaténation de chaînes avec + dans cette fonction)
    function tableMultiplicationHTML(n,nblignes){
        var t=[];
        for(var i=1; i <= nblignes; i++){
            t.push(tableMultiplicationHTMLLigne(n,i));
        }
        return tableMultiplicationHTMLDebut(n)+t.join('')+tableMultiplicationHTMLFin(n);
    }
    
    exports.tableMultiplicationHTML = tableMultiplicationHTML;

})(TableMultiplication);
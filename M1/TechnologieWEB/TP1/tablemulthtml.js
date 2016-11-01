"use strict";

var TableMultiplication = TableMultiplication || {};

(function(exports){
    
    function tableMultiplicationHTMLDebut(n){
        var debut = '<p>Table de Multiplication de <span class="num">'+n+'</span></p><ul>';
        return debut;
    }
    
    function tableMultiplicationHTMLLigne(n, numligne){
        var calcul = '<li><span class="lig">'+numligne+'</span> * <span class="num">'+n+'</span> = <span class="result">'+n*numligne+'</span></li>';
        return calcul;
    }
    
    function tableMultiplicationHTMLFin(n){
        var fin = '</ul>';
        return fin;
    }
    
    function tableMultiplicationHTML(n, nblignes){
        
    }
    
    //console.log(tableMultiplicationHTMLDebut(2));
    //console.log(tableMultiplicationHTMLLigne(5, 6));
    
    
})(TableMultiplication);
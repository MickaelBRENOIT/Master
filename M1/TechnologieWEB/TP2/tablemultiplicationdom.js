"use strict";

var TableMultiplication = TableMultiplication || {};

(function (exports){
    
    //crée le DOM de la ligne "numligne" de la table de multiplication de "n" et renvoie l'élément "li" correspondant 
    function DOMLigneTableMultiplicationListe(n, numligne){
        var li = document.createElement("li");
        
        var spanNumLigne = DOMUtils.createSpan("lig", numligne);
        li.appendChild(spanNumLigne);
        var txtMult = document.createTextNode(" * ");
        li.appendChild(txtMult);
        var spanNum = DOMUtils.createSpan("num", n);
        li.appendChild(spanNum);
        
        var txtEqual = document.createTextNode(" = ");
        li.appendChild(txtEqual);
        var spanResult = DOMUtils.createSpan("result", n*numligne);
        li.appendChild(spanResult);
        
        return li;
    }
    
    //crée le DOM de la table de multiplication de "n" contenant les "nblignes" premieres lignes et renvoie un fragment de document contenant les éléments "p" et "ul" correspondants.
    function DOMTableMultiplicationListe(n, nblignes){
        var fragment = document.createDocumentFragment();
        
        var p = document.createElement("p");
        p.textContent = "Table de multiplication de ";
        var spanP = DOMUtils.createSpan("num", n);
        p.appendChild(spanP);
        
        var ul = document.createElement("ul");
        for(var i = 1; i < nblignes+1; i++){
            ul.appendChild(DOMLigneTableMultiplicationListe(n, i));
        }
        
        fragment.appendChild(p);
        fragment.appendChild(ul);
        
        return fragment;
    }
    
    //crée le DOM de la ligne "numligne" de la table de multiplication de "n" et renvoie l'élément "tr" correspondant
    function DOMLigneMultiplicationTable(n, numligne){
        var tr = document.createElement("tr");
        
        tr.appendChild(DOMUtils.createTag("td", "num", n));
        tr.appendChild(DOMUtils.createTag("td", "undefined", " * "));
        tr.appendChild(DOMUtils.createTag("td", "lig", numligne));
        tr.appendChild(DOMUtils.createTag("td", "undefined", " = "));
        tr.appendChild(DOMUtils.createTag("td", "result", n*numligne));
        
        return tr;
    }
    
    //crée le DOM de la table de multiplication de "n" avec les "nblignes" premières lignes et renvoie un fragment de document contenant l'élément "table" correspondant
    //création d'un espace de nom ???
    function DOMTableMultiplicationTable(n, nblignes){
        var fragment = document.createDocumentFragment();
        
        var table = document.createElement("table");
        var trTitle = document.createElement("tr");
        var thTitle = document.createElement("th");
        thTitle.colSpan = "5";
        thTitle.appendChild(document.createTextNode("Table de multiplication de "));
        thTitle.appendChild(DOMUtils.createTag("span", "num", n));
        trTitle.appendChild(thTitle);
        table.appendChild(trTitle);
        
        for(var i=1; i < nblignes+1; i++)
            table.appendChild(DOMLigneMultiplicationTable(n, i));
        
        fragment.appendChild(table);
        
        return fragment;
        
    }
    
    console.log(DOMTableMultiplicationTable(7, 10));
    
    exports.DOMTableMultiplicationListe = DOMTableMultiplicationListe; // rnevoie ul
    exports.DOMTableMultiplicationTable = DOMTableMultiplicationTable; // renvoie tbody
    
})(TableMultiplication);
"use strict";

var Domutils = Domutils || {};

(function (exports){
    //insère le noeud "n" comme premier enfant du noeud parent
    function insererNoeud(parent, n){
        parent.insertBefore(n, parent.firstChild);
    }
    
    //insère le texte "txt" au début de l'élément "e"
    //si le premier enfant de "e" est un noeud texte, il faut lui rajouter le texte
    //sinon il faut insérer un nouveau noeud texte
    function insererTexte(e, txt){
        var child = e.firstChild;
        if(child !== null && child.nodeType === Node.TEXT_NODE){
            e.data = txt;
        }else{
            e.insertBefore(document.createTextNode(txt), child);
        }
    }
    
})(Domutils);
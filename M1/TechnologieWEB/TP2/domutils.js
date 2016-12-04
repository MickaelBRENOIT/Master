"use strict";

var DOMUtils = DOMUtils || {};

(function (exports){
    
    //enlève les noeuds enfant de l'élément n    
    function removeChildren(n){
        n.textContent=null;
        /*while(n.firstChild)
			n.removeChild(n.firstChild);*/
        //document.getElementsByTagName(n).textContent="";
    }
    
    //crée un élément "span" de classe "className" et contenant le texte "content"
    function createSpan(className, content){
        var span = document.createElement("span");
        span.classList.add(className);
        span.textContent=content;
        return span;
    }
    
    //fonction personnelle qui permet de créer une balise avec sa classe et son contenu
    function createTag(tagToCreate ,className, content){
        var tag = document.createElement(tagToCreate);
        tag.classList.add(className);
        tag.textContent=content;
        return tag;
    }
    
    exports.removeChildren = removeChildren;
    exports.createSpan = createSpan;
    exports.createTag = createTag;
})(DOMUtils);
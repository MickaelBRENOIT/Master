"use strict";

function sommaireLi(h1){
    var li = document.createElement("li");
    var a = document.createElement("a");
    a.setAttribute("href", "#"+h1.id);
    a.appendChild(document.createTextNode(h1.textContent));
    li.appendChild(a);
    
    return li;
}

function genererSommaire(){
    
}
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
    var fragment = document.createDocumentFragment();
    var h1 = document.createElement("h1");
    h1.appendChild(document.createTextNode("Sommaire"));
    fragment.appendChild(h1);
    var ul = document.createElement("ul");
    ul.setAttribute("id", "ulsommaire");
    h1 = document.getElementsByTagName("h1");
    for (var i = 0, len = h1.length; i<len; ++i) {
        ul.appendChild(sommaireLi(h1[i]));
    }
    fragment.appendChild(ul);
    DomUtils.insertFirst(document.body, fragment);
}
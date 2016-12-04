"use strict";

var Data = Data || {};

(function (exports){
    
    //contient le numéro de la table de multiplication actuelle
    var tableNumber;
    
    function onLoad(){
        
    }
    
    function unLoad(){
        
    }
    
    //met à jour le numéro de la table de multiplication actuelle
    function changeTableNumber(num){
        tableNumber = num;
    }
    
    exports.changeTableNumber = changeTableNumber;
    
    window.addEventListener("load", onLoad, false);
    window.addEventListener("unload", unLoad, false);
    
})(Data);
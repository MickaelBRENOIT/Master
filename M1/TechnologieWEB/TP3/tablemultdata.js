"use strict";

var Data = Data || {};

(function (exports){
    
    //contient le numéro de la table de multiplication actuelle (à remplacer)
    /*var tableNumber;*/
    
    function onLoad(){
        
    }
    
    function unLoad(){
        
    }
    
    //met à jour le numéro de la table de multiplication actuelle (à remplacer)
    /*function changeTableNumber(num){
        tableNumber = num;
    }*/
    
    //"classe" représentant une table de multiplication
    //son constructeur prend en paramètres le numéro de la table et le nombre de résultats à calculer
    //l'objet stocke ces données et le tableau contenant les résultats des multiplications du numéro par la ligne correspondante
    
    function MultiplicationTable(tableNumber, nbresults){
        nbresults = nbresults || 10;
        this._nbresults = nbresults;
        this._tableNumber = tableNumber;
        this._values = [];
        for(var i=1; i < nbresults+1; i++){
            this._values.push(i*this._tableNumber);
        }
        //efface les résultats lorsqu'une nouvelle table est appelée.
        View.eraseResult();
        /*if (!(this instanceof MultiplicationTable)) {
            return new MultiplicationTable (nbresults, tableNumber);
        }*/
    }
    
    //renvoie le numéro de la table
    MultiplicationTable.prototype.number = function(){
        return this._tableNumber;        
    };
    
    //revoie la valeur numéro "i" 
    MultiplicationTable.prototype.value = function(i){
        return this._values[i-1];    
    };
    
    //renvoie le tableau des valeurs
    MultiplicationTable.prototype.values = function(){
        return this._values.slice(0);    
    };
    
    exports.MultiplicationTable = MultiplicationTable;
    
    //le remplacement
    var currentMultiplicationTable = null;
    
    //le remplacement
    function changeTableNumber(num){
        currentMultiplicationTable = new MultiplicationTable(num);
    }
    
    //renvoie le numéro de la table
    function tableNumber(){
        return currentMultiplicationTable.number();
    }
    
    //renvoie la valeur numéro "i"
    function tableValue(i){
        return currentMultiplicationTable.value(i);
    }
    
    //renvoie le tableau des valeurs
    function tableValues(){
        return currentMultiplicationTable.values();
    }
    
    exports.changeTableNumber = changeTableNumber;
    exports.tableNumber = tableNumber;
    exports.tableValue = tableValue;
    exports.tableValues = tableValues;
    
    window.addEventListener("load", onLoad, false);
    window.addEventListener("unload", unLoad, false);
    
})(Data);
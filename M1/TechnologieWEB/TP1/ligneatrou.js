"use strict";

//création du module TxtUtils
var TxtUtils = TxtUtils || {};

(function(exports){

    //constructeur qui prend en paramètres une ligne de format et un caractère à trou et calcule le tableau correspondant
    function LigneAtrous(ligneFormat, caractereAtrou){
        
        //split la chaîne de caractère suivant un caractère
        //Exemple : "bonjour je suis % Mickael et il fait % froid" ==> tab[] = {"bonjour je suis", "Mickael et il fait", "froid"}
        var _ligne = ligneFormat.split(caractereAtrou);
        var newTab = [];
        
        while(_ligne.length > 0){
            var firstElement = _ligne.shift(_ligne.length);
            newTab.push(firstElement);
            if(_ligne.length != 0)
                newTab.push('');
        }
        this.newTab = newTab;
    }
    
    //méthode qui renvoie une chaîne contenant la ligne où les trous sont remplis avec les valeurs du tableau contenus de trous passé en paramètre
    LigneAtrous.prototype.ligneRemplie = function(tab){
        var len = this.newTab.length;
        var newTab = this.newTab;
        
        for(var i=0, j=0; i < len+1; i++){
            if(newTab[i] == ''){
                newTab[i] = tab[j];
                ++j;
            }
        }
        return newTab.join('');
    }
    
    //exporte la "classe" LigneAtrous
    exports.LigneAtrous = LigneAtrous;

})(TxtUtils);

var t = new TxtUtils.LigneAtrous('bonjour je suis % et il fait % .', '%');
console.log(t.ligneRemplie(['Mickael', 'froid']));


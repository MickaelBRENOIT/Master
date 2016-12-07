function numeroterSections(num){
    var allH1 = document.getElementsByTagName("h1");
    for(var i = 0; i < allH1.length; i++){
        Domutils.insererTexte(allH1[i], num.numero[i+1]);
    }
}

function init(){
    numeroterSections({
        numero : function(i) {
            return i+". ";    
        }
    });
    genererSommaire();
}

window.addEventListener("load", init, false);

function NumeroNombreSuffixe(suffixe){
    this.suffixe = suffixe;
}

NumeroNombreSuffixe.prototype.numero = function(i){
    return i + this.suffixe;    
};

numeroterSections(new NumeroNombreSuffixe(" - "));

function Numero(num, suffixe){
    this.num = num;
    this.suffixe = suffixe;
}

Numero.prototype.numero = function(i){
    return this.num.numero(i) + this.suffixe;
}

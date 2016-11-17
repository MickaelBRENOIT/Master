"use script";

var View = View || {};

(function(exports){
    var numClassElements;
    //var num;
    
    function onLoad(){
        numClassElements = document.getElementsByClassName("num");
    }
    exports.onLoad=onLoad;
    
    function writeTableNumbers(num){
        var i=0;
        for(i; i<numClassElements.length; i++){
            numClassElements[i].textcontent = null;
            numClassElements[i].appendchild(document.createTextNode(num));
        }
    }
    exports.writeTableNumbers=writeTableNumbers;
    
    
});
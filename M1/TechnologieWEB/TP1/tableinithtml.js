"use strict";

var htmlTable = TableMultiplication.tableMultiplicationHTML;

function ecrireTableMultiplication(n, nblignes) {
  nblignes = nblignes || 12;
  document.getElementById("tablemult").innerHTML = htmlTable(n, nblignes);
}

(function() {
function init(event) {
  ecrireTableMultiplication(5,12);
}
window.addEventListener("load", init, false);
})();

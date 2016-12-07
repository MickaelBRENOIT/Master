var selectedh1 = null;

function onclickSommaire(event) {
    var target = event.target;
    if (target.tagName==="A")
    {
        if (selectedH1) { selectedH1.className = ""; }
        var h1id = target.getAttribute("href").substring(1);
        selectedH1 = document.getElementById(h1id);
        selectedH1.className = "h1selected";
    }
}
var sommaire = document.getElementById("ulsommaire");
sommaire.addEventListener("click",onclickSommaire);
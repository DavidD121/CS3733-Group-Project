/*eslint-env browser*/
/*global console*/
// eslint-disable-line no-console
function init() {
    selectChoice();
}

function selectChoice() {
    const btn = document.getElementById("selectChoiceBtn")
    btn.addEventListener("click", function() {
        var uuid = "aabbccdd"
        window.location = "./choiceview.html" + "?uuid=" + uuid; 
    });
    
    
    const btn3 = document.getElementById("createChoiceBtn")
    btn3.addEventListener("click", onAddChoice())
        
}

document.addEventListener("DOMContentLoaded", function() {
    init();
});
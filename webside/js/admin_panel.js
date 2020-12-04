function init(){
    const btn = document.getElementById("genChoiceReportBtn");
    btn.addEventListener("click", goToChoiceReport);
}

function goToChoiceReport() {
    window.location = "./choicereport.html";
}

document.addEventListener("DOMContentLoaded", function() {
    init();
});


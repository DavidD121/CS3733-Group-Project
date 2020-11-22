function init() {
    selectChoice();
}

function selectChoice() {
    let choiceUUID = document.location.search.replace(/^.*?\=/, "");
    console.log(choiceUUID);
}

document.addEventListener("DOMContentLoaded", function() {
    init();
});
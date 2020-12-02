function loadLikes(alt, i){
    let l = alt["likes"]
    let x = l["number"]
    document.getElementById("alt" + i + "-likes").innerHTML = x;
}
function loadDislikes(alt, i){
    let l = alt["dislikes"]
    let x = l["number"]
    document.getElementById("alt" + i + "-dislikes").innerHTML = x;
}
function loadFeedback(alt, i){
    document.getElementById("alt" + i + "-feedback").innerHTML = alt["feedback"];
}
function loadApproval(alt, i){

}
function loadAlt(alt, i){
    document.getElementById("alt" + i + "-name").innerHTML = alt["name"];
    loadLikes(alt, i);
    loadDislikes(alt, i);
   // loadFeedback(alt, i);
    //loadApproval(alt, i);
}

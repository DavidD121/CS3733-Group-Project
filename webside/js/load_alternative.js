function loadLikes(alt, i){
    let l = alt["likes"]
    let likes = l["number"];
    let likedBy = l["participants"]
    document.getElementById("alt" + i + "-likes").innerHTML = likes;
    load_users_rating(document.getElementById("alt" + i + "-likes-users"), likedBy);
}

function loadDislikes(alt, i){
    let l = alt["dislikes"]
    let dislikes = l["number"];
    let dislikedBy = l["participants"]
    document.getElementById("alt" + i + "-dislikes").innerHTML = dislikes;
    load_users_rating(document.getElementById("alt" + i + "-dislikes-users"), dislikedBy);
}

function load_users_rating(base_element, users) {
    users.forEach(function(user){
        create_user_li(base_element, user);
    });
}

function load_user_selected_ratings() {
    for(let i = 1; i <= totalAlternatives; i++) {
        load_user_selected_alt_rating(i, "like");
        load_user_selected_alt_rating(i, "dislike");
    }
}

function load_user_selected_alt_rating(alt, type) {
    let usersList = document.getElementById("alt" + alt + "-" + type + "s-users").getElementsByTagName("li");
        for(let i = 0; i < usersList.length; i++) {
            if(usersList[i].innerHTML == userName) {
                 document.getElementById("alt" + alt + "-" + type).classList.add("ratingSelected");
            }
        }
}

function create_user_li(baseElement, userName) {
    let element = document.createElement("li");
    element.innerHTML = userName;
    baseElement.appendChild(element);
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

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
    clearAlt(i);
    loadLikes(alt, i);
    loadDislikes(alt, i);
    loadFeedback(alt, i);
    //loadApproval(alt, i);
}

function clearAlt(i) {
    //Clearing Feedback
     removeChildrenNodes(document.getElementById("alt" + i + "-feedback-container"));
    
    //Clearing likes
    removeChildrenNodes(document.getElementById("alt" + i + "-likes-users"));
    
    //Clearing dislikes
    removeChildrenNodes(document.getElementById("alt" + i + "-dislikes-users"));

}

function removeChildrenNodes(parent) {
    while(parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
} 

function loadFeedback (alt, i) {
    let feedbackContainer = document.getElementById("alt" + i + "-feedback-container");
    alt["listOfFeedback"].forEach(function(feedback) {
        let feedbackElem = createFeedbackElement(feedback["author"], feedback["timeCreated"], feedback["description"]);
        feedbackContainer.appendChild(feedbackElem);
    })

}
 
function createFeedbackElement(author, timeStamp, description) {
    let feedbackElem = document.createElement("div");
    
    feedbackElem.classList.add("userFeedback");
    
    let authorElem = document.createElement("h4");
    authorElem.innerHTML = author;
    authorElem.classList.add("feedbackAuthor");
    
    let timeStampElem = document.createElement("h4");
    timeStampElem.innerHTML = timeStamp;
    timeStampElem.classList.add("feedbackTime");
    
    let descriptionElem = document.createElement("h5");
    descriptionElem.innerHTML = description;
    descriptionElem.classList.add("feedbackDesc")
    feedbackElem.appendChild(authorElem);
    feedbackElem.appendChild(timeStampElem);
    feedbackElem.appendChild(descriptionElem);
    
    return feedbackElem;
}
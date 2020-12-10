function deleteInit(){
    const btn = document.getElementById("deleteChoicesBtn");
    btn.addEventListener("click", deleteChoices);
}

function deleteChoices() {
    let data = {};
    data = JSON.stringify(data);
    let xhr = new XMLHttpRequest();
    let input = document.getElementById("deleteChoicesTime").value;

    xhr.open("POST", admin_delete_choices + input, true);
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(data);
    
    
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            let info = JSON.parse(xhr.responseText);
            if (info["statusCode"] == 200) {
                alert("Choices deleted!")
            }
        }
    }
}

document.addEventListener("DOMContentLoaded", function() {
    deleteInit();
});
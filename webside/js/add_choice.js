function onAddChoice(e) {
    let data = {};
    data["description"] = "";
    data["teamMembers"] = 3;
    
    for(let i = 0; i < 5; i++) {
        let alternative = "";
        
        if(!!alternative) {
            data["alternative" + i] = alternative;
        }
    }
    
    var js = JSON.stringify(data);
    console.log("JS:" + js);
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", choice_add_url + "/" + name, true);
}
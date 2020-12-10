// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = "https://v4ylc7usk6.execute-api.us-east-1.amazonaws.com/test/";



var choice_add_url = base_url + "Choice";               // POST
var choice_get_url = base_url + "Choice";               // GET with {choiceUUID}
var choice_complete_url = base_url + "Choice";          // POST with {choiceUUID}
var choice_and_alternative_url = base_url + "Choice";   // POST with {choiceUUID} and {alternativeIndex}
var admin_get_choicereport = base_url + "Admin/ChoiceReport";
var admin_delete_choices = base_url + "Admin/Delete/";
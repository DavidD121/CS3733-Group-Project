package tbd.http;

import tbd.model.Alternative;
import tbd.model.Choice;

/** Arbitrary decision to make this a String and not a native double. */
public class GetChoiceResponse {
	//public int statusCode;  // HTTP status code.
	//public String error;
	
	public String uuid;
	public String description;
	public Integer isLocked;
	public Integer teamMembers;
	public GetChoiceAlternative alternative1;
	public GetChoiceAlternative alternative2;
	public GetChoiceAlternative alternative3;
	public GetChoiceAlternative alternative4;
	public GetChoiceAlternative alternative5;
	
	
	// TODO: Remember to comment out that I got this from the internet
    public boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
	
	public GetChoiceResponse (Choice choice) {
		this.uuid = choice.uuid;
		this.description = choice.description;
		this.isLocked = choice.isLocked;
		this.teamMembers = choice.teamMembers;
		if(!isNullOrEmpty(choice.alternativeObject1.name)) {
			this.alternative1 = new GetChoiceAlternative(choice.alternativeObject1.name, choice.alternativeObject1.likes, choice.alternativeObject1.dislikes, choice.alternativeObject1.likedUsers, choice.alternativeObject1.dislikedUsers, choice.alternativeObject1.listOfFeedback);
			System.out.println("Made it here.... in ChoiceResponse " + choice.alternativeObject1.name);
	}
		System.out.println("Name: " + choice.alternativeObject2.name);
		if(!isNullOrEmpty(choice.alternativeObject2.name)) {
		this.alternative2 = new GetChoiceAlternative(choice.alternativeObject2.name, choice.alternativeObject2.likes, choice.alternativeObject2.dislikes, choice.alternativeObject2.likedUsers, choice.alternativeObject2.dislikedUsers, choice.alternativeObject2.listOfFeedback);
		}
		if(!isNullOrEmpty(choice.alternativeObject3.name)) {
		this.alternative3 = new GetChoiceAlternative(choice.alternativeObject3.name, choice.alternativeObject3.likes, choice.alternativeObject3.dislikes, choice.alternativeObject3.likedUsers, choice.alternativeObject3.dislikedUsers,choice.alternativeObject3.listOfFeedback);
		}
		if(!isNullOrEmpty(choice.alternativeObject4.name)) {
		this.alternative4 = new GetChoiceAlternative(choice.alternativeObject4.name, choice.alternativeObject4.likes, choice.alternativeObject4.dislikes, choice.alternativeObject4.likedUsers, choice.alternativeObject4.dislikedUsers,choice.alternativeObject4.listOfFeedback);
		}
		if(!isNullOrEmpty(choice.alternativeObject5.name)) {
		this.alternative5 = new GetChoiceAlternative(choice.alternativeObject5.name, choice.alternativeObject5.likes, choice.alternativeObject5.dislikes, choice.alternativeObject5.likedUsers, choice.alternativeObject5.dislikedUsers,choice.alternativeObject5.listOfFeedback);
		}
		//this.statusCode = statusCode;
		//this.error = "";

	}
	
	public GetChoiceResponse (String errorMessage) {
		this.uuid = "-1";
		//this.statusCode = statusCode;
		//this.error = errorMessage;
	}
	
	public String toString() {
		/*if (statusCode / 100 == 2) {  // too cute?
			return "Result(" + "yes" + ")";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
		*/
		return "Yes!";
	}
}

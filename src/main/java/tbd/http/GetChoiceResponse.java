package tbd.http;

import tbd.model.Choice;

/** Arbitrary decision to make this a String and not a native double. */
public class GetChoiceResponse {
	public int statusCode;  // HTTP status code.
	public String error;
	
	public String uuid;
	public String description;
	public int isLocked;
	public int maxUsers;
	public String alternative1;
	public String alternative2;
	public String alternative3;
	public String alternative4;
	public String alternative5;
	
	public GetChoiceResponse (int statusCode, Choice choice) {
		this.uuid = choice.uuid;
		this.description = choice.description;
		this.isLocked = choice.isLocked;
		this.maxUsers = choice.maxUsers;
		this.alternative1 = choice.alternative1;
		this.alternative2 = choice.alternative2;
		this.alternative3 = choice.alternative3;
		this.alternative4 = choice.alternative4;
		this.alternative5 = choice.alternative5;
		
		this.statusCode = statusCode;
		this.error = "";

	}
	
	public GetChoiceResponse (int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) {  // too cute?
			return "Result(" + "yes" + ")";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}

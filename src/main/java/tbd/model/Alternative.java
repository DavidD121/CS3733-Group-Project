package tbd.model;

import java.util.ArrayList;

public class Alternative {

	public final String name;
	public final String choiceID;
	public int alternativeID;
	public int likes;
	public int dislikes;
	public String listOfFeedback;
	public ArrayList<String> likedUsers;
	public ArrayList<String> dislikedUsers;

	
	public Alternative(String name, String choiceID, int alternativeID) {
		this.name = name;
		this.choiceID = choiceID;
		this.alternativeID = alternativeID;
	};
	
	

	public Alternative(String name, String choiceID, int likes, int dislikes, ArrayList<String> likedUsers, ArrayList<String> dislikedUsers, String listOFFeedback) {
		this.name = name;
		this.choiceID = choiceID;
		this.likes = likes;
		this.dislikes = dislikes;
		this.likedUsers = likedUsers;
		this.dislikedUsers = dislikedUsers;
		this.listOfFeedback = listOfFeedback;
	}
	
	
	public String toStringJSON() {
		return "{\"name\":" + name + ", \"likes\":" + likes + ", \"dislikes\":" + dislikes + ", \"feedback\": \"\" }";
	}
	
}


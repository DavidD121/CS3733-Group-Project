package tbd.model;

import java.util.ArrayList;

public class Alternative {

	public final String name;
	public final String choiceID;
	public int likes;
	public int dislikes;
	public ArrayList<Feedback> listOfFeedback;

	
	public Alternative(String name, String choiceID) {
		this.name = name;
		this.choiceID = choiceID;
	};

	public Alternative(String name, String choiceID, int likes, int dislikes, ArrayList<Feedback> listOfFeedback) {
		this.name = name;
		this.choiceID = choiceID;
		this.likes = likes;
		this.dislikes = dislikes;
		this.listOfFeedback = listOfFeedback;
	}
	
}


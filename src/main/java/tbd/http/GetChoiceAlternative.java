package tbd.http;

import java.util.ArrayList;

import tbd.model.Feedback;

public class GetChoiceAlternative {

	public final String name;
	public GetChoiceRateResponse likes;
	public GetChoiceRateResponse dislikes;
	public ArrayList<Feedback> listOfFeedback;
	
	GetChoiceAlternative(String name, int likes, int dislikes, ArrayList<String> likedUsers, ArrayList<String> dislikedUsers, String listOfFeedback){
		this.name = name;
		this.likes = new GetChoiceRateResponse(likes, likedUsers);
		this.dislikes = new GetChoiceRateResponse(dislikes, dislikedUsers);
		this.listOfFeedback = new ArrayList<Feedback>();
	}
	
}

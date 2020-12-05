package tbd.http;

import java.util.ArrayList;

public class GetChoiceRateResponse {

	public int number;
	public ArrayList<String> participants;
	
	public GetChoiceRateResponse(int number, ArrayList<String> participants){
		this.number = number;
		this.participants = participants;
	}
}

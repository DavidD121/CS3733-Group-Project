package tbd.http;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import tbd.model.Feedback;

public class AddFeedbackResponse {
	
	public int statusCode;
	public String timeCreated;
	// Remember to add the time created when returning it! this is just for the sake of the temporary HTML on Jon and Travis' end.
	public AddFeedbackResponse(int statusCode, Timestamp timestamp){
		this.statusCode = statusCode;
		
		Date date = new Date();
		date.setTime(timestamp.getTime());
		
		SimpleDateFormat DateFor = new SimpleDateFormat("hh:mm dd/MM/yyyy");

		DateFor.setTimeZone(TimeZone.getTimeZone("EST"));  
		String stringDate= DateFor.format(date);
		this.timeCreated = stringDate;
	}

	public AddFeedbackResponse(int statusCode){
		this.statusCode = statusCode;
	}
}

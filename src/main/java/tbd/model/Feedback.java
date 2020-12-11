package tbd.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Feedback {

        
    public String author;
    public String timeCreated;
    public String description;
    
	public Feedback(String author, Timestamp timeCreated, String description) {
		this.author = author;
		Date d = new Date();
		d.setTime(timeCreated.getTime());
		String formattedDate = new SimpleDateFormat("hh:mm MM/dd/yyyy").format(d);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeCreated);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//sdf.setTimeZone(TimeZone.getDefault());
		//Will print on your default Timezone
		
		
		Date date = new Date();
		date.setTime(timeCreated.getTime());
		
		SimpleDateFormat DateFor = new SimpleDateFormat("hh:mm dd/MM/yyyy");

		DateFor.setTimeZone(TimeZone.getTimeZone("EST"));  
		String stringDate= DateFor.format(date);
		
		this.timeCreated = stringDate;
		//this.timeCreated = formattedDate;
		this.description = description;
		
	}
	
	public Feedback(String author, String description) {
		this.author = author;
		this.description = description;
	}
	
	public Feedback() {
	}

}

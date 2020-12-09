package tbd.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import tbd.model.Alternative;
import tbd.model.Choice;
import tbd.model.Constant;

public class FeedbackDAO { 

	java.sql.Connection conn;
	
	final String tblName = "Constants";   // Exact capitalization
	final String choiceTable = "ChoiceTable";   // Exact capitalization
	final String alternativeTable = "AlternativeTable";
	final String rateAlternativeTable = "UserRateAlternativeTable";
	final String userTable = "UserTable";
	final String feedbackTable = "FeedbackTable";

	public FeedbackDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    		System.out.println("Connected to database!");
    	} catch (Exception e) {
    		System.out.println("Failed to connect");
    		conn = null;
    	}
    }
    

    
    private String generateName(ResultSet resultSet) throws Exception{    	
    	System.out.println("Generating name");
    	String name = resultSet.getString("name");
    	System.out.println("made it past resultSet name " + name);
    	return name;
    }
    
    
    public String getName(String userID) throws Exception{
    	try {
            PreparedStatement psAlternative = conn.prepareStatement("SELECT * FROM " + userTable + " WHERE userID=?;");
            psAlternative.setString(1, userID);
            ResultSet resultSetAlternative = psAlternative.executeQuery();
            while (resultSetAlternative.next()) {
            	String name = generateName(resultSetAlternative);
                return name;
            }        
        } catch (Exception e) {
            System.out.println("Failed to getName!");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    		return "failed";
        	
    	
    }
    
    public Timestamp addFeedback(String choiceUUID, int alternativeIndex, String userID, String feedback) throws Exception {
        try {
        	PreparedStatement ps = conn.prepareStatement("INSERT INTO " + feedbackTable + " (alternativeID, choiceID, userID, description, user, timeCreated) values(?,?,?,?,?,?);");
            ps.setInt(1,  alternativeIndex);
            ps.setString(2,  choiceUUID);
            ps.setString(3, userID);
            ps.setString(4, feedback);
            ps.setString(5, getName(userID));

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            ps.setTimestamp(6, timestamp);
            System.out.println("made it here");

            ps.execute();
            System.out.println("added feedback");

            return timestamp;

        } catch (Exception e) {
            System.out.println("Failed to add choice");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }
    
    
    

    
}
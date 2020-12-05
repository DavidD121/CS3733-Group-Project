package tbd.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import tbd.model.Alternative;
import tbd.model.Choice;
import tbd.model.Constant;

public class CreateChoiceDAO { 

	java.sql.Connection conn;
	
	final String tblName = "Constants";   // Exact capitalization
	final String choiceTable = "ChoiceTable";   // Exact capitalization
	final String alternativeTable = "AlternativeTable";
	final String rateAlternativeTable = "UserRateAlternativeTable";
	final String userTable = "UserTable";

	public CreateChoiceDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    		System.out.println("Connected to database!");
    	} catch (Exception e) {
    		System.out.println("Failed to connect");
    		conn = null;
    	}
    }
    
	// TODO: Remember to comment out that I got this from the internet
    public boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
    
    
    public boolean addChoice(Choice choice) throws Exception {
        try {
        	PreparedStatement ps = conn.prepareStatement("INSERT INTO " + choiceTable + " (UUID, description, isLocked, teamMembers, timeCreated) values(?,?,?,?,?);");
            ps.setString(1,  choice.uuid);
            ps.setString(2,  choice.description);
            ps.setInt(3, choice.isLocked);
            ps.setInt(4, choice.teamMembers);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            ps.setTimestamp(5, timestamp);
            System.out.println("made it here");
            
            // TODO: Need to add an ID linked to the choice.
            	
            addAlternative(new Alternative(choice.alternative1, choice.uuid, 1));
            addAlternative(new Alternative(choice.alternative2, choice.uuid, 2));
            if(!isNullOrEmpty(choice.alternative3)) {
            addAlternative(new Alternative(choice.alternative3, choice.uuid, 3));
            }
            if(!isNullOrEmpty(choice.alternative4)) {
            addAlternative(new Alternative(choice.alternative4, choice.uuid, 4));
            }
            if(!isNullOrEmpty(choice.alternative5)) {
            addAlternative(new Alternative(choice.alternative5, choice.uuid, 5));
            System.out.println("Alt 5" + choice.alternative5);
            }
            System.out.println("Here!");
            ps.execute();
            System.out.println("added choice");

            return true;

        } catch (Exception e) {
            System.out.println("Failed to add choice");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }
    
    public boolean addAlternative(Alternative alternative) throws Exception {
        try {
        	PreparedStatement ps = conn.prepareStatement("INSERT INTO " + alternativeTable + " (name, choiceID, AlternativeID, likes, dislikes) values(?,?,?,?,?);");
            ps.setString(1,  alternative.name);
            
            ps.setString(2,  alternative.choiceID);
            ps.setInt(3, alternative.alternativeID);
            ps.setInt(4,  0);
            ps.setInt(5,  0);

            ps.execute();
            System.out.println("added alternative");

            return true;

        } catch (Exception e) {
            System.out.println("Failed to add choice");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }
    

    
}
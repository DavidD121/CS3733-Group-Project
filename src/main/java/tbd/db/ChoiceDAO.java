package tbd.db;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import tbd.model.Alternative;
import tbd.model.Choice;
import tbd.model.ChoiceReportChoice;
import tbd.model.Constant;
import tbd.model.User;

public class ChoiceDAO { 

	java.sql.Connection conn;
	
	final String tblName = "Constants";   // Exact capitalization
	final String choiceTable = "ChoiceTable";   // Exact capitalization
	final String alternativeTable = "AlternativeTable";

    public ChoiceDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    		System.out.println("Connected to database!");
    	} catch (Exception e) {
    		System.out.println("Failed to connect");
    		conn = null;
    	}
    }
    
    public boolean closeChoice(String uuid) throws Exception {
    	try {
    		
            PreparedStatement ps = conn.prepareStatement("update ChoiceTable SET isLocked = 1, timeCompleted = ? where (UUID = ?);");

            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            ps.setTimestamp(1, timestamp);
            ps.setString(2, uuid);
            ps.executeUpdate();
            
            ps.close();
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
            throw new Exception("Failed in closing choice: " + e.getMessage());
    	}
    }

	public List<ChoiceReportChoice> getAllChoices() throws Exception {
		try {
			List<ChoiceReportChoice> choices = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + choiceTable + ";");
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                choices.add(generateChoiceReportChoice(resultSet));
            }
            resultSet.close();
            ps.close();
            
            return choices;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting all choices: " + e.getMessage());
        }
	}

	private ChoiceReportChoice generateChoiceReportChoice(ResultSet resultSet) throws Exception {
    	String name = resultSet.getString("description");
    	String uuid= resultSet.getString("UUID");
    	String timeCreated = resultSet.getTimestamp("timeCreated") == null ? null : resultSet.getTimestamp("timeCreated").toString();
    	String timeCompleted= resultSet.getTimestamp("timeCompleted") == null ? null : resultSet.getTimestamp("timeCompleted").toString();
    
		return new ChoiceReportChoice(name, uuid, timeCreated, timeCompleted);
	}

}
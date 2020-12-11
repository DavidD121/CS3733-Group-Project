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
    		setTimeZone();
    	} catch (Exception e) {
    		System.out.println("Failed to connect");
    		conn = null;
    	}
    }
    
    public void setTimeZone() throws Exception {
        try {
        	PreparedStatement ps = conn.prepareStatement("SET time_zone = \"-5:00\";");
        	ps.execute();
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed to set timezone: " + e.getMessage());
        }

    }
    
    public boolean closeChoice(String uuid, int index) throws Exception {
    	try {
    		
            PreparedStatement ps = conn.prepareStatement("update ChoiceTable SET approvedAlternative = ?, timeCompleted = now() where (UUID = ?);");
            ps.setInt(1, index);
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

	public void deleteChoices(float days) throws Exception {
		try {
			if(days == 0) {
				PreparedStatement ps = conn.prepareStatement("delete from ChoiceTable where UUID != \"\";");
				ps.executeUpdate();
				
			} else {
	            PreparedStatement ps = conn.prepareStatement("delete from ChoiceTable where timeCreated <= (now() - interval ? second) AND UUID != \"\";");
	            float seconds = days * 86400;
	            ps.setFloat(1, seconds);
	            ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
            throw new Exception("Failed in deleting choices: " + e.getMessage());	
		}
		
	}

}
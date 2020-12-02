package tbd.db;

import java.sql.*;
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
    
    public boolean addChoice(Choice choice) throws Exception {
        try {
        	PreparedStatement ps = conn.prepareStatement("INSERT INTO " + choiceTable + " (UUID, description, isLocked, maxUsers) values(?,?,?,?);");
            ps.setString(1,  choice.uuid);
            ps.setString(2,  choice.description);
            ps.setInt(3, choice.isLocked);
            ps.setInt(4, choice.maxUsers);
            System.out.println("made it here");
            
            // TODO: Need to add an ID linked to the choice.
            addAlternative(new Alternative(choice.alternative1, choice.uuid));
            addAlternative(new Alternative(choice.alternative2, choice.uuid));
            addAlternative(new Alternative(choice.alternative3, choice.uuid));
            addAlternative(new Alternative(choice.alternative4, choice.uuid));
            addAlternative(new Alternative(choice.alternative5, choice.uuid));

            ps.execute();
            System.out.println("added choice");

            return true;

        } catch (Exception e) {
            System.out.println("Failed to add choice");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }
    
    public Choice getChoice(String uuid) throws Exception {
        
        try {
            Choice choice = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + choiceTable + " WHERE uuid=?;");
            ps.setString(1,  uuid);
            ResultSet resultSet = ps.executeQuery();
            
            System.out.println("getChoice made it here");
            
            PreparedStatement psAlternative = conn.prepareStatement("SELECT * FROM " + alternativeTable + " WHERE ChoiceID=?;");
            psAlternative.setString(1,  uuid);
            ResultSet resultSetAlternative = psAlternative.executeQuery();
            System.out.println("getChoice alternative made it here");

            ArrayList<String> alternatives = new ArrayList<String>();
            while (resultSet.next()) {
                System.out.println("getChoice resultSet made it here");
                	
            	while(resultSetAlternative.next()) {
                    System.out.println("getChoice resultSetAlternative made it here");
                    	
            		String alternative = generateAlternative(resultSetAlternative);
            		alternatives.add(alternative);
            	}
            	choice = generateChoice(resultSet, alternatives);
            }
            resultSet.close();
            ps.close();
            
            return choice;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }
    
    public boolean addAlternative(Alternative alternative) throws Exception {
        try {
        	PreparedStatement ps = conn.prepareStatement("INSERT INTO " + alternativeTable + " (name, choiceID) values(?,?);");
            ps.setString(1,  alternative.name);
            ps.setString(2,  alternative.choiceID);
            System.out.println("made it here");

            ps.execute();
            System.out.println("added alternative");

            return true;

        } catch (Exception e) {
            System.out.println("Failed to add choice");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    // generates a list of alternatives to be used in generateChoice.
    private String generateAlternative(ResultSet resultSet) throws Exception{
    	ArrayList<String> alternatives = new ArrayList<String>();
    	alternatives.add(resultSet.getString("name"));

    	return resultSet.getString("name");
    }
    
    private Choice generateChoice(ResultSet resultSetChoice, ArrayList<String> alternatives) throws Exception {
    	String uuid = resultSetChoice.getString("UUID");
    	String description = resultSetChoice.getString("description");
    	int isLocked = resultSetChoice.getInt("isLocked");
    	int maxNumbers = resultSetChoice.getInt("maxUsers");

    
		return new Choice(uuid, description, isLocked, maxNumbers, alternatives.get(0), alternatives.get(1), alternatives.get(2), alternatives.get(3), alternatives.get(4));

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
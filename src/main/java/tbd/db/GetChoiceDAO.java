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

public class GetChoiceDAO { 

	java.sql.Connection conn;
	
	final String tblName = "Constants";   // Exact capitalization
	final String choiceTable = "ChoiceTable";   // Exact capitalization
	final String alternativeTable = "AlternativeTable";
	final String rateAlternativeTable = "UserRateAlternativeTable";
	final String userTable = "UserTable";

	public GetChoiceDAO() {
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
            ArrayList<Alternative> alternativesObjectList = new ArrayList<Alternative>();

            while (resultSet.next()) {
                System.out.println("getChoice resultSet made it here");
                	
            	while(resultSetAlternative.next()) {
                    System.out.println("getChoice resultSetAlternative made it here");
                    	
            		//String alternative = generateAlternative(resultSetAlternative);
                    Alternative alt = generateAlternativeObject(resultSetAlternative);
            		alternativesObjectList.add(alt);
            	}
            	choice = generateChoice(resultSet, alternativesObjectList);
            }
            resultSet.close();
            ps.close();
            
            return choice;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }
    
    
    public ArrayList<String> getLikeAlternativeNames(int alternativeID, String choiceID) throws Exception{
        ArrayList<String> names = new ArrayList<String>();
    	try {
            PreparedStatement psAlternative = conn.prepareStatement("SELECT * FROM " + rateAlternativeTable + " WHERE choiceID=? and alternativeID=? and liked=1;");
            psAlternative.setString(1, choiceID);
            psAlternative.setInt(2, alternativeID);
            ResultSet resultSetAlternative = psAlternative.executeQuery();
            while (resultSetAlternative.next()) {
            	String name = generateName(resultSetAlternative);
                //System.out.println("getLikeAlternativeNames:" + name);
                names.add(name);
            }        
        } catch (Exception e) {
            System.out.println("Failed to getName!");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    		return names;
        	
    }
    
    public ArrayList<String> getDislikeAlternativeNames(int alternativeID, String choiceID) throws Exception{
        ArrayList<String> names = new ArrayList<String>();
    	try {
            PreparedStatement psAlternative = conn.prepareStatement("SELECT * FROM " + rateAlternativeTable + " WHERE choiceID=? and alternativeID=? and liked=0;");
            psAlternative.setString(1, choiceID);
            psAlternative.setInt(2, alternativeID);
            ResultSet resultSetAlternative = psAlternative.executeQuery();
            while (resultSetAlternative.next()) {
            	String name = generateName(resultSetAlternative);
                //System.out.println("getLikeAlternativeNames:" + name);
                names.add(name);
            }        
        } catch (Exception e) {
            System.out.println("Failed to getName!");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    		return names;
        	
    }
    
    
    
   
    
   

    // generates a list of alternatives to be used in generateChoice.
    private String generateAlternative(ResultSet resultSet) throws Exception{
    	ArrayList<String> alternatives = new ArrayList<String>();
    	alternatives.add(resultSet.getString("name"));

    	return resultSet.getString("name");
    }
    
    // generates a list of alternatives to be used in generateChoice.
    private Alternative generateAlternativeObject(ResultSet resultSet) throws Exception{
    	ArrayList<String> alternatives = new ArrayList<String>();
    	Alternative a = new Alternative(resultSet.getString("name"), resultSet.getString("choiceID"), resultSet.getInt("likes"), resultSet.getInt("dislikes"), getLikeAlternativeNames(resultSet.getInt("AlternativeID"), resultSet.getString("choiceID")), getDislikeAlternativeNames(resultSet.getInt("AlternativeID"), resultSet.getString("choiceID")),resultSet.getString("feedback"));
    	return a;
    }
    
    private String generateName(ResultSet resultSet) throws Exception{    	
    	System.out.println("Generating name");
    	String name = resultSet.getString("name");
    	System.out.println("made it past resultSet name " + name);
    	return name;
    }
    
    private Choice generateChoice(ResultSet resultSetChoice, ArrayList<Alternative> alternatives) throws Exception {
    	String uuid = resultSetChoice.getString("UUID");
    	String description = resultSetChoice.getString("description");
    	int isLocked = resultSetChoice.getInt("isLocked");
    	int maxNumbers = resultSetChoice.getInt("teamMembers");
    	

    	if(alternatives.size() == 4) {
		return new Choice(uuid, description, isLocked, maxNumbers, alternatives.get(0), alternatives.get(1), alternatives.get(2), alternatives.get(3), new Alternative("", "", 5));
    	}
    	else if(alternatives.size() == 3) {
    		return new Choice(uuid, description, isLocked, maxNumbers, alternatives.get(0), alternatives.get(1), alternatives.get(2), new Alternative("", "", 4), new Alternative("", "", 5));
    	}
    	else if(alternatives.size() == 2) {
    		return new Choice(uuid, description, isLocked, maxNumbers, alternatives.get(0), alternatives.get(1), new Alternative("", "",3), new Alternative("", "", 4), new Alternative("", "", 5));
    	}
    	else {
        	return new Choice(uuid, description, isLocked, maxNumbers, alternatives.get(0), alternatives.get(1), alternatives.get(2), alternatives.get(3), alternatives.get(4));   	
    	}
    }

}
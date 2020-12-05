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

public class RateDAO { 

	java.sql.Connection conn;
	
	final String tblName = "Constants";   // Exact capitalization
	final String choiceTable = "ChoiceTable";   // Exact capitalization
	final String alternativeTable = "AlternativeTable";
	final String rateAlternativeTable = "UserRateAlternativeTable";
	final String userTable = "UserTable";

	public RateDAO() {
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
    
    public int getLikes(String choiceID, int alternativeIndex) throws Exception {
    	try {
        PreparedStatement psAlternative = conn.prepareStatement("SELECT * FROM " + alternativeTable + " WHERE ChoiceID=? and AlternativeID=?;");
        psAlternative.setString(1, choiceID);
        psAlternative.setInt(2, alternativeIndex);
        ResultSet resultSetAlternative = psAlternative.executeQuery();
        while (resultSetAlternative.next()) {
        	Alternative alt = generateAlternativeObject(resultSetAlternative);
            return alt.likes;
        }        
    } catch (Exception e) {
        System.out.println("Failed to add choice");
        throw new Exception("Failed to insert constant: " + e.getMessage());
    }
		return -30;
    	
    }
    
    
    
    public int getDislikes(String choiceID, int alternativeIndex) throws Exception {
    	try {
            PreparedStatement psAlternative = conn.prepareStatement("SELECT * FROM " + alternativeTable + " WHERE ChoiceID=? and AlternativeID=?;");
            psAlternative.setString(1, choiceID);
            psAlternative.setInt(2, alternativeIndex);
            ResultSet resultSetAlternative = psAlternative.executeQuery();
            while (resultSetAlternative.next()) {
            	Alternative alt = generateAlternativeObject(resultSetAlternative);
                return alt.dislikes;
            }        
        } catch (Exception e) {
            System.out.println("Failed to add choice");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    		return -40;
        	
        }
   
    

   
    
    public boolean deleteLike(String userID, int alternativeID, String choiceID) throws Exception{
    	try {
            PreparedStatement psAlternative = conn.prepareStatement("DELETE FROM " + rateAlternativeTable + " WHERE choiceID=? and userID=? and alternativeID=?;");
            psAlternative.setString(1, choiceID);
            psAlternative.setString(2, userID);
            psAlternative.setInt(3, alternativeID);
            psAlternative.execute();
            
        	PreparedStatement ps = conn.prepareStatement("UPDATE " + alternativeTable + " set likes=? where AlternativeID=? and ChoiceID=?;");
            int likes = getLikes(choiceID, alternativeID);
        	//int likes = 2;
            System.out.print("Here are updated likes: " + likes);
            likes = likes - 1;
            System.out.println("CHANGING LIKES: " + likes);
        	ps.setInt(1,  likes);
            
            ps.setInt(2,  alternativeID);
            ps.setString(3, choiceID);
            ps.execute();
            
            System.out.println("Updated the likes, ie deleting user and updating the table");
            
            return true;
        } catch (Exception e) {
            System.out.println("Failed to delete choice");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }
    
    public boolean deleteDislike(String userID, int alternativeID, String choiceID) throws Exception{
    	try {
            PreparedStatement psAlternative = conn.prepareStatement("DELETE FROM " + rateAlternativeTable + " WHERE choiceID=? and userID=? and alternativeID=?;");
            psAlternative.setString(1, choiceID);
            psAlternative.setString(2, userID);
            psAlternative.setInt(3, alternativeID);
            psAlternative.execute();
            
        	PreparedStatement ps = conn.prepareStatement("UPDATE " + alternativeTable + " set dislikes=? where AlternativeID=? and ChoiceID=?;");
            int dislikes = getDislikes(choiceID, alternativeID);
        	//int likes = 2;
            System.out.print("Here are updated likes: " + dislikes);
        	ps.setInt(1,  dislikes - 1);
            
            ps.setInt(2,  alternativeID);
            ps.setString(3, choiceID);
            ps.execute();
            
            System.out.println("Updated the dislikes, ie deleting user and updating the table");
            
            return true;
        } catch (Exception e) {
            System.out.println("Failed to delete dislike");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }
    
    public boolean hasLikedAlternative(String userID, String choiceID, int alternativeID) throws Exception{
    	try {
            PreparedStatement psAlternative = conn.prepareStatement("SELECT * FROM " + rateAlternativeTable + " WHERE choiceID=? and userID=? and alternativeID=?;");
            psAlternative.setString(1, choiceID);
            psAlternative.setString(2, userID);
            psAlternative.setInt(3,  alternativeID);
            System.out.println("MADE IT HERE2");

            ResultSet resultSetAlternative = psAlternative.executeQuery();
            System.out.println("MADE IT HERE");
            while (resultSetAlternative.next()) {
            	if(resultSetAlternative.getInt("liked") == 1) {
            		System.out.println("ALREADY LIKED");
            		return true;
            	}
            	else {
            		System.out.println("NOT LIKED");
            		return false;
            	}
            }
            return false;
        } catch (Exception e) {
            System.out.println("Failed to delete choice");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }
    
    public boolean hasDislikedAlternative(String userID, String choiceID, int alternativeID) throws Exception{
    	try {
            PreparedStatement psAlternative = conn.prepareStatement("SELECT * FROM " + rateAlternativeTable + " WHERE choiceID=? and userID=? and alternativeID=?;");
            psAlternative.setString(1, choiceID);
            psAlternative.setString(2, userID);
            psAlternative.setInt(3,  alternativeID);
            System.out.println("MADE IT HERE2");

            ResultSet resultSetAlternative = psAlternative.executeQuery();
            System.out.println("MADE IT HERE");
            while (resultSetAlternative.next()) {
            	if(resultSetAlternative.getInt("disliked") == 1) {
            		System.out.println("ALREADY LIKED");
            		return true;
            	}
            	else {
            		System.out.println("NOT LIKED");

            		return false;
            	}
            }
            return false;
        } catch (Exception e) {
            System.out.println("Failed to delete choice");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }
    
    
    
    // Working as of 12:55 on November 30th, 2020
    // Need to test with userID
    public boolean updateLike(int delta, String choiceID, int alternativeIndex, String userID) throws Exception {
        try {
        	PreparedStatement ps = conn.prepareStatement("UPDATE " + alternativeTable + " set likes=? where AlternativeID=? and ChoiceID=?;");
            int likes = getLikes(choiceID, alternativeIndex);
        	//int likes = 2;
            System.out.print("Here are likes: " + likes);
        	ps.setInt(1,  likes + delta);
            
            ps.setInt(2,  alternativeIndex);
            ps.setString(3, choiceID);


            System.out.println("updated like");
            
        	PreparedStatement psRate = conn.prepareStatement("INSERT INTO UserRateAlternativeTable (choiceID, alternativeID, userID, liked, disliked, name) values(?,?,?,?,?,?);");
        	//int likes = 2;
        	psRate.setString(1, choiceID);
        	psRate.setInt(2,  alternativeIndex);
        	psRate.setString(3, userID);
            psRate.setInt(4, 1);
            
            // TODO: THIS IS THE LINE TO CHANGE FOR CHANGING LIKE OR DISLIKE
            psRate.setInt(5, 0);
            System.out.println("before getName");
            // Line below sets the name in the database
            String name = getName(userID);
            psRate.setString(6, name);
            System.out.println("after getName");

            psRate.execute();
            ps.execute();

            System.out.println("worked here!>fjwe");

            return true;

        } catch (Exception e) {
            System.out.println("Failed to add choice");
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    	
    }
    
    public boolean updateDislike(int delta, String choiceID, int alternativeIndex, String userID) throws Exception {
        try {
        	PreparedStatement ps = conn.prepareStatement("UPDATE " + alternativeTable + " set dislikes=? where AlternativeID=? and ChoiceID=?;");
            int likes = getDislikes(choiceID, alternativeIndex);
        	//int likes = 2;
            System.out.print("Here are likes: " + likes + delta);
            likes = likes + delta;
            System.out.print("Here are once changed likes: " + likes);

        	ps.setInt(1,  likes);
            
            ps.setInt(2,  alternativeIndex);
            ps.setString(3, choiceID);


            System.out.println("updated like");
            
        	PreparedStatement psRate = conn.prepareStatement("INSERT INTO UserRateAlternativeTable (choiceID, alternativeID, userID, liked, disliked, name) values(?,?,?,?,?,?);");
        	//int likes = 2;
        	psRate.setString(1, choiceID);
        	psRate.setInt(2,  alternativeIndex);
        	psRate.setString(3, userID);
            psRate.setInt(4, 0);
            
            // TODO: THIS IS THE LINE TO CHANGE FOR CHANGING LIKE OR DISLIKE
            psRate.setInt(5, 1);
            System.out.println("before getName");
            // Line below sets the name in the database
            String name = getName(userID);
            psRate.setString(6, name);
            System.out.println("after getName");

            psRate.execute();
            ps.execute();

            System.out.println("worked here!>fjwe");

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
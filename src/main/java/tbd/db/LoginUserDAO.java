package tbd.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tbd.model.Alternative;
import tbd.model.Choice;
import tbd.model.Constant;
import tbd.model.User;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 * 
 * @author heineman
 *
 */
public class LoginUserDAO { 

	java.sql.Connection conn;
	
	final String tblName = "UserTable";

    public LoginUserDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    		System.out.println("Connected to database!");
    	} catch (Exception e) {
    		System.out.println("Failed to connect");
    		conn = null;
    	}
    }   
    
    public User getUser(String choiceUUID, String name) throws Exception {
        
        try {
            User user = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM" + tblName + " WHERE choiceID=? and name=?;");
            ps.setString(1, choiceUUID);
            ps.setString(2, name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                user = generateUser(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return user;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

	private User generateUser(ResultSet resultSet) throws Exception { 	
		String uuid = resultSet.getString("userID");
		String choiceid = resultSet.getString("choiceID");
    	String name = resultSet.getString("name");
    	String password = resultSet.getString("password");	
		return new User(uuid, choiceid, name, password);
	}
	
	public User createNewUser(String choiceUUID, String name, String password) throws Exception {
        try {
            User user = null;
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName + " (userID, choiceID, name, password) VALUES (?, ?, ?, ?);");
            
            UUID uuid = UUID.randomUUID();
			String shortenedUUID = uuid.toString().substring(0, 5);
            
            ps.setString(1, shortenedUUID);
            ps.setString(2, choiceUUID);
            ps.setString(3, name);
            ps.setString(4, password);
            
            user = new User(shortenedUUID, choiceUUID, name, password);
            
            ps.executeUpdate();
            
            ps.close();
            
            return user;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
	}

}
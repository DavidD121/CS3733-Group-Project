package tbd.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tbd.model.Admin;
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
public class AdminDAO { 

	java.sql.Connection conn;
	
	final String tblName = "AdminAccountTable";

    public AdminDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    		System.out.println("Connected to database!");
    	} catch (Exception e) {
    		System.out.println("Failed to connect");
    		conn = null;
    	}
    }   
    
    public Admin getAdminCredentials(String name) throws Exception {
        
        try {
            Admin admin = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE username=?;");
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                admin = generateAdmin(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return admin;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

	private Admin generateAdmin(ResultSet resultSet) throws Exception { 	
    	String name = resultSet.getString("username");
    	String password = resultSet.getString("password");	
		return new Admin(name, password);
	}

}
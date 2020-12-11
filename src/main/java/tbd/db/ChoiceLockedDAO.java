package tbd.db;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import tbd.model.Admin;
import tbd.model.Alternative;
import tbd.model.Choice;
import tbd.model.ChoiceReportChoice;
import tbd.model.Constant;
import tbd.model.User;

public class ChoiceLockedDAO { 

	java.sql.Connection conn;
	
	final String tblName = "Constants";   // Exact capitalization
	final String choiceTable = "ChoiceTable";   // Exact capitalization
	final String alternativeTable = "AlternativeTable";

    public ChoiceLockedDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    		System.out.println("Connected to database!");
    	} catch (Exception e) {
    		System.out.println("Failed to connect");
    		conn = null;
    	}
    }
    
    public boolean isChoiceLocked(String choiceUUID) throws Exception{
        try {
            Admin admin = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + choiceTable + " WHERE UUID=?;");
            ps.setString(1, choiceUUID);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                int approvedAlternative = resultSet.getInt("approvedAlternative");
                if(approvedAlternative == 0) {
                	return false;
                }
                else {
                	return true;
                }
        }
        return false;    
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }
}

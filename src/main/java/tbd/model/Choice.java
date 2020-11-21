package tbd.model;

import java.util.ArrayList;

public class Choice {

	public boolean system;      // when TRUE this is actually stored in S3 bucket
	
	public final String uuid;
	public final String description;
	public final int isLocked;
	public final int maxUsers;
	public final String alternative1;
	public final String alternative2;
	public final String alternative3;
	public final String alternative4;
	public final String alternative5;
	/*
	 * 
	 * 
	 */
	public Choice (String uuid, String description, int isLocked, int maxUsers,
			String alternative1, String alternative2, String alternative3, String alternative4, String alternative5) {
		this.uuid = uuid;
		this.description = description;
		this.isLocked = isLocked;
		this.maxUsers = maxUsers;
		this.alternative1 = alternative1;
		this.alternative2 = alternative2;
		this.alternative3 = alternative3;
		this.alternative4 = alternative4;
		this.alternative5 = alternative5;
	}

	

}

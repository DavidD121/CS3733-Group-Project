package tbd.model;

import java.util.ArrayList;

public class Choice {

	public boolean system;      // when TRUE this is actually stored in S3 bucket
	
	public final String uuid;
	public final String description;
	public final int isLocked;
	public final int teamMembers;
	public String alternative1;
	public String alternative2;
	public String alternative3;
	public String alternative4;
	public String alternative5;
	
	public Alternative alternativeObject1;
	public Alternative alternativeObject2;
	public Alternative alternativeObject3;
	public Alternative alternativeObject4;
	public Alternative alternativeObject5;
	/*
	 * 
	 * 
	 */
	public Choice (String uuid, String description, int isLocked, int teamMembers,
			String alternative1, String alternative2, String alternative3, String alternative4, String alternative5) {
		this.uuid = uuid;
		this.description = description;
		this.isLocked = isLocked;
		this.teamMembers = teamMembers;
		this.alternative1 = alternative1;
		this.alternative2 = alternative2;
		this.alternative3 = alternative3;
		this.alternative4 = alternative4;
		this.alternative5 = alternative5;
	}

	public Choice (String uuid, String description, int isLocked, int teamMembers,
			Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5) {
		this.uuid = uuid;
		this.description = description;
		this.isLocked = isLocked;
		this.teamMembers = teamMembers;
		this.alternativeObject1 = alternative1;
		this.alternativeObject2 = alternative2;
		this.alternativeObject3 = alternative3;
		this.alternativeObject4 = alternative4;
		this.alternativeObject5 = alternative5;
	}
	

}

package tbd;

import java.util.Scanner;
import java.util.UUID;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import tbd.http.AddChoiceResponse;
import tbd.http.GetChoiceRequest;
import tbd.http.LoginUserRequest;
import tbd.http.LoginUserResponse;
import tbd.http.AddResponse;
import tbd.db.ConstantsDAO;
import tbd.db.LoginUserDAO;
import tbd.model.Choice;
import tbd.model.Constant;
import tbd.model.User;

public class CreateUser implements RequestHandler<LoginUserRequest,LoginUserResponse> {

	LambdaLogger logger;
	
	private AmazonS3 s3 = null;
	

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	
	User createUser(String choiceID, String userName, String password) throws Exception{
		try {
			if (logger != null) { logger.log("in createUser"); }
			LoginUserDAO dao = new LoginUserDAO();
			System.out.println("You connected!");
			User user = dao.getUser(choiceID, userName);
			
			//If user doesn't already exist, create new one and return it
			if(user == null) {
				if (logger != null) { logger.log("trying to create new user"); }
				return dao.createNewUser(choiceID, userName, password);
			} else {
				//if user exists, return null
				return null;
			}
		}
		catch (Exception e) {
			System.out.println("createUser failed!");
			return null;
		}
	}
	
	boolean isSpace(String choiceID) {
		try {
			if (logger != null) { logger.log("in checkFull"); }
			LoginUserDAO dao = new LoginUserDAO();
			System.out.println("You connected!");
			
			return dao.isSpaceInChoice(choiceID);
		}
		catch (Exception e) {
			System.out.println("checkFull failed!");
			return false;
		}
	}
	
	
	@Override
	public LoginUserResponse handleRequest(LoginUserRequest req, Context context) {
		LoginUserRequest req1 = req;
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req1.toString());
		boolean fail = false;
		String failMessage = "";
		User user = null;
		
		try {
			if(!isSpace(req1.getChoiceID())) {
				fail = true;
				failMessage = "Choice is full";
			}
		} catch (Exception e) {
			fail = true;
			failMessage = "Failed to read database!";
		}
		
		if(!fail) {
			try {
				user = createUser(req1.getChoiceID(), req1.getName(), req1.getPassword());
				if (logger != null) { logger.log("New user " + user.getName() + " created"); }
			} catch (Exception e) {
				fail = true;
				failMessage = "Failed to read database!";
			}
			
			if(user == null) {
				fail = true;
				failMessage = "User already exists";
			} 
		}
		
		
		
		
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		LoginUserResponse response;
		if (fail) {
			response = new LoginUserResponse(400, failMessage);
		} else {
			response = new LoginUserResponse(user.getUUID(), 200);  // success
		}

		System.out.println(response);
		//System.out.println(fail);
		return response; 
	}
}
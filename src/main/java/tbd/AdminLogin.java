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
import tbd.http.AdminLoginRequest;
import tbd.http.AdminLoginResponse;
import tbd.db.AdminDAO;
import tbd.db.ConstantsDAO;
import tbd.model.Admin;
import tbd.model.Choice;
import tbd.model.Constant;
import tbd.model.User;

public class AdminLogin implements RequestHandler<AdminLoginRequest,AdminLoginResponse> {

	LambdaLogger logger;
		
	Admin getAdmin(String name) throws Exception{
		try {
			if (logger != null) { logger.log("in getAdmin"); }
			AdminDAO dao = new AdminDAO();
			System.out.println("You connected!");
			Admin admin = dao.getAdminCredentials(name);
			
			return admin;
		}
		catch (Exception e) {
			System.out.println("getAdmin failed!");
			return null;
		}
	}
	
	@Override
	public AdminLoginResponse handleRequest(AdminLoginRequest req, Context context) {
		AdminLoginRequest req1 = req;
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req1.toString());
		boolean fail = false;
		String failMessage = "";

		Admin admin = null;
		try {
			admin = getAdmin(req1.getName());
		} catch (Exception e) {
			fail = true;
			failMessage = "Failed to read database!";
		}
		
		if(admin == null) {
			fail = true;
			failMessage = "Admin credentials incorrect";
		} else {
			//Checking password
			if(admin.getPassword() != null && admin.getPassword().length() != 0) {
				if(req1.getPassword() == null || req1.getPassword().length() == 0) {
					fail = true;
					failMessage = "Password required";
				} else {
					if(!req1.getPassword().equals(admin.getPassword())) {
						fail = true;
						failMessage = "Incorrect password";
					}
				}
			}
		}
		
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		AdminLoginResponse response;
		if (fail) {
			response = new AdminLoginResponse(400, failMessage);
		} else {
			response = new AdminLoginResponse(200);  // success
		}

		System.out.println(response);
		//System.out.println(fail);
		return response; 
	}
}
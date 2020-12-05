package tbd;

import java.util.Scanner;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import tbd.http.GetChoiceResponse;
import tbd.http.GetChoiceRequest;
import tbd.http.CreateChoiceResponse;
import tbd.db.ChoiceDAO;
import tbd.db.GetChoiceDAO;
import tbd.model.Choice;
import tbd.model.Constant;

public class GetChoice implements RequestHandler<GetChoiceRequest,GetChoiceResponse> {

	LambdaLogger logger;
	
	private AmazonS3 s3 = null;
	
	
	Choice getChoice(String choiceName) throws Exception{
		try {
		if (logger != null) { logger.log("in createChoice"); }
		GetChoiceDAO dao = new GetChoiceDAO();
		System.out.println("You connected!");
		//dao.addConstant(new Constant("internetblake", 12));
		Choice choice = dao.getChoice(choiceName);
		System.out.println(choice.description);
		System.out.println(choice.alternative1);
		System.out.println(choice.alternative2);
		System.out.println(choice.alternative3);
		System.out.println(choice.alternative4);
		System.out.println(choice.alternative5);

		return choice;
		}
		catch (Exception e) {
			System.out.println("createChoice failed!");
			return null;
		}
		
	}
	
	
	/*
	 * 
	 * handleRequest is takes the JSON input (in this case the UUID of a choice), 
	 * and gets that choice from o the RDS and returns a successful JSON with the choice or a fail JSON with no choice.
	 *
	 *
	 */
	
	@Override
	public GetChoiceResponse handleRequest(GetChoiceRequest req, Context context) {
		GetChoiceRequest req1 = req;
		//GetChoiceRequest req1 = new GetChoiceRequest("20", "30");
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req1.toString());
		boolean fail = false;
		String failMessage = "";
		double val1 = 0.0;
		double val2 = 0.0;
		Choice choice = null;
		try {
			//loadValueFromRDS("e");
			choice = getChoice(req1.getuuid());
		}
		catch (Exception e) {
			fail = true;
			failMessage = "Failed to read database!";
		}
		if(choice == null) {
			fail = true;
			failMessage = "Filed to read database";
		}
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		GetChoiceResponse response;
		if (fail) {
			response = new GetChoiceResponse(failMessage);
		} else {
			response = new GetChoiceResponse(choice);  // success
		}

		System.out.println(response);
		return response; 
	}
}
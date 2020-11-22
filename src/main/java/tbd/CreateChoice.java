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

import tbd.http.CreateChoiceRequest;
import tbd.http.CreateChoiceResponse;
import tbd.db.ChoiceDAO;
import tbd.model.Choice;
import tbd.model.Constant;

public class CreateChoice implements RequestHandler<CreateChoiceRequest,CreateChoiceResponse> {

	LambdaLogger logger;
	
	private AmazonS3 s3 = null;
	
	/*
	 * Function to access the RDS and create the choice with given parameters. 
	 * Returns true if successful. 
	 * 
	 * 
	 */
	boolean createChoice(String choiceName, String choiceDescription, int maxUsers,
			String alternative1, String alternative2, String alternative3, String alternative4, String alternative5) throws Exception{
		try {
		if (logger != null) { logger.log("in createChoice"); }
		ChoiceDAO dao = new ChoiceDAO();
		System.out.println("You connected!");

		dao.addChoice(new Choice(choiceName, choiceDescription, 1, maxUsers, alternative1, alternative2, alternative3, alternative4, alternative5));

		return false;
		}
		catch (Exception e) {
			System.out.println("createChoice failed!");
			return true;
		}
		
	}
	
	
	/*
	 * 
	 * handleRequest is takes the JSON input (in this case information about a choice), 
	 * and adds that choice to the RDS and returns a successful JSON or a fail JSON.
	 *
	 *
	 */

	@Override
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {
		logger = context.getLogger();

		boolean fail = false;
		String failMessage = "";

		try {
			fail = createChoice(req.getuuid(), req.getchoiceDescription(), req.getmaxUsers(), req.getalternative1(), req.getalternative2(),
					req.getalternative3(), req.getalternative4(), req.getalternative5());
		}
		catch (Exception e) {
			fail = true;
			failMessage = "Failed to add choice!";
		}


		CreateChoiceResponse response;
		if (fail) {
			response = new CreateChoiceResponse(400, failMessage);
		} else {
			response = new CreateChoiceResponse("Success", 200);  // success
		}

		return response; 
	}
}
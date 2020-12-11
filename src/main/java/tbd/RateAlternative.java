package tbd;

import java.util.ArrayList;
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
import tbd.http.RateAlternativeRequest;
import tbd.http.RateAlternativeResponse;
import tbd.http.GetChoiceRequest;
import tbd.http.AddFeedbackResponse;
import tbd.http.CreateChoiceResponse;
import tbd.db.ChoiceDAO;
import tbd.db.ChoiceLockedDAO;
import tbd.db.RateDAO;
import tbd.model.Choice;
import tbd.model.Constant;

public class RateAlternative implements RequestHandler<RateAlternativeRequest,RateAlternativeResponse> {

	LambdaLogger logger;
	
	private AmazonS3 s3 = null;
	
	// TODO: Remember to comment out that I got this from the internet
    public boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
    
	RateAlternativeResponse addLike(int delta, String choiceID, int alternativeIndex, String userID) throws Exception{
		
		// Need to first say which alternative it is (ie which index and choice)
		// Find that in the database, update that
		// and then return the proper RatingResponse code
		
		try {
		if (logger != null) { logger.log("in createChoice"); }
		RateDAO dao = new RateDAO();
		if(dao.hasLikedAlternative(userID, choiceID, alternativeIndex)) {
			System.out.println("Participant has already liked choice!");
			dao.deleteLike(userID, alternativeIndex, choiceID);
			return new RateAlternativeResponse(-1, 0, 200);
		}
		else if(dao.hasDislikedAlternative(userID, choiceID, alternativeIndex)) {
			dao.deleteDislike(userID, alternativeIndex, choiceID);
			dao.updateLike(1, choiceID, alternativeIndex, userID);
			ArrayList<String> names = dao.getLikeAlternativeNames(alternativeIndex, choiceID);
			for(int i = 0; i < names.size(); i++) {
				System.out.println("Doing names: " + names.get(i));
			}
			return new RateAlternativeResponse(1, -1, 200);
		}
		else {
			dao.updateLike(delta, choiceID, alternativeIndex, userID);
			ArrayList<String> names = dao.getLikeAlternativeNames(alternativeIndex, choiceID);
			for(int i = 0; i < names.size(); i++) {
				System.out.println("Doing names: " + names.get(i));
			}
			return new RateAlternativeResponse(1, 0, 200);
		}
		}
		catch (Exception e) {
			System.out.println("addLike failed!");
			return new RateAlternativeResponse(400, 400, 400);
		}
	}
	
	RateAlternativeResponse addDislike(int delta, String choiceID, int alternativeIndex, String userID) throws Exception{
		
		// Need to first say which alternative it is (ie which index and choice)
		// Find that in the database, update that
		// and then return the proper RatingResponse code
		
		try {
			if (logger != null) { logger.log("in createChoice"); }
			RateDAO dao = new RateDAO();
			if(dao.hasDislikedAlternative(userID, choiceID, alternativeIndex)) {
				System.out.println("Participant has already disliked choice!");
				dao.deleteDislike(userID, alternativeIndex, choiceID);
				return new RateAlternativeResponse(0, -1, 200);
			}
			else if(dao.hasLikedAlternative(userID, choiceID, alternativeIndex)) {
				// TODO: Broken here, doesn't subtract one but does remove from DB, 
				// does not put the user removed from the like to the dislike
				dao.deleteLike(userID, alternativeIndex, choiceID);
				dao.updateDislike(1, choiceID, alternativeIndex, userID);
				ArrayList<String> names = dao.getDislikeAlternativeNames(alternativeIndex, choiceID);
				for(int i = 0; i < names.size(); i++) {
					System.out.println("Doing names: " + names.get(i));
				}
				return new RateAlternativeResponse(-1, 1, 200);
			}
			else {
				dao.updateDislike(delta, choiceID, alternativeIndex, userID);
				ArrayList<String> names = dao.getDislikeAlternativeNames(alternativeIndex, choiceID);
				for(int i = 0; i < names.size(); i++) {
					System.out.println("Doing names: " + names.get(i));
				}
				return new RateAlternativeResponse(0, 1, 200);
			}
			}
			catch (Exception e) {
				System.out.println("addDislike failed!");
				return new RateAlternativeResponse(400, 400, 400);
			}
	}
	
	/*
	boolean addDislike() throws Exception{
		
	}
	*/
	
	/*
	 * 
	 * handleRequest is takes the JSON input (in this case the UUID of a choice), 
	 * and gets that choice from o the RDS and returns a successful JSON with the choice or a fail JSON with no choice.
	 *
	 *
	 */
	
	@Override
	public RateAlternativeResponse handleRequest(RateAlternativeRequest req, Context context) {
		RateAlternativeRequest req1 = req;
		//GetChoiceRequest req1 = new GetChoiceRequest("20", "30");
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req1.toString());
		boolean fail = false;
		String failMessage = "";
		double val1 = 0.0;
		double val2 = 0.0;
		Choice choice = null;
		RateAlternativeResponse response = new RateAlternativeResponse(400, 400, 400);
		ChoiceLockedDAO daoTest = new ChoiceLockedDAO();
		try {
			if(daoTest.isChoiceLocked(req1.getuuid())) {
				response = new RateAlternativeResponse(300, 300, 300);
				return response;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//loadValueFromRDS("e");
			
			// Check if the user already has liked an alternative or not
			// IF NOT
			if(req1.gettype().equalsIgnoreCase("like") && !isNullOrEmpty(req1.getuserID())) {
				RateAlternativeResponse like = addLike(1, req1.getuuid(), req1.getalternative(), req1.getuserID());
				response = like;
			}
			else if(req1.gettype().equalsIgnoreCase("dislike") && !isNullOrEmpty(req1.getuserID())) {
				RateAlternativeResponse dislike = addDislike(1, req1.getuuid(), req1.getalternative(), req1.getuserID());
				response = dislike;
			}
			else {
				System.out.println("Failed to do anything!");
			}
			
			// OTHERWISE
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
		/*
		if (fail) {
			response = new GetChoiceResponse(failMessage);
		} else {
			response = new GetChoiceResponse(choice);  // success
		}
		*/

		return response; 
	}
}
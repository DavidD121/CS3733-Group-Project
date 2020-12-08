package tbd;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tbd.db.ChoiceDAO;
import tbd.http.AdminLoginRequest;
import tbd.http.ApproveAlternativeRequest;
import tbd.http.GenericResponse;

public class ApproveAlternative implements RequestHandler<ApproveAlternativeRequest, GenericResponse> {

	LambdaLogger logger;
	
	boolean closeChoice(String uuid) throws Exception {
		try {
			ChoiceDAO dao = new ChoiceDAO();
			dao.closeChoice(uuid);
			return true;
		} catch (Exception e) {
			System.out.println("closeChoice failed!");
			return false;
		}
		
	}

    @Override
    public GenericResponse handleRequest(ApproveAlternativeRequest req, Context context) {
    	ApproveAlternativeRequest req1 = req;
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req1.toString());
		boolean fail = false;
		String failMessage = "";
		
		try {
			fail = !closeChoice(req1.getChoiceID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail = true;
		}
		
        GenericResponse response;
		if (fail) {
			response = new GenericResponse(400, failMessage);
		} else {
			response = new GenericResponse(200);  // success
		}

		System.out.println(response);
		//System.out.println(fail);
		return response; 
    }

}

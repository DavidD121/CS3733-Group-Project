package tbd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import tbd.http.AddFeedbackRequest;
import tbd.http.AddFeedbackResponse;
import tbd.http.CreateChoiceRequest;
import tbd.http.CreateChoiceResponse;
import tbd.http.GetChoiceRateResponse;
import tbd.http.GetChoiceRequest;
import tbd.http.GetChoiceResponse;
import tbd.http.RateAlternativeRequest;
import tbd.http.RateAlternativeResponse;
import tbd.model.Alternative;
import edu.wpi.cs.heineman.demo.TestContext;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class RateAlternativeTest {

    private static Object input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testRateAlternative() {
    	RateAlternative handler = new RateAlternative();
        Context ctx = createContext();
        
        RateAlternativeRequest request = new RateAlternativeRequest(1, "d731c",  "like", "84679");
        System.out.println("Alt" + request.getalternative());
        System.out.println("UUID" + request.getuuid());
        System.out.println("userID" + request.getuserID());
        System.out.println("type" + request.gettype());



    	//public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
    	//		Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5)
        RateAlternativeResponse output = handler.handleRequest(request, ctx);
        
        Assert.assertEquals(200, output.statusCode);
        Assert.assertEquals(1, output.likeChange);
        Assert.assertEquals(0, output.dislikeChange);
        

        RateAlternativeRequest request2 = new RateAlternativeRequest(1, "d731c", "like", "84679");
    	//public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
    	//		Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5)
        RateAlternativeResponse output2 = handler.handleRequest(request2, ctx);
        
        Assert.assertEquals(200, output2.statusCode);
        Assert.assertEquals(-1, output2.likeChange);
        Assert.assertEquals(0, output2.dislikeChange);
        
        RateAlternativeRequest request3 = new RateAlternativeRequest(1, "d731c", "dislike", "84679");
        System.out.println("REQUEST3 " + request3.gettype());
        System.out.println("Request3 userID" + request3.getuserID());
        System.out.println("Request3 uuid" + request3.getuuid());

    	//public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
    	//		Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5)
        RateAlternativeResponse output3 = handler.handleRequest(request3, ctx);
        
        Assert.assertEquals(200, output3.statusCode);
        Assert.assertEquals(0, output3.likeChange);
        Assert.assertEquals(1, output3.dislikeChange);
        
        RateAlternativeRequest request4 = new RateAlternativeRequest(1, "d731c", "dislike", "84679");
    	//public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
    	//		Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5)
        RateAlternativeResponse output4 = handler.handleRequest(request4, ctx);
        
        Assert.assertEquals(200, output4.statusCode);
        Assert.assertEquals(0, output4.likeChange);
        Assert.assertEquals(-1, output4.dislikeChange);
        
        RateAlternativeRequest request5 = new RateAlternativeRequest(1, "d731c", "dislike", "84679");
    	//public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
    	//		Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5)
        RateAlternativeResponse output5 = handler.handleRequest(request5, ctx);
        
        Assert.assertEquals(200, output5.statusCode);
        Assert.assertEquals(0, output5.likeChange);
        Assert.assertEquals(1, output5.dislikeChange);
        
        RateAlternativeRequest request6 = new RateAlternativeRequest(1, "d731c", "like", "84679");
    	//public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
    	//		Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5)
        RateAlternativeResponse output6 = handler.handleRequest(request6, ctx);
        
        Assert.assertEquals(200, output6.statusCode);
        Assert.assertEquals(1, output6.likeChange);
        Assert.assertEquals(-1, output6.dislikeChange);
        
        RateAlternativeRequest request7 = new RateAlternativeRequest(1, "d731c", "dislike", "84679");
    	//public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
    	//		Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5)
        RateAlternativeResponse output7 = handler.handleRequest(request7, ctx);
        
        Assert.assertEquals(200, output7.statusCode);
        Assert.assertEquals(-1, output7.likeChange);
        Assert.assertEquals(1, output7.dislikeChange);
        
        RateAlternativeRequest request8 = new RateAlternativeRequest(1, "d731c", "dislike", "84679");

    	//public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
    	//		Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5)
        RateAlternativeResponse output8 = handler.handleRequest(request8, ctx);
        
        
        
        
    }
    

}

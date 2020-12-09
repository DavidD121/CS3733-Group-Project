package tbd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.heineman.demo.TestContext;
import tbd.http.AddFeedbackRequest;
import tbd.http.AddFeedbackResponse;
import tbd.http.CreateChoiceRequest;
import tbd.http.CreateChoiceResponse;
import tbd.model.Alternative;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AddFeedbackTest {

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
    public void testAddFeedback() {
        AddFeedback handler = new AddFeedback();
        Context ctx = createContext();
        
        UUID uuid = UUID.randomUUID();
        String subuuid = uuid.toString().substring(0,5);

        AddFeedbackRequest request = new AddFeedbackRequest(1, "b9bc4", "38585", "this is a test");
    	//public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
    	//		Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5)
        AddFeedbackResponse output = handler.handleRequest(request, ctx);

        Assert.assertEquals(200, output.statusCode);
    }
    

}

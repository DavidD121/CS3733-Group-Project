package tbd;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import tbd.http.DeleteOldChoicesRequest;
import tbd.http.GenericResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DeleteOldChoicesTest {

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
    public void testDeleteOldChoices() {
        DeleteOldChoices handler = new DeleteOldChoices();
        Context ctx = createContext();
        
        DeleteOldChoicesRequest req = new DeleteOldChoicesRequest((float) 5.5);

        GenericResponse output = handler.handleRequest(req, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200, output.statusCode);
    }
}

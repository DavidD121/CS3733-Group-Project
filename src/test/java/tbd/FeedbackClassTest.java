package tbd;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.heineman.demo.TestContext;

import tbd.model.Feedback;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class FeedbackClassTest {

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
    public void testCreateFeedbackClass() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
    	Feedback feedback = new Feedback("testuser", timestamp, "this is a test!");
    	
    	Assert.assertEquals("testuser", feedback.author);
    	Assert.assertEquals("this is a test!", feedback.description);

    }
    
}


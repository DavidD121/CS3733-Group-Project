package tbd;

import java.io.IOException; 

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import tbd.http.AdminLoginRequest;
import tbd.http.GenericResponse;
import tbd.http.LoginUserRequest;
import tbd.http.LoginUserResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AdminLoginTest {

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
    public void testAdminLogin() {
    	AdminLogin handler = new AdminLogin();
        Context ctx = createContext();

        AdminLoginRequest request = new AdminLoginRequest("admin", "password");
        GenericResponse output = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200, output.statusCode);
    }
    
    @Test
    public void testWrongUsernameAdminLogin() {
    	AdminLogin handler = new AdminLogin();
        Context ctx = createContext();

        AdminLoginRequest request = new AdminLoginRequest("bumbleton", "password");
        GenericResponse output = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(400, output.statusCode);
    }
    
    @Test
    public void testWrongPasswordAdminLogin() {
    	AdminLogin handler = new AdminLogin();
        Context ctx = createContext();

        AdminLoginRequest request = new AdminLoginRequest("admin", "buh");
        GenericResponse output = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(400, output.statusCode);
    }
}
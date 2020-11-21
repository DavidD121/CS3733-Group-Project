package tbd.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

	// DB user names and passwords (as well as the db endpoint) should never be stored directly in code.
	//
	// https://docs.aws.amazon.com/lambda/latest/dg/env_variables.html
	//
	// The above link shows how to ensure Lambda function has access to environment as well as local
	public final static String jdbcTag = "jdbc:mysql://";
	public final static String rdsMySqlDatabasePort = "3306";
	public final static String multiQueries = "?allowMultiQueries=true";
	   
	// Make sure matches Schema created from MySQL WorkBench
	public final static String dbName = "TemplateSchema";            
	
	// pooled across all usages.
	static Connection conn;
 
	/**
	 * Singleton access to DB connection to share resources effectively across multiple accesses.
	 */
	protected static Connection connect() throws Exception {
		if (conn != null) { return conn; }
		
		// this is resistant to any SQL-injection attack.
		String schemaName = dbName;
		
		// These three environment variables must be set!
		//String dbUsername = System.getenv("dbUsername");
		String dbUsername = "admin";
		if (dbUsername == null) {
			System.err.println("Environment variable dbUsername is not set!");
		}
		//String dbPassword = System.getenv("dbPassword");
		String dbPassword = "password";

		if (dbPassword == null) {
			System.err.println("Environment variable dbPassword is not set!");
		}
		
		//String rdsMySqlDatabaseUrl = System.getenv("rdsMySqlDatabaseUrl");
		String rdsMySqlDatabaseUrl = "tobedecideddatabase.ckffibxdyrj2.us-east-1.rds.amazonaws.com";

		if (rdsMySqlDatabaseUrl == null) {
			System.err.println("Environment variable rdsMySqlDatabaseUrl is not set!");
		}
		
		try {
			//System.out.println("start connecting......");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(
					jdbcTag + rdsMySqlDatabaseUrl + ":" + rdsMySqlDatabasePort + "/" + schemaName + multiQueries,
					dbUsername,
					dbPassword);
			return conn;
		} catch (Exception ex) {
			System.err.println("DB-ERROR:" + schemaName + "," + dbUsername + "," + dbPassword + "," + rdsMySqlDatabaseUrl);
			throw new Exception("Failed in database connection");
		}
	}
}

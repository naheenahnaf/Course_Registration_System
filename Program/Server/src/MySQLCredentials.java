/** 
 * Interface MySQLCredentials sets up a MySQL server.
 * 
 * @author A. Naheen
 * @version 16.0.1
 * @since August 08, 2021 
*/
public interface MySQLCredentials 
{
	/** The JDBC driver.*/
	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	//Please fill in the underlined section with the schema you're using.
	/** Location of the database in the user's localhost server.*/
	final String DB_URL = "jdbc:mysql://localhost:3306/______?serverTimezone=MST";
	
	//Please put your username.
	/** Localhost username.*/
	final String USERNAME = "";
	
	//Please put your password.
	/** Localhost password.*/
	final String PASSWORD = "";
}
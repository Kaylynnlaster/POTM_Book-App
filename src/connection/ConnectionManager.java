//Connection manager to connect to the database
package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	//Creating private variables that include the information for the database we want to use
	private static final String URL = "jdbc:mysql://localhost:3306/book_progress";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "JavaDev23!#%";
	//creating a null connection 
	private static Connection connection = null;
	
	private static void createConnection( ) {
		//try catch that throws an sql exception if we are not able to connect
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			connection = conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Method to create a brand new connection
	public static Connection getConnection() {
		if (connection == null) {
			createConnection();
		}
		return connection;
	
	}

	//Main function created to close the connection after use
	public static void main(String[] args) {
		Connection conn = getConnection();
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

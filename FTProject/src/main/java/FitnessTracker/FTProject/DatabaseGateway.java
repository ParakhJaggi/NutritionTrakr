package FitnessTracker.FTProject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


	
public class DatabaseGateway {
	private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_CONNECTION = "jdbc:derby:C:\\Users\\Terlizzi.ECS.002\\MyDB";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

/*	public void createTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "CREATE TABLE FRIEND_FINDER ( User_Id INT NOT NULL, Friend_ID INT NOT NULL)" ;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(createTableSQL);
			// execute the SQL statement
			statement.execute(createTableSQL);
			System.out.println("Table is created!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}*/

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
}
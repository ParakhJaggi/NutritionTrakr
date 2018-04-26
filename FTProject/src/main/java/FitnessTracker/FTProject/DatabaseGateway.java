package FitnessTracker.FTProject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.util.Pair;

import FitnessTracker.Exceptions.FoodNotFoundException;
import FitnessTracker.Exceptions.UserAlreadyInDatabaseException;
import FitnessTracker.Exceptions.UserNotFoundException;


/**
 * @author Garth Terlizzi III
 * This class is a fabricated gateway to all database functions
 */
public class DatabaseGateway {
	private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_CONNECTION = "jdbc:derby:Database/MyDB;";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";
	private static DatabaseGateway instance = null;

	
	/**
	 * @author Garth Terlizzi III
	 * @return The connection from the database
	 * Deletes the user from the database, used for mainly testing purposes.
	 */
	protected static Connection getDBConnection() {
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
package FitnessTracker.FTProject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import FitnessTracker.Exceptions.FoodNotFoundException;


	
public class DatabaseGateway {
	private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_CONNECTION = "jdbc:derby:FTProject/MyDB;create=true";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	public void createTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "CREATE TABLE Foods (Food_Name VarChar(25) NOT NULL, Category_Name VarChar(25) NOT NULL, Calories INT,Primary KEY(Food_Name))"; 
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			statement.execute(createTableSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) 
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();
		}
	}
	
	public void addFoodToTable (Food f,Connection connect) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String insertTableSQL = "INSERT INTO FOODS" + "(Food_Name, Category_Name, Calories) " + "VALUES"
				+ "('"+f.getName()+"', '"+f.getCategory()+"',"+f.getCalories()+")";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			statement.executeUpdate(insertTableSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) 
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();
		}
	}
	//If f is null, a else statement must add to table
	public Food retrieveFood(String foodName,String category) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT * FROM Foods WHERE FOOD_NAME = "+foodName;
		Food f=new Food();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) 
				f=null;
			else {
				f.setName(rs.getString("Food_Name"));
				f.setCategory(rs.getString("Category"));
				f.setCalories(Integer.parseInt((rs.getString("Calories"))));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if (statement != null)
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();	
		}
		return f;
	}
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
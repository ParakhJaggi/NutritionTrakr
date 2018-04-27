package FitnessTracker.FTProject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import FitnessTracker.Exceptions.FoodNotFoundException;

public class ExerciseDatabaseGateway extends InputDatabaseGateway {
	private static ExerciseDatabaseGateway instance = null;
	/**
	 * @author Garth Terlizzi
	 * Database constructor, exists only to defeat instantiation.
	 */
    protected ExerciseDatabaseGateway() { }
    /**
     * @author Garth Terlizzi
     * @return DatabaseGateway instance, core of the singleton
     * Returns the instance
     */
	public static ExerciseDatabaseGateway getInstance() {
	      if(instance == null) {
	         instance = new ExerciseDatabaseGateway();
	      }
	      return instance;
	   }

	

	/**
	 * @author Garth Terlizzi III
	 * @param The food name
	 * @param The category the specific food belongs to
	 * @param The calories attached to the food
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Adds a food to the Exercise Tracker
	 */
	public void addExerciseToTable (String exName,String category, int calories) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String insertTableSQL = "INSERT INTO Exercise" + "(Exercise_Name, Category_Name, Calories) " + "VALUES"
				+ "('"+exName+"', '"+category+"',"+calories+")";
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
	
	/**
	 * @author Garth Terlizzi III
	 * @param The exercise name
	 * @return The exercise associated with the name, loaded with category calories
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Retrieves a exercise from the database
	 */
	public Exercise retrieveExercise(String exName) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT * FROM Exercise WHERE Exercise_NAME = '"+exName+"'";
		Exercise ex=new Exercise();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) 
				throw new SQLException();
			else {
				ex.setName(rs.getString("Exercise_Name"));
				ex.setCategory(rs.getString("Category_Name"));
				ex.setCalories(Integer.parseInt((rs.getString("Calories"))));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if (statement != null)
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();	
		}
		return ex;
	}
	
	
	/**
	 * @author Garth Terlizzi III
	 * @param The exercise name
	 * @return An ArrayList with all exercise associated from the category
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Shows all exercise in a given category
	 */
	public ArrayList<String> displayExerciseFromCategory(String cat) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT * FROM Exercise WHERE CATEGORY_NAME = '"+cat+"'";
		ArrayList<String> myList= new ArrayList<String>();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) 
				throw new FoodNotFoundException("Exercise NOT FOUND");
			else {
				do {
					myList.add(rs.getString("Exercise_Name"));
				} while (rs.next());
			} 
		}catch (SQLException|FoodNotFoundException e) {
			System.out.println(e.getMessage());
		}finally {
			if (statement != null)
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();	
		}
		return myList;
	}
	
	

}

package FitnessTracker.FTProject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import FitnessTracker.Exceptions.FoodNotFoundException;

public class FoodDatabaseGateway extends DatabaseGateway {
	private static FoodDatabaseGateway instance = null;
	/**
	 * @author Garth Terlizzi
	 * Database constructor, exists only to defeat instantiation.
	 */
    protected FoodDatabaseGateway() { }
    /**
     * @author Garth Terlizzi
     * @return DatabaseGateway instance, core of the singleton
     * Returns the instance
     */
	public static FoodDatabaseGateway getInstance() {
	      if(instance == null) {
	         instance = new FoodDatabaseGateway();
	      }
	      return instance;
	   }
	/**
	 * @author Garth Terlizzi III
	 * @param The food name
	 * @return The food associated with the name, loaded with category calories
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Retrieves a food from the database
	 */
	public Food retrieveFood(String foodName) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT * FROM Foods WHERE FOOD_NAME = '"+foodName+"'";
		Food f=new Food();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) 
				throw new FoodNotFoundException("FOOD NOT FOUND");
			else {
				f.setName(rs.getString("Food_Name"));
				f.setCategory(rs.getString("Category_Name"));
				f.setCalories(Integer.parseInt((rs.getString("Calories"))));
			}
		} catch (SQLException|FoodNotFoundException e) {
			System.out.println(e.getMessage());
		}finally {
			if (statement != null)
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();	
		}
		return f;
	}
	/**
	 * @author Garth Terlizzi III
	 * @param The food name
	 * @param The category the specific food belongs to
	 * @param The calories attached to the food
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Adds a food to the Exercise Tracker
	 */
	public void addFoodToTable (String foodName,String category, int calories) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String insertTableSQL = "INSERT INTO FOODS" + "(Food_Name, Category_Name, Calories) " + "VALUES"
				+ "('"+foodName+"', '"+category+"',"+calories+")";
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
	 * @param The food name
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Deletes a food or exercise from the database (Used for testing purposes and database cleanup)
	 */
	public void deleteFoodEx(String name, boolean isFood) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String deleteSQL= null;
		if (isFood)
			 deleteSQL = "DELETE FROM Foods WHERE Food_name = '"+name+"'";
		else
			 deleteSQL = "DELETE FROM Exercise WHERE Exercise_name = '"+name+"'";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			statement.executeUpdate(deleteSQL);
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
	 * @param The category name
	 * @return An ArrayList with all foods associated from the category
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Shows all food in a given category
	 */
	public ArrayList<String> displayFoodFromCategory(String cat) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT * FROM Foods WHERE CATEGORY_NAME = '"+cat+"'";
		ArrayList<String> myList= new ArrayList<String>();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) 
				throw new FoodNotFoundException("FOOD NOT FOUND");
			else {
				do {
					myList.add(rs.getString("Food_Name"));
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
	/**
	 * @author Garth Terlizzi III
	 * @param The name of the food
	 * @param The new amount of calories the food should have
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Updates a food, provided it is in the database
	 */
	public void updateCaloriesFood(String foodName, int cals) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "UPDATE Foods SET Calories = "+cals+ " WHERE FOOD_NAME = '"+foodName+"'";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			statement.executeUpdate(selectTableSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if (statement != null)
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();	
		}
	}
	/**
	 * @author Garth Terlizzi III
	 * @param The ID of the user, the Date associated with the tracker, The calories of the food, and The calories of the exercise
	 * @param The new amount of calories the food should have
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Adds calories to both trackers.To change one tracker, put a 0 in the tracker you don't want to update
	 * To change one tracker, put a 0 in the tracker you don't want to update
	 */
	public void addCaloriesToTrackers(int userID, Date d, int calFood, int calEx) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String selectCount= "Select * FROM FITNESS_TRACKER WHERE USER_ID = "+userID+" AND ENTRY_DATE = '"+d+"'";
		String insertTableSQL = "INSERT INTO FITNESS_TRACKER" + "(USER_ID, ENTRY_DATE, Calories_FROM_FOOD, CALORIES_FROM_EXERCISE) "
				+ "VALUES" + "("+userID+",'"+d+"',"+calFood+ ","+calEx+")";
		String updateTableSQL = "UPDATE FITNESS_TRACKER SET Calories_FROM_FOOD = CALORIES_FROM_FOOD + "
				+ ""+calFood+ ", CALORIES_FROM_EXERCISE =CALORIES_FROM_EXERCISE + "+calEx+ " WHERE USER_ID = " 
				+userID+" AND ENTRY_DATE = '"+d+"'";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			
			ResultSet rs=statement.executeQuery(selectCount);
			if (rs.next()==false)	{
				statement.executeUpdate(insertTableSQL);
			}
			else
				statement.executeUpdate(updateTableSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if (statement != null)
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();	
		}
	}
	
	/**
	 * @author Garth Terlizzi III
	 * @param The ID of the user, the Date associated with the tracker, The calories of the food, and The calories of the exercise
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Deletes a tracker, used for testing purposes only
	 */
	public static void  deleteTracker(int user, Date d) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String deleteTableSQL = "DELETE FROM FITNESS_TRACKER WHERE USER_ID = " +user+" AND ENTRY_DATE = '"+d+"'";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			statement.executeUpdate(deleteTableSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) 
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();
		}
	}
}

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
	 * @author Garth Terlizzi
	 * Database constructor, exists only to defeat instantiation.
	 */
    protected DatabaseGateway() { }
    /**
     * @author Garth Terlizzi
     * @return DatabaseGateway instance, core of the singleton
     * Returns the instance
     */
	public static DatabaseGateway getInstance() {
	      if(instance == null) {
	         instance = new DatabaseGateway();
	      }
	      return instance;
	   }

	/**
	 * @author Garth Terlizzi III
	 * @param id- The id of the user that owns the goal
	 * @param goal- The goal of the user in string format
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Inserts a goal to the database, with the user as the kety
	 */
	public void insertGoal(int id, String goal) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String insertTableSQL = "INSERT INTO Goals" + "(User_ID, Goal_Name) " + "VALUES"+ "("+id+", '"+goal+"')";
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
	 * @param id- The id of the user that owns the goal
	 * @param goal- The goal of the user in string format
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Deletes the goal of the user from the database
	 */
	public void deleteGoal(int id, String goal) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String deleteTableSQL = "DELETE FROM Goals WHERE USER_ID = " +id+" AND Goal_Name = '"+goal+"'";
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
	/**
	 * @author Garth Terlizzi III
	 * @param The ID of the user
	 * @return An arrayList of goals that belong to the user
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Gets all goals by the user
	 */
	public ArrayList<String> getGoals(int id) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		ArrayList<String> myGoals=new ArrayList<String>();
		String selectTableSQL = "SELECT * FROM Goals WHERE USER_ID = "+ id+" ORDER BY Goal_Name ASC";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) 
				throw new SQLException();
			else {
				do {
					myGoals.add(rs.getString("Goal_Name"));
				} while (rs.next());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if (statement != null)
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();	
		}
		return myGoals;
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
	 * @param The food name
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Deletes a food from the database (Used for testing purposes and database cleanup)
	 */
	public void deleteFood(String name) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String deleteSQL = "DELETE FROM Foods WHERE Food_name = ' "+name+"'";
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
	public ArrayList<String> DisplayFoodFromCategory(String cat) throws SQLException{
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
	 * @param The exercise name
	 * @return An ArrayList with all exercise associated from the category
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Shows all exercise in a given category
	 */
	public ArrayList<String> DisplayExerciseFromCategory(String cat) throws SQLException{
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
	 * @param The name of the exercise
	 * @param The new amount of calories the food should have
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Updates a food, provided it is in the database
	 */
	public void updateCaloriesExercise(String exName, int cals) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "UPDATE Exercise SET Calories = "+cals+ " WHERE Exercise_NAME = '"+exName+"'";
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
		String updateTableSQL = "UPDATE FITNESS_TRACKER SET Calories_FROM_FOOD = CALORIES_FROM_FOOD + "
				+ ""+calFood+ ", CALORIES_FROM_EXERCISE =CALORIES_FROM_EXERCISE + "+calEx+ " WHERE USER_ID = " 
				+userID+" AND ENTRY_DATE = '"+d+"'";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
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
	 * Adds calories to both trackers.To change one tracker, put a 0 in the tracker you don't want to update
	 * To change one tracker, put a 0 in the tracker you don't want to update
	 */
	public void createTrackerEntry(int userID, Date d, int calFood, int calEx) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		deleteTracker(userID, d);
		String insertTableSQL = "INSERT INTO FITNESS_TRACKER" + "(USER_ID, ENTRY_DATE, Calories_FROM_FOOD, CALORIES_FROM_EXERCISE) "
		+ "VALUES" + "("+userID+",'"+d+"',"+calFood+ ","+calEx+")";
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
	/**
	 * @author Garth Terlizzi III
	 * @param The email of the user
	 * @param The password of the user
	 * @return the user loaded from the database
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Loads a user from the database
	 */
	public User LoadUser(String email, String password) throws SQLException{
		User userLoader=null;
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = '"+email+"' AND  PASSWORD = '"+ password+"'";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) 
				throw new UserNotFoundException("User NOT FOUND");
			else {
					String gender= rs.getString("Gender");
					if(gender.contentEquals("Male"))
						userLoader= MaleUser.getInstance();
					else
						userLoader= FemaleUser.getInstance();
				userLoader.setEmail(email);
				userLoader.setPassword(password);
				userLoader.setUserId(rs.getInt("USER_ID"));
				userLoader.setHeight(rs.getDouble("HEIGHT"));
				userLoader.setWeight(rs.getDouble("WEIGHT"));
				userLoader.setGender(rs.getString("GENDER"));
				userLoader.setFirstName(rs.getString("FIRST_NAME"));
				userLoader.setLastName(rs.getString("LAST_NAME"));
				userLoader.setFitnessScore(rs.getInt("FITNESS_SCORE"));
				if(rs.getDouble("NECK_MEASUREMENT") != 0) 
					userLoader.setNeckMeasurment(rs.getDouble("NECK_MEASUREMENT"));
				if(rs.getDouble("WAIST_MEASUREMENT") !=0) 
					userLoader.setWaistMeasurment(rs.getDouble("WAIST_MEASUREMENT"));
				if(rs.getDouble("HIP_MEASUREMENT") !=0 && userLoader instanceof FemaleUser) {
					FemaleUser f=(FemaleUser)userLoader;//Down-casting
					f.setHipMeasurment(rs.getDouble("HIP_MEASUREMENT"));
					userLoader=f;
				}
				selectTableSQL= "SELECT * FROM FITNESS_TRACKER WHERE USER_ID = " + userLoader.getUserId();
				rs = statement.executeQuery(selectTableSQL);
				while(rs.next()) {
					userLoader.setDataPointCalorieMap(rs.getDate("ENTRY_DATE"), rs.getInt("CALORIES_FROM_FOOD"));
					userLoader.setDataPointExerciseMap(rs.getDate("ENTRY_DATE"), rs.getInt("CALORIES_FROM_EXERCISE"));
				} 
			} 
		}catch (SQLException|UserNotFoundException e) {
			System.out.println(e.getMessage());
		}finally {
			if (statement != null)
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();	
		}
		return userLoader;
	}
	
	/**
	 * @author Garth Terlizzi III
	 * @param The id of the user
	 * @param The height,weight,neck, waist, and hip that can be updated by the values
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Updates the values of the user, if no update is wanted, a 0 is placed.
	 */
	public void updateValues(int id, Integer height, Integer weight, Double neck, Double waist, Double hip) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = null;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			if(height != 0) {
				selectTableSQL = "UPDATE Users SET Height = "+height+ " WHERE USER_ID = "+id;
				statement.executeUpdate(selectTableSQL);
			}
			if(weight != 0) {
				selectTableSQL = "UPDATE Users SET Weight = "+weight+ " WHERE USER_ID = "+id;
				statement.executeUpdate(selectTableSQL);
			}
			if(neck != 0) {
				selectTableSQL = "UPDATE Users SET NECK_MEASUREMENT = "+neck+ " WHERE USER_ID = "+id;
				statement.executeUpdate(selectTableSQL);
			}
			if(waist != 0) {
				selectTableSQL = "UPDATE Users SET Waist_Measurement = "+waist+ " WHERE USER_ID = "+id;
				statement.executeUpdate(selectTableSQL);
			}
			if(hip != 0) {
				selectTableSQL = "UPDATE Users SET Hip_Measurement = "+hip+ " WHERE USER_ID = "+id;
				statement.executeUpdate(selectTableSQL);
			}
			
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
	 * @param The user
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Registers a user into the database
	 */
	public void registrationHelper(User u) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		
		String insertTableSQL = "INSERT INTO USERS" + "(HEIGHT, WEIGHT, NECK_MEASUREMENT, "
				+ "WAIST_MEASUREMENT, FITNESS_SCORE, GENDER, EMAIL_ADDRESS, "
				+ "PASSWORD, FIRST_NAME,LAST_NAME) " + "VALUES"
				+ "("+u.getHeight()+","+u.getWeight()+ ","+u.getNeckMeasurement()+","+u.getWaistMeasurement()+
				 ","+50+ ",'"+ u.getGender()+ "','"+ u.getEmail()+"','"+
				u.getPassword()+"','"+u.getFirstName()+"','"+u.getLastName()+"')";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			statement.executeUpdate(insertTableSQL);
			if (u instanceof FemaleUser) {
				FemaleUser f=(FemaleUser)u;//Down-casting
				String updateTableSQL= "UPDATE USERS SET HIP_MEASUREMENT = "+f.getHipMeasurment()+ " WHERE EMAIL_ADDRESS = '"+f.getEmail()+"'";
				statement.executeUpdate(updateTableSQL);
			}
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
	 * @param The email of the user
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Deletes the user from the database, used for mainly testing purposes.
	 */
	public void deleteUser(String email) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String deleteSQL = "DELETE FROM USERS WHERE EMAIL_ADDRESS = ' "+email+"'";
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
	 * @return An ArrayList
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Deletes the user from the database, used for mainly testing purposes.
	 */
	public ArrayList<Pair<String,Integer>> getTopTenLeaderBoard() throws SQLException{
		
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT * FROM USERS ORDER BY FITNESS_SCORE DESC";
		ArrayList<Pair<String,Integer>> myList=new ArrayList<Pair<String,Integer>>();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			
			if (rs.next() == false) 
				throw new UserNotFoundException("User NOT FOUND");
			else {
				
				for(int i=0;i<10;i++) {
					String fullName= rs.getString("FIRST_NAME") + rs.getString("LAST_NAME");
					Pair<String,Integer> p = new Pair<String,Integer>(fullName,rs.getInt("FITNESS_SCORE") );
					myList.add(p);
					if(rs.next()==false)
						i=10;
				}
			} 
		}catch (SQLException|UserNotFoundException e) {
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
	 * @return The connection from the database
	 * Deletes the user from the database, used for mainly testing purposes.
	 */
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
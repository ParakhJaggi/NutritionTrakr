package FitnessTracker.FTProject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import FitnessTracker.Exceptions.FoodNotFoundException;
import FitnessTracker.Exceptions.UserAlreadyInDatabaseException;
import FitnessTracker.Exceptions.UserNotFoundException;


	
public class DatabaseGateway {
	private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_CONNECTION = "jdbc:derby:Database/MyDB;";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	public void createTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "Alter Table USERS ADD COLUMN EMAIL_ADDRESS VARCHAR(50) UNIQUE";
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
	//This should be called if it is known the food is in the Database 
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
	
	//This should be called if it is known the food is in the Database 
	public void updateCalories(String foodName, int cals) throws SQLException {
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
	//To change one tracker, put a 0 in the tracker you don't want to update
	public void addCaloriesToTrackers(int userID, Date d, int calFood, int calEx) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String updateTableSQL = "UPDATE FITNESS_TRACKER SET Calories_FROM_FOOD = CALORIES_FROM_FOOD + "
				+ ""+calFood+ ", CALORIES_FROM_EXERCISE = CALORIES_FROM_EXERCISE + "+calEx+ " WHERE USER_ID = " 
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
	
	public void createTrackerEntry(int userID, Date d, int calFood, int calEx) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
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
	
	//USE FOR NON-REGISTRATION LOGIN
	public User LoadUser(String email, String password) throws SQLException{
		User userLoader = null;//REMOVE ONCE COMPLETE
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
						userLoader= new MaleUser();
					else
						userLoader= new FemaleUser();
				userLoader.setUserId(rs.getInt("USER_ID"));
				userLoader.setHeight(rs.getDouble("HEIGHT"));
				userLoader.setWeight(rs.getDouble("WEIGHT"));
				userLoader.setGender(rs.getString("GENDER"));
				userLoader.setFirstName(rs.getString("FIRST_NAME"));
				userLoader.setLastName(rs.getString("LAST_NAME"));
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
				do {
					userLoader.setDataPointCalorieMap(rs.getDate("ENTRY_DATE"), rs.getInt("CALORIES_FROM_FOOD"));
					userLoader.setDataPointExerciseMap(rs.getDate("ENTRY_DATE"), rs.getInt("CALORIES_FROM_EXERCISE"));
				} while(rs.next());
			} 
		}catch (SQLException|UserNotFoundException e) {
			System.out.println(e.getMessage());
		}finally {
			if (statement != null)
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();	
		}
		userLoader.setEmail(email);
		userLoader.setPassword(password);
		return userLoader;
	}
	
	//USE WITH REGISTRATION, Loads a user to database. Should only occur once.
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
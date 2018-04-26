package FitnessTracker.FTProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import FitnessTracker.Exceptions.UserNotFoundException;
import javafx.util.Pair;

public class UserDatabaseGateway extends DatabaseGateway {
	/**
	 * @author Garth Terlizzi
	 * Database constructor, exists only to defeat instantiation.
	 */
	private static UserDatabaseGateway instance = null;
    protected UserDatabaseGateway() { }
    /**
     * @author Garth Terlizzi
     * @return DatabaseGateway instance, core of the singleton
     * Returns the instance
     */
	public static UserDatabaseGateway getInstance() {
	      if(instance == null) {
	         instance = new UserDatabaseGateway();
	      }
	      return instance;
	   }
	/**
	 * @author Garth Terlizzi III
	 * @param The email of the user
	 * @param The password of the user
	 * @return the user loaded from the database, null if not found
	 * @throws A SQL Exception if there is an error in accessing the Database
	 * Loads a user from the database
	 */
	public User loadUser(String email, String password) throws SQLException{
		User userLoader=null;
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = '"+email+"' AND  PASSWORD = '"+ password+"'";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) 
				userLoader=null;
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
		}catch (SQLException e) {
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
	
	
	public void updateFitnessScore(String email, int score) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = null;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			
			selectTableSQL = "UPDATE Users SET FITNESS_SCORE = "+score+ " WHERE EMAIL_ADDRESS = '"+email+"'";
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
				 ","+u.getFitnessScore()+ ",'"+ u.getGender()+ "','"+ u.getEmail()+"','"+
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
		String deleteSQL = "DELETE FROM USERS WHERE EMAIL_ADDRESS = '"+email+"'";
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
					String fullName= rs.getString("FIRST_NAME") +" "+ rs.getString("LAST_NAME");
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
}

package FitnessTracker.FTProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GoalDatabaseGateway extends DatabaseGateway {
	private static GoalDatabaseGateway instance = null;
	/**
	 * @author Garth Terlizzi
	 * Database constructor, exists only to defeat instantiation.
	 */
    protected GoalDatabaseGateway() { }
    /**
     * @author Garth Terlizzi
     * @return DatabaseGateway instance, core of the singleton
     * Returns the instance
     */
	public static GoalDatabaseGateway getInstance() {
	      if(instance == null) {
	         instance = new GoalDatabaseGateway();
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
}

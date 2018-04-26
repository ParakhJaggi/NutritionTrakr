package FitnessTracker.FTProject;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runners.MethodSorters;

import FitnessTracker.Exceptions.UserNotFoundException;
import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTest extends TestCase {
	private static DatabaseGateway gateway= DatabaseGateway.getInstance();
	private static MaleUser u= MaleUser.getInstance();
	private static FemaleUser u2= FemaleUser.getInstance();
	/**
	 * @author Terlizzi
	 * @throws SQLException
	 * Tests an insert and reloads to check if the insert was preserved
	 */
	@Test
	public synchronized void testInsert() throws SQLException {
		gateway.deleteUser("Testy@Test3.test");//If exists beforehand, remove from the database
		u.setFitnessScore(50);
		u.setFirstName("Testy");
		u.setLastName("McTestFace");
		u.setEmail("Testy@Test3.test");
		u.setHeight(60);
		u.setWeight(50);
		u.setWaistMeasurment(30);
		u.setNeckMeasurment(14);
		u.setPassword("TestPassword");
		gateway.registrationHelper(u);
		u=(MaleUser)gateway.LoadUser("Testy@Test3.test", "TestPassword");
		assert(u.getFirstName().equals("Testy"));
		assert(u.getLastName().equals("McTestFace"));
		assert(u.getEmail().equals("Testy@Test3.test"));
		assert(u.getGender().equals(u.getGender()));
		assert(u.getFitnessScore()==50);
		assert(u.getHeight()==60);
		assert(u.getWaistMeasurement()==30);
		gateway.deleteUser("Testy@Test3.test");//Clean up Database		
	}
	/**
	 * @author Terlizzi
	 * @throws SQLException
	 * Tests an insert with a bad load and verifies that the user is null
	 */
	@Test
	public synchronized void badLoad() throws SQLException {
		User u=gateway.LoadUser("BAD_LOAD", "USER_NOT_FOUND");
		assert(u==null);
	}
	/**
	 * @author Terlizzi
	 * @throws SQLException
	 * Adds a food to the table and checks preservation
	 */
	@Test
	public synchronized void testAddFood() throws SQLException {
		gateway.addFoodToTable("Test4", "TestCategory", 100);
		Food f= gateway.retrieveFood("Test4");
		assert(f.getName().equals("Test4"));
		assert(f.getCalories()==100);
		assert(f.getCategory().equals("TestCategory"));
		gateway.deleteFoodEx("Test4",true);//Database cleanup
	}
	/**
	 * @author Terlizzi
	 * @throws SQLException
	 * Adds an exercise to the table and checks preservation
	 */
	@Test
	public synchronized void testAddExercise() throws SQLException {
		gateway.addExerciseToTable("Test4", "TestCategory", 100);
		Exercise e= gateway.retrieveExercise("Test4");
		assert(e.getName().equals("Test4"));
		assert(e.getCalories()==100);
		assert(e.getCategory().equals("TestCategory"));
		gateway.deleteFoodEx("Test4",false);
	}
	/**
	 * @author Terlizzi
	 * @throws SQLException
	 * Checks to see if the categories properly display for the Foods
	 */
	@Test
	public synchronized void testDisplayCategoryFood() throws SQLException {
		gateway.addFoodToTable("Test", "TestCategory", 100);
		gateway.addFoodToTable("Test2", "TestCategory", 101);
		gateway.addFoodToTable("Test3", "TestCategory", 101);
		ArrayList<String> myArray=gateway.DisplayFoodFromCategory("TestCategory");
		assert(myArray.get(0).equals("Test"));
		assert(myArray.get(1).equals("Test2"));
		assert(myArray.get(2).equals("Test3"));
		gateway.deleteFoodEx("Test",true);
		gateway.deleteFoodEx("Test2",true);
		gateway.deleteFoodEx("Test3",true);
	}
	/**
	 * @author Terlizzi
	 * @throws SQLException
	 * Checks to see if the categories properly display for the Exercises
	 */
	@Test
	public synchronized void testDisplayCategoryEx() throws SQLException {
		gateway.addExerciseToTable("Test", "TestCategory", 100);
		gateway.addExerciseToTable("Test2", "TestCategory", 101);
		gateway.addExerciseToTable("Test3", "TestCategory", 101);
		ArrayList<String> myArray=gateway.DisplayExerciseFromCategory("TestCategory");
		assert(myArray.get(0).equals("Test"));
		assert(myArray.get(1).equals("Test2"));
		assert(myArray.get(2).equals("Test3"));
		gateway.deleteFoodEx("Test",false);
		gateway.deleteFoodEx("Test2",false);
		gateway.deleteFoodEx("Test3",false);
	}
	/**
	 * @author Terlizzi
	 * @throws SQLException
	 * Updates a food already in the table
	 */
	@Test
	public synchronized void testUpdateFood() throws SQLException {
		gateway.addFoodToTable("Test", "TestCategory", 100);
		gateway.updateCaloriesFood("Test", 5000);
		Food f= gateway.retrieveFood("Test");
		assert(f.getName().equals("Test"));
		assert(f.getCalories()==5000);
		assert(f.getCategory().equals("TestCategory"));
		gateway.deleteFoodEx("Test",true);
	}
	/**
	 * @author Terlizzi
	 * @throws SQLException
	 * Updates an exercise already in the table
	 */
	@Test
	public synchronized void testUpdateExercise() throws SQLException {
		gateway.addExerciseToTable("Test", "TestCategory", 100);
		gateway.updateCaloriesExercise("Test", 5000);
		Exercise e= gateway.retrieveExercise("Test");
		assert(e.getName().equals("Test"));
		assert(e.getCalories()==5000);
		assert(e.getCategory().equals("TestCategory"));
		gateway.deleteFoodEx("Test",false);
	}
	/**
	 * @author Terlizzi
	 * @throws SQLException
	 * Tests the trackers in the table
	 */
	@Test
	public synchronized void testTrackers() throws SQLException {
		
		User u= new MaleUser();
		u.setFitnessScore(50);
		u.setFirstName("Testy");
		u.setLastName("McTestFace");
		u.setEmail("Testy@Testy.test");
		u.setPassword("TestPassword");
		u.setHeight(60);
		u.setWeight(50);
		u.setWaistMeasurment(30);
		u.setNeckMeasurment(14);
		u.setPassword("TestPassword");
		gateway.registrationHelper(u);
		u=gateway.LoadUser("Testy@Testy.test", "TestPassword");
		int originalCal=u.getDataPointCalorieMap(Date.valueOf(LocalDate.now()));
		int originalEx=u.getDataPointExerciseMap(Date.valueOf(LocalDate.now()));
		gateway.addCaloriesToTrackers(u.getUserId(), Date.valueOf(LocalDate.now()), 1, 2);
		u=gateway.LoadUser("Testy@Testy.test", "TestPassword");
		
		assert(u.getDataPointCalorieMap(Date.valueOf(LocalDate.now()))==originalCal+1);
		assert(u.getDataPointExerciseMap(Date.valueOf(LocalDate.now()))==originalEx+2);
		gateway.deleteUser("Testy@Test.test");//Clean up Database
	}
	
	/**
	 * @author Terlizzi
	 * @throws SQLException
	 * Tests an update to the trackers in the table
	 */
	@Test
	public synchronized void testTrackerUpdate() throws SQLException {
		//gateway.deleteUser("Test");//If exists, deletes from database
		u2.setFirstName("Testy");
		u2.setLastName("McTestFace");
		u2.setEmail("Test");
		u2.setPassword("TestPassword");
		gateway.registrationHelper(u2);
		u2=(FemaleUser)gateway.LoadUser("Test", "TestPassword");
		gateway.addCaloriesToTrackers(u2.getUserId(), Date.valueOf(LocalDate.now()), 6, 2);
		int originalCals=u2.getDataPointCalorieMap(Date.valueOf(LocalDate.now()));
		u2=(FemaleUser)gateway.LoadUser("Test", "TestPassword");
		int cals=u2.getDataPointCalorieMap(Date.valueOf(LocalDate.now()));
		assert(cals==originalCals+6);
		gateway.addCaloriesToTrackers(u2.getUserId(),  Date.valueOf(LocalDate.now()), 5, 0);
		u2=(FemaleUser)gateway.LoadUser("Test", "TestPassword");
		cals=u2.getDataPointCalorieMap(Date.valueOf(LocalDate.now()));
		assert(cals==originalCals+11);
		gateway.deleteUser("Test");//Clean up Database
	}
	/**
	 * @author Terlizzi
	 * @throws SQLException
	 * Verifies the leaderboards are working by inserting a test with an impossible fitness score.
	 */
	@Test
	public synchronized void testleaderboard() throws SQLException {
		gateway.deleteUser("leaderboard");
		u2.setFitnessScore(101);
		u2.setEmail("leaderboard");
		u2.setFirstName("Test");
		u2.setLastName("Test");
		gateway.registrationHelper(u2);
		String x=gateway.getTopTenLeaderBoard().get(0).getKey();
		int y=gateway.getTopTenLeaderBoard().get(0).getValue();
		assert(x.equals("Test Test"));
		assert(y==101);
		gateway.deleteUser("leaderboard");
	}
	
	
}

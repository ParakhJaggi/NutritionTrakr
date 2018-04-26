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
	@Test
	public synchronized void testInsert() throws SQLException {
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
	
	@Test
	public synchronized void badLoad() throws SQLException {
		User u=gateway.LoadUser("BAD_LOAD", "USER_NOT_FOUND");
		assert(u==null);
	}
	
	@Test
	public synchronized void testAddFood() throws SQLException {
		gateway.addFoodToTable("Test4", "TestCategory", 100);
		Food f= gateway.retrieveFood("Test4");
		assert(f.getName().equals("Test4"));
		assert(f.getCalories()==100);
		assert(f.getCategory().equals("TestCategory"));
		gateway.deleteFood("Test4");
	}
	
	@Test
	public synchronized void testDisplayCategory() throws SQLException {
		gateway.addFoodToTable("Test", "TestCategory", 100);
		gateway.addFoodToTable("Test2", "TestCategory", 101);
		gateway.addFoodToTable("Test3", "TestCategory", 101);
		ArrayList<String> myArray=gateway.DisplayFoodFromCategory("TestCategory");
		assert(myArray.get(0).equals("Test"));
		assert(myArray.get(1).equals("Test2"));
		assert(myArray.get(2).equals("Test3"));
		gateway.deleteFood("Test");
		gateway.deleteFood("Test2");
		gateway.deleteFood("Test3");
	}
	
	@Test
	public synchronized void testUpdateCalories() throws SQLException {
		gateway.addFoodToTable("Test", "TestCategory", 100);
		gateway.updateCaloriesFood("Test", 5000);
		Food f= gateway.retrieveFood("Test");
		assert(f.getName().equals("Test"));
		assert(f.getCalories()==5000);
		assert(f.getCategory().equals("TestCategory"));
		gateway.deleteFood("Test");
	}
	
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
		gateway.createTrackerEntry(u.getUserId(), Date.valueOf(LocalDate.now()), 1, 2);
		assert(u.getDataPointCalorieMap(Date.valueOf(LocalDate.now()))==1);
		assert(u.getDataPointExerciseMap(Date.valueOf(LocalDate.now()))==2);
		gateway.deleteUser("Testy@Test.test");//Clean up Database
	}
	
	
	@Test
	public synchronized void testTrackerUpdate() throws SQLException {
		gateway.deleteUser("Test");//If exists, deletes from database
		u2.setFirstName("Testy");
		u2.setLastName("McTestFace");
		u2.setEmail("Test");
		u2.setPassword("TestPassword");
		gateway.registrationHelper(u2);
		u2=(FemaleUser)gateway.LoadUser("Test", "TestPassword");
		gateway.createTrackerEntry(u2.getUserId(), Date.valueOf(LocalDate.now()), 6, 2);
		u2=(FemaleUser)gateway.LoadUser("Test", "TestPassword");
		int cals=u2.getDataPointCalorieMap(Date.valueOf(LocalDate.now()));
		assert(cals==6);
		gateway.addCaloriesToTrackers(u2.getUserId(),  Date.valueOf(LocalDate.now()), 5, 0);
		u2=(FemaleUser)gateway.LoadUser("Test", "TestPassword");
		cals=u2.getDataPointCalorieMap(Date.valueOf(LocalDate.now()));
		assert(cals==11);
		gateway.deleteUser("Test");//Clean up Database
	}
	
	@Test
	public synchronized void testleaderboard() throws SQLException {
		u2.clear();
		u2=FemaleUser.getInstance();
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

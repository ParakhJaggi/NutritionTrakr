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
	@Test
	public void testInsert() throws SQLException {
			MaleUser u= new MaleUser();
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
			User u2=gateway.LoadUser("Testy@Test3.test", "TestPassword");
			assert(u2.getFirstName().equals(u.getFirstName()));
			assert(u2.getLastName().equals(u.getLastName()));
			assert(u2.getEmail().equals(u.getEmail()));
			assert(u2.getGender().equals(u.getGender()));
			assert(u2.getFitnessScore()==u.getFitnessScore());
			assert(u2.getHeight()==u.getHeight());
			assert(u2.getWaistMeasurement()==u.getWaistMeasurement());
			
			gateway.deleteUser("Testy@Test3.test");//Clean up Database
			
	}
	
	@Test
	public void badLoad() throws SQLException {
		User u=gateway.LoadUser("BAD_LOAD", "USER_NOT_FOUND");
		assert(u==null);
	}
	
	@Test
	public void testAddFood() throws SQLException {
		gateway.addFoodToTable("Test4", "TestCategory", 100);
		Food f= gateway.retrieveFood("Test4");
		assert(f.getName().equals("Test4"));
		assert(f.getCalories()==100);
		assert(f.getCategory().equals("TestCategory"));
		gateway.deleteFood("Test4");
	}
	
	@Test
	public void testDisplayCategory() throws SQLException {
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
	public void testUpdateCalories() throws SQLException {
		gateway.addFoodToTable("Test", "TestCategory", 100);
		gateway.updateCaloriesFood("Test", 5000);
		Food f= gateway.retrieveFood("Test");
		assert(f.getName().equals("Test"));
		assert(f.getCalories()==5000);
		assert(f.getCategory().equals("TestCategory"));
		gateway.deleteFood("Test");
	}
	
	@Test
	public void testTrackers() throws SQLException {
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
	public void testTrackerUpdate() throws SQLException {
		User u= new MaleUser();
		u.setFitnessScore(50);
		u.setFirstName("Testy");
		u.setLastName("McTestFace");
		u.setEmail("Testy@Testy2.test");
		u.setPassword("TestPassword");
		u.setHeight(60);
		u.setWeight(50);
		u.setWaistMeasurment(30);
		u.setNeckMeasurment(14);
		u.setPassword("TestPassword");
		gateway.registrationHelper(u);
		u=gateway.LoadUser("Testy@Testy2.test", "TestPassword");
		gateway.createTrackerEntry(u.getUserId(), Date.valueOf(LocalDate.now().plusDays(500)), 1, 2);
		assert(u.getDataPointCalorieMap(Date.valueOf(LocalDate.now()))==1);
		gateway.addCaloriesToTrackers(u.getUserId(), Date.valueOf(LocalDate.now().plusDays(500)), 5, 0);
		u=gateway.LoadUser("Testy@Testy2.test", "TestPassword");
		assert(u.getDataPointCalorieMap(Date.valueOf(LocalDate.now().plusDays(500)))==6);
		
		gateway.deleteUser("Testy@Testy2.test");//Clean up Database
	}
	
	@Test
	public void testleaderboard() throws SQLException {
		gateway.getTopTenLeaderBoard();
	}
	
	Lock sequential = new ReentrantLock();

	@Override
	protected void setUp() throws Exception {
	    super.setUp();
	    sequential.lock();
	}

	@Override
	protected void tearDown() throws Exception {
	    sequential.unlock();
	    super.tearDown();
	}
}
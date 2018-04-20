package FitnessTracker.FTProject;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import FitnessTracker.Exceptions.UserNotFoundException;
import junit.framework.TestCase;

public class DatabaseTest {
	
	private static DatabaseGateway gateway= DatabaseGateway.getInstance();
	@Test
	public void testInsert() throws SQLException {
			MaleUser u= new MaleUser();
			u.setFitnessScore(50);
			u.setFirstName("Testy");
			u.setLastName("McTestFace");
			u.setEmail("Testy@Test.test");
			u.setHeight(60);
			u.setWeight(50);
			u.setWaistMeasurment(30);
			u.setNeckMeasurment(14);
			u.setPassword("TestPassword");
			gateway.registrationHelper(u);
			User u2=gateway.LoadUser("Testy@Test.test", "TestPassword");
			assert(u2.getFirstName().equals(u.getFirstName()));
			assert(u2.getLastName().equals(u.getLastName()));
			assert(u2.getEmail().equals(u.getEmail()));
			assert(u2.getGender().equals(u.getGender()));
			assert(u2.getFitnessScore()==u.getFitnessScore());
			assert(u2.getHeight()==u.getHeight());
			assert(u2.getWaistMeasurement()==u.getWaistMeasurement());
			
			gateway.deleteUser("Testy@Test.test");//Clean up Database
			
	}
	
	@Test
	public void badLoad() throws SQLException {
		User u=gateway.LoadUser("BAD_LOAD", "USER_NOT_FOUND");
		assert(u==null);
	}
	
	@Test
	public void testAddFood() throws SQLException {
		gateway.addFoodToTable("Test", "TestCategory", 100);
		Food f= gateway.retrieveFood("Test");
		assert(f.getName().equals("Test"));
		assert(f.getCalories()==100);
		assert(f.getCategory().equals("TestCategory"));
		gateway.deleteFood("Test");
	}
	
	@Test
	public void testDisplayCategory(String cat) throws SQLException {
		gateway.addFoodToTable("Test", "TestCategory", 100);
		gateway.addFoodToTable("Test2", "TestCategory", 101);
		gateway.addFoodToTable("Test3", "TestCategory", 101);
		//ArrayList<String>my array=gateway
	}
}

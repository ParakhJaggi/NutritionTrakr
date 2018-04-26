package FitnessTracker.FTProject;

import java.sql.SQLException;



import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class UserTests {
	private static MaleUser u= MaleUser.getInstance();
	private static FemaleUser u2= FemaleUser.getInstance();
	/**
	 * @author Terlizzi
	 * Verifies the male has the right body fat
	 */
	@Test
	public void verifyMaleBFA() {
		u.setFirstName("Garth");
		u.setLastName("Terlizzi");
		u.setHeight(69);
		u.setWeight(165.5);
		u.setWaistMeasurment(34);
		u.setNeckMeasurment(15.25);
		assert(u.calculateBFNavyMethod()==17.456010245873976);
	}
	/**
	 * @author Terlizzi
	 * Verifies the female has the right body fat
	 */
	@Test
	public void verifyFemaleBFA() {
		u2.setFirstName("Garthina");
		u2.setLastName("Terlizzi");
		u2.setHeight(69);
		u2.setWeight(165.5);
		u2.setWaistMeasurment(33);
		u2.setNeckMeasurment(10);
		u2.setHipMeasurment(51);
		System.out.println(u2.calculateBFNavyMethod());
		assert(u2.calculateBFNavyMethod()==22.386942013216697);
	}
	/**
	 * @author Terlizzi
	 * Verifies the bmi is consistent regardless of gender and measurements
	 */
	@Test
	public void verifyBMI() {
		u.setHeight(69);
		u.setWeight(165.5);
		u2.setHeight(69);
		u2.setWeight(165.5);
		assert(u.calculateBMI()==u2.calculateBMI());
	}
}

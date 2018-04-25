package FitnessTracker.FTProject;
/*
 * @author Garth Terlizzi III
 * Initiated if the gender of the user is "Female"
 */
public class FemaleUser extends User {
	private static FemaleUser instance = null;
	   protected FemaleUser() {
	      // Exists only to defeat instantiation.
	   }
	   /*
	    * @author Garth Terlizzi
	    * @return The instance of the user
	    */
	   public static FemaleUser getInstance() {
	      if(instance == null) {
	         instance = new FemaleUser();
	      }
	      return instance;
	   }

	public double hipMeasurement;
	
	public void setHipMeasurment(double h) {
		hipMeasurement=h;
	}
	public double getHipMeasurment() {
		return hipMeasurement;
	}
	
	public String getGender() {
		return "Female";
	}
	 /*
		* @author Garth Terlizzi III
		* @return The Body Fat of the User
		* Note: The Body Fat Navy Method calculates the body fat of the user using a height, waist Measurement, neck measurement, and Hip Measurement
		*/
	@Override
	public double calculateBFNavyMethod() {
		return 163.205*Math.log10(waistMeasurement+hipMeasurement-neckMeasurement)- 70.041*Math.log10(height)+36.76;
	}
}

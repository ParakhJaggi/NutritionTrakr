package FitnessTracker.FTProject;
/*
 * @author Garth Terlizzi III
 * Initiated if the gender of the user is "Male"
 */
public class MaleUser extends User {
	private static MaleUser instance = null;
	   protected MaleUser() {
	   }
	   /*
	    * @author Garth Terlizzi
	    * @return The instance of the user
	    */
	   public static MaleUser getInstance() {
	      if(instance == null) {
	         instance = new MaleUser();
	      }
	      return instance;
	   }
	   
   /*
	* @author Garth Terlizzi III
	* @return The Body Fat of the User
	* Note: The Body Fat Navy Method calculates the body fat of the user using a height, waist Measurement neck Measurement
	*/
	@Override
	public double calculateBFNavyMethod() {
		return 86.010*Math.log10(waistMeasurement-neckMeasurement)- 70.041*Math.log10(height)+36.76;
	}
	
	public String getGender() {
		return "Male";
	}
}

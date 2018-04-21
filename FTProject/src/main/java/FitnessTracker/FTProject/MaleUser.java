package FitnessTracker.FTProject;
//test
public class MaleUser extends User {
	private static MaleUser instance = null;
	   protected MaleUser() {
	      // Exists only to defeat instantiation.
	   }
	   public static MaleUser getInstance() {
	      if(instance == null) {
	         instance = new MaleUser();
	      }
	      return instance;
	   }
	@Override
	public double calculateBFNavyMethod() {
		return 86.010*Math.log10(waistMeasurement-neckMeasurement)- 70.041*Math.log10(height)+36.76;
	}
	
	public String getGender() {
		return "Male";
	}
}

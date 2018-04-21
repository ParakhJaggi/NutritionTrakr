package FitnessTracker.FTProject;

public class FemaleUser extends User {
	private static FemaleUser instance = null;
	   protected FemaleUser() {
	      // Exists only to defeat instantiation.
	   }
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
	@Override
	public double calculateBFNavyMethod() {
		return 163.205*Math.log10(waistMeasurement+hipMeasurement-neckMeasurement)- 70.041*Math.log10(height)+36.76;
	}
}

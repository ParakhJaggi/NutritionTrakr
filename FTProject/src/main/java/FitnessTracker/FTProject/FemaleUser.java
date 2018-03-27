package FitnessTracker.FTProject;

public class FemaleUser extends User {
	public FemaleUser(double hip) {
		this.hipMeasurement=hip;
	}

	public double hipMeasurement;
	
	public void setHipMeasurment(double h) {
		hipMeasurement=h;
	}
	public double getHipMeasurment() {
		return hipMeasurement;
	}
	
	@Override
	public double calculateBFNavyMethod() {
		return 163.205*Math.log10(waistMeasurement+hipMeasurement-neckMeasurement)- 70.041*Math.log10(height)+36.76;
	}
}

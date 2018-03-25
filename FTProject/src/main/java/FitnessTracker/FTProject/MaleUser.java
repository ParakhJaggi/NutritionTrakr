package FitnessTracker.FTProject;

public class MaleUser extends User {
	public MaleUser(int id) {
		super(id);
	}

	@Override
	public double calculateBFNavyMethod() {
		return 86.010*Math.log10(waistMeasurement-neckMeasurement)- 70.041*Math.log10(height)+36.76;
	}
}

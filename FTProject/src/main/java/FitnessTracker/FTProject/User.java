package FitnessTracker.FTProject;

public abstract class User {
	double height;//In in
	double weight;//In Lbs
	int avgDailyCalorieIntake;
	int avgDailyCalorieBurn;
	double waistMeasurement;//Inches
	double neckMeasurement;//Inches
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double h) {
		height=h;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double w) {
		weight=w;
	}
	
	public double getWaistMeasurement() {
		return waistMeasurement;
	}
	
	public void setWaistMeasurment(double w) {
		waistMeasurement=w;
	}
	public double getNeckMeasurement() {
		return neckMeasurement;
	}
	
	public void setNeckMeasurment(double n) {
		neckMeasurement=n;
	}
	
	public int getAvgDailyCalorieIntake() {
		return avgDailyCalorieIntake;
	}
	
	public void setAvgDailyCalorieIntake(int a) {
		avgDailyCalorieIntake=a;
	}
	
	public int getAvgDailyCalorieBurn() {
		return avgDailyCalorieBurn;
	}
	
	public void setAvgDailyCalorieBurn(int a) {
		avgDailyCalorieBurn=a;
	}
	
	public double calculateBMI() {
		return (.45*weight)/(.000625*height*height);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + avgDailyCalorieBurn;
		result = prime * result + avgDailyCalorieIntake;
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(neckMeasurement);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(waistMeasurement);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (avgDailyCalorieBurn != other.avgDailyCalorieBurn)
			return false;
		if (avgDailyCalorieIntake != other.avgDailyCalorieIntake)
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (Double.doubleToLongBits(neckMeasurement) != Double.doubleToLongBits(other.neckMeasurement))
			return false;
		if (Double.doubleToLongBits(waistMeasurement) != Double.doubleToLongBits(other.waistMeasurement))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	public abstract double calculateBFNavyMethod();//Note the method is different for both Male and Females
}

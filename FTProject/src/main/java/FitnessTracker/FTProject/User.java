package FitnessTracker.FTProject;


import java.sql.Date;
import java.util.HashMap;

public abstract class User {
	int user_id;
	double height;//In in
	double weight;//In Lbs
	HashMap<Date, Integer> calorieMap;
	HashMap<Date, Integer> exerciseMap;
	double waistMeasurement;//Inches
	double neckMeasurement;//Inches
	
	public User(int id) {
		user_id=id;
	}
	public int getUserId() {
		return user_id;
		}
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
	public double calculateBMI() {
		return (.45*weight)/(.000625*height*height);
	}
	public int getDataPointCalorieMap(Date d) {
		return calorieMap.get(d);
	}
	public int getDataPointExerciseMap(Date d) {
		return exerciseMap.get(d);
	}
	public void setDataPointCalorieMap(Date d,Integer i) {
		calorieMap.put(d,i);
	}
	public void setDataPointExerciseMap(Date d,Integer i) {
		exerciseMap.put(d,i);
	}
	
	public abstract double calculateBFNavyMethod();//Note the method is different for both Male and Females
}

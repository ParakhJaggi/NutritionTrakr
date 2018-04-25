package FitnessTracker.FTProject;


import java.sql.Date;
import java.util.HashMap;
/*
 * @author Garth Terlizzi III
 * Abstract class with setters and getters, references the user at login
 */
public abstract class User {
	String firstName;
	String lastName;
	String gender;
	String email;
	String password;
	
	int fitnessScore;
	int user_id;
	double height;//In in
	double weight;//In Lbs
	HashMap<Date, Integer> calorieMap=new HashMap<Date,Integer>();
	HashMap<Date, Integer> exerciseMap=new HashMap<Date,Integer>();
	double waistMeasurement;//Inches
	double neckMeasurement;//Inches
	
	public User() {};
	
	public void setUserId(int i) {
		user_id=i;
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
		if(calorieMap.get(d)==null)
			return 0;
		return calorieMap.get(d);
	}
	public int getDataPointExerciseMap(Date d) {
		if(exerciseMap.get(d)==null)
			return 0;
		return exerciseMap.get(d);
	}
	public void setDataPointCalorieMap(Date d,Integer i) {
		calorieMap.put(d,i);
	}
	public void setDataPointExerciseMap(Date d,Integer i) {
		exerciseMap.put(d,i);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getFitnessScore() {
		return fitnessScore;
	}

	public void setFitnessScore(int fitnessScore) {
		this.fitnessScore = fitnessScore;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/*
	 * @author Garth Terlizzi
	 * Different for Male and Females
	 */
	public abstract double calculateBFNavyMethod();
	/*
	 * @author Garth Terlizzi
	 * @return a score that correlates to the Fitness Tracker
	 */
	public int getScore() {
		//Implement later
		return 50;
	}
}

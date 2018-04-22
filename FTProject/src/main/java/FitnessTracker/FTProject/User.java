package FitnessTracker.FTProject;


import java.sql.Date;
import java.util.HashMap;

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
	
	public User() {
	};
	
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
	public abstract double calculateBFNavyMethod();//Note the method is different for both Male and Females

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calorieMap == null) ? 0 : calorieMap.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((exerciseMap == null) ? 0 : exerciseMap.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + fitnessScore;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		temp = Double.doubleToLongBits(neckMeasurement);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + user_id;
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
		if (calorieMap == null) {
			if (other.calorieMap != null)
				return false;
		} else if (!calorieMap.equals(other.calorieMap))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (exerciseMap == null) {
			if (other.exerciseMap != null)
				return false;
		} else if (!exerciseMap.equals(other.exerciseMap))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (fitnessScore != other.fitnessScore)
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (Double.doubleToLongBits(neckMeasurement) != Double.doubleToLongBits(other.neckMeasurement))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (user_id != other.user_id)
			return false;
		if (Double.doubleToLongBits(waistMeasurement) != Double.doubleToLongBits(other.waistMeasurement))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}
	
	public int getScore() {
		//Implement later
		return 50;
	}
}

package FitnessTracker.FTProject;
/*
 * @author Garth Terlizzi III
 * Basic Food class to retreive exercise information
 */
public class Exercise {
	int calories;
	String category;
	String name;
	
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
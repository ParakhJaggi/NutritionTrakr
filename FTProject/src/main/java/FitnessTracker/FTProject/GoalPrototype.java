package FitnessTracker.FTProject;

public abstract class GoalPrototype implements Cloneable{
	public GoalPrototype clone() throws CloneNotSupportedException{
        return (GoalPrototype) super.clone();
    }
	

}

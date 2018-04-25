package FitnessTracker.FTProject;
/**
 * 
 * @author ParakhJaggi
 * This class will help the Prototype Design Model
 *
 */
public abstract class GoalPrototype implements Cloneable{
	public GoalPrototype clone() throws CloneNotSupportedException{
        return (GoalPrototype) super.clone();
    }
	

}

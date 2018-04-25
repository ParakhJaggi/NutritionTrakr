package FitnessTracker.FTProject;

import java.util.ArrayList;
/**
 * 
 * @author ParakhJaggi
 * THis class will implement memento
 *
 */
public class UndoListMemento extends GoalPrototype {
	private String state;
	/**
	 * @author ParakhJaggi
	 * @param state-the state of the object 
	 * This is the contructor for the memento
	 */
    public UndoListMemento(String state) {
        this.state = state;
    }
    /**
     * @author ParakhJaggi
     * @return String-the state's string 
     * This meathod will return the String of the state 
     */
    public String getState() {
        return state;
    }
    /**
     * @author ParakhJaggi
     * This method will implement Prototyping 
     */
    public GoalPrototype clone() throws CloneNotSupportedException{
        return (UndoListMemento) super.clone();
    }

}

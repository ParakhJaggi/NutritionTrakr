package FitnessTracker.FTProject;
/**
 * 
 * @author ParakhJaggi
 * This class is using memento to get the deleted goals back 
 *
 */
public class PreviousGoals {
	 private String state;
	    /**
	     * @author ParakhJaggi
	     * @param state-the state of the object 
	     * This method will set the state of the object 
	     */
	    public void setState(String state) {
	        this.state = state;
	    }
	    /**
	     * @author ParakhJaggi
	     * @return UndoListMemento-the memento object
	     * This method will let the user set the memento string 
	     */
	    public UndoListMemento save() {
	        return new UndoListMemento(state);
	    }
	    /**
	     * @author ParakhJaggi
	     * @param m
	     * This method will restore the last deleted goal 
	     */
	    public void restore(UndoListMemento m) {
	    	
	        state = m.getState();
	    }

}

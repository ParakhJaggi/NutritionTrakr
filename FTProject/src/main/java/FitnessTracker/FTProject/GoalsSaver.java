package FitnessTracker.FTProject;

import java.util.ArrayList;
/**
 * 
 * @author ParakhJaggi
 * This class will use momento to save deleted goals and let the user undo
 *
 */
public class GoalsSaver  {
	private ArrayList<UndoListMemento> mementos = new ArrayList<>();
	/**
	 * @author ParakhJaggi
	 * @param m-The memento object
	 * This method will add a memento to the Deleted list 
	 * 
	 */
    public void addMemento(UndoListMemento m) {
        mementos.add(0,m);
    }
    /**
     * @author ParakhJaggi
     * @return UndoListMemento-the mementoobject 
     * This method will get the memento String
     * 
     */
    public UndoListMemento getMemento() {
        UndoListMemento m =  mementos.get(0);
        mementos.remove(0);
        try {
			m.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
        return m;
        
    }
    

}

package FitnessTracker.FTProject;

public class PreviousGoals {
	 private String state;
	  
	    public void setState(String state) {
	        System.out.println("Originator: Setting state to " + state);
	        this.state = state;
	    }

	    public UndoListMemento save() {
	        System.out.println("Originator: Saving to Memento.");
	        return new UndoListMemento(state);
	    }
	    public void restore(UndoListMemento m) {
	        state = m.getState();
	        System.out.println("Originator: State after restoring from Memento: " + state);
	    }

}

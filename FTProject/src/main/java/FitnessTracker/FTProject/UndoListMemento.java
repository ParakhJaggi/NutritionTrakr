package FitnessTracker.FTProject;

import java.util.ArrayList;

public class UndoListMemento {
	private String state;

    public UndoListMemento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}

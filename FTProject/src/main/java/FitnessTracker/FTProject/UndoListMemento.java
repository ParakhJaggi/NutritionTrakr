package FitnessTracker.FTProject;

import java.util.ArrayList;

public class UndoListMemento extends GoalPrototype {
	private String state;

    public UndoListMemento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
    public GoalPrototype clone() throws CloneNotSupportedException{
        return (UndoListMemento) super.clone();
    }

}

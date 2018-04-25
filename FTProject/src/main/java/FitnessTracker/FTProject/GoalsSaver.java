package FitnessTracker.FTProject;

import java.util.ArrayList;

public class GoalsSaver {
	private ArrayList<UndoListMemento> mementos = new ArrayList<>();

    public void addMemento(UndoListMemento m) {
        mementos.add(m);
    }

    public UndoListMemento getMemento() {
        return mementos.get(0);
    }

}

package FitnessTracker.FTProject;

import java.util.ArrayList;

public class GoalsSaver  {
	private ArrayList<UndoListMemento> mementos = new ArrayList<>();

    public void addMemento(UndoListMemento m) {
        mementos.add(0,m);
    }

    public UndoListMemento getMemento() {
        UndoListMemento m =  mementos.get(0);
        mementos.remove(0);
        try {
			m.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return m;
        
    }
    

}

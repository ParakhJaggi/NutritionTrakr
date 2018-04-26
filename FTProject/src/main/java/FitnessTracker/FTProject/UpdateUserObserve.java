package FitnessTracker.FTProject;

public class UpdateUserObserve extends java.util.Observable {
	private int id;
	
	private void setid(int id) {
		this.id = id;
	}
	public void doNotify() {
		setChanged();
		
		notifyObservers(id);
	}

}

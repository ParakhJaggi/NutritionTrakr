package FitnessTracker.FTProject;

public class RibbonContext {
	private RibbonState state;

	   public RibbonContext(){
	      state = null;
	   }

	   public void setState(RibbonState state){
	      this.state = state;		
	   }

	   public RibbonState getState(){
	      return state;
	   }
}

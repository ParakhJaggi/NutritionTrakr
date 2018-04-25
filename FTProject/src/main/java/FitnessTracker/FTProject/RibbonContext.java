package FitnessTracker.FTProject;
/**
 * 
 * @author Terlizzi
 * Context for the ribbon at the home center which controls the state
 */
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

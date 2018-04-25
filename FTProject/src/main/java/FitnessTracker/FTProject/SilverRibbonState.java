package FitnessTracker.FTProject;

public class SilverRibbonState implements RibbonState{

	@Override
	public String doAction(RibbonContext context) {
		context.setState(this);
		return "silverRibbon.png";
	}

}

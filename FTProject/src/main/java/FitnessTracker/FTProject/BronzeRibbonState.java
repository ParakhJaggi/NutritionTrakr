package FitnessTracker.FTProject;

public class BronzeRibbonState implements RibbonState{

	@Override
	public String doAction(RibbonContext context) {
		context.setState(this);
		return "bronzeRibbon.png";
	}

}

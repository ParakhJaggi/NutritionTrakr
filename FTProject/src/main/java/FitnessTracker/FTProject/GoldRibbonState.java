package FitnessTracker.FTProject;

public class GoldRibbonState implements RibbonState {

	@Override
	public String doAction(RibbonContext context) {
		context.setState(this);
		return "goldRibbon.png";
	}

}

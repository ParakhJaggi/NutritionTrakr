package FitnessTracker.FTProject;

public class GoldRibbonState implements RibbonState {
	/**
	 * @author Terlizzi
	 * @param Context for which the image is selected
	 * @return The image file for which the gold ribbon is held
	 */
	@Override
	public String doAction(RibbonContext context) {
		context.setState(this);
		return "goldRibbon.png";
	}

}

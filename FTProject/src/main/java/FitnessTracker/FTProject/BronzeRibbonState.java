package FitnessTracker.FTProject;

public class BronzeRibbonState implements RibbonState{
	/**
	 * @author Terlizzi
	 * @param Context for which the image is selected
	 * @return The image file for which the bronze ribbon is held
	 */
	@Override
	public String doAction(RibbonContext context) {
		context.setState(this);
		return "bronzeRibbon.png";
	}

}

package FitnessTracker.FTProject;

public class SilverRibbonState implements RibbonState{
/**
 * @author Terlizzi
 * @param Context for which the image is slected
 * @return The image file for which the silver ribbon is held
 */
	@Override
	public String doAction(RibbonContext context) {
		context.setState(this);
		return "silverRibbon.png";
	}

}

package FitnessTracker.FTProject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MaleUser u=new MaleUser();
        u.setHeight(69);
        u.setNeckMeasurment(15);
        u.setWaistMeasurment(34.5);
        System.out.println(u.calculateBFNavyMethod());
    }
}

package FitnessTracker.FTProject;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        MaleUser u=new MaleUser();
        u.setHeight(69);
        u.setNeckMeasurment(15);
        u.setWaistMeasurment(34.5);
        System.out.println(u.calculateBFNavyMethod());
        DatabaseGateway d = new DatabaseGateway();
        //d.createTable();
    }
}

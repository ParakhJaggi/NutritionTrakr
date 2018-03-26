package FitnessTracker.FTProject;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        MaleUser u=new MaleUser(1);
        u.setHeight(69);
        u.setNeckMeasurment(15);
        u.setWaistMeasurment(34.5);
        System.out.println(u.calculateBFNavyMethod());
        DatabaseGateway d = new DatabaseGateway();
        d.createTable();
        Food food= new Food();
        food.setCalories(105);
        food.setName("Banana");
        food.setCategory("Fruit");
        System.out.println(d.retrieveFood("Banana").getCalories());
        //d.updateCalories("Banana", 105);
        System.out.println(d.retrieveFood("Banana").getCalories());
        d.DisplayFoodFromCategory("Fruit");
        //d.addCaloriesToTrackers(1, Date.valueOf(LocalDate.now()), 0, 0);
        //d.createTrackerEntry(1, Date.valueOf(LocalDate.now()), 2000, 300);
    }
}

package FitnessTracker.FTProject;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application 
{
    

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		 Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));

         primaryStage.setTitle("My Application");
         primaryStage.setScene(new Scene(root));
         primaryStage.show();
		
	}
	public static void main( String[] args ) throws SQLException
    {
		DatabaseGateway d = new DatabaseGateway();
		d.LoadUser("a", "a");
		launch(args);
		
        /*MaleUser u=new MaleUser(1);
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
         * 
         */
    }
}

package FitnessTracker.FTProject;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author ParakhJaggi
 * This is the heart of the APP, everything starts here 
 *
 */
public class App extends Application  
{
    
	/**
	 * @author ParakhJaggi
	 * @param primaryStage-the appllication to open 
	 * The method will all start here after launch is called
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		 Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));

         primaryStage.setTitle("My Application");
         primaryStage.setScene(new Scene(root));
         primaryStage.show();
		
	}
	/**
	 * @author ParakhJaggi
	 * @param args
	 * @throws SQLException
	 * The main method of the APP
	 */
	public static void main( String[] args ) throws SQLException
    {
		launch(args);
    }
}

package FitnessTracker.FTProject;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 * @author ParakhJaggi
 * this class will controll the AboutUs screen and message
 *
 */
public class AboutUsController implements Command {
	
	/**
	 * @author ParakhJaggi
	 * @param usrname- the email of the user
	 * @param password- the users password
	 * @return void 
	 * This method will use the Command Pattern to Open the About us page
	 */
	@Override
	public void execute(String username, String password) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutUs.fxml"));

		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}          
		
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
		
		
	}

}

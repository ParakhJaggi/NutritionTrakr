package FitnessTracker.FTProject;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AboutUsController implements Command {

	@Override
	public void execute(String username, String password) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutUs.fxml"));     

		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();
		
		
	}

}

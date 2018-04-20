package FitnessTracker.FTProject;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DashboardController {
//test
	@FXML
	private void UpdateCalories(ActionEvent action) throws IOException {
		Parent root;
        
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("signup.fxml"));
		root = FXMLLoader.load(getClass().getResource("AddFood.fxml"));
		
        Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 720, 360));
        stage.show();
        ((Node)(action.getSource())).getScene().getWindow().hide();

		
	}
	
}

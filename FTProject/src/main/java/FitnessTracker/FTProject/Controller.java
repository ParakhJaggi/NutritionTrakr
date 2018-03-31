package FitnessTracker.FTProject;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
	@FXML
	public TextField usrname;
	@FXML
	public PasswordField pass;
	
	public void pressSignUp(ActionEvent action) throws IOException {
		Parent root;
        
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("signup.fxml"));
		root = FXMLLoader.load(getClass().getResource("signup.fxml"));
		
        Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 450, 450));
        stage.show();
        // Hide this current window (if this is what you want)
         ((Node)(action.getSource())).getScene().getWindow().hide();
	}
	public void pressSignIn(ActionEvent action) throws SQLException {
		
		String usr = usrname.getText();
		String password = pass.getText();
		DatabaseGateway d = new DatabaseGateway();
		User user = d.LoadUser(usr,password);
	}
}

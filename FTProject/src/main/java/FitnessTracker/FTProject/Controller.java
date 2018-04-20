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
	public static TextField usrname;
	@FXML
	public static PasswordField pass;
	
	public void pressSignUp(ActionEvent action) throws IOException {
		Parent root;
        
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("signup.fxml"));
		root = FXMLLoader.load(getClass().getResource("signup.fxml"));
		
        Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 339, 409));
        stage.show();
        // Hide this current window (if this is what you want)
         ((Node)(action.getSource())).getScene().getWindow().hide();
	}
	public void pressSignIn(ActionEvent action) throws SQLException, IOException {
		
		Parent root;
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("signup.fxml"));
		root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 1091, 713));
        stage.show();
        // Hide this current window (if this is what you want)
         ((Node)(action.getSource())).getScene().getWindow().hide();
		
	}
	public static User sendUser() throws SQLException {
		String usr = usrname.getText();
		String password = pass.getText();
		DatabaseGateway d = DatabaseGateway.getInstance();
		User user = d.LoadUser(usr,password);
		return user;
	}
}

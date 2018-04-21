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
        stage.setScene(new Scene(root, 339, 409));
        stage.show();
        // Hide this current window (if this is what you want)
         ((Node)(action.getSource())).getScene().getWindow().hide();
	}
	public void pressSignIn(ActionEvent action) throws SQLException, IOException {
		/*
		
		//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));     
		Parent root;
		root = FXMLLoader.load(getClass().getResource("home.fxml"));
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));     

		Parent root1 = (Parent)fxmlLoader.load();          
		DashboardController controller = fxmlLoader.<DashboardController>getController();
		controller.setUser(usrname.getText().toString(), pass.getText().toString());
        Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 1091, 713));
        stage.show();
        // Hide this current window (if this is what you want)
         ((Node)(action.getSource())).getScene().getWindow().hide();
        */
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		DashboardController controller = fxmlLoader.<DashboardController>getController();
		controller.setUser(usrname.getText().toString(), pass.getText().toString());
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        ((Node)(action.getSource())).getScene().getWindow().hide();

		
	}
	
}

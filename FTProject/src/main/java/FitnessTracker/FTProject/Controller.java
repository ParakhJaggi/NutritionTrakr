package FitnessTracker.FTProject;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
	@FXML
	public TextField usrname;
	@FXML
	public PasswordField pass;
	
	public void pressSignUp(ActionEvent action) {
		
		
	}
	public void pressSignIn(ActionEvent action) throws SQLException {
		
		String usr = usrname.getText();
		String password = pass.getText();
		DatabaseGateway d = new DatabaseGateway();
		User user = d.LoadUser(usr,password);
	}
}

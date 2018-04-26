package FitnessTracker.FTProject;


import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FemaleProfileController implements Command {
	@FXML
	public Button registerButton;
	@FXML
	public TextField height;
	@FXML
	public TextField weight;
	@FXML
	public TextField waistMeasurement;
	@FXML
	public TextField neckMeasurement;
	@FXML
	public TextField hipMeasurement;
	
	private String usrname,pass;
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * This method will load stats when the button is hit 
	 */
	@FXML 
	public void buttonhit() throws SQLException {
		final UserDatabaseGateway d = UserDatabaseGateway.getInstance();
		final User usr;
		usr = d.loadUser(usrname, pass);
		if(!checker.checkString(height.getText())&&!checker.checkString(weight.getText())
				||!checker.checkString(neckMeasurement.getText())||!checker.checkString(waistMeasurement.getText())
				||!checker.checkString(hipMeasurement.getText())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Special Character Error");
			alert.setContentText("We do not allow Special characters");
			alert.showAndWait();
			return;
		}
		UpdateUserObserve u = new UpdateUserObserve();
		u.addObserver((obj, arg) -> {
		    Integer Height=0;
		    Integer Weight=0;
		    Double Neck=0.0;
		    Double Waist=0.0;
		    Double Hip= 0.0;
		    try {
		    if (height.getText().isEmpty()) {
		        Height = 0;
		    } else {
		        Height = Integer.parseInt(height.getText());
		    }
		    if (weight.getText().isEmpty()) {
		        Weight = 0;
		    } else {
		        Weight = Integer.parseInt(weight.getText());
		    }
		    if (waistMeasurement.getText().isEmpty()) {
		        Waist = Double.valueOf(0);
		    } else {
		        Waist = Double.parseDouble(waistMeasurement.getText());
		    }
		    if (neckMeasurement.getText().isEmpty()) {
		        Neck = Double.valueOf(0);
		    } else {
		        Neck = Double.parseDouble(neckMeasurement.getText());
		    }
		    if (hipMeasurement.getText().isEmpty()) {
		        Neck = Double.valueOf(0);
		    } else {
		        Hip = Double.parseDouble(hipMeasurement.getText());
		    } 
		        d.updateValues(usr.getUserId(), Height, Weight, Neck, Waist, Hip);
		    } catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("Error");
				alert.setContentText("An Error has occurred");
				alert.showAndWait();
			}
		});
    }
	/**
	 * @author ParakhJaggi
	 * @param email
	 * @param pass
	 * This method will make sure the controller has the users credentials 
	 */
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	}
	/**
	 * @author ParakhJaggi
	 * @param username-the users email
	 * @param password-the users password
	 * This method will execute and open up the FemailProfileCHanger 
	 */
	@Override
	public void execute(String username,String password) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FemaleProfileChanger.fxml"));
		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		FemaleProfileController controller = fxmlLoader.<FemaleProfileController>getController();
		controller.setUser(username.toString(), password.toString());	
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   		
	}
}

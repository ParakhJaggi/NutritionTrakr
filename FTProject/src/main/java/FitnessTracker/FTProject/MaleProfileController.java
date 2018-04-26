package FitnessTracker.FTProject;


import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
 * 
 * @author ParakhJaggi
 * THis class will Control the MalePRofile Screen
 *
 */
public class MaleProfileController implements Command {
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
	String usrname,pass;
	/**
	 * @author ParakhJaggi
	 * @param action-the event of pressing the button 
	 * @throws SQLException
	 * This method will edit the profile when the button is hit 
	 */
	@FXML 
	public void buttonhit(ActionEvent action) throws SQLException {
		final DatabaseGateway d = DatabaseGateway.getInstance();
		final User usr;
		usr = d.loadUser(usrname, pass);
		
		UpdateUserObserve u = new UpdateUserObserve();
		
		if(!checker.checkString(height.getText())||!checker.checkString(weight.getText())
				||!checker.checkString(neckMeasurement.getText())||!checker.checkString(waistMeasurement.getText())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Special Character Error");
			alert.setContentText("We do not allow Special characters");
			alert.showAndWait();
			return;
		}
		u.addObserver((obj, arg) -> {
			System.out.println("here");
			Integer Height,Weight;
			Double Neck,Waist;
			try {
			if(height.getText().isEmpty()) {
				Height = 0;
			}
			else {
				Height = Integer.parseInt(height.getText());
			}
			if(weight.getText().isEmpty()) {
				Weight = 0;
			}
			else {
				Weight = Integer.parseInt(weight.getText());
			}
			if(waistMeasurement.getText().isEmpty()) {
				Waist = Double.valueOf(0);
			}
			else {
				Waist = Double.parseDouble(waistMeasurement.getText());
			}
			if(neckMeasurement.getText().isEmpty()) {
				Neck = Double.valueOf(0);
			}
			else {
				Neck = Double.parseDouble(neckMeasurement.getText());
			}
				d.updateValues(usr.getUserId(), Height, Weight, Neck, Waist, Double.valueOf(0));
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("Error");
				alert.setContentText("An Error has occurred");
				alert.showAndWait();
			}
			
		});
		u.doNotify();
		

        ((Node)(action.getSource())).getScene().getWindow().hide();

	}
	/**
	 * @author ParakhJaggi
	 * @param email
	 * @param pass
	 * This method will keep the users credentials 
	 */
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	}
	/**
	 * @author ParakhJaggi
	 * @param username
	 * @param password
	 * This method will load the fxml file  
	 */
	@Override
	public void execute(String username,String password) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MaleProfileChanger.fxml"));
		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		MaleProfileController controller = fxmlLoader.<MaleProfileController>getController();
		controller.setUser(username.toString(), password.toString());	
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   		
	}
}

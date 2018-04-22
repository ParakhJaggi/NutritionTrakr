package FitnessTracker.FTProject;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MaleProfileController {
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
	
	@FXML 
	public void buttonhit() throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		int Height,Weight;
		double Neck,Waist;
		if(height.getText().isEmpty()) {
			Height = (Integer) null;
		}
		else {
			Height = Integer.parseInt(height.getText());
		}
		if(weight.getText().isEmpty()) {
			Weight = (Integer) null;
		}
		else {
			Weight = Integer.parseInt(weight.getText());
		}
		if(waistMeasurement.getText().isEmpty()) {
			Waist = (Double) null;
		}
		else {
			Waist = Double.parseDouble(waistMeasurement.getText());
		}
		if(neckMeasurement.getText().isEmpty()) {
			Neck = (Double) null;
		}
		else {
			Neck = Double.parseDouble(neckMeasurement.getText());
		}
		d.updateValues(usr.getUserId(), Height, Weight, Neck, Waist, null);
		
		
	}
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	}
}

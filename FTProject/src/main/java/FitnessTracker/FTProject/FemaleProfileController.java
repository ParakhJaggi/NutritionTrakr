package FitnessTracker.FTProject;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FemaleProfileController {
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
	
	String usrname,pass;
	
	@FXML 
	public void buttonhit() throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		int Height,Weight;
		double Neck,Waist, Hip;
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
		if(hipMeasurement.getText().isEmpty()) {
			Hip = (Double) null;
		}
		else {
			Hip = Double.parseDouble(hipMeasurement.getText());
		}
		d.updateValues(usr.getUserId(), Height, Weight, Neck, Waist, Hip);
		
		
	}
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	}
}

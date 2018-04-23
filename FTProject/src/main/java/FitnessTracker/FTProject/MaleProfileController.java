package FitnessTracker.FTProject;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
	public void buttonhit(ActionEvent action) throws SQLException {
		final DatabaseGateway d = DatabaseGateway.getInstance();
		final User usr;
		usr = d.LoadUser(usrname, pass);
		
		UpdateUser u = new UpdateUser();
		u.addObserver(new Observer() {
            public void update(Observable obj, Object arg) {
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
        		try {
					d.updateValues(usr.getUserId(), Height, Weight, Neck, Waist, null);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
            }

			
        });
		
		/*int Height,Weight;
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
		*/
        ((Node)(action.getSource())).getScene().getWindow().hide();

	}
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	}
}

package FitnessTracker.FTProject;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	@Override
	public void execute(String username,String password) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FemaleProfileChanger.fxml"));  
		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

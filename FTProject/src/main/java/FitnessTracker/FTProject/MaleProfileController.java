package FitnessTracker.FTProject;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
		usr = d.LoadUser(usrname, pass);
		
		UpdateUser u = new UpdateUser();
		
		u.addObserver(new Observer() {
            public void update(Observable obj, Object arg) {
            	System.out.println("here");
            	Integer Height,Weight;
        		Double Neck,Waist;
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
        		try {
					d.updateValues(usr.getUserId(), Height, Weight, Neck, Waist, Double.valueOf(0));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
            }

			
        });
		u.doNotify();
		
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
	 * @param email
	 * @param pass
	 * This method will load the fxml file  
	 */
	@Override
	public void execute(String username,String password) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MaleProfileChanger.fxml"));  
		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

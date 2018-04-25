package FitnessTracker.FTProject;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * 
 * @author ParakhJaggi
 * THis class will manage the Score screen 
 *
 */
public class MyScoreController implements Command{

	
	
	public String usrname;
	
	public String pass;
	@FXML
	public Label Score;
	@FXML 
	public Button button; 
	
	@FXML
	public Label BMI;
	
	@FXML 
	public Label BodyFat;
	
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * This meathod will show the score when the button is hit 
	 */
	@FXML
	public void buttonhit() throws SQLException {
		User usr = null;
		DatabaseGateway d = DatabaseGateway.getInstance();
		usr = d.LoadUser(usrname, pass);
		
		Score.setText(String.valueOf(usr.getScore()));
		
		BMI.setText(String.valueOf(usr.calculateBMI()));
		BodyFat.setText(String.valueOf(usr.calculateBFNavyMethod()));
		
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
	    System.out.println(usrname);
		System.out.println(usrname);
	}

	/**
	 * @author ParakhJaggi
	 * @param email
	 * @param pass
	 * This method will load the fxml file 
	 */
	@Override
	public void execute(String username,String password) {
		System.out.println(usrname);
		System.out.println(pass);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("getScore.fxml"));     

		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		MyScoreController controller = fxmlLoader.<MyScoreController>getController();
		controller.setUser(username.toString(), password.toString());	
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();
		
	}
}

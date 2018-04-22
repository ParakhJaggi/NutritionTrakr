package FitnessTracker.FTProject;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MyScoreController{

	
	
	public String usrname;
	
	public String pass;
	@FXML
	public Label Score;
	@FXML 
	public Button button; 
	
	
	@FXML
	public void buttonhit() throws SQLException {
		User usr = null;
		DatabaseGateway d = DatabaseGateway.getInstance();
		usr = d.LoadUser(usrname, pass);
		
		Score.setText(String.valueOf(usr.getScore()));
		
		
	}
	
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	    System.out.println(usrname);
		System.out.println(usrname);
	}
}

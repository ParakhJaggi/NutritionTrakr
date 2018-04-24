package FitnessTracker.FTProject;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DashboardController  {
//test

	public String usrname;
	
	public String pass;
	
	@FXML
	private void UpdateCalories(ActionEvent action) throws IOException {
		System.out.println(usrname);
		System.out.println(usrname);

		AddCalorieController controller = new AddCalorieController();
		controller.execute(usrname,pass);
		
	}
	@FXML
	public void getScore() throws SQLException, IOException {
		MyScoreController c = new MyScoreController();
		c.execute(usrname, pass);
	}
	@FXML
	private void UpdateExercise(ActionEvent action) throws IOException {
		AddExerciseController e = new AddExerciseController();
		e.execute(usrname, pass);
		
	}
	@FXML
	private void initialize() {
		
	}
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	    
	    
	}
	@FXML
	public void getGraph() throws SQLException, IOException {
		BarGraphController b = new BarGraphController();
		b.execute(usrname, pass);

	}
	@FXML
	public void getLeaderboard() throws SQLException, IOException {
		LeaderboardController l = new LeaderboardController();
		l.execute(usrname, pass);
		
		

	}
	@FXML
	public void getGoals() throws SQLException, IOException {
		GoalsController g = new GoalsController();
		g.execute(usrname, pass);
	}
	@FXML
	public void getAboutUs() throws IOException{
		
		AboutUsController a = new AboutUsController();
		a.execute(usrname, pass);
	}
	@FXML
	public void getProfile() throws SQLException, IOException {
		
		
		if(DatabaseGateway.getInstance().LoadUser(usrname, pass).getGender().equals("Male")) {
			MaleProfileController m = new MaleProfileController();
			m.execute(usrname, pass);
		}
		else { 
			FemaleProfileController f = new FemaleProfileController();
			f.execute(usrname, pass);
		}
		

		        
		
		
        //((Node)(action.getSource())).getScene().getWindow().hide();
	}
		
		
	
}

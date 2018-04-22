package FitnessTracker.FTProject;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class GoalsController {
	@FXML
	public String usrname;
	@FXML
	public String pass;
	
	@FXML
	public ChoiceBox<String> Goals;
	
	@FXML
	public ObservableList<String> GoalsList;
	@FXML
	public TextField newGoal;
	
	@FXML
	public Button AddGoal;
	
	@FXML
	public Button RemoveGoal;
	
	@FXML
	public void pressChoiceBox(MouseEvent action) throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		ArrayList<String> temp = d.getGoals(usr.getUserId());
		GoalsList = FXCollections.observableArrayList(temp);
		Goals.setItems(GoalsList);
		
		
	}
	@FXML
	public void removeGoal() throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		d.deleteGoal(usr.getUserId(), Goals.getValue());
	
	}
	@FXML
	public void addGoal() throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		d.insertGoal(usr.getUserId(), newGoal.getText());
	
	}
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	    System.out.println(usrname);
		System.out.println(usrname);
	}
	
}

package FitnessTracker.FTProject;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
	public Label goal1;
	@FXML
	public Label goal2;
	@FXML
	public Label goal3;
	@FXML
	public Label goal4;
	@FXML
	public Label goal5;

	


	
	
	@FXML
	public Button AddGoal;
	
	@FXML
	public Button RemoveGoal;
	
	
	
	@FXML
	private void start() throws SQLException {
		
	
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		ArrayList<String> temp = d.getGoals(usr.getUserId());
		GoalsList = FXCollections.observableArrayList(temp);
		Goals.setItems(GoalsList);
		
		goal1.setText(temp.get(0));
		goal2.setText(temp.get(1));
		goal3.setText(temp.get(2));
		goal4.setText(temp.get(3));
		goal5.setText(temp.get(4));

		
		
		
		
	}
	@FXML
	public void removeGoal() throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		d.deleteGoal(usr.getUserId(), Goals.getValue());
		
		usr = d.LoadUser(usrname, pass);
		ArrayList<String> temp = d.getGoals(usr.getUserId());
		GoalsList = FXCollections.observableArrayList(temp);
		Goals.setItems(GoalsList);
		
		goal1.setText(temp.get(0));
		goal2.setText(temp.get(1));
		goal3.setText(temp.get(2));
		goal4.setText(temp.get(3));
		goal5.setText(temp.get(4));
	
	}
	@FXML
	public void addGoal() throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		d.insertGoal(usr.getUserId(), newGoal.getText());
		
		usr = d.LoadUser(usrname, pass);
		ArrayList<String> temp = d.getGoals(usr.getUserId());
		GoalsList = FXCollections.observableArrayList(temp);
		Goals.setItems(GoalsList);
		
		goal1.setText(temp.get(0));
		goal2.setText(temp.get(1));
		goal3.setText(temp.get(2));
		goal4.setText(temp.get(3));
		goal5.setText(temp.get(4));
	
	}
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	    System.out.println(usrname);
		System.out.println(usrname);
	}
	
}

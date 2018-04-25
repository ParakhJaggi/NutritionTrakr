package FitnessTracker.FTProject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GoalsController implements Command{
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

	private GoalsSaver saver = new GoalsSaver();
	private PreviousGoals previous = new PreviousGoals();
	


	
	
	@FXML
	public Button AddGoal;
	
	@FXML
	public Button RemoveGoal;
	
	
	
	@FXML
	private void start() throws SQLException {
		
	
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		ListFactory factory = new ListFactory();
		
		ArrayList<String> temp = factory.createArray();
		
		temp = d.getGoals(usr.getUserId());
		GoalsList = FXCollections.observableArrayList(temp);
		Goals.setItems(GoalsList);
		
		if(temp.size()==0) {
			goal1.setText("");
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		
		}
		else if(temp.size()==1) {
			goal1.setText(temp.get(0));
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		
		}
		else if(temp.size()==2) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		}
		else if(temp.size()==3) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText("");
			goal5.setText("");
		}
		else if(temp.size()==4) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText(temp.get(3));
			goal5.setText("");
		}
		else if(temp.size()==5) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText(temp.get(3));
			goal5.setText(temp.get(4));
		}
		else {
			goal1.setText("");
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
			
		}
		
	

		
		
		
		
	}
	@FXML
	private void update() throws SQLException {
		
	
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		ListFactory factory = new ListFactory();
		
		ArrayList<String> temp = factory.createArray();
		
		temp = d.getGoals(usr.getUserId());
		
		
		if(temp.size()==0) {
			goal1.setText("");
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		
		}
		else if(temp.size()==1) {
			goal1.setText(temp.get(0));
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		
		}
		else if(temp.size()==2) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		}
		else if(temp.size()==3) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText("");
			goal5.setText("");
		}
		else if(temp.size()==4) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText(temp.get(3));
			goal5.setText("");
		}
		else if(temp.size()==5) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText(temp.get(3));
			goal5.setText(temp.get(4));
		}
		else {
			goal1.setText("");
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
			
		}
		
	

		
		
		
		
	}
	@FXML
	public void removeGoal() throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
	
		previous.setState(Goals.getValue());
		saver.addMemento(previous.save());
		
		
		d.deleteGoal(usr.getUserId(), Goals.getValue());
		ListFactory factory = new ListFactory();

		usr = d.LoadUser(usrname, pass);
		
		ArrayList<String> temp = factory.createArray();
		
		temp = d.getGoals(usr.getUserId());
		GoalsList = FXCollections.observableArrayList(temp);
		Goals.setItems(GoalsList);
		if(temp.size()==0) {
			goal1.setText("");
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		
		}
		else if(temp.size()==1) {
			goal1.setText(temp.get(0));
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		
		}
		else if(temp.size()==2) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		}
		else if(temp.size()==3) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText("");
			goal5.setText("");
		}
		else if(temp.size()==4) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText(temp.get(3));
			goal5.setText("");
		}
		else if(temp.size()==5) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText(temp.get(3));
			goal5.setText(temp.get(4));
		}
		else {
			goal1.setText("");
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
			
		}
		
	
	}
	
	@FXML
	public void restoreGoal() {
		DatabaseGateway d = DatabaseGateway.getInstance();
		previous.restore(saver.getMemento());
		try {
			addGoal(previous.save().getState());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void addGoal() throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		d.insertGoal(usr.getUserId(), newGoal.getText());
		
		usr = d.LoadUser(usrname, pass);	
		ListFactory factory = new ListFactory();

		ArrayList<String> temp =factory.createArray();
		d.getGoals(usr.getUserId());
		GoalsList = FXCollections.observableArrayList(temp);
		Goals.setItems(GoalsList);
		
		if(temp.size()==0) {
			goal1.setText("");
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		
		}
		else if(temp.size()==1) {
			goal1.setText(temp.get(0));
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		
		}
		else if(temp.size()==2) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		}
		else if(temp.size()==3) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText("");
			goal5.setText("");
		}
		else if(temp.size()==4) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText(temp.get(3));
			goal5.setText("");
		}
		else if(temp.size()==5) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText(temp.get(3));
			goal5.setText(temp.get(4));
		}
		else {
			goal1.setText("");
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
			
		}
		
	
	}
	public void addGoal(String previous) throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.LoadUser(usrname, pass);
		d.insertGoal(usr.getUserId(), previous);
		
		usr = d.LoadUser(usrname, pass);	
		ListFactory factory = new ListFactory();

		ArrayList<String> temp =factory.createArray();
		d.getGoals(usr.getUserId());
		GoalsList = FXCollections.observableArrayList(temp);
		Goals.setItems(GoalsList);
		
		if(temp.size()==0) {
			goal1.setText("");
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		
		}
		else if(temp.size()==1) {
			goal1.setText(temp.get(0));
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		
		}
		else if(temp.size()==2) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
		}
		else if(temp.size()==3) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText("");
			goal5.setText("");
		}
		else if(temp.size()==4) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText(temp.get(3));
			goal5.setText("");
		}
		else if(temp.size()==5) {
			goal1.setText(temp.get(0));
			goal2.setText(temp.get(1));
			goal3.setText(temp.get(2));
			goal4.setText(temp.get(3));
			goal5.setText(temp.get(4));
		}
		else {
			goal1.setText("");
			goal2.setText("");
			goal3.setText("");
			goal4.setText("");
			goal5.setText("");
			
		}
	
	}
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	    System.out.println(usrname);
		System.out.println(usrname);
	}
	@Override
	public void execute(String username,String password) {
		System.out.println(usrname);
		System.out.println(usrname);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("getGoals.fxml"));     

		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		GoalsController controller = fxmlLoader.<GoalsController>getController();
		controller.setUser(username.toString(), password.toString());	
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();
				
	}
	
}

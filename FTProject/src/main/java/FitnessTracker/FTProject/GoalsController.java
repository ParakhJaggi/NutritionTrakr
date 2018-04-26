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
import javafx.stage.Stage;
/**
 * 
 * @author ParakhJaggi
 * This class will control the goal screen
 *
 */
public class GoalsController implements Command{
	@FXML
	public String usrname;
	@FXML
	public String pass;
	
	@FXML
	public ChoiceBox<String> goals;
	
	@FXML
	public ObservableList<String> goalsList;
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
	public Button addGoal;
	
	@FXML
	public Button removeGoal;
	
	
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * This method will start the Goals list 
	 */
	@FXML
	private void start() throws SQLException {
		
		DatabaseGateway d = new DatabaseGateway();
		d = DatabaseGateway.getInstance();
		ListFactory factory = new ListFactory();
		User usr = d.loadUser(usrname, pass);
		ArrayList<String> temp =factory.createArray();
		temp =d.getGoals(usr.getUserId());
		goalsList = FXCollections.observableArrayList(temp);
		goals.setItems(goalsList);
		
		//To avoid getting Null Pointer Exceptions 
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
	
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * This method will update the choicebox when a new goal is added
	 */
	@FXML
	private void update() throws SQLException {
		
	
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.loadUser(usrname, pass);
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
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * This method will remove a goal if the user doesnt want it
	 */
	@FXML
	public void removeGoal() throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.loadUser(usrname, pass);
	
		previous.setState(goals.getValue());
		saver.addMemento(previous.save());
		
		
		d.deleteGoal(usr.getUserId(), goals.getValue());
		ListFactory factory = new ListFactory();

		usr = d.loadUser(usrname, pass);
		
		ArrayList<String> temp = factory.createArray();
		
		temp = d.getGoals(usr.getUserId());
		goalsList = FXCollections.observableArrayList(temp);
		goals.setItems(goalsList);
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
	/**
	 * @author ParakhJaggi
	 * This method will use Memento to undo a goal 
	 */
	@FXML
	public void restoreGoal() {
		previous.restore(saver.getMemento());
		try {
			addGoal(previous.save().getState());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * This method will add a goal if a user wants to 
	 */
	@FXML
	public void addGoal() throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.loadUser(usrname, pass);
		SqlInjectionChecker checker = new SqlInjectionChecker();
		if(!checker.checkString(String.valueOf(usr.getUserId()))||!checker.checkString(newGoal.getText())) {
			
			System.out.println("You tyrna SQL Inject?");
			return;
		}
		d.insertGoal(usr.getUserId(), newGoal.getText());
		
		ListFactory factory = new ListFactory();

		ArrayList<String> temp =factory.createArray();
		d.getGoals(usr.getUserId());
		goalsList = FXCollections.observableArrayList(temp);
		goals.setItems(goalsList);
		
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
	/**
	 * @author ParakhJaggi
	 * @param previous
	 * @throws SQLException
	 * This method will add a goal if a user has a specific string to add 
	 */
	public void addGoal(String previous) throws SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		User usr;
		usr = d.loadUser(usrname, pass);
		SqlInjectionChecker checker = new SqlInjectionChecker();

		if(!checker.checkString(String.valueOf(usr.getUserId()))||!checker.checkString(previous.toString())) {
			
			System.out.println("You tyrna SQL Inject?");
			return;
		}
		d.insertGoal(usr.getUserId(), previous);
		
		ListFactory factory = new ListFactory();

		ArrayList<String> temp =factory.createArray();
		d.getGoals(usr.getUserId());
		goalsList = FXCollections.observableArrayList(temp);
		goals.setItems(goalsList);
		
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
	/**
	 * @author ParakhJaggi
	 * @param email
	 * @param pass
	 * This method will keep track of the Users email and password 
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
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("getGoals.fxml"));

		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}          
		GoalsController controller = fxmlLoader.<GoalsController>getController();
		controller.setUser(username.toString(), password.toString());	
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
				
	}
	
}

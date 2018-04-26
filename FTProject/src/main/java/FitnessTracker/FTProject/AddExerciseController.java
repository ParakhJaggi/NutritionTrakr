package FitnessTracker.FTProject;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * 
 * @author ParakhJaggi
 * This class will control the adding exercise screen
 *
 */
public class AddExerciseController implements Command {
	
	public String usrname;
	
	public String pass;
	
	@FXML
	public ChoiceBox<String>ExerciseChoice;
	@FXML
	public ChoiceBox<String> Catagory;
	@FXML
	public ObservableList<String> CategoryList= FXCollections.observableArrayList("Cardio","Weight Lifting");
	
	@FXML
	public TextField newExercise;
	
	@FXML
	public TextField Calorie;
	@FXML
	public Button AddExcercise;
	
	
	@FXML
	ObservableList<String> ExerciseList;
	
	
	private User usr;
	/**
	 * @author ParakhJaggi
	 * @param action-the users interaction with the choicebox
	 * @throws SQLException
	 */
	@FXML
	public void pressChoiceBox(MouseEvent action) throws SQLException {
	
		DatabaseGateway d = DatabaseGateway.getInstance();

		String catagory = Catagory.getValue();
		
		
		ListFactory factory = new ListFactory();
		
		
		ArrayList<String> temp = factory.createArray();
		temp = d.displayExerciseFromCategory(catagory);
		ExerciseList = FXCollections.observableArrayList(temp);
		ExerciseChoice.setItems(ExerciseList);
		System.out.println(temp.toString());
		if(temp.size()>0) {
			ExerciseChoice.setValue(temp.get(0).toString());
		}
		else {
			CategoryList = FXCollections.observableArrayList("Press Here for Exercise");
			ExerciseChoice.setItems(ExerciseList);
			ExerciseChoice.setValue("Press Here for Exercise");

			
		}
		
	}
	/**
	 * @author ParakhJaggi
	 * @throws NumberFormatException
	 * @throws SQLException
	 * This method adds an excercise to the database
	 */
	@FXML
	public void pressAddExcersice() throws NumberFormatException, SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();
		SqlInjectionChecker checker = new SqlInjectionChecker();
		if(!checker.checkString(newExercise.getText())||!checker.checkString(Catagory.getValue())
				||!checker.checkString(Calorie.getText())){
			
			System.out.println("You tyrna SQL Inject?");
			return;
		}
		d.addExerciseToTable(newExercise.getText(), Catagory.getValue(), Integer.parseInt(Calorie.getText()));
		String catagory = Catagory.getValue();
		
		ListFactory factory = new ListFactory();
		
		
		ArrayList<String> temp = factory.createArray();
		temp = d.displayExerciseFromCategory(catagory);
		ExerciseList = FXCollections.observableArrayList(temp);
		ExerciseChoice.setItems(ExerciseList);
		
	}
	/**
	 * @author ParakhJaggi
	 * @param action
	 * @throws SQLException
	 * This method will subtract calories
	 */
	@FXML
	public void subtractCalorie(ActionEvent action) throws SQLException {
		DatabaseGateway d;
		d = DatabaseGateway.getInstance();
		usr = d.loadUser(usrname, pass);
		LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
		Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
		
		d.addCaloriesToTrackers(usr.getUserId(), sqlDate,0 , d.retrieveExercise(ExerciseChoice.getValue()).getCalories());
		
        ((Node)(action.getSource())).getScene().getWindow().hide();
        System.out.println(usrname+pass);
	}
	/**
	 * @author ParakhJaggi
	 * This method will initialize the choicebox 
	 */
	@FXML
	private void initialize() {
		Catagory.setValue("Cardio");
		Catagory.setItems(CategoryList);
		
		
	}
	/**
	 * @author ParakhJaggi
	 * @param email-the users email
	 * @param pass-the users pass
	 * This method will make sure the controller still has the users login credentials
	 */
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	}
	/**
	 * @author ParakhJaggi
	 * @param username-the users email
	 * @param password-the users password
	 */
	@Override
	public void execute(String username,String password) {
		

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddExercise.fxml"));

		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		       
		AddExerciseController controller = fxmlLoader.<AddExerciseController>getController();
		controller.setUser(username.toString(), password.toString());
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
	}

	


}

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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
	@FXML
	public void pressChoiceBox(MouseEvent action) throws SQLException {
	
		DatabaseGateway d = DatabaseGateway.getInstance();

		String catagory = Catagory.getValue();
		System.out.println(usrname);
		System.out.println(usrname);
		
		ListFactory factory = new ListFactory();
		
		
		ArrayList<String> temp = factory.createArray();
		temp = d.DisplayExerciseFromCategory(catagory);
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
	@FXML
	public void pressAddExcersice() throws NumberFormatException, SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();

		d.addExerciseToTable(newExercise.getText(), Catagory.getValue(), Integer.parseInt(Calorie.getText()));
		String catagory = Catagory.getValue();
		
		ListFactory factory = new ListFactory();
		
		
		ArrayList<String> temp = factory.createArray();
		temp = d.DisplayExerciseFromCategory(catagory);
		ExerciseList = FXCollections.observableArrayList(temp);
		ExerciseChoice.setItems(ExerciseList);
		
	}
	@FXML
	public void SubtractCalorie(ActionEvent action) throws SQLException {
		DatabaseGateway d;
		d = DatabaseGateway.getInstance();
		usr = d.LoadUser(usrname, pass);
		System.out.println(usr.user_id);
		LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
		Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
		Exercise f = new Exercise();
		f = d.retrieveExercise(ExerciseChoice.getValue());
	
		d.addCaloriesToTrackers(usr.getUserId(), sqlDate,0 , f.getCalories());
		
        ((Node)(action.getSource())).getScene().getWindow().hide();
        System.out.println(usrname+pass);
	}
	
	@FXML
	private void initialize() {
		Catagory.setValue("Cardio");
		Catagory.setItems(CategoryList);
		
		
	}
	
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	}
	@Override
	public void execute(String username,String password) {
		System.out.println(usrname);
		System.out.println(usrname);

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddExercise.fxml"));     

		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		AddExerciseController controller = fxmlLoader.<AddExerciseController>getController();
		controller.setUser(username.toString(), password.toString());
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();		
	}

	


}

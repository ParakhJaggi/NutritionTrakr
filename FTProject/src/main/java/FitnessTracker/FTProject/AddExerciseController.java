package FitnessTracker.FTProject;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AddExerciseController {
	@FXML
	public String usrname;
	@FXML
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
		
		ArrayList<String> temp = d.DisplayFoodFromCategory(catagory);
		ExerciseList = FXCollections.observableArrayList(d.DisplayFoodFromCategory(catagory));
		ExerciseChoice.setItems(CategoryList);
		
		if(temp.size()>0) {
			ExerciseChoice.setValue(temp.get(0).toString());
		}
		else {
			CategoryList = FXCollections.observableArrayList("Press Here for Food");
			ExerciseChoice.setItems(CategoryList);
			ExerciseChoice.setValue("Press Here for Food");

			
		}
		
	}
	@FXML
	public void pressAddExcersice() throws NumberFormatException, SQLException {
		DatabaseGateway d = DatabaseGateway.getInstance();

		d.addFoodToTable(newExercise.getText(), Catagory.getValue(), Integer.parseInt(Calorie.getText()));
		String catagory = Catagory.getValue();
		ArrayList<String> temp = d.DisplayFoodFromCategory(catagory);
		ExerciseList = FXCollections.observableArrayList(d.DisplayFoodFromCategory(catagory));
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
		f = d.retrieveFood(ExerciseChoice.getValue());
	
		d.addCaloriesToTrackers(usr.getUserId(), sqlDate, f.getCalories(), 0);
		
        ((Node)(action.getSource())).getScene().getWindow().hide();

	}
	
	@FXML
	private void initialize() {
		Catagory.setValue("Protein");
		Catagory.setItems(CategoryList);
		
	}
	
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	}

	


}

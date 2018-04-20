package FitnessTracker.FTProject;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AddCalorieController {
	@FXML
	public ChoiceBox<String> FoodChoice;
	@FXML
	public TextField newFood;
	
	@FXML
	public TextField Calorie;
	@FXML
	public Button AddFood;
	
	DatabaseGateway d = DatabaseGateway.getInstance();
	
	@FXML
	ObservableList<String> FoodList;
	@FXML
	public TextField Catagory;
	
	private User usr;
	@FXML
	public void pressChoiceBox(KeyEvent action) throws SQLException {
		
		String catagory = Catagory.getText();
		ArrayList<String> temp = d.DisplayFoodFromCategory(catagory);
		FoodList = FXCollections.observableArrayList(d.DisplayFoodFromCategory(catagory));
		FoodChoice.setItems(FoodList);
	}
	
	public void pressAddFood() throws NumberFormatException, SQLException {
		d.addFoodToTable(newFood.getText(), Catagory.getText(), Integer.parseInt(Calorie.getText()));
		String catagory = Catagory.getText();
		ArrayList<String> temp = d.DisplayFoodFromCategory(catagory);
		FoodList = FXCollections.observableArrayList(d.DisplayFoodFromCategory(catagory));
		FoodChoice.setItems(FoodList);
		
	}
	
	@FXML
	private void initialize() {
		FoodChoice.setValue("Food");
		
	}

	


}

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
		
		if(temp.size()>0) {
			FoodChoice.setValue(temp.get(0).toString());
		}
		else {
			FoodList = FXCollections.observableArrayList("Press Here for Food");
			FoodChoice.setItems(FoodList);
			FoodChoice.setValue("Press Here for Food");

			
		}
		
	}
	@FXML
	public void pressAddFood() throws NumberFormatException, SQLException {
		d.addFoodToTable(newFood.getText(), Catagory.getText(), Integer.parseInt(Calorie.getText()));
		String catagory = Catagory.getText();
		ArrayList<String> temp = d.DisplayFoodFromCategory(catagory);
		FoodList = FXCollections.observableArrayList(d.DisplayFoodFromCategory(catagory));
		FoodChoice.setItems(FoodList);
		
	}
	@FXML
	public void addCalorie() throws SQLException {
		
		usr  = DashboardController.getUser();
		LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
		Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
		d.addCaloriesToTrackers(usr.getUserId(), sqlDate, Integer.parseInt(Calorie.getText()), 0);
		
		
	}
	
	@FXML
	private void initialize() {
		FoodChoice.setValue("Food");
		
	}

	


}

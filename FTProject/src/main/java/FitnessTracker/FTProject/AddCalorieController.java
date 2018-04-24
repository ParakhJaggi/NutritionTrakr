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

public class AddCalorieController {
	private Boolean bool = true;
	@FXML
	public String usrname;
	@FXML
	public String pass;
	
	@FXML
	public ChoiceBox<String> FoodChoice;
	@FXML
	public ChoiceBox<String> Catagory;
	@FXML
	public ObservableList<String> CategoryList= FXCollections.observableArrayList("Fruits","Vegetables","Meats/Fish","Dairy","Junk Food","Water","Drinks");
	
	@FXML
	public TextField newFood;
	
	@FXML
	public TextField Calorie;
	@FXML
	public Button AddFood;
	
	DatabaseGateway d = DatabaseGateway.getInstance();
	
	@FXML
	ObservableList<String> FoodList;
	
	
	private User usr;
	@FXML
	public void stop(MouseEvent action) {
		bool = true;
		System.out.println(bool);
	}
	@FXML
	public void pressChoiceBox(MouseEvent action) throws SQLException {
		String catagory = Catagory.getValue();
		
		ListFactory factory = new ListFactory();
		
		
		ArrayList<String> temp = factory.createArray();
		temp =d.DisplayFoodFromCategory(catagory);
		FoodList = FXCollections.observableArrayList(temp);
		FoodChoice.setItems(FoodList);
		
		if(temp.size()>0) {
			FoodChoice.setValue(temp.get(0).toString());
		}
		else if(temp.size()>1) {
			FoodList = FXCollections.observableArrayList("Press Here for Food");
			if(bool) {
			FoodChoice.setItems(FoodList);
			bool = false;
			System.out.println(bool);
			}
			
			FoodChoice.setValue("Press Here for Food");

			
		}
		
	}
	@FXML
	public void moving(MouseEvent action) throws SQLException {
		String catagory = Catagory.getValue();
		
		ListFactory factory = new ListFactory();
		
		ArrayList<String> temp = factory.createArray();
	    temp = d.DisplayFoodFromCategory(catagory);
		FoodList = FXCollections.observableArrayList(temp);
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
		d.addFoodToTable(newFood.getText(), Catagory.getValue(), Integer.parseInt(Calorie.getText()));
		String catagory = Catagory.getValue();
		
		ListFactory factory = new ListFactory();
		
		
		ArrayList<String> temp = factory.createArray();
		temp = d.DisplayFoodFromCategory(catagory);
		FoodList = FXCollections.observableArrayList(temp);
		FoodChoice.setItems(FoodList);

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
	public void addCalorie(ActionEvent action) throws SQLException {
		DatabaseGateway d;
		d = DatabaseGateway.getInstance();
		usr = d.LoadUser(usrname, pass);
		LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
		Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
		Food f = new Food();
		f = d.retrieveFood(FoodChoice.getValue());
	
		d.createTrackerEntry(usr.getUserId(), sqlDate, f.getCalories(), 0);
		
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

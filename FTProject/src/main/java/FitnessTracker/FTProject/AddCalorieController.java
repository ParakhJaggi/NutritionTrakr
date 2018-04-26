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
 * This class will control the add calorie page 
 */
public class AddCalorieController implements Command {
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
	/**
	 * @author ParakhJaggi
	 * @param action
	 * @return void
	 * This method will stop the choice box from reloading 
	 */
	@FXML
	public void stop(MouseEvent action) {
		bool = true;
		System.out.println(bool);
	}
	/**
	 * @author ParakhJaggi
	 * @param action
	 * @throws SQLException
	 * This method will show the lists of available foods and categories in the choice boxes
	 */
	@FXML
	public void pressChoiceBox(MouseEvent action) throws SQLException {
		String catagory = Catagory.getValue();
		
		ListFactory factory = new ListFactory();
		
		
		ArrayList<String> temp = factory.createArray();
		temp =d.displayFoodFromCategory(catagory);
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
	/**
	 * @author ParakhJaggi
	 * @param action-the users action with the button
	 * @throws SQLException
	 * This method will update the choicebox when the mouse moves 
	 */
	@FXML
	public void moving(MouseEvent action) throws SQLException {
		String catagory = Catagory.getValue();
		
		ListFactory factory = new ListFactory();
		
		ArrayList<String> temp = factory.createArray();
	    temp = d.displayFoodFromCategory(catagory);
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
	/**
	 * @author ParakhJaggi
	 * @throws NumberFormatException
	 * @throws SQLException
	 * This Method will add a user typed food to the dataBase
	 */
	@FXML
	public void pressAddFood() throws NumberFormatException, SQLException {
		SqlInjectionChecker checker = new SqlInjectionChecker();
		if(!checker.checkString(newFood.getText())||!checker.checkString(Catagory.getValue())
				||!checker.checkString(Calorie.getText())){
			
			System.out.println("You tyrna SQL Inject?");
			return;
		}
		d.addFoodToTable(newFood.getText(), Catagory.getValue(), Integer.parseInt(Calorie.getText()));
		String catagory = Catagory.getValue();
		
		ListFactory factory = new ListFactory();
		
		
		ArrayList<String> temp = factory.createArray();
		temp = d.displayFoodFromCategory(catagory);
		FoodList = FXCollections.observableArrayList(temp);
		FoodChoice.setItems(FoodList);

		FoodList = FXCollections.observableArrayList(d.displayFoodFromCategory(catagory));
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
	/**
	 * @author ParakhJaggi
	 * @param action-the users action with the button
	 * @throws SQLException
	 * This Method will add the amount of calories eaten to the database
	 */
	@FXML
	public void addCalorie(ActionEvent action) throws SQLException {
		DatabaseGateway d;
		d = DatabaseGateway.getInstance();
		usr = d.loadUser(usrname, pass);
		LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
		Date sqlDate = java.sql.Date.valueOf( todayLocalDate );

		
		d.addCaloriesToTrackers(usr.getUserId(), sqlDate,d.retrieveFood(FoodChoice.getValue()).getCalories() ,0);
		
        ((Node)(action.getSource())).getScene().getWindow().hide();
         

	}
	/**
	 * @author ParakhJaggi
	 * @return void
	 * This Method will initialize the choicebox to have categories
	 */
	@FXML
	private void initialize() {
		Catagory.setValue("Protein");
		Catagory.setItems(CategoryList);
		
	}
	/**
	 * @author ParakhJaggi
	 * @param email-the users email
	 * @param pass-the users password
	 * @return void 
	 * This Method will 
	 * 
	 */
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	}
	
	
	/**
	 * @author ParakhJaggi
	 * @param usrname-the users email
	 * @param pass-the users password
	 */
	@Override
	public void execute(String username,String password) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddFood.fxml"));

		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}          
		
		this.setUser(username.toString(), password.toString());
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
		
	}

	


}

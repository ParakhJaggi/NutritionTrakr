package FitnessTracker.FTProject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * 
 * @author ParakhJaggi
 * This class will manage the register page 
 *
 */
public class RegisterUserController implements Command {
	@FXML
	public ChoiceBox<String> genderbox;
	@FXML
	ObservableList<String> GenderList = FXCollections.observableArrayList("Male","Female");
	@FXML
	public TextField email;
	@FXML 
	public PasswordField password;
	@FXML
	public Button registerButton;
	@FXML
	public TextField height;
	@FXML
	public TextField weight;
	@FXML
	public TextField waistMeasurement;
	@FXML
	public TextField neckMeasurement;
	@FXML
	public TextField firstname;
	@FXML
	public TextField lastname;
	

	/**
	 * @author ParakhJaggi
	 * @param action-the user hitting the button 
	 * @throws SQLException
	 * @throws IOException
	 * This method will allow the user to register into the database when the button is clicked 
	 */
	@FXML 
	private void PressRegister(ActionEvent action) throws SQLException, IOException {
		User usr;
		if(genderbox.getValue().equals("Male")) {
			usr = new MaleUser();
		}
		else {
			usr = new FemaleUser();
		}
		usr.setEmail(email.getText());
		usr.setFirstName(firstname.getText());
		usr.setLastName(lastname.getText());
		usr.setGender(genderbox.getValue());
		usr.setPassword(password.getText());
		try {
		usr.setHeight(Double.parseDouble(height.getText()));
		usr.setNeckMeasurment(Double.parseDouble(neckMeasurement.getText()));
		usr.setWaistMeasurment(Double.parseDouble(waistMeasurement.getText()));
		usr.setWeight(Double.parseDouble(weight.getText()));
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Number error");
			alert.setContentText("These have to be a number");
			alert.showAndWait();
			return;
		}
		
		if(!checker.checkString(email.getText())||!checker.checkString(firstname.getText())
				||!checker.checkString(lastname.getText())||!checker.checkString(height.getText())
				||!checker.checkString(neckMeasurement.getText())||!checker.checkString(password.getText())
				||!checker.checkString(waistMeasurement.getText())||!checker.checkString(weight.getText())) {
			
			System.out.println("You tyrna SQL Inject?");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Special character error");
			alert.setContentText("We do not allow semicolons or equals symbols. Try again.");
			alert.showAndWait();
			return;
		}
		
		  
		DatabaseGateway d = DatabaseGateway.getInstance();
		if(d.loadUser(email.getText(), password.getText())!=null) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText("Email");
		alert.setContentText("Email already found. Try agian.");
		alert.showAndWait();
			return;
		}
		d.registrationHelper(usr);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));

		Parent root = (Parent)fxmlLoader.load();          
		DashboardController controller = fxmlLoader.<DashboardController>getController();
		controller.setUser(email.getText().toString(), password.getText().toString());
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        ((Node)(action.getSource())).getScene().getWindow().hide();

		
	}
	/**
	 * @author ParakhJaggi
	 * This method will load the choiceboxes 
	 */
	@FXML
	private void initialize() {
		genderbox.setValue("Male");
		genderbox.setItems(GenderList);
	}
	/**
	 * @author ParakhJaggi
	 * @param email
	 * @param pass
	 * This method will keep the users credentials 
	 */
	@Override
	public void execute(String usernmae,String password) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));

		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}          
		
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
         
		
	}

}

package FitnessTracker.FTProject;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterUserController {
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
	
	@FXML 
	private void PressRegister() throws SQLException {
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
		usr.setHeight(Double.parseDouble(height.getText()));
		usr.setNeckMeasurment(Double.parseDouble(neckMeasurement.getText()));
		usr.setPassword(password.getText());
		usr.setWaistMeasurment(Double.parseDouble(waistMeasurement.getText()));
		usr.setWeight(Double.parseDouble(weight.getText()));
		//usr.setUserId(i);
		
		DatabaseGateway d = DatabaseGateway.getInstance();
		d.registrationHelper(usr);
		
		
	}
	@FXML
	private void initialize() {
		genderbox.setValue("Male");
		genderbox.setItems(GenderList);
	}

}

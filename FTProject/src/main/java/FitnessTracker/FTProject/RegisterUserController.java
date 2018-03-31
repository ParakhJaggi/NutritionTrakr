package FitnessTracker.FTProject;

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
	public TextField hight;
	@FXML
	public TextField weight;
	@FXML
	public TextField waistMeasurement;
	@FXML
	public TextField neckMeasurement;
	
	@FXML 
	private void PressRegister() {
		
	}
	@FXML
	private void initialize() {
		genderbox.setValue("Male");
		genderbox.setItems(GenderList);
	}

}

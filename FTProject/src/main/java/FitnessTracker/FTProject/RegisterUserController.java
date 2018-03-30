package FitnessTracker.FTProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class RegisterUserController {
	@FXML
	public ChoiceBox genderbox;
	ObservableList<String> GenderList = FXCollections.observableArrayList("Male","Female");
	

}

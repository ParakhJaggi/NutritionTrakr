package FitnessTracker.FTProject;

import java.io.IOException;
import java.sql.SQLException;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
		usr.setHeight(Double.parseDouble(height.getText()));
		usr.setNeckMeasurment(Double.parseDouble(neckMeasurement.getText()));
		usr.setPassword(password.getText());
		usr.setWaistMeasurment(Double.parseDouble(waistMeasurement.getText()));
		usr.setWeight(Double.parseDouble(weight.getText()));
		//usr.setUserId(i);
		
		DatabaseGateway d = DatabaseGateway.getInstance();
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
	@FXML
	private void initialize() {
		genderbox.setValue("Male");
		genderbox.setItems(GenderList);
	}
	@Override
	public void execute(String usernmae,String password) {
		// TODO Auto-generated method stub
		
	}

}

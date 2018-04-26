package FitnessTracker.FTProject;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * 
 * @author ParakhJaggi
 * This Class will control the opening page of the Application 
 *
 */
public class Controller {
	@FXML
	public TextField usrname;
	@FXML
	public PasswordField pass;
	/**
	 * @author ParakhJaggi
	 * @param action-the users pressing of the button
	 * @throws IOException
	 * This method will let the user register themself into the database 
	 */
	public void pressSignUp(ActionEvent action) throws IOException {
		RegisterUserController r = new RegisterUserController();
		r.execute(usrname.getText(), pass.getText());
        ((Node)(action.getSource())).getScene().getWindow().hide();

	}
	/**
	 * @author ParakhJaggi
	 * @param action
	 * @throws SQLException
	 * @throws IOException
	 * This method will allow the user to sign into the application 
	 */
	public void pressSignIn(ActionEvent action) throws SQLException, IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));

		Parent root = (Parent)fxmlLoader.load();          
		DashboardController controller = fxmlLoader.<DashboardController>getController();
		DataValidator checker = new DataValidator();
		if(!checker.checkString(usrname.getText())||!checker.checkString(pass.getText())){
			
			System.out.println("You tyrna SQL Inject?");
			return;
		}
		if(UserDatabaseGateway.getInstance().loadUser(usrname.getText(), pass.getText()) ==null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Username/Password");
			alert.setContentText("Combination of Username/Password not found. Try agian.");
			alert.showAndWait();
			return;
			}
		controller.setUser(usrname.getText().toString(), pass.getText().toString());
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);
		

		stage.show();   
        ((Node)(action.getSource())).getScene().getWindow().hide();

		
	}
	
}

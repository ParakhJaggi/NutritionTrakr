package FitnessTracker.FTProject;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DashboardController {
//test

	public String usrname;
	
	public String pass;
	
	@FXML
	private void UpdateCalories(ActionEvent action) throws IOException {
		System.out.println(usrname);
		System.out.println(usrname);

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddFood.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		AddCalorieController controller = fxmlLoader.<AddCalorieController>getController();
		controller.setUser(usrname.toString(), pass.toString());
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();
		
	}
	@FXML
	public void getScore() throws SQLException, IOException {
		System.out.println(usrname);
		System.out.println(pass);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("getScore.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		MyScoreController controller = fxmlLoader.<MyScoreController>getController();
		controller.setUser(usrname.toString(), pass.toString());	
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();
	}
	@FXML
	private void UpdateExercise(ActionEvent action) throws IOException {
		System.out.println(usrname);
		System.out.println(usrname);

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddExercise.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		AddExerciseController controller = fxmlLoader.<AddExerciseController>getController();
		controller.setUser(usrname.toString(), pass.toString());
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();
		
	}
	@FXML
	private void initialize() {
		
	}
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	    
	    
	}
	@FXML
	public void getGraph() throws SQLException, IOException {
		System.out.println(usrname);
		System.out.println(usrname);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bargraph.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		BarGraphController controller = fxmlLoader.<BarGraphController>getController();
		controller.setUser(usrname.toString(), pass.toString());	
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();
		
		

	}
	@FXML
	public void getLeaderboard() throws SQLException, IOException {
		System.out.println(usrname);
		System.out.println(usrname);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leaderboard.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		//BarGraphController controller = fxmlLoader.<BarGraphController>getController();
		//controller.setUser(usrname.toString(), pass.toString());	
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();
		
		
		

	}
	@FXML
	public void getGoals() throws SQLException, IOException {
		System.out.println(usrname);
		System.out.println(usrname);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("getGoals.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		GoalsController controller = fxmlLoader.<GoalsController>getController();
		controller.setUser(usrname.toString(), pass.toString());	
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();
		
	}
	@FXML
	public void getAboutUs() throws IOException{
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutUs.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();
		
	}
	@FXML
	public void getProfile() throws SQLException, IOException {
		System.out.println(usrname);
		System.out.println(usrname);
		
		
		if(DatabaseGateway.getInstance().LoadUser(usrname, pass).getGender().equals("Male")) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MaleProfileChanger.fxml"));  
			Parent root = (Parent)fxmlLoader.load();  
			MaleProfileController controller = fxmlLoader.<MaleProfileController>getController();
			controller.setUser(usrname.toString(), pass.toString());	
			Scene scene = new Scene(root); 
			Stage stage = new Stage();
			stage.setScene(scene);    

			stage.show();   
		}
		else { 
			System.out.println("hi");
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FemaleProfileChanger.fxml"));  
			Parent root = (Parent)fxmlLoader.load();  
			FemaleProfileController controller = fxmlLoader.<FemaleProfileController>getController();
			controller.setUser(usrname.toString(), pass.toString());	
			Scene scene = new Scene(root); 
			Stage stage = new Stage();
			stage.setScene(scene);    

			stage.show();   
		}
		

		        
		
		
        //((Node)(action.getSource())).getScene().getWindow().hide();
	}
		
		
	
}

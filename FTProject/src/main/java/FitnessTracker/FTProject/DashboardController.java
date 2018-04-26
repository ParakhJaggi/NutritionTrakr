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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * 
 * @author ParakhJaggi
 * This class will control the home screen of the APP
 *
 */
public class DashboardController  {
//test

	public String usrname;
	
	public String pass;
	@FXML
	private ImageView bronze;
	@FXML
	private ImageView silver;
	@FXML
	private ImageView gold;
	/**
	 * @author ParakhJaggi
	 * @param action
	 * @throws IOException
	 * This method will update the number of calories a food has  
	 */
	@FXML
	private void UpdateCalories(ActionEvent action) throws IOException {
		System.out.println(usrname);
		System.out.println(usrname);

		AddCalorieController controller = new AddCalorieController();
		controller.execute(usrname,pass);
		
	}
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * @throws IOException
	 * This method will calculate the users score
	 */
	@FXML
	public void getScore() throws SQLException, IOException {
		MyScoreController c = new MyScoreController();
		c.execute(usrname, pass);
	}
	/**
	 * @author ParakhJaggi
	 * @param action
	 * @throws IOException
	 * This method will update the exercise choicebox 
	 */
	@FXML
	private void UpdateExercise(ActionEvent action) throws IOException {
		AddExerciseController e = new AddExerciseController();
		e.execute(usrname, pass);
		
	}
	
	/**
	 * @author ParakhJaggi
	 * @param email
	 * @param pass
	 * This method will allow the controller to keep the users credentials 
	 */
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	    
	    
	}
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * @throws IOException
	 * This method will open up the graph screen 
	 */
	@FXML
	public void getGraph() throws SQLException, IOException {
		BarGraphController b = new BarGraphController();
		b.execute(usrname, pass);

	}
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * @throws IOException
	 * This method will open the leaderboard screen
	 */
	@FXML
	public void getLeaderboard() throws SQLException, IOException {
		LeaderboardController l = new LeaderboardController();
		l.execute(usrname, pass);
		
		

	}
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * @throws IOException
	 * This method will open the goals screen 
	 */
	@FXML
	public void getGoals() throws SQLException, IOException {
		GoalsController g = new GoalsController();
		g.execute(usrname, pass);
	}
	/**
	 * @author ParakhJaggi
	 * @throws IOException
	 * This method will open the aboutus screen
	 */
	@FXML
	public void getAboutUs() throws IOException{
		
		AboutUsController a = new AboutUsController();
		a.execute(usrname, pass);
	}
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * @throws IOException
	 * This method will open up the my profile screen
	 */
	@FXML
	public void getProfile() throws SQLException, IOException {
		
		
		if(UserDatabaseGateway.getInstance().loadUser(usrname, pass).getGender().equals("Male")) {
			MaleProfileController m = new MaleProfileController();
			m.execute(usrname, pass);
		}
		else { 
			FemaleProfileController f = new FemaleProfileController();
			f.execute(usrname, pass);
		}
	}
	/**
	 * @author ParakhJaggi
	 * @throws SQLException
	 * This method will implement the stage pattern 
	 */
	@FXML
	public void ribbonize() throws SQLException{
		int score=UserDatabaseGateway.getInstance().loadUser(usrname, pass).getScore();
		RibbonContext rib=new RibbonContext();
		if (score<33)
			rib.setState(new BronzeRibbonState());
		else if(score<66)
			rib.setState(new SilverRibbonState());
		else
			rib.setState(new GoldRibbonState());
		String img= rib.getState().doAction(rib);
		if(img.equals("bronzeRibbon.png")) {
			bronze.setVisible(true);
		}
		else if(img.equals("silverRibbon.png")) {
			silver.setVisible(true);
		}
		else if(img.equals("goldRibbon.png")) {
			gold.setVisible(true);
		}
	}
	
}

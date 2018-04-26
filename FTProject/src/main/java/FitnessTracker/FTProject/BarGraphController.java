package FitnessTracker.FTProject;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * 
 * @author ParakhJaggi
 * This class will display the BarGraph
 *
 */
public class BarGraphController implements Command{

	@FXML
	public Button graph;
	
	public String usrname;
	
	public String pass;
	@FXML
	public BarChart<String,Number> calorieChart;
	@FXML
	public CategoryAxis xaxis;
	@FXML
	public NumberAxis yaxis;
	/**
	 * @author ParakhJaggi
	 * This method will produce the graph 
	 */
	@FXML
	private void getgraph() {
		System.out.println(usrname);
		System.out.println(usrname);
	
		UserDatabaseGateway d = UserDatabaseGateway.getInstance();
		User user = null;
		try {
			user = d.loadUser(usrname,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		calorieChart.setTitle("FITNESS");
        calorieChart.setCategoryGap(0);
        calorieChart.setBarGap(-20);
		
		ArrayList<XYChart.Series> list= new ArrayList<XYChart.Series>();
        LocalDate date= LocalDate.now().minusDays(30);
        
        for(int i=0;i<30;i++) {
        	int curDay= date.getDayOfMonth();
        	XYChart.Series mySeries=new XYChart.Series();
        	mySeries.setName("a");
        	int value=user.getDataPointCalorieMap(Date.valueOf(date));
        	System.out.println(value+" ");
        	mySeries.getData().add(new XYChart.Data(String.valueOf(curDay),value));
        	list.add(mySeries);
        	date=date.plusDays(1);
        }
        
        for(int x =0; x<list.size();x++) {
        	
        	calorieChart.getData().addAll(list.get(x));
        }
       
        
       calorieChart.setLegendVisible(false);
        
		
        
        
		
	}
	/**
	 * @author ParakhJaggi
	 * this method will open the graph when the button is pressed 
	 */
	@FXML
	public void buttonhit() {
		for(int x = 0; x<5; x++) {
			getgraph();
		}
		
		
		
	}
	/**
	 * @author ParakhJaggi
	 * @param email
	 * @param pass
	 * This method will keep track of the users credentials 
	 */
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	    System.out.println(usrname);
		System.out.println(usrname);
	}
	/**
	 * @author ParakhJaggi
	 * @param email
	 * @param pass
	 * This method will open the bargraph screen when pressed
	 */
	@Override
	public void execute(String username,String password) {
		System.out.println(usrname);
		System.out.println(usrname);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bargraph.fxml"));

		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}          
		BarGraphController controller = fxmlLoader.<BarGraphController>getController();
		controller.setUser(username.toString(), password.toString());	
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
		
		
		
	}
}

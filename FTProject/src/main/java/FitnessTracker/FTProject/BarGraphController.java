package FitnessTracker.FTProject;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BarGraphController implements Command{

	@FXML
	public Button graph;
	
	public String usrname;
	
	public String pass;
	@FXML
	public BarChart<String,Number> CalorieChart;
	@FXML
	public CategoryAxis Xaxis;
	@FXML
	public NumberAxis Yaxis;
	
	@FXML
	private void getgraph() {
		System.out.println(usrname);
		System.out.println(usrname);
	
		DatabaseGateway d = DatabaseGateway.getInstance();
		User user = null;
		try {
			user = d.LoadUser(usrname,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(user.user_id);
		CalorieChart.setTitle("FITNESS");
        CalorieChart.setCategoryGap(0);
        CalorieChart.setBarGap(-20);
		
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
        	
        	CalorieChart.getData().addAll(list.get(x));
        }
       
        
       CalorieChart.setLegendVisible(false);
        
		
        
        /*
        
		XYChart.Series series1 = new XYChart.Series<>();
		series1.getData().add(new XYChart.Data("James",5000));
		CalorieChart.getData().addAll(series1);
		*/
		
	}
	@FXML
	public void buttonhit() {
		for(int x = 0; x<5; x++) {
			getgraph();
		}
		
		
		
	}
	
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	    System.out.println(usrname);
		System.out.println(usrname);
	}
	@Override
	public void execute(String username,String password) {
		System.out.println(usrname);
		System.out.println(usrname);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bargraph.fxml"));     

		Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		BarGraphController controller = fxmlLoader.<BarGraphController>getController();
		controller.setUser(username.toString(), password.toString());	
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);    

		stage.show();   
        //((Node)(action.getSource())).getScene().getWindow().hide();
		
		
		
	}
}

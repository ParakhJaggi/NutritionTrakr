package FitnessTracker.FTProject;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BarGraphController implements Initializable {

	@FXML
	public String usrname;
	@FXML
	public String pass;
	@FXML
	public BarChart<?,?> CalorieChart;
	@FXML
	public CategoryAxis Xaxis;
	@FXML
	public NumberAxis Yaxis;
	
	@Override
	public void initialize(URL url,ResourceBundle rb)  {
		DatabaseGateway d = DatabaseGateway.getInstance();

		User user = null;
		try {
			user = d.LoadUser(usrname, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XYChart.Series set1 = new XYChart.Series<>();
		ArrayList<XYChart.Series> list= new ArrayList<XYChart.Series>();
        LocalDate date= LocalDate.now().minusDays(30);
        
        for(int i=0;i<30;i++) {
        	int curDay= date.getDayOfMonth();
        	XYChart.Series mySeries=new XYChart.Series();
        	mySeries.setName("a");
        	int value=user.getDataPointCalorieMap(Date.valueOf(date));
        	mySeries.getData().add(new XYChart.Data(String.valueOf(curDay),value));
        	list.add(mySeries);
        	date=date.plusDays(1);
        }
        
        for(XYChart.Series a:list) {
        	set1.getData().addAll(a);
        }
		
	}
	public void setUser(String email,String pass){
	    this.usrname = email;
	    this.pass = pass;
	}
}

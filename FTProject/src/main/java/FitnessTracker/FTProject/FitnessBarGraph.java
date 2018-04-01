package FitnessTracker.FTProject;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
@SuppressWarnings("restriction")
public class FitnessBarGraph extends Application {
    final static String CALORIES = "Calories";
    private User user;
    
    
    @Override public void start(Stage stage) throws SQLException {
    	
    	DatabaseGateway d= new DatabaseGateway();

		user=d.LoadUser("a", "a");
    	d.createTrackerEntry(user.getUserId(), Date.valueOf(LocalDate.now().minusDays(1)), 1500, 400);
    	user=d.LoadUser("a", "a");
        stage.setTitle("My Calories this month");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("FITNESS");
        xAxis.setLabel("Date");       
        yAxis.setLabel("Calories");
        ArrayList<XYChart.Series> list= new ArrayList<XYChart.Series>();
        LocalDate date= LocalDate.now();
        for(int i=0;i<30;i++) {
        	XYChart.Series mySeries=new XYChart.Series();
        	mySeries.setName("a");
        	mySeries.getData().add(new XYChart.Data(CALORIES,user.getDataPointCalorieMap(Date.valueOf(date))));
        	list.add(mySeries);
        	date.minusDays(1);
        }
        bc.setLegendVisible(false);
        Scene scene  = new Scene(bc,800,600);
        for(XYChart.Series a:list) {
        	bc.getData().addAll(a);
        }
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
    	launch(args);
    }
}

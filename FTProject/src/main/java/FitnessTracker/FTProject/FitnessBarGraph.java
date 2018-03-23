package FitnessTracker.FTProject;

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
    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";
 
    @Override public void start(Stage stage) {
        stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("FITNESS");
        xAxis.setLabel("Date");       
        yAxis.setLabel("Calories");
 
        ArrayList<XYChart.Series> list= new ArrayList<XYChart.Series>();
        for(int i=0;i<20;i++) {
        	XYChart.Series mySeries=new XYChart.Series();
        	mySeries.setName("a");
        	mySeries.getData().add(new XYChart.Data(usa,i));
        	list.add(mySeries);
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

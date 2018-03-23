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
        xAxis.setLabel("GARTH");       
        yAxis.setLabel("Value");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");       
        series1.getData().add(new XYChart.Data(usa, 0));      
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(usa, 1));  
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(usa, 2));
        ArrayList<XYChart.Series> list= new ArrayList<XYChart.Series>();
        for(int i=0;i<20;i++) {
        	XYChart.Series mySeries=new XYChart.Series();
        	mySeries.setName("a");
        	mySeries.getData().add(new XYChart.Data(usa,i));
        	list.add(mySeries);
        }
        XYChart.Series series4 = new XYChart.Series();
        //series3.setName("2005");
        series4.getData().add(new XYChart.Data(usa, 92633.68));  
        bc.setLegendVisible(false);
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1, series2, series3);
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

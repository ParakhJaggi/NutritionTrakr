package FitnessTracker.FTProject;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Pair;

public class LeaderboardController {
	@FXML
	private Label number;
	@FXML
	private Label user;
	@FXML
	private Label number2;
	@FXML
	private Label user2;
	@FXML
	private Label number3;
	@FXML
	private Label user3;
	@FXML
	private Label number4;
	@FXML
	private Label user4;
	@FXML
	private Label number5;
	@FXML
	private Label user5;
	@FXML
	private Label number6;
	@FXML
	private Label user6;
	@FXML
	private Label number7;
	@FXML
	private Label user7;
	@FXML
	private Label number8;
	@FXML
	private Label user8;
	@FXML
	private Label number9;
	@FXML
	private Label user9;
	@FXML
	private Label number10;
	@FXML
	private Label user10;
	
	@FXML
    public void initialize() throws SQLException{
		DatabaseGateway d = new DatabaseGateway();
		d = d.getInstance();
		ArrayList<Pair<String,Integer>> leaderboard = new ArrayList<Pair<String,Integer>>();
		leaderboard = d.getTopTenLeaderBoard();
		user.setText(String.valueOf(leaderboard.get(0).getKey()));
		user2.setText(String.valueOf(leaderboard.get(1).getKey()));
		user3.setText(String.valueOf(leaderboard.get(2).getKey()));
		user4.setText(String.valueOf(leaderboard.get(3).getKey()));
		user5.setText(String.valueOf(leaderboard.get(4).getKey()));
		user6.setText(String.valueOf(leaderboard.get(5).getKey()));
		user7.setText(String.valueOf(leaderboard.get(6).getKey()));
		user8.setText(String.valueOf(leaderboard.get(7).getKey()));
		user9.setText(String.valueOf(leaderboard.get(8).getKey()));
		user10.setText(String.valueOf(leaderboard.get(9).getKey()));
		
		number.setText(String.valueOf(leaderboard.get(0).getValue()));
		number2.setText(String.valueOf(leaderboard.get(1).getValue()));
		number3.setText(String.valueOf(leaderboard.get(2).getValue()));
		number4.setText(String.valueOf(leaderboard.get(3).getValue()));
		number5.setText(String.valueOf(leaderboard.get(4).getValue()));
		number6.setText(String.valueOf(leaderboard.get(5).getValue()));
		number7.setText(String.valueOf(leaderboard.get(6).getValue()));
		number8.setText(String.valueOf(leaderboard.get(7).getValue()));
		number9.setText(String.valueOf(leaderboard.get(8).getValue()));
		number10.setText(String.valueOf(leaderboard.get(9).getValue()));









		
	}

}

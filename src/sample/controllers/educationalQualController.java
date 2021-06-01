package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.ProjectDao;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.*;
import static sample.ProjectDao.saveEducationalDetails;

public class educationalQualController implements Initializable {
	@FXML
	public Button eduToPrevbtn;
	@FXML
	public Button eduBtn;
	@FXML
	public TextField year_1; public TextField year_2; public TextField year_3; public TextField year_4;
	public TextField course_1; public TextField course_2; public TextField course_3; public TextField course_4;
	public TextField board_1; public TextField board_2; public TextField board_3; public TextField board_4;
	public TextField percentage_1; public TextField percentage_2; public TextField percentage_3; public TextField percentage_4;
	public TextField achievement_1; public TextField achievement_2; public TextField achievement_3;
	public TextField school_1; public TextField school_2; public TextField school_3;public TextField school_4;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			ResultSet resultSet=ProjectDao.loadDetails();
			if(resultSet.next()){
				if(resultSet.getString(14)!=null)
					year_1.setText(resultSet.getString(14));
				if(resultSet.getString(15)!=null)
					course_1.setText(resultSet.getString(15));
				if(resultSet.getString(16)!=null)
					school_1.setText(resultSet.getString(16));
				if(resultSet.getString(17)!=null)
					board_1.setText(resultSet.getString(17));
				if(resultSet.getString(18)!=null)
					percentage_1.setText(resultSet.getString(18));
				if(resultSet.getString(19)!=null)
					year_2.setText(resultSet.getString(19));
				if(resultSet.getString(20)!=null)
					course_2.setText(resultSet.getString(20));
				if(resultSet.getString(21)!=null)
					school_2.setText(resultSet.getString(21));
				if(resultSet.getString(22)!=null)
					board_2.setText(resultSet.getString(22));
				if(resultSet.getString(23)!=null)
					percentage_2.setText(resultSet.getString(23));
				if(resultSet.getString(24)!=null)
					year_3.setText(resultSet.getString(24));
				if(resultSet.getString(25)!=null)
					course_3.setText(resultSet.getString(25));
				if(resultSet.getString(26)!=null)
					school_3.setText(resultSet.getString(26));
				if(resultSet.getString(27)!=null)
					board_3.setText(resultSet.getString(27));
				if(resultSet.getString(28)!=null)
					percentage_3.setText(resultSet.getString(28));
				if(resultSet.getString(29)!=null)
					year_4.setText(resultSet.getString(29));
				if(resultSet.getString(30)!=null)
					course_4.setText(resultSet.getString(30));
				if(resultSet.getString(31)!=null)
					school_4.setText(resultSet.getString(31));
				if(resultSet.getString(32)!=null)
					board_4.setText(resultSet.getString(32));
				if(resultSet.getString(33)!=null)
					percentage_4.setText(resultSet.getString(33));
				if(resultSet.getString(34)!=null)
					achievement_1.setText(resultSet.getString(34));
				if(resultSet.getString(35)!=null)
					achievement_2.setText(resultSet.getString(35));
				if(resultSet.getString(36)!=null)
					achievement_3.setText(resultSet.getString(36));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		eduBtn.setOnAction(e -> {
			String year1=year_1.getText(); String year2=year_2.getText(); String year3=year_3.getText(); String year4=year_4.getText();
			String course1=course_1.getText();String course2=course_2.getText();String course3=course_3.getText();String course4=course_4.getText();
			String school1=school_1.getText();String school2=school_2.getText();String school3=school_3.getText();String school4=school_4.getText();
			String board1=board_1.getText();String board2=board_2.getText();String board3=board_3.getText();String board4=board_4.getText();
			String percentage1=percentage_1.getText();String percentage2=percentage_2.getText();String percentage3=percentage_3.getText();String percentage4=percentage_4.getText();
			String achievement1=achievement_1.getText();String achievement2=achievement_2.getText();String achievement3=achievement_3.getText();
			try {
				saveEducationalDetails(year1,year2,year3,year4,school1,school2,school3,school4,board1,board2,board3,board4,course1,course2,course3,course4,percentage1,percentage2,percentage3,percentage4,achievement1,achievement2,achievement3);
			} catch (SQLException throwable) {
				throwable.printStackTrace();
			}
			try {
				Parent root1=FXMLLoader.load(getClass().getResource("..\\FXML\\projectsAndSoftware.fxml"));
				Stage stage1= (Stage) eduBtn.getScene().getWindow();
				Scene scene1=new Scene(root1);
				stage1.setScene(scene1);
				stage1.setTitle("Software Competencies and Projects Undertaken");
				stage1.show();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		});
	}
	public void toPersonal(ActionEvent event) throws SQLException, ClassNotFoundException {

		try {
			Parent root= load(getClass().getResource("..//FXML//personal.fxml"));
			Stage stage=(Stage) eduBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Personal Details");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

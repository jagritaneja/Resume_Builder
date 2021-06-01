package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ProjectDao;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class skillsAndExp implements Initializable {
	@FXML
	public Button skillToProject;
	@FXML
	public Button skillBtn;
	public TextField expLearning_1;
	public TextField expLearning_2;
	public TextField expLearning_3;
	public TextField interSkills_1;
	public TextField interSkills_2;
	public TextField interSkills_3;
	public TextField interSkills_4;
	public TextField extraActivity_1;
	public TextField extraActivity_2;
	public TextField extraActivity_3;
	public TextField extraActivity_4;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ResultSet resultSet=ProjectDao.loadDetails();
			if(resultSet.next()){
				if(resultSet.getString(49)!=null)
					expLearning_1.setText(resultSet.getString(49));
				if(resultSet.getString(50)!=null)
					expLearning_2.setText(resultSet.getString(50));
				if(resultSet.getString(51)!=null)
					expLearning_3.setText(resultSet.getString(51));
				if(resultSet.getString(52)!=null)
					interSkills_1.setText(resultSet.getString(52));
				if(resultSet.getString(53)!=null)
					interSkills_2.setText(resultSet.getString(53));
				if(resultSet.getString(54)!=null)
					interSkills_3.setText(resultSet.getString(54));
				if(resultSet.getString(55)!=null)
					interSkills_4.setText(resultSet.getString(55));
				if(resultSet.getString(56)!=null)
					extraActivity_1.setText(resultSet.getString(56));
				if(resultSet.getString(57)!=null)
					extraActivity_2.setText(resultSet.getString(57));
				if(resultSet.getString(58)!=null)
					extraActivity_3.setText(resultSet.getString(58));
				if(resultSet.getString(59)!=null)
					extraActivity_4.setText(resultSet.getString(59));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		skillToProject.setOnAction(event ->
		{
			try {
				Parent root= FXMLLoader.load(getClass().getResource("..\\FXML\\projectsAndSoftware.fxml"));
				Stage stage=(Stage) skillToProject.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.setTitle("Software Competencies and Projects Undertaken");
			} catch (IOException e) {
				e.printStackTrace();
			}

		});
		skillBtn.setOnAction(event ->
		{
			try {
				ProjectDao.saveLearningAndSkills(expLearning_1.getText(),expLearning_2.getText(),expLearning_3.getText(),interSkills_1.getText(),interSkills_2.getText(),interSkills_3.getText(),interSkills_4.getText(),extraActivity_1.getText(),extraActivity_2.getText(),extraActivity_3.getText(),extraActivity_4.getText());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				Parent root=FXMLLoader.load(getClass().getResource("..\\FXML\\lastPage.fxml"));
				Stage stage=(Stage)skillBtn.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.setTitle("Inserting Photograph");
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}
}

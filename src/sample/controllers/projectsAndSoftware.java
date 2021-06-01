package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ProjectDao;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class projectsAndSoftware implements Initializable {
	@FXML
	public Button projectBtn;
	@FXML
	public Button projectToEduBtn;
	public TextArea description_1;
	public TextField projectName_1;
	public TextArea description_2;
	public TextField projectName_2;
	public TextField environment_1;
	public TextField environment_2;
	public TextField role_1;
	public TextField role_2;
	public TextField operatingSystem;
	public TextField languagesKnown;
	public TextField databaseKnown;
	public TextField otherInterest;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ResultSet resultSet=ProjectDao.loadDetails();
			if(resultSet.next()){
				if(resultSet.getString(37)!=null)
					operatingSystem.setText(resultSet.getString(37));
				if(resultSet.getString(38)!=null)
					languagesKnown.setText(resultSet.getString(38));
				if(resultSet.getString(39)!=null)
					databaseKnown.setText(resultSet.getString(39));
				if(resultSet.getString(40)!=null)
					otherInterest.setText(resultSet.getString(40));
				if(resultSet.getString(41)!=null)
					projectName_1.setText(resultSet.getString(41));
				if(resultSet.getString(42)!=null)
					description_1.setText(resultSet.getString(42));
				if(resultSet.getString(43)!=null)
					role_1.setText(resultSet.getString(43));
				if(resultSet.getString(44)!=null)
					environment_1.setText(resultSet.getString(44));
				if(resultSet.getString(45)!=null)
					projectName_2.setText(resultSet.getString(45));
				if(resultSet.getString(46)!=null)
					description_2.setText(resultSet.getString(46));
				if(resultSet.getString(47)!=null)
					role_2.setText(resultSet.getString(47));
				if(resultSet.getString(48)!=null)
					environment_2.setText(resultSet.getString(48));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		projectToEduBtn.setOnAction(event ->
		{
			try {
				Parent root = FXMLLoader.load(getClass().getResource("..\\FXML\\educationalQual.fxml"));
				Stage stage = (Stage) projectToEduBtn.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.setTitle("Educational Qualifications");
			} catch (IOException e) {
				e.printStackTrace();
			}

		});
		projectBtn.setOnAction(event ->
		{
			try {
				ProjectDao.saveProjectAndSoftware(operatingSystem.getText(),databaseKnown.getText(),languagesKnown.getText(),otherInterest.getText(),projectName_1.getText(),environment_1.getText(),description_1.getText(),role_1.getText(),projectName_2.getText(),environment_2.getText(),description_2.getText(),role_2.getText());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				Parent root=FXMLLoader.load(getClass().getResource("..\\FXML\\skillsAndExp.fxml"));
				Stage stage=(Stage)projectBtn.getScene().getWindow();
				Scene scene=new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Skills and Experience");
				stage.show();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		});


	}




}

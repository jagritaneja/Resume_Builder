package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DbProject;
import sample.ProjectDao;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
	public static String primaryUser;
	@FXML
	public Button loginButton;
	@FXML
	public TextField userName;
	@FXML
	public PasswordField password;
	public Button registerBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		registerBtn.setOnAction(event -> {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("..//FXML//Register.fxml"));
				Stage stage=(Stage) loginButton.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.setTitle("Smart CV Builder");

			} catch (IOException e) {
				e.printStackTrace();
			}

		});


	}

	@FXML
	public void userLogin(ActionEvent event) throws SQLException, ClassNotFoundException {
		primaryUser=userName.getText();
		if(userName.getText().isEmpty()||password.getText().isEmpty()){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid Credentials");
			alert.setHeaderText("Please enter your username or password");
			alert.show();
		}
		boolean bl=ProjectDao.loginFunc(userName.getText(),password.getText());


if(bl) {
	System.out.println("LOGIN SUCCESSFUL");
	try {
		Parent root = FXMLLoader.load(getClass().getResource("..//FXML//personal.fxml"));
		Stage stage=(Stage) loginButton.getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.setTitle("Personal Details");
	} catch (IOException e) {
		System.out.println("Trouble in loading personal");
		e.printStackTrace();
	}
}
	else{
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("OOPS!! Invalid Credentials");
		alert.setHeaderText("Invalid Username or Password");
		alert.show();
		userName.setText("");
	    userName.requestFocus();
	    password.setText("");
	}
	}
}

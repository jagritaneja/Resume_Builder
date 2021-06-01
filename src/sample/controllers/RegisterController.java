package sample.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DbProject;
import sample.ProjectDao;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
	@FXML
	public PasswordField password;
	public PasswordField confirmPassword;
	public TextField email_id;
	public Button Submit;
	public Button loginButton;
	public TextField userName;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Submit.setOnAction(event -> {
				if(!password.getText().isEmpty() &&password.getText().equals(confirmPassword.getText())) {
					try {
						ResultSet rs=DbProject.dbRetrieve("select * from project_data where username='"+userName.getText()+"'");
						if(rs.next()&&rs.getString(1).equals(userName.getText()))
						{
							System.out.println("in try");
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Username Already Exists");
							alert.setHeaderText(" Please try with different username");
						}
						else
						ProjectDao.registerDetails(userName.getText(), password.getText(), email_id.getText());
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Registration Successful");
						alert.setHeaderText(" Please Login to Continue");
						ButtonType LoginBtn = new ButtonType("Login");
						ButtonType noBtn = new ButtonType("No,Exit");
						alert.getButtonTypes().setAll(LoginBtn, noBtn);
						Optional<ButtonType> btnClicked = alert.showAndWait();
						if (btnClicked.isPresent() && btnClicked.get() == LoginBtn) {
							try {
								Parent root= FXMLLoader.load(getClass().getResource("..\\FXML\\loginPage.fxml"));
								Stage stage=(Stage)Submit.getScene().getWindow();
								stage.setScene(new Scene(root));
								stage.setTitle("Login");
								stage.show();
							} catch (IOException e) {
								e.printStackTrace();
							}

						} else {
							Platform.exit();
						}
					


					} catch (SQLException | ClassNotFoundException e) {
						e.printStackTrace();
					}}
					else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("OOPS!!");
					alert.setHeaderText(" Please enter the same passwords");
					alert.show();
					password.requestFocus();
				}
			});
		loginButton.setOnAction(event -> {
			try {
				Parent root= FXMLLoader.load(getClass().getResource("..\\FXML\\loginPage.fxml"));
				Stage stage=(Stage)Submit.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.setTitle("Login");
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

	}

}

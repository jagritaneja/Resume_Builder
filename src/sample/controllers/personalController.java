package sample.controllers;

import javafx.event.ActionEvent;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class personalController implements Initializable {
	@FXML
	public  TextField p_name;
	@FXML
	public Button perButton;
	@FXML
	public ChoiceBox p_gender;
	@FXML
	public TextField p_contact;
	@FXML
	public TextField p_email;
	@FXML
	public TextArea p_address;
	@FXML
	public ChoiceBox p_marriedStatus;
	@FXML
	public DatePicker p_dob;
	@FXML
	public TextField hobbies_1;
	public TextField hobbies_2;
	public  TextField hobbies_3;
	public TextField hobbies_4;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		p_gender.getItems().addAll("Male", "Female", "Prefer not to say");
		p_marriedStatus.getItems().addAll("Unmarried", "Married");

		try {
			ResultSet rs=ProjectDao.loadDetails();
			if(rs.next()){
				if(rs.getString(4)!=null)
				p_name.setText(rs.getString(4));
				else
					p_name.setText("");
				if(rs.getString(2)!=null)
					p_email.setText(rs.getString(2));
				else
					p_email.setText("");

				//if(rs.getString(5)!=null)
				  //   p_dob.setValue(LocalDate.parse(rs.getString(5)));
				if(rs.getString(7)!=null)
					p_address.setText(rs.getString(7));
				else
					p_address.setText("");
				if(rs.getString(6)!=null)
					p_gender.setValue(rs.getString(6));
				else
					p_gender.setValue("");
				if(rs.getString(8)!=null)
					p_contact.setText(rs.getString(8));
				else
					p_contact.setText("");
				if(rs.getString(9)!=null)
					p_marriedStatus.setValue(rs.getString(9));
				else
					p_marriedStatus.setValue("");
				if(rs.getString(10)!=null)
					hobbies_1.setText(rs.getString(10));
				else
					hobbies_1.setText("");
				if(rs.getString(11)!=null)
					hobbies_2.setText(rs.getString(11));
				else
					hobbies_2.setText("");
				if(rs.getString(12)!=null)
					hobbies_3.setText(rs.getString(12));
				else
					hobbies_3.setText("");
				if(rs.getString(13)!=null)
					hobbies_4.setText(rs.getString(13));
				else
					hobbies_4.setText("");


			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void perToNext(ActionEvent event) throws SQLException, ClassNotFoundException {
		String name=p_name.getText();
		String gender= (String) p_gender.getValue();
		String email=p_email.getText();
		String contact=p_contact.getText();
		String address=p_address.getText();
		String marriedStatus=(String) p_marriedStatus.getValue();
		String dateOfBirth= String.valueOf(p_dob.getValue());
		String hobby_1=hobbies_1.getText();
		String hobby_2=hobbies_2.getText();
		String hobby_3=hobbies_3.getText();
		String hobby_4=hobbies_4.getText();


		ProjectDao.savePersonalDetails(name, gender, email, contact, address, marriedStatus, dateOfBirth,hobby_1,hobby_2,hobby_3,hobby_4,LoginController.primaryUser);
		try {
			Parent root = FXMLLoader.load(getClass().getResource("..//FXML//educationalQual.fxml"));
			Stage stage = (Stage) perButton.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Educational Qualifications");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void LoadBackPersonal(String email_id,String name,String dob, String gender,String address,String contact,String maritialStatus ,String hobbies_1,String hobbies_2,String hobbies_3,String hobbies_4 ){

	}
}

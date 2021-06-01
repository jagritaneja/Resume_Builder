package sample.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.DbProject;
import sample.Print;
import sample.ProjectDao;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ImageChoosing implements Initializable {
	public static Image image;
	@FXML
	public Button generateResume;
	public Button imgToPrev;
	@FXML
	private ImageView imageView;
	@FXML
	private Button browse;
	private FileInputStream fis;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ResultSet rs= ProjectDao.loadDetails();
			if(rs.next()){
				if(rs.getBinaryStream(60)!=null){
					Blob blob=rs.getBlob(60);
					InputStream inputStream=blob.getBinaryStream();
					BufferedImage image= ImageIO.read(inputStream);
					Image image1= SwingFXUtils.toFXImage(image,null);
					imageView.setFitWidth(150);
					imageView.setFitHeight(150);
					imageView.setImage(image1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		browse.setOnAction(event -> {
			FileChooser imageChooser=new FileChooser();
			imageChooser.setTitle("Select Image");
			Stage stage=(Stage) browse.getScene().getWindow();
			File file= imageChooser.showOpenDialog(stage);
			try {
				 fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String query="update project_data set image=? where username=?";
			try {
				PreparedStatement preparedStatement=DbProject.connection.prepareStatement(query);
				preparedStatement.setBinaryStream(1,fis,file.length());
				preparedStatement.setString(2,LoginController.primaryUser);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (file!=null){
				try {
					Path source=Paths.get(file.getAbsolutePath());
					Path destination= Paths.get("E:\\SmartCVBuilder\\src\\sample\\FXML\\image.png");
					Files.copy(source,destination, StandardCopyOption.REPLACE_EXISTING);
					 image=new Image(file.toURI().toString(),150,150,true,true);
					imageView.setImage(image);
					imageView.setFitWidth(150);
					imageView.setFitHeight(150);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		generateResume.setOnAction(event -> {
			try {
				Print.createFile();
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Your Resume is generated successfully and saved at \n "+Print.showPath());
				alert.show();
			} catch (IOException | SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
		imgToPrev.setOnAction(event -> {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("..\\FXML\\skillsAndExp.fxml"));
				Stage stage = (Stage) imgToPrev.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.setTitle("Skills and Experience");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});


	}
}

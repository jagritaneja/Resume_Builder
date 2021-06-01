package sample;

import sample.controllers.LoginController;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.controllers.personalController;

public class ProjectDao {


	public static boolean loginFunc(String username, String password) throws SQLException, ClassNotFoundException {
		String string="";
		String query="Select password from project_data where username='"+username+"'";
		ResultSet rst= DbProject.dbRetrieve(query);
		if(rst.next()) {
			string=rst.getString(1).trim();
		}
			if(string.equals(password.trim()))
				return true;

		else
			return false;

	}


	public static void savePersonalDetails(String name,String gender,String email,String contact,String address,String marriedStatus, String Dob, String hobbies_1,String hobbies_2,String hobbies_3,String hobbies_4,String primaryUser) throws SQLException, ClassNotFoundException {
		String query="update project_data set email_id=?,name=?,dob=?,gender=?,address=?,contact=?,maritialStatus=? ,hobbies_1=?,hobbies_2=?,hobbies_3=?,hobbies_4=? where username=?";
		PreparedStatement statement=DbProject.connection.prepareStatement(query);
		statement.setString(1,email);
		statement.setString(2,name);
		statement.setDate(3, Date.valueOf(Dob));
		statement.setString(4,gender);
		statement.setString(5,address);
		statement.setString(6,contact);
		statement.setString(7,marriedStatus);
		statement.setString(8,hobbies_1);
		statement.setString(9,hobbies_2);
		statement.setString(10,hobbies_3);
		statement.setString(11,hobbies_4);
		statement.setString(12,primaryUser);

		statement.executeUpdate();
		//DbProject.dbDisconnect();

	}

	public static void saveEducationalDetails (String year_1,String year_2,String year_3,String year_4, String school_1, String school_2, String school_3, String school_4
			, String board_1,String board_2,String board_3,String board_4,String course_1,String course_2,String course_3,String course_4,
	String percentage_1,String percentage_2,String percentage_3,String percentage_4,String achievement_1,String achievement_2,String achievement_3)throws SQLException{
		String query="update project_data set year_1=?,year_2=?,year_3=?,year_4=?,course_1=?,course_2=?,course_3=?,course_4=?,school_1=?,school_2=?,school_3=?,school_4=?,board_1=?,board_2=?,board_3=?,board_4=?," +
				"percentage_1=?,percentage_2=?,percentage_3=?,percentage_4=?,achievemnt_1=?,achievemnt_2=?,achievemnt_3=? where username=?";
		PreparedStatement statement=DbProject.connection.prepareStatement(query);
		statement.setString(1,year_1);
		statement.setString(2,year_2);
		statement.setString(3,year_3);
		statement.setString(4,year_4);
		statement.setString(5,course_1);
		statement.setString(6,course_2);
		statement.setString(7,course_3);
		statement.setString(8,course_4);
		statement.setString(9,school_1);
		statement.setString(10,school_2);
		statement.setString(11,school_3);
		statement.setString(12,school_4);
		statement.setString(13,board_1);
		statement.setString(14,board_2);
		statement.setString(15,board_3);
		statement.setString(16,board_4);
		statement.setString(17,percentage_1);
		statement.setString(18,percentage_2);
		statement.setString(19,percentage_3);
		statement.setString(20,percentage_4);
		statement.setString(21,achievement_1);
		statement.setString(22,achievement_2);
		statement.setString(23,achievement_3);
		statement.setString(24, LoginController.primaryUser);
		statement.executeUpdate();

	}

	public static void saveProjectAndSoftware(String operatingSystem,String databaseKnown,String languageKnown,String otherInterest,String projectName_1,String environment_1,String description_1,String role_1,String projectName_2,String environment_2,String description_2,String role_2) throws SQLException {
		String query="update project_data set operatingSystem=?,databaseKnown=?,languageKnown=?,otherInterest=?,projectName_1=?,projectName_2=?,description_1=?,description_2=?,environment_1=?,environment_2=?,role_1=?,role_2=? where username=?";
		PreparedStatement statement=DbProject.connection.prepareStatement(query);
		statement.setString(1,operatingSystem);
		statement.setString(2,databaseKnown);
		statement.setString(3,languageKnown);
		statement.setString(4,otherInterest);
		statement.setString(5,projectName_1);
		statement.setString(6,projectName_2);
		statement.setString(7,description_1);
		statement.setString(8,description_2);
		statement.setString(9,environment_1);
		statement.setString(10,environment_2);
		statement.setString(11,role_1);
		statement.setString(12,role_2);
		statement.setString(13,LoginController.primaryUser);
		statement.executeUpdate();
	}

	public static void saveLearningAndSkills(String expLearning_1,String expLearning_2,String expLearning_3,String interSkills_1, String interSkills_2,String interSkills_3,String interSkills_4,String extraActivity_1,String extraActivity_2, String extraActivity_3,String  extraActivity_4) throws SQLException{
		String query="update project_data set expLearning_1=?,expLearning_2=?,expLearning_3=?,interSkills_1=?,interSkills_2=?,interSkills_3=?,interSkills_4=?,extraActivity_1=?,extraActivity_2=?,extraActivity_3=?,extraActivity_4=? where username=?";
		PreparedStatement statement=DbProject.connection.prepareStatement(query);
		statement.setString(1,expLearning_1);
		statement.setString(2,expLearning_2);
		statement.setString(3,expLearning_3);
		statement.setString(4,interSkills_1);
		statement.setString(5,interSkills_2);
		statement.setString(6,interSkills_3);
		statement.setString(7,interSkills_4);
		statement.setString(8,extraActivity_1);
		statement.setString(9,extraActivity_2);
		statement.setString(10,extraActivity_3);
		statement.setString(11,extraActivity_4);
		statement.setString(12,LoginController.primaryUser);
		statement.executeUpdate();
	}

	public static void registerDetails(String username,String password,String email) throws SQLException, ClassNotFoundException {
		DbProject.dbConnect();
		String query="insert into project_data(username,password,email_id) values(?,?,?)";
		PreparedStatement statement=DbProject.connection.prepareStatement(query);
		statement.setString(1,username);
		statement.setString(2,password);
		statement.setString(3,email);
		statement.executeUpdate();

	}
	public static ResultSet loadDetails() throws SQLException, ClassNotFoundException {


		String query=" select * from project_data where username= '"+LoginController.primaryUser+"'";
		//String query=" select email_id,name,dob,gender,address,contact,maritialStatus ,hobbies_1,hobbies_2,hobbies_3,hobbies_4 from project_data where username= "+LoginController.primaryUser;
		ResultSet resultSet=DbProject.dbRetrieve(query);

		return resultSet;

		}

	}


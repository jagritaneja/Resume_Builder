package sample;



import sample.controllers.LoginController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.ProjectDao.*;


public class Print {
	public static File resumeFile=null;
	public static void createFile() throws IOException, SQLException, ClassNotFoundException {
		ResultSet resultSet= loadDetails();
		File directory = new File("E:","Generated_Resume");
		directory.mkdir();
		resumeFile=new File(directory, LoginController.primaryUser+"_Resume.doc");
		resumeFile.createNewFile();
		PrintWriter printWriter = new PrintWriter(resumeFile);

			if (resultSet.next()) {
				printWriter.println("                              "+"CURRICULUM VITAE");
				printWriter.println(resultSet.getString(4));
				printWriter.println(resultSet.getString(7));
				printWriter.println(resultSet.getString(8));
				printWriter.println(resultSet.getString(2));
				printWriter.println();
				printWriter.println("EDUCATIONAL QUALIFICATIONS");
				printWriter.println();
				printWriter.println(String.format("%-9s %-10s %-20s %-20s %-15s%n","Year","Degree","School/Institute","Board/University","Percentage"));
				printWriter.println(String.format("%-9s %-10s %-20s %-20s %-15s%n",resultSet.getString(14),resultSet.getString(15),resultSet.getString(16),resultSet.getString(17),resultSet.getString(18)));
				printWriter.println(String.format("%-9s %-10s %-20s %-20s %-15s%n",resultSet.getString(19),resultSet.getString(20),resultSet.getString(21),resultSet.getString(22),resultSet.getString(23)));
				printWriter.println(String.format("%-9s %-10s %-20s %-20s %-15s%n",resultSet.getString(24),resultSet.getString(25),resultSet.getString(26),resultSet.getString(27),resultSet.getString(28)));
				printWriter.println(String.format("%-9s %-10s %-20s %-20s %-15s%n",resultSet.getString(29),resultSet.getString(30),resultSet.getString(31),resultSet.getString(32),resultSet.getString(33)));
				printWriter.println();
				printWriter.println("ACADEMIC ACHIEVEMENTS");
				printWriter.println();
				printWriter.println("* "+resultSet.getString(34));
				printWriter.println("* "+resultSet.getString(35));
				printWriter.println("* "+resultSet.getString(36));
				printWriter.println();
				printWriter.println("SOFTWARE COMPETENCIES");
				printWriter.println();
				printWriter.println("Operating Systems: "+resultSet.getString(37));
				printWriter.println("Languages known:   "+resultSet.getString(38));
				printWriter.println("Database(s) known: "+resultSet.getString(39));
				printWriter.println("Other Interests:   "+resultSet.getString(40));
				printWriter.println();
				printWriter.println("PROJECTS UNDERTAKEN");
				printWriter.println();
				printWriter.println("1. Project Name:         "+resultSet.getString(41));
				printWriter.println("   Environment used:     "+resultSet.getString(44));
				printWriter.println("   Project Description:  "+resultSet.getString(42));
				printWriter.println("   My Role:              "+resultSet.getString(43));
				printWriter.println();
				printWriter.println("2. Project Name:          "+resultSet.getString(45));
				printWriter.println("   Environment used:      "+resultSet.getString(48));
				printWriter.println("   Project Description:   "+resultSet.getString(46));
				printWriter.println("   My Role:               "+resultSet.getString(47));
				printWriter.println();
				printWriter.println("EXPERIENTIAL LEARNING");
				printWriter.println("1. "+resultSet.getString(49));
				printWriter.println("2. "+resultSet.getString(50));
				printWriter.println("3. "+resultSet.getString(51));
				printWriter.println();
				printWriter.println("INTERPERSONAL SKILLS");
				printWriter.println();
				printWriter.println("1. "+resultSet.getString(52));
				printWriter.println("2. "+resultSet.getString(53));
				printWriter.println("3. "+resultSet.getString(54));
				printWriter.println("4. "+resultSet.getString(55));
				printWriter.println();
				printWriter.println("EXTRA-CURRICULAR ACTIVITIES");
				printWriter.println();
				printWriter.println("1. "+resultSet.getString(56));
				printWriter.println("2. "+resultSet.getString(57));
				printWriter.println("3. "+resultSet.getString(58));
				printWriter.println("4. "+resultSet.getString(59));
				printWriter.println("HOBBIES/INTERESTS");
				printWriter.println();
				printWriter.println("1. "+resultSet.getString(10));
				printWriter.println("2. "+resultSet.getString(11));
				printWriter.println("3. "+resultSet.getString(12));
				printWriter.println("4. "+resultSet.getString(13));
				printWriter.println();
				printWriter.println("PERSONAL INFORMATION");
				printWriter.println();
				printWriter.println("DOB:             "+resultSet.getString(5));
				printWriter.println("Gender           "+resultSet.getString(6));
				printWriter.println("Marital Status   "+resultSet.getString(9));
				printWriter.println();
				printWriter.println("DECLARATION");
				printWriter.println();
				printWriter.println("I do hereby declare that the above information is true to the best of my knowledge.");
				printWriter.println(resultSet.getString(4));
				printWriter.println();
				printWriter.println("(Signature)");
				printWriter.println();
				if(resultSet.getBinaryStream(60)!=null) {
					Blob blob = resultSet.getBlob(60);
					InputStream inputStream = blob.getBinaryStream();
					BufferedImage image = ImageIO.read(inputStream);
					ImageIO.write(image,"jpg",new File(directory,"image1.jpg"));
				}
				printWriter.flush();
				printWriter.close();
			}
		}
	public static String showPath(){
		String path=resumeFile.getAbsolutePath();
		return path;

	}
}

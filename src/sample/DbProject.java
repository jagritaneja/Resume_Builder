package sample;

import java.sql.*;

import oracle.jdbc.driver.OracleDriver;

public class DbProject {
	private static final String JDBC_DRIVER="oracle.jdbc.driver.OracleDriver";
	public static Connection connection;
	private static final String url="jdbc:oracle:thin:@//localhost:1521/orcl";
	public static void dbConnect() throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		try {
			connection= DriverManager.getConnection(url,"system","jagrit123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void dbDisconnect() throws SQLException{
		if(connection!=null && !connection.isClosed()){
			connection.close();
		}
	}
	//for insert,delete,update
	public static void dbExecuteQuery(String sqlQuery) throws SQLException, ClassNotFoundException{
		Statement statement=null;
		try {
			statement=connection.createStatement();
			statement.executeUpdate(sqlQuery);
		}catch (SQLException e){
			System.out.println("Error occurred at dbExecuteQuery "+e);
			throw e;
		}

		finally{
			if(statement!=null) {
				statement.close();
			}
			dbDisconnect();
		}

	}
	//To retrieve data
	public static ResultSet dbRetrieve(String sqlQuery) throws ClassNotFoundException,SQLException{
		ResultSet resultSet=null;
		Statement statement2=null;


		try {
			dbConnect();
			statement2=connection.createStatement();
			resultSet=statement2.executeQuery(sqlQuery);

		}catch (SQLException e){
			System.out.println("Error occurred at dbRetrieve "+e);
			throw e;
		}
		return resultSet ;
	}

}

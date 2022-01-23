package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnUtil {
	
	//Restituisce una connessione al database mySql
	public static Connection getMySqlConnection(String dbName, String UserName, String pass) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, UserName, pass);
		
		return conn;
	}
	
	
}

package databaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

//Classe factory che genera una classe Connection in base al tipo di database
public class ConnectionFactory {
	private static final int MYSQL = 1;
	private static final int ORACLE = 2;
	
	public static Connection getConnection(int typeDatabase) throws ClassNotFoundException, SQLException {
		
		//----- AGGIUNGERE CONTROLLI PER AGGIUNGERE ALTRI DATABASE
		switch(typeDatabase) {
		
			//Wardrobe
			case 1:
				return MySqlConnUtil.getMySqlConnection("shirt_db", "admin", "Galadriel2293");
			
			//Book
			case 2:
				return MySqlConnUtil.getMySqlConnection("library_db", "admin", "Galadriel2293");
			default:
				return null;
		}
		
	}
	
	public static void closeConnection(Connection conn) throws SQLException {
		try {
			conn.close();
		}
		catch(Exception exc){}
		
	}
	
	public static void rollBackConnection(Connection conn) throws SQLException {
		try {
			conn.rollback();
		}
		catch(Exception exc){
			conn.rollback();
		}
	}
}

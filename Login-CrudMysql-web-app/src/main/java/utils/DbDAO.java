package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.PreparedStatement;

import bean.Article;
import bean.UserAccount;

//Contiene metodi compiere manipolazioni sul database
public class DbDAO {

	//Ricerca nel db con username e password
	public static UserAccount findUser(Connection conn, String userName, String pass) throws SQLException {

		//se l'utente ricercato e presente nel database, restituiscilo
		String sql = "Select User_Name, Password, Gender from User_Account" + " where User_name = ? and password= ?";

		PreparedStatement selectQuery = (PreparedStatement) conn.prepareStatement(sql);
		selectQuery.setString(1, userName);
		selectQuery.setString(2, pass);
		ResultSet resultQuery = selectQuery.executeQuery();

		//Se l'utente e stato trovato
		if(resultQuery.next()) {
			String gender = resultQuery.getString("Gender");
			UserAccount user = new UserAccount(userName, pass, gender);
			return user;
		}
		return null;
	}

	//Ricerca nel database con solo username(usato per i cookie)
	public static UserAccount findUser(Connection conn, String userName) throws SQLException {

		//se l'utente ricercato e presente nel database, restituiscilo
		String sql = "Select User_Name, password, gender from User_account" + " where user_name = ?";

		PreparedStatement selectQuery = (PreparedStatement) conn.prepareStatement(sql);
		selectQuery.setString(1, userName);
		ResultSet resultQuery = selectQuery.executeQuery();

		//Se l'utente e stato trovato
		if(resultQuery.next()) {
			String gender = resultQuery.getString("Gender");
			String pass = resultQuery.getString("Password");
			UserAccount user = new UserAccount(userName, pass, gender);
			return user;
		}
		return null;
	}
	
	//Restituisce un singolo prodotto
	public static Article readArticle(Connection conn, String code) throws SQLException {
		
		Article article = null;
		String sql = "select * from shirt where shirt_id='" + code + "';";
		PreparedStatement selectQuery = conn.prepareStatement(sql);
		ResultSet resultQuery = selectQuery.executeQuery();
		resultQuery.next();
		int id = resultQuery.getInt("shirt_id");
		String nameArticle = resultQuery.getString("article");
		String color = resultQuery.getString("color");
		String size = resultQuery.getString("size");
		int last_worn = resultQuery.getInt("last_worn");
		article = new Article(nameArticle, color, size, last_worn);
		article.setIdArticle(id);
		return article;
	}
	
	//Restituisce la lista degli articoli non ordinati
	public static List<Article> ReadArticleList(Connection conn) throws SQLException{
		List<Article> listArticle = new ArrayList<Article>();
		String sql = "select * from shirt";
		PreparedStatement selectQuery = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet resultQuery = selectQuery.executeQuery();
		Article newArticle = null;

		//Scorri tutti i record
		while(resultQuery.next()) {
			int id = resultQuery.getInt("shirt_id");
			String nameArticle = resultQuery.getString("article");
			String color = resultQuery.getString("color");
			String size = resultQuery.getString("size");
			int last_worn = resultQuery.getInt("last_worn");
			newArticle = new Article(nameArticle, color, size, last_worn);
			newArticle.setIdArticle(id);
			listArticle.add(newArticle);
		}
		return listArticle;
	}
	
	//Resituisce la lista degli articoli ordinati secondo un campo
	public static List<Article> ReadArticleList(Connection conn, String sort) throws SQLException{
		List<Article> listArticle = new ArrayList<Article>();
		String sql = "select * from shirt order by " + sort;
		PreparedStatement selectQuery = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet resultQuery = selectQuery.executeQuery();
		Article newArticle = null;

		//Scorri tutti i record
		while(resultQuery.next()) {
			int id = resultQuery.getInt("shirt_id");
			String nameArticle = resultQuery.getString("article");
			String color = resultQuery.getString("color");
			String size = resultQuery.getString("size");
			int last_worn = resultQuery.getInt("last_worn");
			newArticle = new Article(nameArticle, color, size, last_worn);
			newArticle.setIdArticle(id);
			listArticle.add(newArticle);
		}
		return listArticle;
	}

	//Restituisce una lista degli articoli ricercati
	public static List<Article> readSearchArticleList(Connection conn, Map<String, Object> mapFields) throws SQLException{
		List<Article> listArticle = new ArrayList<Article>();
		Set<String> setKey = mapFields.keySet();
		Collection<Object> collValues = mapFields.values();

		int id = 0;
		String nameArticle = null;
		String color = null;
		String size = null;
		int last_worn = 0;

		//Composizione query
		String sql = "select * ";
		sql += "from shirt where ";
		Iterator<Object> iterValues = collValues.iterator();
		Iterator<String>iterKey = setKey.iterator();

		//composizione valori where
		do {
			String key = iterKey.next();
			Object value = iterValues.next();

			sql += "(" + key + "=" + "'" + value + "'" + ")";

			//controlla se ci sono altri campi
			if(iterKey.hasNext()) {
				sql += " and ";
			}
			else {
				sql += ";";
			}

		}while(iterKey.hasNext());

		PreparedStatement selectQuery = conn.prepareStatement(sql);
		ResultSet result = selectQuery.executeQuery();

		//Creazione lista di articoli SELEZIONATI

		//Scorri tutti i record
		while(result.next()) {
			id = result.getInt("shirt_id");
			nameArticle = result.getString("article");
			color = result.getString("color");
			size = result.getString("size");
			last_worn = result.getInt("last_worn");
			Article newArticle = new Article(nameArticle, color, size, last_worn);
			newArticle.setIdArticle(id);
			listArticle.add(newArticle);
		}
		return listArticle;
	}
	//Inserisce un articolo nel db
	public static void createArticle(Connection conn, Article article) throws SQLException {
		String sql = "insert into shirt(article, color, size, last_worn) values (?,?,?,?);";

		PreparedStatement insertQuery = conn.prepareStatement(sql);
		insertQuery.setString(1, article.getNameArticle());
		insertQuery.setString(2, article.getColor());
		insertQuery.setString(3, article.getSize());
		insertQuery.setInt(4, article.getLast_worn());
		insertQuery.executeUpdate();
	}
	
	//Aggiorna un record con i dati del nuovo articolo
	public static void updateArticle(Connection conn, Article article) throws SQLException {
		String sql = "update shirt set article = ?, color = ?, size = ?, last_worn = ? where shirt_id = ?;";
		
		PreparedStatement update = conn.prepareStatement(sql);
		update.setString(1, article.nameArticle);
		update.setString(2, article.color);
		update.setString(3, article.size);
		update.setInt(4, article.last_worn);
		update.setInt(5, article.idArticle);
		update.executeUpdate();
		
		
	}
	
	//elimina il record con l'id desderato
	public static void deleteArticle(Connection conn, String code) throws SQLException {
		String sql = "delete from shirt where shirt_id=?";
		
		PreparedStatement delete = conn.prepareStatement(sql);
		delete.setString(1, code);
		delete.executeUpdate();
	}
}
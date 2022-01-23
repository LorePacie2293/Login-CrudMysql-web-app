package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bean.Article;
import bean.Book;

public class BookDAO {

	//Restituisce un singolo prodotto
	public static Book readBook(Connection conn, String code) throws SQLException {

		Book book = null;
		String sql = "select * from book where id_book='" + code + "';";
		PreparedStatement selectQuery = conn.prepareStatement(sql);
		ResultSet resultQuery = selectQuery.executeQuery();
		resultQuery.next();
		int id = resultQuery.getInt("id_book");
		String nameBook = resultQuery.getString("title_book");
		String firstName = resultQuery.getString("first_name_author");
		String lastName = resultQuery.getString("last_name_author");
		String released_date = resultQuery.getString("release_year");
		int stock = resultQuery.getInt("stock_quantity");
		int pages = resultQuery.getInt("pages");
		book = new Book(nameBook, firstName, lastName, released_date, stock, pages);
		book.setId_book(id);
		return book;
	}

	//Restituisce la lista dei libri
	public static List<Book> ReadBookList(Connection conn) throws SQLException{
		List<Book> listArticle = new ArrayList<Book>();
		String sql = "select * from book";
		PreparedStatement selectQuery = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet resultQuery = selectQuery.executeQuery();
		Book newBook = null;

		//Scorri tutti i record
		while(resultQuery.next()) {
			int id = resultQuery.getInt("id_book");
			String nameBook = resultQuery.getString("title_book");
			String firstName = resultQuery.getString("first_name_author");
			String lastName = resultQuery.getString("last_name_author");
			String releaseYear = resultQuery.getString("release_year");
			int stockQuantity = resultQuery.getInt("stock_quantity");
			int pages = resultQuery.getInt("pages");

			newBook = new Book(nameBook, firstName, lastName, releaseYear, stockQuantity, pages);
			newBook.setId_book(id);
			listArticle.add(newBook);
		}
		return listArticle;
	}

	//Restituisce una lista degli articoli ricercati
	public static List<Book> readSearchBookList(Connection conn, Map<String, Object> mapFields) throws SQLException{
		List<Book> listBook = new ArrayList<Book>();
		Set<String> setKey = mapFields.keySet();
		Collection<Object> collValues = mapFields.values();

		int id = 0;
		String nameBook = null;
		String authorName = null;
		String authorLast = null;
		String release = null;
		Integer stock = null;
		Integer pages = null;


		//Composizione query
		String sql = "select * ";
		sql += "from book where ";
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
			id = result.getInt("id_book");
			nameBook = result.getString("title_book");
			authorName = result.getString("first_name_author");
			authorLast = result.getString("last_name_author");
			release = result.getString("release_year");
			stock = result.getInt("stock_quantity");
			pages = result.getInt("pages");

			Book newBook = new Book(nameBook, authorName, authorLast, release, stock, pages);
			newBook.setId_book(id);
			listBook.add(newBook);
		}
		return listBook;
	}

	//Inserisce un libro nel db
	public static void createBook(Connection conn, Book book) throws SQLException {
		String sql = "insert into book(title_book, first_name_author, last_name_author, release_year, stock_quantity, pages) values (?,?,?,?,?,?);";

		PreparedStatement insertQuery = conn.prepareStatement(sql);
		insertQuery.setString(1, book.getName_book());
		insertQuery.setString(2, book.getFirst_name());
		insertQuery.setString(3, book.getLast_name());
		insertQuery.setString(4, book.getYear_released());
		insertQuery.setInt(5, book.getStock_quantity());
		insertQuery.setInt(6, book.getPages());
		insertQuery.executeUpdate();
	}

	//Aggiorna un record con i dati del nuovo book
	public static void updateBook(Connection conn, Book book) throws SQLException {
		String sql = "update book set title_book = ?, first_name_author = ?, last_name_author = ?, release_year = ?, stock_quantity = ?, pages = ? where id_book = ?;";

		PreparedStatement update = conn.prepareStatement(sql);
		update.setString(1, book.name_book);
		update.setString(2, book.first_name);
		update.setString(3, book.last_name);
		update.setString(4, book.year_released);
		update.setInt(5, book.stock_quantity);
		update.setInt(6, book.pages);
		update.setInt(7, book.id_book);
		update.executeUpdate();


	}

	//elimina il record con l'id desderato
	public static void deleteBook(Connection conn, String code) throws SQLException {
		String sql = "delete from book where id_book=?";

		PreparedStatement delete = conn.prepareStatement(sql);
		delete.setString(1, code);
		delete.executeUpdate();
	}
}

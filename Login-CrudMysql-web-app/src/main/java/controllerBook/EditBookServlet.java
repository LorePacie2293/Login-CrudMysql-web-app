package controllerBook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Article;
import bean.Book;
import utils.BookDAO;
import utils.DbDAO;
import utils.MyUtils;

//Modifica l'articolo corrente(passato tramite parametro code).
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getRequestConnection(request);
		String idBook = request.getParameter("code");
		String errString = null;
		Book newBook = null;
		
		try {
			newBook = BookDAO.readBook(conn, idBook);
		}
		catch(SQLException e) {
			e.printStackTrace();
			errString = e.getMessage();
		}
		
		//se l'articolo non e stato trovato torna alla home
		if(newBook == null && errString != null) {
			request.setAttribute("errorString", errString);
			request.getRequestDispatcher("/homeBook").forward(request, response);
			return;
		}
		
		request.setAttribute("errorString", errString);
		request.setAttribute("book", newBook);
		request.setAttribute("crud", 3);
		
		request.getRequestDispatcher("/homeBook").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getRequestConnection(request);
		
		//cambiare i campi
		int id = Integer.parseInt(request.getParameter("code"));
		String titleBook = request.getParameter("title_book");
		String firstName = request.getParameter("first_name_author");
		String lastName = request.getParameter("last_name_author");
		String releaseYear = request.getParameter("release_year");
		Integer stock = null;
		Integer pages = null;
		String errString = null;
		Book newBook = null;
		
		try {
			stock = Integer.parseInt(request.getParameter("stock_quantity"));
			
			try {
				pages = Integer.parseInt(request.getParameter("pages"));
				newBook = new Book(titleBook, firstName, lastName, releaseYear, stock, pages);
				newBook.setId_book(id);
			}
			catch(NumberFormatException e){}
			
			
		}
		catch(NumberFormatException e) {}
		
		try {
			BookDAO.updateBook(conn, newBook);
		}
		catch(SQLException e) {
			e.printStackTrace();
			errString = e.getMessage();
		}
		
		//In caso di errore reinserire i dati nel form
		if(errString != null) {
			request.setAttribute("errString", errString);
			request.setAttribute("book", newBook);
			request.setAttribute("crud", 3);
			request.getRequestDispatcher("/homeBook").forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/homeBook");
		}
	}

}

package controllerBook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bean.Book;
import utils.BookDAO;

import utils.MyUtils;

//Crea un'articolo e lo inserisce nel db
public class CreateBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("crud", 1);
		request.getRequestDispatcher("/homeBook").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getRequestConnection(request);
		String errString = null;
		Book newBook = null;
		String nameBook = null;
		String firstName = null;
		String lastName = null;
		String releaseYear = null;
		Integer stockQuantity = null;
		Integer pages = null;
		
		//Creazione oggetto di cui i valori verrano inseriti all'interno del db
		try {
			
			nameBook = request.getParameter("title_book");
			firstName = request.getParameter("first_name_author");
			lastName = request.getParameter("last_name_author");
			releaseYear = request.getParameter("release_year");
			stockQuantity = Integer.parseInt(request.getParameter("stock_quantity"));
			pages = Integer.parseInt(request.getParameter("pages"));
		}
		catch(Exception e) {}
		
		newBook = new Book(nameBook, firstName, lastName, releaseYear, stockQuantity, pages);
		
		if(nameBook.length() == 0 || firstName.length() == 0 || lastName.length() == 0 || releaseYear == null || stockQuantity == null || pages == null) {
			errString = "Inserisci le credenziali";
		}
		
		//Se le credenziali sono state inserite
		if(errString == null) {
			try {
				BookDAO.createBook(conn, newBook);
			}
			catch(SQLException exc) {
				exc.printStackTrace();
				errString = exc.getMessage();
			}
			
			//In caso di errori nell'inserimento dell'articolo torna al form di creazione dell'articolo
			if(errString != null) {
				request.setAttribute("crud", 1);
				request.setAttribute("errorString", errString);
				request.getRequestDispatcher("/home").forward(request, response);
			}
			else {
				request.setAttribute("crud", null);
				response.sendRedirect(request.getContextPath() + "/homeBook");
			}
		}
		else {
			request.setAttribute("crud", 1);
			request.setAttribute("errorString", errString);
			request.getRequestDispatcher("/home").forward(request, response);
		}
	}

}

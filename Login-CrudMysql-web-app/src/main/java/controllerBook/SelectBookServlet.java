package controllerBook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//effettua una ricerca nel db e restituisce i record desiderati
public class SelectBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("crud", 2);
		request.getRequestDispatcher("/homeBook").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int MIN_FIELDS = 1;
		List<Book> listBook = new ArrayList<>();
		Map <String, Object> mapFields= new HashMap<String, Object>();
		Connection conn = MyUtils.getRequestConnection(request);
		Boolean isSearch = false;
		String errString = null;
		int contInsertFields = 0;
		Integer release = null, stock = null, pages = null;
		
		//Crea una map che contiene tutti i campi inseriti nel form
		//Contollo dei campi inseriti
		if(request.getParameter("nameBook").length() > 0) {
			mapFields.put("title_book", request.getParameter("nameBook"));
			contInsertFields++;
		}
		
		if(request.getParameter("authorName").length() > 0) {
			mapFields.put("first_name_author", request.getParameter("authorName"));
			contInsertFields++;
		}
		
		if(request.getParameter("authorLastName").length() > 0) {
			mapFields.put("last_name_author", request.getParameter("authorLastName"));
			contInsertFields++;
		}
		
		try {
			release = Integer.parseInt(request.getParameter("release_year"));
			mapFields.put("release_year", request.getParameter("release_year"));
			contInsertFields++;
		}
		catch(Exception e) {}
		
		try {
			stock = Integer.parseInt(request.getParameter("stock_quantity"));
			mapFields.put("stock_quantity", request.getParameter("stock_quantity"));
			contInsertFields++;
		}
		catch(Exception e) {}
		
		try {
			pages = Integer.parseInt(request.getParameter("pages"));
			mapFields.put("pages", request.getParameter("pages"));
			contInsertFields++;
		}
		catch(Exception e) {}
	
		//Se e stato inserito almeno 1 campo
		if(contInsertFields >= 1) {
			try {
				listBook = BookDAO.readSearchBookList(conn, mapFields);
			}
			catch(SQLException e) {
				e.printStackTrace();
				errString = e.getMessage();
			}
			
			//In caso di errore sql
			if(errString != null) {
				request.setAttribute("crud", 2);
				request.setAttribute("errorString", errString);
				request.getRequestDispatcher("/homeBook").forward(request, response);
			}
			
			//In caso di record inesistenti
			else if(listBook.size() == 0) {
				errString = "Ricerca fallita";
				request.setAttribute("crud", 2);
				request.setAttribute("errorString", errString);
				request.getRequestDispatcher("/homeBook").forward(request, response);
			}
			else {
				request.setAttribute("crud", 2);
				request.setAttribute("search", true);
				
				request.setAttribute("errorString", errString);
				request.setAttribute("bookSearch", listBook);
				request.getRequestDispatcher("/homeBook").forward(request, response);

			}
		}
		
		//Se non e stato inserito nessun campo
		else {
			errString = "Inserisci almeno 1 campo di ricerca";
			request.setAttribute("crud", 2);
			request.setAttribute("errorString", errString);
			request.getRequestDispatcher("/homeBook").forward(request, response);
		}
	
	}

}

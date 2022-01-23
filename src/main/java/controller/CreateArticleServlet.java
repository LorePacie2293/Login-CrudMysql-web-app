package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Article;
import utils.DbDAO;
import utils.MyUtils;

//Crea un'articolo e lo inserisce nel db
public class CreateArticleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("crud", 1);
		request.getRequestDispatcher("/home").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getRequestConnection(request);
		String errString = null;
		Article newArticle = null;
		
		int id = 0;
		String name = null;
		String color = null;
		String size = null;
		Integer last_worn = 0;
		
		//Creazione oggetto di cui i valori verrano inseriti all'interno del db
		try {
			
			name = request.getParameter("nameArticle");
			color = request.getParameter("color");
			size = request.getParameter("size");
			last_worn = Integer.parseInt(request.getParameter("last_worn"));
		}
		catch(Exception e) {}
		
		newArticle = new Article(name, color, size, last_worn);

		if(name.length() == 0 || color.length() == 0 || size.length() == 0 || last_worn == null) {
			errString = "Inserisci le credenziali";
			
		}
		
		//Se le credenziali sono state inserite
		if(errString == null) {
			try {
				DbDAO.createArticle(conn, newArticle);
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
				response.sendRedirect(request.getContextPath() + "/home");
			}
		}
		else {
			request.setAttribute("crud", 1);
			request.setAttribute("errorString", errString);
			request.getRequestDispatcher("/home").forward(request, response);
		}
	}

}

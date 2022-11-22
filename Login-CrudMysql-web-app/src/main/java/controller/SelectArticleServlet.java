package controller;

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
import utils.DbDAO;
import utils.MyUtils;

//effettua una ricerca nel db e restituisce i record desiderati
public class SelectArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("crud", 2);
		request.getRequestDispatcher("/home").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int MIN_FIELDS = 1;
		List<Article> listArticle = new ArrayList<>();
		Map <String, Object> mapFields= new HashMap<String, Object>();
		Connection conn = MyUtils.getRequestConnection(request);
		Boolean isSearch = false;
		String errString = null;
		int contInsertFields = 0;
		
		//Crea una map che contiene tutti i campi inseriti nel form
		//Contollo dei campi inseriti
		if(request.getParameter("nameArticle").length() > 0) {
			mapFields.put("article", request.getParameter("nameArticle"));
			contInsertFields++;
		}
		
		if(request.getParameter("color").length() > 0) {
			mapFields.put("color", request.getParameter("color"));
			contInsertFields++;
		}
		
		if(request.getParameter("size").length() > 0) {
			mapFields.put("size", request.getParameter("size"));
			contInsertFields++;
		}
		
		Integer last_worn = -1;
		try {
			last_worn = Integer.parseInt(request.getParameter("last_worn"));
		}
		catch(Exception e) {}
		if(last_worn != -1) {
			mapFields.put("last_worn", request.getParameter("last_worn"));
			contInsertFields++;
		}
		
		//Se e stato inserito almeno 1 campo
		if(contInsertFields >= 1) {
			try {
				listArticle = DbDAO.readSearchArticleList(conn, mapFields);
			}
			catch(SQLException e) {
				e.printStackTrace();
				errString = e.getMessage();
			}
			
			//In caso di errore sql
			if(errString != null) {
				request.setAttribute("crud", 2);
				request.setAttribute("errorString", errString);
				request.getRequestDispatcher("/home").forward(request, response);
			}
			
			//In caso di record inesistenti
			else if(listArticle.size() == 0) {
				errString = "Ricerca fallita";
				request.setAttribute("crud", 2);
				request.setAttribute("errorString", errString);
				request.getRequestDispatcher("/home").forward(request, response);
			}
			else {
				request.setAttribute("crud", 2);
				request.setAttribute("search", true);
				
				request.setAttribute("errorString", errString);
				request.setAttribute("articleSearch", listArticle);
				request.getRequestDispatcher("/home").forward(request, response);

			}
		}
		
		//Se non e stato inserito nessun campo
		else {
			errString = "Inserisci almeno 1 campo di ricerca";
			request.setAttribute("crud", 2);
			request.setAttribute("errorString", errString);
			request.getRequestDispatcher("/home").forward(request, response);
		}
	
	}

}

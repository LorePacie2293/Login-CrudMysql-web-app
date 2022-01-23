package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Article;
import utils.DbDAO;
import utils.MyUtils;

//Modifica l'articolo corrente(passato tramite parametro code).
public class EditArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getRequestConnection(request);
		String idArticle = request.getParameter("code");
		String errString = null;
		Article newArticle = null;
		
		try {
			newArticle = DbDAO.readArticle(conn, idArticle);
		}
		catch(SQLException e) {
			e.printStackTrace();
			errString = e.getMessage();
		}
		
		//se l'articolo non e stato trovato torna alla home
		if(newArticle == null && errString != null) {
			request.setAttribute("errorString", errString);
			request.getRequestDispatcher("/home").forward(request, response);
			return;
		}
		
		request.setAttribute("errorString", errString);
		request.setAttribute("article", newArticle);
		request.setAttribute("crud", 3);
		
		request.getRequestDispatcher("/home").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getRequestConnection(request);
		
		int id = Integer.parseInt(request.getParameter("code"));
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		int last_worn = 0;
		
		try {
			last_worn = Integer.parseInt(request.getParameter("last_worn"));
		}
		catch(NumberFormatException e) {
			last_worn = 100;
		}
		
		String errString = null;
		Article newArticle = new Article(name, color, size, last_worn);
		newArticle.setIdArticle(id);
		
		try {
			DbDAO.updateArticle(conn, newArticle);
		}
		catch(SQLException e) {
			e.printStackTrace();
			errString = e.getMessage();
		}
		
		//In caso di errore reinserire i dati nel form
		if(errString != null) {
			request.setAttribute("errString", errString);
			request.setAttribute("article", newArticle);
			request.setAttribute("crud", 3);
			request.getRequestDispatcher("/home").forward(request, response);
		}
		else {
			request.setAttribute("crud", null);
			request.getRequestDispatcher("/home").forward(request, response);
		}
	}

}

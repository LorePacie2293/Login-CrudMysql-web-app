package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Article;
import bean.UserAccount;
import utils.DbDAO;
import utils.MyUtils;

//Servlet home che indirizza alla jsp homeView
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//gestione login tramite sessione
		HttpSession session = request.getSession();
		UserAccount user = MyUtils.getStoreLoginUser(session);
		
		//Se l'utente non e loggato nella sessione
		if(user == null) {
			response.sendRedirect("loginView.jsp");
			return;
		}
		
		Connection conn = MyUtils.getRequestConnection(request);
		String strError = (String) request.getAttribute("errorString");
		List<Article> listArticle = null;
		
		try {
			
			//controlla se ci sono ordinamenti
			if(request.getParameter("sort") != null) {
				listArticle = DbDAO.ReadArticleList(conn, request.getParameter("sort").toString());
			}
			else {
				listArticle = DbDAO.ReadArticleList(conn);
			}
			
		}
		catch(SQLException exc){
			strError = exc.getMessage();
			exc.printStackTrace();
		}
		
		request.setAttribute("errorString", strError);
		request.setAttribute("articleList", listArticle);
		
		//dispatcher a homeView
		request.getRequestDispatcher("homeView.jsp").forward(request, response);
		request.getSession().removeAttribute("crud");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

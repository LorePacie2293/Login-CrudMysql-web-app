package controllerBook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Article;
import bean.Book;
import databaseConnection.ConnectionFactory;
import utils.BookDAO;
import utils.DbDAO;
import utils.MyUtils;

//Servlet home che indirizza alla jsp homeView
public class HomeBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		try {
			conn = ConnectionFactory.getConnection(2);
			MyUtils.storeRequestConnection(request, conn);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		String strError = (String) request.getAttribute("errorString");
		List<Book> listArticle = null;
		
		try {
			listArticle = BookDAO.ReadBookList(conn);
		}
		catch(SQLException exc){
			strError = exc.getMessage();
			exc.printStackTrace();
		}
		
		request.setAttribute("errorString", strError);
		request.setAttribute("bookList", listArticle);
		
		//dispatcher a homeView
		request.getRequestDispatcher("homeBook.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

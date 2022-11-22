package controllerBook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BookDAO;
import utils.DbDAO;
import utils.MyUtils;

//Elimina un'articolo nel database
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getRequestConnection(request);
		String code = request.getParameter("code");
		String errString = null;

		try {
			BookDAO.deleteBook(conn, code);
		}
		catch(SQLException e){
			e.printStackTrace();
			errString = e.getMessage();
		}

		if(errString != null) {
			request.setAttribute("errorString", errString);
			request.setAttribute("crud", null);
			request.getRequestDispatcher("/homeBook").forward(request, response);
		}
		else {
			request.setAttribute("crud", null);
			response.sendRedirect(request.getContextPath() + "/homeBook");
		}
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

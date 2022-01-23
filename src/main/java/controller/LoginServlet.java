package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;

import bean.UserAccount;
import databaseConnection.MySqlConnUtil;
import utils.DbDAO;
import utils.MyUtils;

//Servlet che gestisce il login dell'utente
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    //Se e una richiesta get indirizza alla login JSP
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
	request.getRequestDispatcher(request.getContextPath() + "/wardrobe/loginView.jsp").forward(request, response);
	
	}

	//Se e una richiesta post 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Ottieni i dati dal form di login
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		boolean remember = false;
		
		//Controllo remember
		if(request.getParameter("rememberMe").equals("Y")){
			remember = true;
		}
		
		//Stringa di errore
		boolean hasError = false;
		String stringError = null;
		
		UserAccount user = null;
		
		//VERIFICA ERRORI
		//Se i campi del form non sono stati inseriti
		if(userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			stringError = "Inserisci le credenziali di accesso";
		}
		
		//Altrimenti ottieni la connessione con il db
		else {
			Connection conn = null;
			try {
				conn = (Connection) MySqlConnUtil.getMySqlConnection("firstDb", "admin", "Galadriel2293");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Controlla se l'utente e presente nel db
			try {
				user = DbDAO.findUser(conn, userName, password);
				
				//Se l'utente non e presente nel database
				if(user == null) {
					hasError = true;
					stringError = "Credenziali non valide";
				}
			}
			catch(SQLException exc) {
				exc.printStackTrace();
				hasError = true;
				stringError = exc.getMessage();
			}
		}
		
		//se ci sono errori
		if(hasError) {
			request.setAttribute("strError", stringError);
			request.setAttribute("user", user);
			
			//Torna alla loginView
			request.getRequestDispatcher("loginView.jsp").forward(request, response);
		}
		
		//Se non ci sono errori salva l'utente nella sessione e anche se serve nel cookie
		else {
			
			//sessione
			HttpSession session = request.getSession();
			MyUtils.storeLogineduser(session, user);
			
			//Cookie
			//se l'utente a selezionato ricordami
			if(remember) {
				MyUtils.storeCookieLogingUser(response, user);
			}
			else {
				MyUtils.deleteCoikieLoginUser(response);
			}
			
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}

}

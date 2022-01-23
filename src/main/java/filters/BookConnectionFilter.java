package filters;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import java.sql.Connection;

import databaseConnection.ConnectionFactory;
import databaseConnection.MySqlConnUtil;
import utils.MyUtils;

//Filtro che verifica se la risorsa richiesta sia una servlet collegata ad un database, in modo da evitare di aprire connessioni 
//jdbc per altri tipi di risorse(css, image, ecc..)


public class BookConnectionFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

		//conversione ServletRequest a HttpServletRequest
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		//Se la richiesta e diretta ad una servlet o a una risorsa SPECIALE(servlet, JSP, database, ecc..) crea una connessione JDBC
		if(this.isRequestServlet(httpRequest)) {

			Connection conn = null;

			try {

				//ottieni connessione per database mySql
				conn = ConnectionFactory.getConnection(2);

				//Imposta l'auto commit a false, in modo da poter inserire piu di una istruzione SQL alla volta(transazioni)
				conn.setAutoCommit(false);

				//Aggiungi alla richiesta http la connessione con il db
				MyUtils.storeRequestConnection(httpRequest, conn);

				//procedi con la risorsa successiva
				chain.doFilter(httpRequest, response);

				//Avvia la transazione
				conn.commit();
			}
			catch(Exception  exc) {
				exc.printStackTrace();

				try {
					
					//In caso di errore ripristina il db allo 
					ConnectionFactory.rollBackConnection(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		//Altrimenti passa alla risorsa successiva senza creare una connessione JDBC
		else {
			chain.doFilter(httpRequest, response);
		}
	}

	//Controlla che la richiesta sia indirizzata ad una servlet
	private boolean isRequestServlet(HttpServletRequest request) {

		//Percorso della RICHIESTA
		String servletPath = request.getServletPath();		//Url servlet
		String infoPath = request.getPathInfo();			//Informazioni aggiuntive: altre servlet, jsp, db, ecc...
		String urlPattern = servletPath;

		//Se ci sono altre risorse collegate alla servlet, imposta il filtro a filtrarle tutte
		if(infoPath != null) {
			urlPattern += "/*";
		}

		//Map che contiene per ogni servlet la sua registrazione(ServletRegistration:  serve a configurare ulteriolmente una servlet)
		Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext().getServletRegistrations();

		//Collection che contiene la lista di tutte le servlet registrate nell'attuale servletContext
		Collection<? extends ServletRegistration> servletList = servletRegistrations.values();

		//Scorrimento delle servlet
		for(ServletRegistration reg : servletList) {

			//Lista delle mappature presenti per l'attuale ServletRegistration
			Collection<String> servletMappingList = reg.getMappings();

			//Se la mappatura della request e presente nella lista di mappature,
			//allora si ha la conferma che la richista sta puntando ad una servlet
			if(servletMappingList.contains(urlPattern)) {
				return true;
			}
		}
		return false;
	}
}


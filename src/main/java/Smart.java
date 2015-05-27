

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
//import org.jdom2.*;
/**
 * Servlet implementation class Smart
 */
@WebServlet("/Smart")
public class Smart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	  private Statement statement = null;
	  private java.sql.PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;
	  
	  String Host;
	  String Port;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Smart() {
        super();
    	Host = System.getenv("$OPENSHIFT_MYSQL_DB_HOST");
        Port = System.getenv("$OPENSHIFT_MYSQL_DB_PORT");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("Baza");
	
		try {
			response.getWriter().println(readDataBase());
			response.getWriter().println(Host);
			response.getWriter().println(Port);

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().println(e.getMessage());
		}
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("Baza");
		response.getWriter().println(Host);
		response.getWriter().println(Port);
		try {
			response.getWriter().println(readDataBase());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().println(e.getMessage());
		}


	}
	public String readDataBase() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      String $OPENSHIFT_MYSQL_DB_PORT = "3306" ;
		String $OPENSHIFT_MYSQL_DB_HOST = Host;
		// Setup the connection with the DB
	      connect = DriverManager.getConnection("jdbc:mysql://127.13.19.130:"+$OPENSHIFT_MYSQL_DB_PORT+"/expert","admincsPQAMd","KRjPCPMwYSY8");

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement.executeQuery("select * from telefony");
	          
	     return resultSet.getString(1);
	      
	      
	    } catch (Exception e) {
	      throw e;
	    } finally {
	      
	    }
	}
	

}

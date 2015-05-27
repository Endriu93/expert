

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Smart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
response.getWriter().println("HHHUj");
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("HHHUj");
		try {
			response.getWriter().println(readDataBase());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public String readDataBase() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager.getConnection("mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/expert","admincsPQAMd","KRjPCPMwYSY8");

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



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.jdom2.*;
/**
 * Servlet implementation class Smart
 */
@WebServlet("/Smart")
public class Smart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		response.addHeader("Access-Control-Allow-Origin", "*");//
		response.addHeader("Access-Control-Allow-Methods", "POST,GET");//
		//response.addHeader("Access-Control-Allow-Methods", "GET,POST");//
		response.addHeader("Access-Control-Allow-Headers", "*");//
	    response.setHeader("Access-Control-Max-Age", "6000");
	    response.setHeader("Access-Control-Allow-Credentials", "true");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("HHHUj");
		
		response.addHeader("Access-Control-Allow-Origin", "*");//
		response.addHeader("Access-Control-Allow-Methods", "POST,GET");//
		//response.addHeader("Access-Control-Allow-Methods", "GET,POST");//
		response.addHeader("Access-Control-Allow-Headers", "*");//
	    response.setHeader("Access-Control-Max-Age", "6000");
	    response.setHeader("Access-Control-Allow-Credentials", "true");


	}

}

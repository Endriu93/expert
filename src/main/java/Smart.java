

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
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
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		try {
			response.getWriter().println(readDataBase());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().println(e.getMessage());
		}
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			response.getWriter().println(readDataBase());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().println(e.getMessage());
		}


	}
	public String readDataBase() throws Exception {
	    
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      connect = DriverManager.getConnection("jdbc:mysql://127.13.19.130:3306/expert","admincsPQAMd","KRjPCPMwYSY8");
	      statement = connect.createStatement();
 	      resultSet = statement.executeQuery("select * from telefony");
	      
	      return DbToXML(resultSet);
	      
	    
	}
	private String DbToXML(ResultSet rs) throws SQLException
	{
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		int ColNum = rsmd.getColumnCount();
		Document root = new Document();
		String TableName = rsmd.getTableName(1);
		Element Root = new Element(TableName);
		root.addContent(Root);
		String[] ColNames = new String[ColNum];
		
		for(int i=0; i<ColNum; ++i)
		{
			ColNames[i] = rsmd.getColumnName(i+1);
		}
		
		
		while(rs.next())
		{
			Element phone = new Element("phone");
			for(int i=0; i< ColNum; ++i)
			{
				Element tmp = new Element(ColNames[i]);
				String tmp2 = new String(rs.getString(i+1));
				tmp.addContent(tmp2);
				phone.addContent(tmp);
			}
			Root.addContent(phone);
		}
		XMLOutputter out = new XMLOutputter();
		
		out.setFormat(Format.getPrettyFormat());
		
		return out.outputString(root);
		
	}
	

}

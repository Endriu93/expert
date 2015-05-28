import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class Database {
	private Connection connect = null;
	private Statement statement = null;
	private java.sql.PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public Database()
	{
		 // This will load the MySQL driver, each DB has its own driver
	      try {
			Class.forName("com.mysql.jdbc.Driver");
		   connect = DriverManager.getConnection("jdbc:mysql://127.13.19.130:3306/expert","admincsPQAMd","KRjPCPMwYSY8");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getAllPhones() throws Exception {
	    
	   
	      statement = connect.createStatement();
	      resultSet = statement.executeQuery("select * from Phones");
	      connect.close();
	      return DbToXML(resultSet);
	      
	}
	public String getSpecifiedPhones(String query) throws SQLException
	{
		  statement = connect.createStatement();
	      resultSet = statement.executeQuery(query);
	      connect.close();
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

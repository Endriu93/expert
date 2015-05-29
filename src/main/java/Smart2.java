

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Servlet implementation class Smart2
 */
@WebServlet("/Smart2")
public class Smart2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Database db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Smart2() {
        super();
        // TODO Auto-generated constructor stub
        db = new Database();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String content = getRequestContent(request);
		
		try {
			if(db.connect == null) db.connect();
			String query = createQueryFromXml(content);
			response.getWriter().println(query);
			response.getWriter().println(db.getSpecifiedPhones(query));
			db.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().println(e.getMessage());
		}
	}
	private String getRequestContent(HttpServletRequest request) throws IOException
	{
		String line;
		StringBuilder jb = new StringBuilder();
		  BufferedReader reader = request.getReader();
		  while ((line = reader.readLine()) != null)
	      jb.append(line);
		  
		return jb.toString();
	}
	private String createQueryFromXml(String xml) throws JDOMException, IOException
	{
		SAXBuilder builder = new SAXBuilder();
		Map<String,String> mapa = new HashMap<String,String>();
		String ParamName;
		String value;
		StringBuilder b = new StringBuilder();
		b.append("select * from Phones where ");
		String query = " ";
		
	 
		InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		Document document = builder.build(stream);
	 
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren();
	 
			for (int i = 0; i < list.size(); i++) {
				if(i!= 0) b.append(" and ");
			   Element node = (Element) list.get(i);
			   ParamName = node.getName();
			   value = node.getText();
			   query = addToQuery(ParamName, value);
			    b.append(query);
			 
	 
			}
			return b.toString();
	 
		 
	}
	private String addToQuery(String ParamName, String Value)
	{
		String out;
		
		if(ParamName.equals("minscreensize")) out = " Display > "+Value; 
		else
			if(ParamName.equals("minprice")) out = " Price > "+Value; 
			else
				if(ParamName.equals("maxprice")) out = " Price < "+Value; 
				else
					if(ParamName.equals("ram")) out = " Ram > "+Value; 
					else
						if(ParamName.equals("storage")) out = " Storage > "+Value; 
						else
		if(ParamName.equals("maxscreensize")) out = " Display < "+Value; 
		else
		if(ParamName.equals("batterysize")) out = " Battery > "+Value; 
		else
		if(ParamName.equals("minproc")) out = " Processor > "+Value;
		else
			if(ParamName.equals("mincoresnum")) out = " Cores > "+Value;
		else
			if(ParamName.equals("camera")) out = " Camera_Res > "+Value;
		else
			if(ParamName.equals("wifi"))
			{
				if(Value.equals("1"))
				out = " Wifi like  'YES' ";
				//else if(Value.equals("0")) out = " Wifi like 'NO' ";
				else out = " Cores > 0 ";
			}
			else
				if(ParamName.equals("android"))
				{
					if(Value.equals("1"))
					out = " OS_Name like  'Android' ";
					else out = " Cores > 0 ";
				}
				else
					if(ParamName.equals("windowsphone"))
					{
						if(Value.equals("1"))
						out = " OS_Name like  'W%' ";
						else out = " Cores > 0 ";
					}
					else
						if(ParamName.equals("ios"))
						{
							if(Value.equals("1"))
							out = " OS_Name like  'iOS' ";
							else out = " Cores > 0 ";
						}
						else
							if(ParamName.equals("lte"))
							{
								if(Value.equals("1"))
									out = " LTE like  'YES' ";
					//				else if(Value.equals("0")) out = " LTE like 'NO' ";
									else out = " Cores > 0 ";
							}
							else
								if(ParamName.equals("sd"))
								{
									if(Value.equals("1"))
										out = " SD like  'YES' ";
						//				else if(Value.equals("0")) out = " SD like 'NO' ";
										else out = " Cores > 0 ";
								}else
									if(ParamName.equals("gps"))
									{
										if(Value.equals("1"))
											out = " GPS like  'YES' ";
							//				else if(Value.equals("0")) out = " GPS like 'NO' ";
											else out = " Cores > 0 ";
									}
		else out = " Cores > 0 ";
		
		
		
		return out;
	}
}

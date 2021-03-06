

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
			boolean valid = isXmlValid(content);
			if(valid == false) response.getWriter().println(StringIfInvalid());
			else
			{
			String query = createQueryFromXml(content);
			String phonesXml = db.getSpecifiedPhones(query);
			//response.getWriter().println(phonesXml); //REMOVE

			ArrayList<String> criteria = getCriteria(content);
			//response.getWriter().println(criteria.toString()); //REMOVE

			ArrayList<String> Ids = getPhonesID(phonesXml, criteria,response.getWriter());
			String FinalQuery = addIDsToQuery(query,Ids);
		//	response.getWriter().println(FinalQuery); 
			response.getWriter().println(db.getSpecifiedPhones(FinalQuery));
			}
			db.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.printStackTrace(response.getWriter());
		}
	}
	
	private String StringIfInvalid()
	{
		String a = "<xml>error</xml>";
		return a;
	}
	private boolean isXmlValid(String xml) throws JDOMException, IOException
	{
		SAXBuilder builder = new SAXBuilder();
		String ParamName;
		ArrayList<Phone> phonesList = new ArrayList<Phone>();
		int ID;
		boolean var = true;
		
		InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		Document document = builder.build(stream);
	 
			Element rootNode = document.getRootElement();
			List<Element> params = rootNode.getChildren();
			for(Element parameter: params)
			{
				if(parameter.getText().trim().length()==0) var = false;
			}
			
			return var;
	}
	/**
	 * 
	 * @param xml
	 * @param criteria - tablica kryteriów słuzące do wybranioa 3 najlepszych telefonóœ
	 * @return zwraca tablicę id 3 telefonów do wyświetlenia
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	private ArrayList<String> getPhonesID(String xml, ArrayList<String> criteria, PrintWriter a) throws JDOMException, IOException
	{
		SAXBuilder builder = new SAXBuilder();
		String ParamName;
		ArrayList<Phone> phonesList = new ArrayList<Phone>();
		int ID;
		float value;
		float sum=0;
		
	
		InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		Document document = builder.build(stream);
	 
			Element rootNode = document.getRootElement();
			List<Element> phones = rootNode.getChildren();
			List<Element> parameters;
			// zbieranie MinMax do kalkulatora
			for (int i = 0; i < phones.size(); i++) {
				// it po telefonach
			   Element phone = (Element) phones.get(i);
			   
			   parameters = phone.getChildren();
			 //  a.println(parameters.get(0).toString()+ " "+parameters.get(0).getText());
			   ID = Integer.parseInt(parameters.get(0).getText());
			   
			   for(Element param: parameters)
			   {	// it po parametrach
				   ParamName = param.getName();
				  
				   if(criteria.contains(ParamName))
				   {
					   value = Float.parseFloat(param.getText());

					   if(ParamName.equals("Display")) 
						   {
						   	Calculator.DisplayMin = (value<Calculator.DisplayMin ? value : Calculator.DisplayMin);
						   	Calculator.DisplayMax = (value>Calculator.DisplayMax ? value : Calculator.DisplayMax);

						   }
					   else
						   if(ParamName.equals("Battery")) 
						   {
						   	Calculator.BatteryMin = (value<Calculator.BatteryMin ? value : Calculator.BatteryMin);
						   	Calculator.BatteryMax = (value>Calculator.BatteryMax ? value : Calculator.BatteryMax);

						   }						   else
							   if(ParamName.equals("Cores")) 
							   {
							   	Calculator.CoresMin = (value<Calculator.CoresMin ? value : Calculator.CoresMin);
							   	Calculator.CoresMax = (value>Calculator.CoresMax ? value : Calculator.CoresMax);

							   }else
								   if(ParamName.equals("Processor")) 
								   {
								   	Calculator.ProcessorMin = (value<Calculator.ProcessorMin ? value : Calculator.ProcessorMin);
								   	Calculator.ProcessorMax = (value>Calculator.ProcessorMax ? value : Calculator.ProcessorMax);

								   }								   else
									   if(ParamName.equals("Ram")) 
									   {
									   	Calculator.RamMin = (value<Calculator.RamMin ? value : Calculator.RamMin);
									   	Calculator.RamMax = (value>Calculator.RamMax ? value : Calculator.RamMax);

									   }									   else
										   if(ParamName.equals("Storage")) 
										   {
										   	Calculator.StorageMin = (value<Calculator.StorageMin ? value : Calculator.StorageMin);
										   	Calculator.StorageMax = (value>Calculator.StorageMax ? value : Calculator.StorageMax);

										   }										   else
											   if(ParamName.equals("Camera_Res")) 
											   {
											   	Calculator.CameraMin = (value<Calculator.CameraMin ? value : Calculator.CameraMin);
											   	Calculator.CameraMax = (value>Calculator.CameraMax ? value : Calculator.CameraMax);

											   }
				   }
			   }
			}   
			a.println("CoresMin: "+Calculator.CoresMin+" CoresMax: "+Calculator.CoresMax);
			a.println("procMin: "+Calculator.ProcessorMin+" procMax: "+Calculator.ProcessorMax);
			a.println("StorMin: "+Calculator.StorageMin+" StorageMax: "+Calculator.StorageMax);

			// obliczanie współczynnika dla każdego phona
			for (int i = 0; i < phones.size(); i++) {
				// it po telefonach
			   Element phone = (Element) phones.get(i);
			   
			   parameters = phone.getChildren();
			 //  a.println(parameters.get(0).toString()+ " "+parameters.get(0).getText());
			   ID = Integer.parseInt(parameters.get(0).getText());
			   
			   for(Element param: parameters)
			   {	// it po parametrach
				   ParamName = param.getName();
				  
				   if(criteria.contains(ParamName))
				   {
					   value = Float.parseFloat(param.getText());

					   if(ParamName.equals("Display")) sum+= Calculator.displayPoints(value);
					   else
						   if(ParamName.equals("Battery")) sum+= Calculator.batteryPoints(value);
						   else
							   if(ParamName.equals("Cores")) sum+= Calculator.coresPoints(value);else
								   if(ParamName.equals("Processor")) sum+= Calculator.processorPoints(value);else
									   if(ParamName.equals("Ram")) sum+= Calculator.ramPoints(value);else
										   if(ParamName.equals("Storage")) sum+= Calculator.storagePoints(value);else
											   if(ParamName.equals("Camera_Res")) sum+= Calculator.cameraPoints(value);

				   }
			   }
			   phonesList.add(new Phone(ID, sum));
			  a.println("id: "+String.valueOf(ID)+" sum:"+String.valueOf(sum));
			   sum = 0;
			   
			}
			ArrayList<String> ids = new ArrayList<String>();
			Collections.sort(phonesList);
			int iter = (3>phonesList.size() ? phonesList.size() : 3);
			for(int i=0; i<iter; ++i)
			{
				ids.add(String.valueOf(phonesList.get(i).getID()));		
			}
			return ids;
	}
	
	/**

	 * zwraca tablicę kryteriów w postaci takiej jak w bazie
	 * @throws IOException 
	 * @throws JDOMException 
	 * 
	 */
	private ArrayList<String> getCriteria(String xml) throws JDOMException, IOException
	{
		SAXBuilder builder = new SAXBuilder();
		String ParamName;
		String value;
		ArrayList<String> criteria = new ArrayList<String>();
	
		InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		Document document = builder.build(stream);
	 
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren();
	 
			for (int i = 0; i < list.size(); i++) {
				
			   Element node = (Element) list.get(i);
			   ParamName = node.getName();
			   
			   value = node.getText();
			   if(ParamName.equals("kryterium1") || ParamName.equals("kryterium2") || ParamName.equals("kryterium3") )
			   {
				   if(value.equalsIgnoreCase("Rozmiar ekranu")) criteria.add("Display");
				   else
					   if(value.equalsIgnoreCase("pojemnosc baterii")) criteria.add("Battery");
					   else
						   if(value.equalsIgnoreCase("Liczba rdzeni procesora")) criteria.add("Cores");
						   else
							   if(value.equalsIgnoreCase("Predkosc taktowania")) criteria.add("Processor");
							   else
								   if(value.equalsIgnoreCase("Ilosc pamieci ram")) criteria.add("Ram");
								   else
									   if(value.equalsIgnoreCase("Rozdzielczosc aparatu")) criteria.add("Camera_Res");
									   else
										   if(value.equalsIgnoreCase("Ilosc pamieci wlasnej")) criteria.add("Storage");

					   
			   }
			}
			   return  criteria;
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
	private String addIDsToQuery(String query, ArrayList<String> ids)
	{
		StringBuilder b = new StringBuilder();
		b.append(" and ID in (");
		for(String id : ids)
		{
			b.append(id);
			b.append(", ");
		}
		b.append("10000 ) order by Price asc");
		return query+b.toString();
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
		if(ParamName.equals("producent")) 
			{
				if(Value.equalsIgnoreCase("lg"))
				out = " Product_Name like 'LG' ";
				else 
					if(Value.equalsIgnoreCase("nokia"))
						out = " Product_Name like 'Nokia' ";
					else 
						if(Value.equalsIgnoreCase("samsung"))
							out = " Product_Name like 'Samsung' ";else 
								if(Value.equalsIgnoreCase("HTC"))
									out = " Product_Name like 'HTC' ";else 
										if(Value.equalsIgnoreCase("lenovo"))
											out = " Product_Name like 'Lenovo' ";else 
												if(Value.equalsIgnoreCase("sony"))
													out = " Product_Name like 'Sony' ";
												else out = " Cores > 0 ";
			}
		else
		if(ParamName.equals("minscreensize")) out = " Display >= "+Value; 
		else
			if(ParamName.equals("minprice")) out = " Price >= "+Value; 
			else
				if(ParamName.equals("maxprice")) out = " Price <= "+Value; 
				else
					if(ParamName.equals("ram")) out = " Ram >= "+Value; 
					else
						if(ParamName.equals("storage")) out = " Storage >= "+Value; 
						else
		if(ParamName.equals("maxscreensize")) out = " Display <= "+Value; 
		else
		if(ParamName.equals("batterysize")) out = " Battery >= "+Value; 
		else
		if(ParamName.equals("minproc")) out = " Processor >= "+Value;
		else
			if(ParamName.equals("mincoresnum")) out = " Cores >= "+Value;
		else
			if(ParamName.equals("camera")) out = " Camera_Res >= "+Value;
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
					if(Value.equals("0"))
					out = " OS_Name not like 'Android' ";
					else out = " Cores > 0 ";
				}
				else
					if(ParamName.equals("windowsphone"))
					{
						if(Value.equals("0"))
						out = " OS_Name  not like 'W%' ";
						else out = " Cores > 0 ";
					}
					else
						if(ParamName.equals("ios"))
						{
							if(Value.equals("0"))
							out = " OS_Name not like  'iOS' ";
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

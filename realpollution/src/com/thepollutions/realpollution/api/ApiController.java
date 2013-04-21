package com.thepollutions.realpollution.api;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller  
@RequestMapping("/api/data")  
public class ApiController {  
	private static final long serialVersionUID = 1L;  

	@Autowired  
	ApiService apiService;  


	@RequestMapping(value = "/get/{deviceId}", method = RequestMethod.GET)  
	public String getAllDataObj(@PathVariable String deviceId, ModelMap model, HttpServletResponse response) throws IOException {  
		List<DataObj> allDataObjs = apiService.getAllDataObj(deviceId);
		StringBuffer jsonMessage = new StringBuffer("{\n\t\"dataObjEntryList\": [\n");
		Iterator<DataObj> allDataObjsIter = allDataObjs.iterator();
		while (allDataObjsIter.hasNext()) {
			jsonMessage.append(allDataObjsIter.next().getJson());
			if (allDataObjsIter.hasNext())
			{
				jsonMessage.append(",\n");
			}
		}
		jsonMessage.append("\n\t]\n}");

		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(jsonMessage);
		out.flush();
		return null; 
	}  


	@RequestMapping(value = "/save/{deviceId}", method = RequestMethod.GET)  
	public String addDataObj(@PathVariable String deviceId,  
			@RequestParam String longCord,  
			@RequestParam String latCord,  
			@RequestParam String value, 
			ModelMap model, HttpServletResponse response) throws IOException {
		String jsonMessage = "{}";
		try {
			DataObj dataObj = new DataObj(deviceId, Double.parseDouble(longCord), Double.parseDouble(latCord), Double.parseDouble(value), new Date().getTime());  
			apiService.addDataObj(dataObj);
			//model.addAttribute("json", "{status: \"success\"}");
			jsonMessage = "{\"status\": \"success\"}";
		} catch (Exception e) {
			jsonMessage = "{\"status\": \"failed\", message: \"" + e.getMessage() + "\"}";
			//model.addAttribute("json", jsonMessage);
		}
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(jsonMessage);
		out.flush();
		return null; 


	}  



	@RequestMapping(value = "/import/{deviceId}", method = RequestMethod.GET)  
	public String addDataObj(@PathVariable String deviceId,  
			ModelMap model, HttpServletResponse response, HttpServletRequest request) throws IOException {

		try{


			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(request.getSession().getServletContext().getResourceAsStream("/WEB-INF/points2.txt"));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				// Print the content on the console
				String[] content = strLine.split("\t");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				DataObj dataObj = new DataObj(deviceId, Double.parseDouble(content[2]), Double.parseDouble(content[3]), Double.parseDouble(content[4]), df.parse(content[1]).getTime());  
				apiService.addDataObj(dataObj);
				
			}
			//Close the input stream
			in.close();
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print("{\"status\": \"success\"}");
			out.flush();
		}catch (Exception e){//Catch exception if any
			e.printStackTrace();
		}
		return null; 


	}  
}  
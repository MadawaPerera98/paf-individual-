package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Ebill;

@Path("/Bills")
public class EbillService {

	Ebill ebillObj = new Ebill();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBills()
	{
		return ebillObj.getAllBills();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBills(@FormParam("cname") String cname,
			 @FormParam("unit") String unit,
			 @FormParam("price") String price,
			 @FormParam("month") String month,
			 @FormParam("username") String username,
			 @FormParam("role") String role)
			{
			 String output = ebillObj.addBills(cname, unit, price, month,username,role);
				return output;
			}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBills(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String eid = userObject.get("eid").getAsString();
	 String cname = userObject.get("cname").getAsString();
	 String unit = userObject.get("unit").getAsString();
	 String price = userObject.get("price").getAsString();
	 String month = userObject.get("month").getAsString();
	 String username = userObject.get("username").getAsString();
	 String role = userObject.get("role").getAsString();
	 String output = ebillObj.updateBills(eid, cname, unit, price, month,username,role);
	 return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBills(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String id = doc.select("id").text();
	 String output = ebillObj.deleteBills(id);
	return output;
	}

}

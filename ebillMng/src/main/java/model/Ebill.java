package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ebill {

	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pafebill", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	public String addBills(String cname, String unit, String price, String month,String username,String role)
	 {
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
			// create a prepared statement
			String query = " insert into ebills(`eid`,`cname`,`unit`,`price`,`month`,`username`,`role`)"+ " values (?, ?, ?, ?, ?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cname);
			preparedStmt.setString(3, unit);
			preparedStmt.setString(4,price);
			preparedStmt.setString(5, month);
			preparedStmt.setString(6, username);
			preparedStmt.setString(7, role);
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			
			String newEbill = getAllBills(); 
			output = "{\"status\":\"success\", \"data\": \"" + newEbill + "\"}"; 
		}
	    catch (Exception e)
		{
		output = "{\"status\":\"error\", \"data\": \"Error while inserting the Ebill.\"}"; 

		System.err.println(e.getMessage());
		}
		return output;
	 }
	
	public String getAllBills()
	 {
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer Name</th><th>Unit</th>" + "<th>Price</th>" + "<th>Month</th>" +"<th>Username</th>" +"<th>Role</th>" +"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from ebills";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String eid = Integer.toString(rs.getInt("eid"));
				String cname = rs.getString("cname");
				String unit = rs.getString("unit");
				String price = rs.getString("price");
				String month = rs.getString("month");
				String username = rs.getString("username");
				String role = rs.getString("role");
				// Add into the html table
				output += "<tr><td>" + cname + "</td>";
				output += "<td>" + unit + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + role + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-itemid='" + eid + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + eid + "'></td></tr>"; 
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the bill.";
			System.err.println(e.getMessage());
		}
	 return output;
	 }
	
	public String updateBills(String eid,String cname, String unit, String price, String month,String username,String role)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE ebills SET cname=?,unit=?,price=?,month=?,username=?,role=? WHERE eid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, cname);
			preparedStmt.setString(2, unit);
			preparedStmt.setString(3, price);
			preparedStmt.setString(4, month);
			preparedStmt.setString(5, username);
			preparedStmt.setString(6, role);
			preparedStmt.setInt(7, Integer.parseInt(eid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newEbill = getAllBills(); 
			output = "{\"status\":\"success\", \"data\": \"" + newEbill + "\"}"; 
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the Ebill.\"}"; 
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteBills(String eid)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			// create a prepared statement
			String query = "delete from ebills where eid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(eid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newEbill = getAllBills(); 
			output = "{\"status\":\"success\", \"data\": \"" + newEbill + "\"}"; 
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the Ebill.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
}

















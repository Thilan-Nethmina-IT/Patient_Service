package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Patient {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertpatient(String name, String address, String phoneNo, String year, String email)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting!"; } 
	 
			// create a prepared statement 
			String query = " insert into patient(`pid`,`name`,`address`,`phoneNo`,`year`,`email`)"
					 + " values (?, ?, ?, ?, ?, ?)";
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, address);
			 preparedStmt.setString(4, phoneNo);
			 preparedStmt.setString(5, year);
			 preparedStmt.setString(6, email);
			 
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newPatients = readpatient(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newPatients + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Patient!\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	public String readpatient()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading!"; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Name</th><th>Address</th><th>Phone Number</th><th>Birth Date</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from patient";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String pid = Integer.toString(rs.getInt("pid"));
				 String name = rs.getString("name");
				 String address = rs.getString("address");
				 String phoneNo = rs.getString("phoneNo");
				 String year = rs.getString("year");
				 String email = rs.getString("email");
				  
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidPatientIDUpdate\' name=\'hidPatientIDUpdate\' type=\'hidden\' value=\'" + pid + "'>" 
							+ name + "</td>"; 
				output += "<td>" + address + "</td>";
				output += "<td>" + phoneNo + "</td>";
				output += "<td>" + year + "</td>";
				output += "<td>" + email + "</td>";
				
				 
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-patientid='" + pid + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the patient!";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	public String updatepatient(String pid, String name, String address, String phoneNo, String year, String email)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE patient SET name=?,address=?,phoneNo=?,year=?,email=?" 
					   + "WHERE pid=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			   preparedStmt.setString(1, name);
			   preparedStmt.setString(2, address);
			   preparedStmt.setString(3, phoneNo);
			   preparedStmt.setString(4, year);
			   preparedStmt.setString(5, email);
			   preparedStmt.setInt(6, Integer.parseInt(pid)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newPatients = readpatient();    
			output = "{\"status\":\"success\", \"data\": \"" + newPatients + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the patient!\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deletepatient(String pid)   
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
			String query = "delete from patient where pid=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(pid)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newPatients = readpatient();    
			output = "{\"status\":\"success\", \"data\": \"" +  newPatients + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the patient.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
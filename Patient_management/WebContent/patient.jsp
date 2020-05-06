<%@ page import="com.Patient"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Management System</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<style>
.container{
  margin: auto;
  width: 60%;
  border: 3px solid black;
  padding: 50px;
 
}
.row{
  margin: auto;
  padding: 40px;
 
}
</style>
<script src="component/jquery-3.2.1.min.js"></script>
<script src="component/patient.js"></script> 
</head>
<body><br>
<div  class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Patient Management System</h1>
				<form id="formPatient" name="formPatient" method="post" action="patient.jsp">  
		           Patient Name: 
					<input id="name" name="name" type="text"  class="form-control form-control-sm">
					<br> Address:   
  					<input id="address" name="address" type="text" class="form-control form-control-sm">   
					<br> Phone NO:   
  					<input id="phoneNo" name="phoneNo" type="text"  class="form-control form-control-sm">   
					<br> Birth Date:   
  					<input id="year" name="year" type="date" class="form-control form-control-sm">   
  					<br> Email:   
  					<input id="email" name="email" type="text" class="form-control form-control-sm"> 
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidPatientIDSave" name="hidPatientIDSave" value=""> 
				</form>	
				<br>
				
				<div id="alertSuccess" class="alert alert-success">
					
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
			
				<div id="divPatientsGrid">
					<%
						Patient patientObj = new Patient();
						out.print(patientObj.readpatient());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>
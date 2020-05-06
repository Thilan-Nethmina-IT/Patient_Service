$(document).ready(function() 
{
	
	    $("#alertSuccess").hide();   
	    $("#alertError").hide();
 
});
 
 
// SAVE 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts 
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation  
	var status = validatePatientForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// Validate  
	var type = ($("#hidPatientIDSave").val() == "") ? "POST" : "PUT"; ///
	
	$.ajax( 
	{  
		url : "patientAPI",  ///
		type : type,  
		data : $("#formPatient").serialize(), //////// 
		dataType : "text",  
		complete : function(response, status)  
		{   
			onPatientSaveComplete(response.responseText, status);  
		} 
	}); 
}); 

function onPatientSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully Saved!");    
			$("#alertSuccess").show(); 

			$("#divPatientsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving!");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving!");   
		$("#alertError").show();  
	} 

	$("#hidPatientIDSave").val("");  
	$("#formPatient")[0].reset(); 
} 
 
// UPDATE
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidPatientIDSave").val($(this).closest("tr").find('#hidPatientIDUpdate').val());     
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#address").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#phoneNo").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#year").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#email").val($(this).closest("tr").find('td:eq(4)').text()); 
	 
}); 

//REMOVE
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "patientAPI",   
		type : "DELETE",   
		data : "pid=" + $(this).data("patientid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onPatientDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onPatientDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully Deleted!");    
			$("#alertSuccess").show(); 
		
			$("#divPatientsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting!");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting!");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL
function validatePatientForm() 
{  
	// NAME  
	if ($("#name").val().trim() == "")  
	{   
		return "Insert Patient Name!";  
	} 

	// ADDRESS  
	if ($("#address").val().trim() == "")  
	{   
		return "Insert Address!";  
	} 
	
	// PHONE NO 
	if ($("#phoneNo").val().trim() == "")  
	{   
		return "Insert Phone Number!";  
	} 
	
	//PHONE Number is Numeric?
	 var tmpPhone = $("#phoneNo").val().trim();
	if (!$.isNumeric(tmpPhone)) 
	 {
	 return "Invalid Telephone Number!";
	 }

	// Birth Date  
	if ($("#year").val().trim() == "")  
	{   
		return "Insert Birth Date!";  
	} 
	
	// EMAIL  
	if ($("#email").val().trim() == "")  
	{   
		return "Insert Email!";  
	} 

	return true; 
}
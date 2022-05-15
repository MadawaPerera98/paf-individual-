var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";






$(document).on("click", "#btnSave", function(event) 
{ 
	// Clear alerts---------------------
	$("#alertSuccess").text(""); 
 	$("#alertSuccess").hide(); 
 	$("#alertError").text(""); 
 	$("#alertError").hide(); 
	// Form validation-------------------
	var status = validateEbillForm(); 
	if (status != true) 
 	{ 
 		$("#alertError").text(status); 
 		$("#alertError").show(); 
 		return; 
 	} 
	// If valid------------------------
	var type = ($("#hidEbillIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
 url : "EbillAPI", 
 type : type, 
 data : $("#formEbill").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 	onEbillSaveComplete(response.responseText, status); 
 } 
}); 
});


function onEbillSaveComplete(response, status)
{
	if (status == "success") 
	{
		var resultSet = JSON.parse(response);
		 
		 if (resultSet.status.trim() == "success") 
 		 { 
 			$("#alertSuccess").text("Successfully saved."); 
 			$("#alertSuccess").show(); 
 			$("#divEbillGrid").html(resultSet.data); 
 		 } else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
 			 $("#alertError").show(); 
 		 } 
	}
	 else if (status == "error") 
	 {
		$("#alertError").text("Error while saving."); 
 		$("#alertError").show(); 
	 }
	 else 
	 {
		$("#alertError").text("Unknown error while saving.."); 
 		$("#alertError").show(); 
	 }
	 $("#hidEbillIDSave").val(""); 
 	 $("#formEbill")[0].reset(); 

}


function onEbillDeleteComplete(response, status) 
{ 
	if (status == "success") 
	 { 
 		var resultSet = JSON.parse(response); 
 	if (resultSet.status.trim() == "success") 
 	{ 
		$("#alertSuccess").text("Successfully deleted."); 
		$("#alertSuccess").show(); 
 		$("#divEbillGrid").html(resultSet.data); 
 	} else if (resultSet.status.trim() == "error") 
 	{ 
 		$("#alertError").text(resultSet.data); 
 		$("#alertError").show(); 
 	} 
 	} else if (status == "error") 
 	{ 
 		$("#alertError").text("Error while deleting."); 
 		$("#alertError").show(); 
 	} else
 	{ 
 		$("#alertError").text("Unknown error while deleting.."); 
 		$("#alertError").show(); 
	} 
}




































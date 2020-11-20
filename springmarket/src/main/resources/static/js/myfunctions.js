$(document).ready(function() {

	document.getElementById("loginbtn").addEventListener("click", myFunction);

});

function myFunction() {
	
	alert("Holaa");
	
	$("#myModal").dialog({
		modal: true
	});
}
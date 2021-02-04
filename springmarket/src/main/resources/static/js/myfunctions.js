$("body").on('click', '#botonpregunta', agregarPregunta);

function agregarPregunta() {
	var textoPregunta = $('#pregunta').val();

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	$.ajax({
		url: "/profesor/disponibleUsername/" + nombreUsuario,
		contentType: "application/json; charset=utf-8",
		data: { "username": nombreUsuario },
		type: "POST",
		success: function(response) {
			var alerta;
			if (response == "false") {
				alerta = "Código html en caso de usuario ya en uso";
			} else {
				alerta  = "Código html en caso de usuario ya en uso";
			}
			$('#nombreUsuarioError').html(alerta);
		},
		error: function(xhr, status, error) {
			alerta = "Código html en caso de error";
			$('#nombreUsuarioError').html(alerta);
		}
	});
}



/*function loadDoc() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("demo").innerHTML = this.responseText;
		}
	};
	xhttp.open("GET", "ajax_info.txt", true);
	xhttp.send();
}*/

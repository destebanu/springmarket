$("body").on('click', '#botonpregunta', agregarPregunta);

function agregarPregunta() {
	var pregunta = $('#pregunta').val();
	var idProducto = $('#idProducto').val();
	var idUsuario = getCookie("idUsuario");


	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
// $('#preguntaForm').submit(function(evento) {
	$.ajax({
	
		type: "POST",
		url: "/product/pregunta/" + idProducto,
		contentType: "application/json",
		data: {
		},
		success: function(response) {
			var alerta;
			if (response == "false") {
				alerta = "Fallo catastrófico";
			} else {
				alerta = "No sé que está pasando. Está bien?";
			}
			$('#containerpreguntas').html(alerta);
		},
		error: function(xhr, status, error) {
			alerta = "Código html en caso de error. Fallo enorme";
			$('#containerpreguntas').html(alerta);
		}
	});
}


function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
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

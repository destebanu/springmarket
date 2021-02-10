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
	
	var datos = { "pregunta": pregunta,
				  "idProducto" : idProducto };

	$.ajax({
		url: "/qanda/pregunta/",
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(datos),
		type: "POST",
		success: function(response) {
			var alerta;
			if (response == "false") {
				alerta = "Fallo catastrófico";
			} else {
				alerta = "No sé que está pasando. Está bien?";
			}
			$('#tablaqanda').html(alerta);
		},
		error: function(xhr, status, error) {
			alerta = "Código html en caso de error. Fallo enorme";
			$('#tablaqanda').html(alerta);
		}
	});
};

/*function getCookie(cname) {
	var name = cname + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for (var i = 0; i < ca.length; i++) {
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
*/

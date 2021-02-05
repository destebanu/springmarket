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
	
/*$('#preguntaForm').submit(function(evento) {
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
			$('containerpreguntas').html(alerta);
		},
		error: function(xhr, status, error) {
			alerta = "Código html en caso de error. Fallo enorme";
			$('containerpreguntas').html(alerta);
		}
	});
});*/

}

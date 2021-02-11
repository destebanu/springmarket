$("body").on('click', '#botonpregunta', agregarPregunta);

function agregarPregunta() {
	var pregunta = $('#pregunta').val();
	var idProducto = $('#idProducto').val();
	//var idUsuario = getCookie("idUsuario");


	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	var datos = {
		"pregunta": pregunta,
		"idProducto": idProducto
	};

	$.ajax({
		url: "/qanda/pregunta/",
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(datos),
		type: "POST",
		success: function(response) {
			
			// Se recoge la pregunta del  controlador y se obtienen sus cosas
			var pfecha = response.fechaPregunta;
			var pusuario = response.nombreUsuario;
			var ptexto = response.textoPregunta;
			 
			// Se crean los nodos para la tabla
			var fila = document.createElement("tr");
			var tdpfecha = document.createElement("td");
			var tdpusuario = document.createElement("td");
			var tdptexto = document.createElement("td");
			
			// Se asignan los datos a los nodos
			tdpfecha.textContent = `${pfecha}`;
			tdpusuario.textContent = `${pusuario}`;
			tdptexto.textContent = `${ptexto}`;
			
			fila.appendChild(tdpfecha);
			fila.appendChild(tdpusuario);
			fila.appendChild(tdptexto);
			
			
			//${fila}.addClass("table-info");
			
			$('#tablaqanda').append(fila);


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

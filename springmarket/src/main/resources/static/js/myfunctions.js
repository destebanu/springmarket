// Llamamos a la función cuando damos al botón de hacer la pregunta
$("body").on('click', '#botonpregunta', agregarPregunta);

// Creamos el botón para responder
var botonresponder = document.createElement("button");
botonresponder.setAttribute("id","botonresponder");
botonresponder.innerHTML = "Responde a esta pregunta";
botonresponder.classList.add("btn btn-outline-primary")
botonresponder.css("display","none")

//Creamos el botón para borrar la pregunta
var botonrborrar = document.createElement("button");
botonresponder.setAttribute("id","botonborrar");
botonborrar.innerHTML = "Borra esta pregunta";
botonborrar.classList.add("btn btn-outline-danger")
botonborrar.css("display","none")

// Llamamos a la función para responder
$("body").on('click', '#botonresponder', agregarRespuesta);

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
			var tdpopciones = document.createElement("td");
			
			// Se asignan los datos a los nodos
			tdpfecha.textContent = `${pfecha}`;
			tdpusuario.textContent = `${pusuario}`;
			tdptexto.textContent = `${ptexto}`;
			tdpopciones.appendChild = botonborrar;
			
			botonresponder.css("display","inline");
			botonborrar.css("display","inline");
			
			fila.appendChild(tdpfecha);
			fila.appendChild(tdpusuario);
			fila.appendChild(tdptexto);
			fila.appendChild(tdprespuesta);
			
			fila.classList.add("table-info");
			
			$('#tablaqanda').append(fila);


		},
		error: function(xhr, status, error) {
			alerta = "Código html en caso de error. Fallo enorme";
			$('#tablaqanda').html(alerta);
		}
	});
};

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

function responderPregunta() {
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
			
			botonresponder.css("display","inline");
			
			fila.appendChild(tdpfecha);
			fila.appendChild(tdpusuario);
			fila.appendChild(tdptexto);
			fila.appendChild(botonresponder);
			
			
			fila.classList.add("table-info");
			
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

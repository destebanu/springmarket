// Llamamos a la función cuando damos al botón de hacer la pregunta
$("body").on('click', '#botonpregunta', agregarPregunta);


// Llamamos a la función para responder
$("body").on('click', '#botonParaResponder', agregarRespuesta);

function agregarPregunta() {
	var pregunta = $('#pregunta').val();
	var idProducto = $('#idProducto').val();
	$('#pregunta').val('');
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
			var filaPregunta = document.createElement("tr");
			var tdpfecha = document.createElement("td");
			var tdpusuario = document.createElement("td");
			var tdptexto = document.createElement("td")
			var tdpopciones1 = document.createElement("td");

			// Se asignan los datos a los nodos
			tdpfecha.textContent = `${pfecha}`;
			tdpusuario.textContent = `${pusuario}`;
			tdptexto.textContent = `${ptexto}`;

			//Creamos el botón para borrar la pregunta
			var botonBorrarPregunta = document.createElement("button");
			botonBorrarPregunta.setAttribute("id", "botonBorrarPregunta");
			botonBorrarPregunta.innerHTML = "Borra esta pregunta";
			botonBorrarPregunta.classList.add("btn");
			botonBorrarPregunta.classList.add("btn-outline-danger");

			// Aquí recogerá el ID de la pregunta y se la pasará a la respuesta 
			var pID = response.idPregunta;
			var inputHIDDENpID = document.createElement("input");
			inputHIDDENpID.setAttribute("type", "hidden");
			inputHIDDENpID.setAttribute("id", "idPregunta");
			inputHIDDENpID.setAttribute("value", pID);

			tdpopciones1.appendChild(botonBorrarPregunta);

			filaPregunta.appendChild(tdpfecha);
			filaPregunta.appendChild(tdpusuario);
			filaPregunta.appendChild(tdptexto);
			filaPregunta.appendChild(tdpopciones1);
			filaPregunta.appendChild(inputHIDDENpID);

			filaPregunta.classList.add("table-info");

			$('#tablaqanda').append(filaPregunta);

			// Fila para responder
			var filaParaResponder = document.createElement("tr");
			filaParaResponder.setAttribute("id", "filaParaResponder");
			var tdtextoRespuesta = document.createElement("td");
			tdtextoRespuesta.setAttribute("colspan", "3");
			var tdpopciones2 = document.createElement("td");
			var areaRespuesta = document.createElement("textarea");
			areaRespuesta.setAttribute("cols", "170");
			areaRespuesta.setAttribute("rows", "3");
			areaRespuesta.setAttribute("id", "respuesta");

			// Creamos el botón para responder
			var botonParaResponder = document.createElement("button");
			botonParaResponder.setAttribute("id", "botonParaResponder");
			botonParaResponder.innerHTML = "Responde a esta pregunta";
			botonParaResponder.classList.add("btn");
			botonParaResponder.classList.add("btn-outline-primary");

			//Anidamos
			tdtextoRespuesta.appendChild(areaRespuesta);
			tdpopciones2.appendChild(botonParaResponder);
			filaParaResponder.appendChild(tdtextoRespuesta);
			filaParaResponder.appendChild(tdpopciones2);
			filaParaResponder.appendChild(inputHIDDENpID);

			$('#tablaqanda').append(filaParaResponder);

		},
		error: function(xhr, status, error) {
			alerta = "Código html en caso de error. Fallo enorme";
			$('#tablaqanda').html(alerta);
		}
	});
};

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Función que agrega la respuesta

function agregarRespuesta() {
	var respuesta = $(this).closest("tr") // Finds the closest row <tr>
		.find("#respuesta") // Gets a descendent with class="nr"
		.val();
	var idPregunta = $(this).closest("tr") // Finds the closest row <tr>
		.find("#idPregunta") // Gets a descendent with class="nr"
		.val();
	var obj = $(this);

	// Vaciamos la respuesta al enviarla
	$('#respuesta').val('');

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	var datos = {
		"respuesta": respuesta,
		"idPregunta": idPregunta
	};

	$.ajax({
		url: "/qanda/respuesta/",
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(datos),
		type: "POST",
		success: function(response) {

			// Se recoge la pregunta del  controlador y se obtienen sus cosas
			var rfecha = response.fechaRespuesta;
			var rusuario = response.nombreUsuario;
			var rtexto = response.textoRespuesta;

			// Se crean los nodos para la tabla
			var filaRespuesta = document.createElement("tr");
			var tdrfecha = document.createElement("td");
			var tdrusuario = document.createElement("td");
			var tdrtexto = document.createElement("td");
			var tdropciones = document.createElement("td");

			// Se asignan los datos a los nodos
			tdrfecha.textContent = `${rfecha}`;
			tdrusuario.textContent = `${rusuario}`;
			tdrtexto.textContent = `${rtexto}`;

			//Creamos el botón para borrar la respuesta
			var botonBorrarRespuesta = document.createElement("button");
			botonBorrarRespuesta.setAttribute("id", "botonBorrarRespuesta");
			botonBorrarRespuesta.innerHTML = "Borra esta respuesta";
			botonBorrarRespuesta.classList.add("btn");
			botonBorrarRespuesta.classList.add("btn-outline-danger");

			//Creamos el botón para borrar la respuesta
			var botonEditarRespuesta = document.createElement("button");
			botonEditarRespuesta.setAttribute("id", "botonEditarRespuesta");
			botonEditarRespuesta.innerHTML = "Edita esta respuesta";
			botonEditarRespuesta.classList.add("btn");
			botonEditarRespuesta.classList.add("btn-outline-secondary");

			tdropciones.appendChild(botonBorrarRespuesta);
			tdropciones.appendChild(botonEditarRespuesta);

			filaRespuesta.appendChild(tdrfecha);
			filaRespuesta.appendChild(tdrusuario);
			filaRespuesta.appendChild(tdrtexto);
			filaRespuesta.appendChild(tdropciones);

			filaRespuesta.classList.add("table-success");

			// Agregamos la fila de la respuesta antes de la fila para responder
			var  lafile= $(obj).closest("tr"); // Finds the closest row <tr>; // Gets a descendent with class="nr"
			lafile.before(filaRespuesta);
			
			//$('#filaParaResponder').before(filaRespuesta);

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

// Llamamos a la función cuando damos al botón de hacer la pregunta
$("body").on('click', '#botonpregunta', agregarPregunta);


// Llamamos a la función para responder
$("body").on('click', '#botonParaResponder', agregarRespuesta);

// Llamamos a la función para borrar la pregunta
$("body").on('click', '#botonBorrarPregunta', borrarPregunta);

// Llamamos a la función para borrar la respuesta
$("body").on('click', '#botonBorrarRespuesta', borrarRespuesta);

// Llamamos a la función para editar la respuesta
$("body").on('click', '#botonEditarRespuesta', editarRespuesta);

// Llamamos a la función para enviar la respuesta editada
$("body").on('click', '#botonParaActualizar', enviarRespuesta);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Función que agrega la pregunta
function agregarPregunta() {
	var pregunta = $('#pregunta').val();
	var idProducto = $('#idProducto').val();

	if (pregunta == "") {
		return document.location.reload(true);
	}

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
			filaPregunta.setAttribute("id", "filaPregunta");
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
// Función para borrar la pregunta
function borrarPregunta() {
	var idPregunta = $(this).closest("tr") // Finds the closest row <tr>
		.find("#idPregunta") // Gets a descendent with class="nr"
		.val();
	var obj = $(this);


	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	var datos = {
		"idPregunta": idPregunta
	};

	$.ajax({
		url: "/qanda/borrarpregunta/",
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(datos),
		type: "POST",
		success: function(response) {
			var filaPreguntaABorrar = $(obj).closest("tr");
			filaPreguntaABorrar.remove();
			document.location.reload(true);
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

	if (respuesta == "") {
		return document.location.reload(true);
	}

	var idPregunta = $(this).closest("tr") // Finds the closest row <tr>
		.find("#idPregunta") // Gets a descendent with class="nr"
		.val();
	var obj = $(this);

	// Vaciamos la respuesta al enviarla
	$(respuesta).val('');

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
			filaRespuesta.setAttribute("id", "filaRespuesta");
			var tdrfecha = document.createElement("td");
			var tdrusuario = document.createElement("td");
			var tdrtexto = document.createElement("td");
			tdrtexto.setAttribute("id", "textoRespuesta");
			var tdropciones = document.createElement("td");

			// Aquí recogerá el ID de la respuesta
			var rID = response.idRespuesta;
			var inputHIDDENrID = document.createElement("input");
			inputHIDDENrID.setAttribute("type", "hidden");
			inputHIDDENrID.setAttribute("id", "idRespuesta");
			inputHIDDENrID.setAttribute("value", rID);

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
			filaRespuesta.appendChild(inputHIDDENrID);

			filaRespuesta.classList.add("table-success");

			// Agregamos la fila de la respuesta antes de la fila para responder
			var lafile = $(obj).closest("tr"); // Finds the closest row <tr>; // Gets a descendent with class="nr"
			lafile.before(filaRespuesta);
		},
		error: function(xhr, status, error) {
			alerta = "Código html en caso de error. Fallo enorme";
			$('#tablaqanda').html(alerta);
		}
	});
};

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Función que elimina la respuesta
function borrarRespuesta() {
	var idRespuesta = $(this).closest("tr") // Finds the closest row <tr>
		.find("#idRespuesta") // Gets a descendent with class="nr"
		.val();
	var obj = $(this);


	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	var datos = {
		"idRespuesta": idRespuesta
	};

	$.ajax({
		url: "/qanda/borrarrespuesta/",
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(datos),
		type: "POST",
		success: function(response) {
			var filaRespuestaABorrar = $(obj).closest("tr");
			filaRespuestaABorrar.remove();
		},
		error: function(xhr, status, error) {
			alerta = "Código html en caso de error. Fallo enorme";
			$('#tablaqanda').html(alerta);
		}
	});
};

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Función que habilita la edición de la respuesta
function editarRespuesta() {

	var obj = $(this);


	// Fila para editar
	var filaParaEditar = document.createElement("tr");
	filaParaEditar.setAttribute("id", "filaParaEditar");
	var tdtextoRespuestaEditada = document.createElement("td");
	tdtextoRespuestaEditada.setAttribute("colspan", "3");
	var tdropcionesEdicion = document.createElement("td");


	var areaRespuestaEditada = document.createElement("textarea");
	areaRespuestaEditada.setAttribute("cols", "170");
	areaRespuestaEditada.setAttribute("rows", "3");
	areaRespuestaEditada.setAttribute("id", "respuestaEditada");
	areaRespuestaEditada.value = this.parentNode.parentNode.parentNode.querySelector("#textoRespuesta").innerText;
	//$(this).closest("tr").find("#textoRespuesta").val();
	//document.getElementById("textoRespuesta").innerText;

	// Creamos el botón para actualizar la respuesta
	var botonParaActualizar = document.createElement("button");
	botonParaActualizar.setAttribute("id", "botonParaActualizar");
	botonParaActualizar.innerHTML = "Envía tu respuesta";
	botonParaActualizar.classList.add("btn");
	botonParaActualizar.classList.add("btn-outline-success");


	// Aquí recogerá el ID de la respuesta
	var rID = $(this).closest("tr") // Finds the closest row <tr>
		.find("#idRespuesta") // Gets a descendent with class="nr"
		.val();

	var inputHIDDENrID = document.createElement("input");
	inputHIDDENrID.setAttribute("type", "hidden");
	inputHIDDENrID.setAttribute("id", "idRespuesta");
	inputHIDDENrID.setAttribute("value", rID);

	//Anidamos
	tdtextoRespuestaEditada.appendChild(areaRespuestaEditada);
	tdropcionesEdicion.appendChild(botonParaActualizar);

	filaParaEditar.appendChild(tdtextoRespuestaEditada);
	filaParaEditar.appendChild(tdropcionesEdicion);
	filaParaEditar.appendChild(inputHIDDENrID);


	filaParaEditar.classList.add("table-warning");

	var lafile = $(obj).closest("tr"); // Finds the closest row <tr>; // Gets a descendent with class="nr"
	lafile.after(filaParaEditar);

};

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Este método envía la respuesta modificada y la modifica por AJAX
function enviarRespuesta() {
	var idRespuesta = $(this).closest("tr") // Finds the closest row <tr>
		.find("#idRespuesta") // Gets a descendent with class="nr"
		.val();

	var textoEditado = $('#respuestaEditada').val();


	var obj2 = $(this).closest("tr").prev().find("#textoRespuesta");

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	var datos = {
		"idRespuesta": idRespuesta,
		"textoEditado": textoEditado
	};

	$.ajax({
		url: "/qanda/editarrespuesta/",
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(datos),
		type: "POST",
		success: function(response) {

			var textotd = $(obj2);
			//			textotd.remove();
			//
			var nuevotexto = document.createElement("td");
			nuevotexto.setAttribute("id", "textoRespuesta");
			nuevotexto.textContent = `${textoEditado}`;
			//
			//			textotd.append(nuevotexto);
			textotd.replaceWith(nuevotexto);

			//			var textotd = $(obj2); // Finds the closest row <tr>; // Gets a descendent with class="nr"
			//			textotd.innerHTML = textoEditado;

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

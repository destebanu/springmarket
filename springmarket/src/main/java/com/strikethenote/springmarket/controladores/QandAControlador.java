package com.strikethenote.springmarket.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.strikethenote.springmarket.entidades.PreguntaDTO;
import com.strikethenote.springmarket.entidades.RespuestaDTO;
import com.strikethenote.springmarket.servicios.PreguntaServicio;
import com.strikethenote.springmarket.servicios.ProductoServicio;
import com.strikethenote.springmarket.servicios.RespuestaServicio;
import com.strikethenote.springmarket.servicios.UsuarioServicio;

@Controller
@RequestMapping(value = "/qanda")
public class QandAControlador {

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private PreguntaServicio preguntaServicio;

	@Autowired
	private RespuestaServicio respuestaServicio;

	@Autowired
	private UsuarioServicio usuarioServicio;

	// Métodos Q&A

	// Este método persiste preguntas
	@RequestMapping(value = "/pregunta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PreguntaDTO publicarPregunta(@RequestBody JsonNode values, HttpServletRequest request) {

		String pregunta = values.findValue("pregunta").asText();
		long idUsuario = ((long) request.getSession().getAttribute("idUsuario"));
		long idProducto = values.findValue("idProducto").asLong();

		if (pregunta != "") {

			PreguntaDTO p = preguntaServicio.crearGuardarPregunta(pregunta, idUsuario, idProducto);

			return p;

		} else
			return null;
	}

	// Este método borra preguntas
	@RequestMapping(value = "/borrarpregunta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean borrarPregunta(@RequestBody JsonNode values, HttpServletRequest request, Model model,
			HttpSession session) {

		long idPregunta = values.findValue("idPregunta").asLong();
		preguntaServicio.borrarPregunta(idPregunta);

		// Tal vez esto lo necesitamos para que solo el usuario con la sesión iniciada o
		// el admin puedan borrar/editar la pregunta
		/*
		 * long idUsuarioSESSION = (long) session.getAttribute("idUsuario");
		 * model.addAttribute("idUsuarioSESSION", idUsuarioSESSION);
		 */
		return true;

	}

	// Este método persiste respuestas
	@RequestMapping(value = "/respuesta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody RespuestaDTO publicarRespuesta(@RequestBody JsonNode values, HttpServletRequest request) {

		String respuesta = values.findValue("respuesta").asText();
		long idUsuario = ((long) request.getSession().getAttribute("idUsuario"));
		long idPregunta = values.findValue("idPregunta").asLong();

		if (respuesta != "") {

			RespuestaDTO r = respuestaServicio.crearGuardarRespuesta(respuesta, idUsuario, idPregunta);

			return r;

		} else
			return null;
	}

	// Este método borra respuestas
	@RequestMapping(value = "/borrarrespuesta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean borrarRespuesta(@RequestBody JsonNode values, HttpServletRequest request, Model model,
			HttpSession session) {

		long idRespuesta = values.findValue("idRespuesta").asLong();
		respuestaServicio.borrarRespuesta(idRespuesta);
		// Tal vez esto lo necesitamos para que solo el usuario con la sesión iniciada o
		// el admin puedan borrar/editar la pregunta
		/*
		 * long idUsuarioSESSION = (long) session.getAttribute("idUsuario");
		 * model.addAttribute("idUsuarioSESSION", idUsuarioSESSION);
		 */
		return true;

	}

	// Este método edita respuestas
	@RequestMapping(value = "/editarrespuesta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean editarRespuesta(@RequestBody JsonNode values, HttpServletRequest request, Model model,
			HttpSession session) {

		long idRespuesta = values.findValue("idRespuesta").asLong();
		respuestaServicio.modificarRespuesta(idRespuesta);
		// Tal vez esto lo necesitamos para que solo el usuario con la sesión iniciada o
		// el admin puedan borrar/editar la pregunta
		/*
		 * long idUsuarioSESSION = (long) session.getAttribute("idUsuario");
		 * model.addAttribute("idUsuarioSESSION", idUsuarioSESSION);
		 */
		return true;

	}

}

package com.strikethenote.springmarket.controladores;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.strikethenote.springmarket.entidades.PreguntaDTO;
import com.strikethenote.springmarket.entidades.Respuesta;
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

			PreguntaDTO p =preguntaServicio.crearGuardarPregunta(pregunta, idUsuario, idProducto);
			
			return p;

		} else
			return null;

//		// Validación de que la pregunta ha sido persistida
//		if (preguntaGuardada)
//			return "true";
//		else
//			return "false";
	}

	// Este método persiste respuestas
	@RequestMapping(value = "/productid/{idProducto}", method = RequestMethod.POST)
	@ResponseBody
	public String publicarRespuesta(@PathVariable("respuesta") String respuesta) {
		LocalDate fecha = LocalDate.now();
		Respuesta r = new Respuesta(respuesta, fecha);

		Boolean guardarRespuesta = respuestaServicio.guardarRespuesta(r);

		if (guardarRespuesta) {
			return "true";
		} else {
			return "false";
		}
	}

}

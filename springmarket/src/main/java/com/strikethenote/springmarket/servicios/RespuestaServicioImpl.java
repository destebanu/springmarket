package com.strikethenote.springmarket.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.strikethenote.springmarket.dao.PreguntaRepository;
import com.strikethenote.springmarket.dao.RespuestaRepository;
import com.strikethenote.springmarket.entidades.Pregunta;
import com.strikethenote.springmarket.entidades.PreguntaDTO;
import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.entidades.Respuesta;
import com.strikethenote.springmarket.entidades.RespuestaDTO;
import com.strikethenote.springmarket.entidades.Usuario;

@Transactional
@Service
public class RespuestaServicioImpl implements RespuestaServicio {

	@Autowired
	private RespuestaRepository respuestaRepository;

	@Autowired
	private PreguntaRepository preguntaRepository;

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Override
	public RespuestaDTO crearGuardarRespuesta(String respuesta, long idUsuario, long idPregunta) {

		Usuario usuario = usuarioServicio.obtenerUsuario(idUsuario);
		Pregunta pregunta = preguntaRepository.findById(idPregunta).orElse(null);
		LocalDate fechActual = LocalDate.now();
		Respuesta respuestaPersistida = new Respuesta(respuesta, fechActual, usuario, pregunta);

		// Aquí persiste el objeto respuesta en bbdd
		Respuesta nueva = respuestaRepository.save(respuestaPersistida);

		// Se crea y devuelve el objeto DTO para usarlo en AJAX
		RespuestaDTO r = new RespuestaDTO();
		r.setTextoRespuesta(respuesta);
		r.setIdRespuesta(nueva.getIdRespuesta());
		r.setFechaRespuesta(fechActual);
		r.setNombreUsuario(usuario.getNombreUsuario());
		r.setIdUsuario(usuario.getIdUsuario());
		r.setIdPregunta(pregunta.getIdPregunta());

		return r;
	}

	@Override
	public Respuesta obtenerRespuesta(Long id) {
		Respuesta findById = respuestaRepository.findById(id).orElse(null);

		if (findById != null) {
			Respuesta getRespuestaDetails = findById;
			return findById;
		} else {
			return null;
		}
	}

	@Override
	public RespuestaDTO modificarRespuesta(Long id) {

		Respuesta res = obtenerRespuesta(id);

		// Aquí persiste el objeto respuesta en bbdd
		respuestaRepository.save(res);

		// Se crea y devuelve el objeto DTO para usarlo en AJAX
		RespuestaDTO r = new RespuestaDTO();
		r.setTextoRespuesta(res.getTextoRespuesta());
		r.setIdRespuesta(res.getIdRespuesta());
		r.setFechaRespuesta(res.getFechaRespuesta());
		r.setNombreUsuario(res.getUsuario().getNombreUsuario());
		r.setIdUsuario(res.getUsuario().getIdUsuario());
		r.setIdPregunta(res.getPregunta().getIdPregunta());

		return r;
	}

	@Override
	public void borrarRespuesta(long idRespuesta) {
		Respuesta p = respuestaRepository.findById(idRespuesta).orElse(null);
		if (p != null) {

			respuestaRepository.deleteById(idRespuesta);
		}

	}

}

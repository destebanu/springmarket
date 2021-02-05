package com.strikethenote.springmarket.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.strikethenote.springmarket.dao.RespuestaRepository;
import com.strikethenote.springmarket.entidades.Pregunta;
import com.strikethenote.springmarket.entidades.Respuesta;

@Transactional
@Service
public class RespuestaServicioImpl implements RespuestaServicio {

	@Autowired
	private RespuestaRepository respuestaRepository;

	@Override
	public boolean guardarRespuesta(Respuesta resp) {
		try {
			respuestaRepository.save(resp);
			return true;
		} catch (Exception e) {
			return false;
		}
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
	public Respuesta modificarRespuesta(Respuesta respuesta) {

		return respuestaRepository.save(respuesta);
	}

	@Override
	public void borrarRespuesta(long idRespuesta) {
		Respuesta p = respuestaRepository.findById(idRespuesta).orElse(null);
		if (p != null) {

			respuestaRepository.deleteById(idRespuesta);
		}

	}

}

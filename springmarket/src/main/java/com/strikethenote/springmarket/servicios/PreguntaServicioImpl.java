package com.strikethenote.springmarket.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.strikethenote.springmarket.dao.PreguntaRepository;
import com.strikethenote.springmarket.entidades.Pregunta;

@Transactional
@Service
public class PreguntaServicioImpl implements PreguntaServicio {

	@Autowired
	private PreguntaRepository preguntaRepository;

	@Override
	public boolean guardarPregunta(Pregunta preg) {
		try {
			preguntaRepository.save(preg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Pregunta obtenerPregunta(Long id) {
		Pregunta findById = preguntaRepository.findById(id).orElse(null);

		if (findById != null) {
			Pregunta getPreguntaDetails = findById;
			return findById;
		} else {
			return null;
		}
	}

	@Override
	public void borrarPregunta(long idPregunta) {
		Pregunta p = preguntaRepository.findById(idPregunta).orElse(null);
		if (p != null) {

			preguntaRepository.deleteById(idPregunta);
		}

	}

}

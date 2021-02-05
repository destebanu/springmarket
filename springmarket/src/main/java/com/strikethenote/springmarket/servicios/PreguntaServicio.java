package com.strikethenote.springmarket.servicios;

import com.strikethenote.springmarket.entidades.Pregunta;

public interface PreguntaServicio {

	public boolean guardarPregunta(Pregunta preg);

	public Pregunta obtenerPregunta(Long id);

	public void borrarPregunta(long idPregunta);

}

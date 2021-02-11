package com.strikethenote.springmarket.servicios;

import com.strikethenote.springmarket.entidades.Pregunta;
import com.strikethenote.springmarket.entidades.PreguntaDTO;

public interface PreguntaServicio {

	public PreguntaDTO crearGuardarPregunta(String pregunta, long idUsuario, long idProducto);

	public Pregunta obtenerPregunta(Long id);

	public void borrarPregunta(long idPregunta);

}

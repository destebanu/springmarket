package com.strikethenote.springmarket.servicios;

import com.strikethenote.springmarket.entidades.Respuesta;
import com.strikethenote.springmarket.entidades.RespuestaDTO;

public interface RespuestaServicio {

	public RespuestaDTO crearGuardarRespuesta(String respuesta, long idUsuario, long idPregunta);

	public Respuesta obtenerRespuesta(Long id);

	public Respuesta modificarRespuesta(Respuesta respuesta);

	public void borrarRespuesta(long idRespuesta);

}

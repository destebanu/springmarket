package com.strikethenote.springmarket.servicios;

import com.strikethenote.springmarket.entidades.Respuesta;

public interface RespuestaServicio {

	public boolean guardarRespuesta(Respuesta resp);

	public Respuesta obtenerRespuesta(Long id);

	public Respuesta modificarRespuesta(Respuesta respuesta);

	public void borrarRespuesta(long idRespuesta);

}

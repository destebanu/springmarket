package com.strikethenote.springmarket.entidades;

import java.time.LocalDate;

public class RespuestaDTO {

	private Long idRespuesta;

	private Long idPregunta;

	private String textoRespuesta;

	private LocalDate fechaRespuesta;

	private Long idUsuario;

	private String nombreUsuario;

	public RespuestaDTO() {
		super();
	}

	public RespuestaDTO(Long idRespuesta, Long idPregunta, String textoRespuesta, LocalDate fechaRespuesta,
			Long idUsuario, String nombreUsuario) {
		super();
		this.idRespuesta = idRespuesta;
		this.idPregunta = idPregunta;
		this.textoRespuesta = textoRespuesta;
		this.fechaRespuesta = fechaRespuesta;
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
	}

	public RespuestaDTO(Long idPregunta, String textoRespuesta, LocalDate fechaRespuesta, Long idUsuario,
			String nombreUsuario) {
		super();
		this.idPregunta = idPregunta;
		this.textoRespuesta = textoRespuesta;
		this.fechaRespuesta = fechaRespuesta;
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
	}

	public Long getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(Long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public Long getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getTextoRespuesta() {
		return textoRespuesta;
	}

	public void setTextoRespuesta(String textoRespuesta) {
		this.textoRespuesta = textoRespuesta;
	}

	public LocalDate getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(LocalDate fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	

}

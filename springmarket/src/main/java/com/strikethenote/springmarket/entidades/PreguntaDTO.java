package com.strikethenote.springmarket.entidades;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PreguntaDTO {

	private Long idPregunta;

	private String textoPregunta;

	private LocalDate fechaPregunta;

	private Long idUsuario;

	private String nombreUsuario;

	private Long idProducto;

	private Set<Respuesta> respuestas = new HashSet<>();

	public PreguntaDTO() {
	}

	public PreguntaDTO(Long idPregunta, String textoPregunta, LocalDate fechaPregunta, Long idUsuario,
			String nombreUusario, Long idProducto, Set<Respuesta> respuestas) {
		super();
		this.idPregunta = idPregunta;
		this.textoPregunta = textoPregunta;
		this.fechaPregunta = fechaPregunta;
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.idProducto = idProducto;
		this.respuestas = respuestas;
	}

	public Long getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getTextoPregunta() {
		return textoPregunta;
	}

	public void setTextoPregunta(String textoPregunta) {
		this.textoPregunta = textoPregunta;
	}

	public LocalDate getFechaPregunta() {
		return fechaPregunta;
	}

	public void setFechaPregunta(LocalDate fechaPregunta) {
		this.fechaPregunta = fechaPregunta;
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

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Set<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(Set<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

}

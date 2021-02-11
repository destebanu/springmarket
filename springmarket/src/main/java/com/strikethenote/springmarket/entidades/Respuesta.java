package com.strikethenote.springmarket.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESPUESTA")
/* POJO */

public class Respuesta implements Serializable {

	private static final long serialVersionUID = -7067377195518937621L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESPUESTA")
	private Long idRespuesta;

	@Column(name = "TEXTO_RESPUESTA")
	private String textoRespuesta;

	@Column(name = "FECHA_PREGUNTA")
	private LocalDate fechaRespuesta;
	
	

	public Respuesta(Long idRespuesta, String textoRespuesta, LocalDate fechaPregunta) {
		super();
		this.idRespuesta = idRespuesta;
		this.textoRespuesta = textoRespuesta;
		this.fechaRespuesta = fechaPregunta;
		this.pregunta = pregunta;
		this.usuario = usuario;
	}

	public Respuesta(String textoRespuesta, LocalDate fechaPregunta) {
		super();
		this.textoRespuesta = textoRespuesta;
		this.fechaRespuesta = fechaPregunta;
		this.pregunta = pregunta;
		this.usuario = usuario;
	}

	public Respuesta() {
		super();
	}

	// OneToMany Pregunta - Poseida
	@ManyToOne
	@JoinColumn(name = "ID_PREGUNTA")
	private Pregunta pregunta;

	// OneToMany Usuario - Poseida
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	//

	public Long getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(Long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getTextoRespuesta() {
		return textoRespuesta;
	}

	public void setTextoRespuesta(String textoRespuesta) {
		this.textoRespuesta = textoRespuesta;
	}

	public LocalDate getFechaPregunta() {
		return fechaRespuesta;
	}

	public void setFechaPregunta(LocalDate fechaPregunta) {
		this.fechaRespuesta = fechaPregunta;
	}

}

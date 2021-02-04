package com.strikethenote.springmarket.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private LocalDate fechaPregunta;
	
	// OneToMany
	
	
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
		return fechaPregunta;
	}

	public void setFechaPregunta(LocalDate fechaPregunta) {
		this.fechaPregunta = fechaPregunta;
	}

	}

package com.strikethenote.springmarket.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "PREGUNTA")
/* POJO */

public class Pregunta implements Serializable {
	
	private static final long serialVersionUID = -6082164105502864129L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PREGUNTA")
	private Long idPregunta;

	@Column(name = "TEXTO_PREGUNTA")
	private String textoPregunta;
			
	@Column(name = "FECHA_PREGUNTA")
	private LocalDate fechaPregunta;
	
	// OneToMany Respuestas - Propietaria	
	@OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Respuesta> respuestas = new HashSet<>();
	
	
	//OneToMany Usuario - Poseida
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	//

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
}

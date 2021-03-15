package com.strikethenote.springmarket.servicios;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.strikethenote.springmarket.dao.PreguntaRepository;
import com.strikethenote.springmarket.entidades.Pregunta;
import com.strikethenote.springmarket.entidades.Respuesta;
import com.strikethenote.springmarket.entidades.Usuario;

public class PreguntaServicioImplTest {
	
	PreguntaRepository preguntaRepository = Mockito.mock(PreguntaRepository.class);
	
	@Autowired
	PreguntaServicioImpl preguntaServicioImpl = new PreguntaServicioImpl(preguntaRepository);
	
	@BeforeEach
	void setUp() {
		Pregunta p1 = new Pregunta();
		
		p1.setIdPregunta(1L);
		p1.setTextoPregunta("¿Cuanto cuesta un piano?");
		LocalDate l1 = LocalDate.now();
		p1.setFechaPregunta(l1);
		Usuario u1 = new Usuario();
		p1.setUsuario(u1);
		Set<Respuesta> respuestas = new HashSet<Respuesta>();
		p1.setRespuestas(respuestas);
		Optional<Pregunta> mockPregunta = Optional.of(p1);
		Mockito.when(preguntaRepository.findById(1L)).thenReturn(mockPregunta);

	}
	
	@Test
	void obtenerPreguntaTest() {
		
		Pregunta t1 = preguntaServicioImpl.obtenerPregunta(1L);

		Assertions.assertEquals(1L, t1.getIdPregunta());
		Assertions.assertEquals("¿Cuanto cuesta un piano?", t1.getTextoPregunta());
		
	}
	
	
	
}

package com.strikethenote.springmarket.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.strikethenote.springmarket.dao.PreguntaRepository;
import com.strikethenote.springmarket.entidades.Pregunta;
import com.strikethenote.springmarket.entidades.PreguntaDTO;
import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.entidades.Usuario;

@Transactional
@Service
public class PreguntaServicioImpl implements PreguntaServicio {

	@Autowired
	private PreguntaRepository preguntaRepository;

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private UsuarioServicio usuarioServicio;
	

	public PreguntaServicioImpl() {
		super();
	}
	

	public PreguntaServicioImpl(PreguntaRepository preguntaRepository) {
		super();
		this.preguntaRepository = preguntaRepository;
	}


	public PreguntaServicioImpl(PreguntaRepository preguntaRepository, ProductoServicio productoServicio,
			UsuarioServicio usuarioServicio) {
		super();
		this.preguntaRepository = preguntaRepository;
		this.productoServicio = productoServicio;
		this.usuarioServicio = usuarioServicio;
	}


	@Override
	public PreguntaDTO crearGuardarPregunta(String pregunta, long idUsuario, long idProducto) {

		Usuario usuario = usuarioServicio.obtenerUsuario(idUsuario);
		Producto producto = productoServicio.obtenerProducto(idProducto);
		LocalDate fechActual = LocalDate.now();
		Pregunta preguntaPersistida = new Pregunta(pregunta, fechActual, usuario, producto);

		// Aquí persiste el objeto pregunta en bbdd
		Pregunta nueva = preguntaRepository.save(preguntaPersistida);
		
		// Se crea y devuelve el objeto DTO para usarlo en AJAX
		PreguntaDTO p = new PreguntaDTO();
		p.setTextoPregunta(pregunta);
		p.setIdPregunta(nueva.getIdPregunta());
		p.setFechaPregunta(fechActual);
		p.setIdProducto(producto.getIdProducto());
		p.setNombreUsuario(usuario.getNombreUsuario());
		p.setIdUsuario(usuario.getIdUsuario());
		p.setRespuestas(null);
		
		return p;
	}

	@Override
	public Pregunta obtenerPregunta(Long id) {
		Pregunta findById = preguntaRepository.findById(id).orElse(null);

		if (findById != null) {
			Pregunta getPreguntaDetails = findById;
			return findById;
		} else {
			return null;
		}
	}

	@Override
	public void borrarPregunta(long idPregunta) {
		Pregunta p = preguntaRepository.findById(idPregunta).orElse(null);
		if (p != null) {
			preguntaRepository.deleteById(idPregunta);
		}

	}

}

package com.strikethenote.springmarket.servicios;

import org.springframework.web.multipart.MultipartFile;

import com.strikethenote.springmarket.entidades.Imagen;

public interface ImagenServicio {

	public int guardarImagen(Imagen img);
	
	public Imagen obtenerImagen(Long id);
	
	public Boolean actualizarImagen(long idUsuario, MultipartFile file);
	
}

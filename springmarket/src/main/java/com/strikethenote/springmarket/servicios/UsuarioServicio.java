package com.strikethenote.springmarket.servicios;

import java.util.List;

import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.entidades.Usuario;

public interface UsuarioServicio {
	
	public Usuario crearUsuario (Usuario usuario);
	
	public void eliminarUsuario (long idUsuario);
	
	public Usuario obtenerUsuario(long idUsuario);

	public List<Usuario> listarUsuarios();
	
	public List<Usuario> buscarPorNombre (String nombreUsuario);
	
}

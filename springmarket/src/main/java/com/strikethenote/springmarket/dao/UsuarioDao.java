package com.strikethenote.springmarket.dao;

import java.util.List;

import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.entidades.Usuario;

public interface UsuarioDao extends DaoGenerico<Usuario> {
	
	public List<Usuario> buscarPorNombre (String nombreUsuario);
	
	public List<Usuario> buscarPorPassword (String passwordUsuario);
	
	public List<Usuario> buscarPorEmail (String emailUsuario);

	public List<Usuario> listarUsuarios();

}

package com.strikethenote.springmarket.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.entidades.Usuario;

public interface UsuarioDao extends DaoGenerico<Usuario> {
	
	public Usuario buscarPorNombre (String nombreUsuario);
	
	public Usuario buscarPorEmail (String emailUsuario);

	public List<Usuario> listarUsuarios();

}

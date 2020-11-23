package com.strikethenote.springmarket.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikethenote.springmarket.dao.UsuarioDao;
import com.strikethenote.springmarket.entidades.Usuario;

@Transactional
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		return usuarioDao.crear(usuario);
	}

	@Override
	public void eliminarUsuario(long idUsuario) {
		usuarioDao.borrar(idUsuario);

	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioDao.listarUsuarios();
	}

	@Override
	public Usuario obtenerUsuario(long idUsuario) {
		return usuarioDao.buscar(idUsuario);
	}

	@Override
	public List<Usuario> buscarPorNombre(String nombreUsuario) {
		return usuarioDao.buscarPorNombre(nombreUsuario);
	}

		
	@Override
	public Usuario buscarPorEmail(String emailUsuario) {
		return usuarioDao.buscarPorEmail(emailUsuario);
	}

}

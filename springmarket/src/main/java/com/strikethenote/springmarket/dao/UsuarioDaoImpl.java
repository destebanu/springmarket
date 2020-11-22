package com.strikethenote.springmarket.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.strikethenote.springmarket.entidades.Usuario;

@Repository
@Component("UsuarioDao")
public class UsuarioDaoImpl extends DaoGenericoImpl<Usuario> implements UsuarioDao {
	
	@Override
	public List<Usuario> buscarPorNombre(String nombreUsuario) {
		Query query = this.em.createQuery("select u FROM Usuario u where u.nombreUsuario LIKE CONCAT('%', :nombreUsuario, '%')");
		query.setParameter("nombreUsuario", nombreUsuario);
		List<Usuario> usuarios = query.getResultList();

		if (usuarios != null) {
			return usuarios;
		}
		return null;
	}
	
	@Override
	public List<Usuario> buscarPorPassword (String passwordUsuario) {
		
		Query query = this.em.createQuery("select u FROM Usuario u where u.passwordUsuario LIKE CONCAT('%', :passwordUsuario, '%')");
		query.setParameter("passwordUsuario", passwordUsuario);
		List<Usuario> usuarios = query.getResultList();

		if (usuarios != null) {
			return usuarios;
		}
		return null;
	}
	
	@Override
	public List<Usuario> buscarPorEmail (String emailUsuario) {
		
		Query query = this.em.createQuery("select u FROM Usuario u where u.emailUsuario LIKE CONCAT('%', :emailUsuario, '%')");
		query.setParameter("emailUsuario", emailUsuario);
		List<Usuario> usuarios = query.getResultList();

		if (usuarios != null) {
			return usuarios;
		}
		return null;
	}

	@Override
	public List<Usuario> listarUsuarios() {
		Query query = this.em.createQuery("FROM Usuario");
		List<Usuario> usuarios = query.getResultList();

		if (usuarios != null) {
			return usuarios;
		}
		return null;
	}


}

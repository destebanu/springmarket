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
		Query query = this.em
				.createQuery("select u FROM Usuario u where u.nombreUsuario LIKE CONCAT('%', :nombreUsuario, '%')");
		query.setParameter("nombreUsuario", nombreUsuario);
		List<Usuario> usuarios = query.getResultList();

		if (usuarios != null) {
			return usuarios;
		}
		return null;
	}

	@Override
	public Usuario buscarPorEmail (String emailUsuario) {
		
		Query query = this.em.createQuery("select u FROM Usuario u where u.emailUsuario =  :emailUsuario");
		query.setParameter("emailUsuario", emailUsuario);
		
		try {

		Usuario usuario = (Usuario) query.getSingleResult();

			return usuario;

		}
		catch(javax.persistence.NoResultException e){
			return null;
		}
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

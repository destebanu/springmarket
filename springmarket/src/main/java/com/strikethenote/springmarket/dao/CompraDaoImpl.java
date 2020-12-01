package com.strikethenote.springmarket.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.strikethenote.springmarket.entidades.Compra;
import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.entidades.Usuario;

@Repository
@Component("CompraDao")
public class CompraDaoImpl extends DaoGenericoImpl<Compra> implements CompraDao {
	public List<Compra> listarCompras() {
		Query query = this.em.createQuery("FROM Compra");
		List<Compra> compras = query.getResultList();

		if (compras != null) {
			return compras;
		}
		return null;
	}

	@Override
	public List<Compra> buscarPorUsuario(Usuario usuario) {

		Query query = this.em.createQuery("select u FROM Compra u where u.usuario =  :usuario");
		query.setParameter("usuario", usuario);

		try {
			List<Compra> compras = query.getResultList();

			return compras;

		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

}

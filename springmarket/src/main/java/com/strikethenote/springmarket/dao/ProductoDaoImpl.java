package com.strikethenote.springmarket.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.strikethenote.springmarket.entidades.Producto;

@Repository
@Component("ProductoDao")
public class ProductoDaoImpl extends DaoGenericoImpl<Producto> implements ProductoDao {

	@Override
	public List<Producto> buscarPorNombre(String nombreProducto) {
		Query query = this.em.createQuery("select u FROM Producto u where u.nombreProducto LIKE CONCAT('%', :nombreProducto, '%')");
		query.setParameter("nombreProducto", nombreProducto);
		List<Producto> productos = query.getResultList();

		if (productos != null) {
			return productos;
		}
		return null;
	}

	@Override
	public List<Producto> listarProductos() {
		Query query = this.em.createQuery("FROM Producto");
		List<Producto> productos = query.getResultList();

		if (productos != null) {
			return productos;
		}
		return null;
	}

}

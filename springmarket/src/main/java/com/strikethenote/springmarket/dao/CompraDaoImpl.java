package com.strikethenote.springmarket.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.strikethenote.springmarket.entidades.Compra;

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

}

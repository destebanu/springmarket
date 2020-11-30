package com.strikethenote.springmarket.dao;

import java.util.List;

import com.strikethenote.springmarket.entidades.Compra;

public interface CompraDao extends DaoGenerico<Compra> {

	public List<Compra> listarCompras();
}

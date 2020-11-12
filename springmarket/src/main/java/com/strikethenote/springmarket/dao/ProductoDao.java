package com.strikethenote.springmarket.dao;

import com.strikethenote.springmarket.entidades.Producto;

import java.util.List;

public interface ProductoDao extends DaoGenerico<Producto> {
	
	public List<Producto> listarProductos();
}

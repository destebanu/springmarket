package com.strikethenote.springmarket.dao;

import com.strikethenote.springmarket.entidades.Producto;

import java.util.List;

public interface ProductoDao extends DaoGenerico<Producto> {

	public Producto buscarPorNombre (String nombreProducto);

	public List<Producto> listarProductos();
}

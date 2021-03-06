package com.strikethenote.springmarket.servicios;

import java.util.List;

import com.strikethenote.springmarket.entidades.Producto;

public interface ProductoServicio {

	public Producto crearProducto (Producto producto);
	
	public void eliminarProducto (long idProducto);
	
	public Producto obtenerProducto(long idProducto);

	public List<Producto> listarProductos();
	
	public List<Producto> buscarPorNombre (String nombreProducto);
	
}

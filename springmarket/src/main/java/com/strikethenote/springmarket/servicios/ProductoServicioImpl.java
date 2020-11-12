package com.strikethenote.springmarket.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikethenote.springmarket.dao.ProductoDao;
import com.strikethenote.springmarket.entidades.Producto;

@Transactional
@Service
public class ProductoServicioImpl implements ProductoServicio {

	@Autowired
	private ProductoDao productoDao;
	
	@Override
	public Producto crearProducto(Producto producto) {
		return productoDao.crear(producto);
	}

	@Override
	public void eliminarProducto(long idProducto) {
		productoDao.borrar(idProducto);
		
	}

	@Override
	public List<Producto> listarProductos() {
		return productoDao.listarProductos();
	}

	@Override
	public Producto obtenerProducto(long idProducto) {
		return productoDao.buscar(idProducto);
	}

	@Override
	public Producto buscarPorNombre(String nombreProducto) {
		return productoDao.buscarPorNombre(nombreProducto);
	}

	
	
	
	


}

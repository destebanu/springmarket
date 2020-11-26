package com.strikethenote.springmarket.entidades;

import com.strikethenote.springmarket.entidades.Producto;

/* POJO */
public class Carrito {

	private Long idProductoCarrito;
	private String nombreProductoCarrito;
	private Double precioProductoCarrito;
	private Integer cantidadProductoCarrito;
	
	public Carrito() {
		
	}

	
	public Carrito(String nombreProductoCarrito, Double precioProductoCarrito, Integer cantidadProductoCarrito) {
		setNombreProductoCarrito(nombreProductoCarrito);
		setPrecioProductoCarrito(precioProductoCarrito);
		setCantidadProductoCarrito(cantidadProductoCarrito);
	}


	public Carrito(Long idProductoCarrito, String nombreProductoCarrito, Double precioProductoCarrito,
			Integer cantidadProductoCarrito) {
		setIdProductoCarrito(idProductoCarrito);
		setNombreProductoCarrito(nombreProductoCarrito);
		setPrecioProductoCarrito(precioProductoCarrito);
		setCantidadProductoCarrito(cantidadProductoCarrito);
	}
	
	public Carrito(Producto producto, Integer cantidadProductoCarrito) {
		setIdProductoCarrito(producto.getIdProducto());
		setNombreProductoCarrito(producto.getNombreProducto());
		setPrecioProductoCarrito(producto.getPrecioProducto());
		setCantidadProductoCarrito(cantidadProductoCarrito);
	}


	public Long getIdProductoCarrito() {
		return idProductoCarrito;
	}


	public void setIdProductoCarrito(Long idProductoCarrito) {
		this.idProductoCarrito = idProductoCarrito;
	}


	public String getNombreProductoCarrito() {
		return nombreProductoCarrito;
	}


	public void setNombreProductoCarrito(String nombreProductoCarrito) {
		this.nombreProductoCarrito = nombreProductoCarrito;
	}


	public Double getPrecioProductoCarrito() {
		return precioProductoCarrito;
	}


	public void setPrecioProductoCarrito(Double precioProductoCarrito) {
		this.precioProductoCarrito = precioProductoCarrito;
	}


	public Integer getCantidadProductoCarrito() {
		return cantidadProductoCarrito;
	}


	public void setCantidadProductoCarrito(Integer cantidadProductoCarrito) {
		this.cantidadProductoCarrito = cantidadProductoCarrito;
	}
	
	

	
}

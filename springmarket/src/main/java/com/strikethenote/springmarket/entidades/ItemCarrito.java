package com.strikethenote.springmarket.entidades;

/* POJO */
public class ItemCarrito {

	private Long idItemCarrito;
	private String nombreItemCarrito;
	private Double precioItemCarrito;
	private Integer cantidadItemCarrito;
	private Double descuentoItemCarrito;

	public ItemCarrito() {

	}

	public ItemCarrito(String nombreItemCarrito, Double precioItemCarrito, Integer cantidadItemCarrito,
			Double descuentoItemCarrito) {
		setNombreItemCarrito(nombreItemCarrito);
		setPrecioItemCarrito(precioItemCarrito);
		setCantidadItemCarrito(cantidadItemCarrito);
		setDescuentoItemCarrito(descuentoItemCarrito);
	}

	public ItemCarrito(Long idItemCarrito, String nombreItemCarrito, Double precioItemCarrito,
			Integer cantidadItemCarrito, Double descuentoItemCarrito) {
		setIdItemCarrito(idItemCarrito);
		setNombreItemCarrito(nombreItemCarrito);
		setPrecioItemCarrito(precioItemCarrito);
		setCantidadItemCarrito(cantidadItemCarrito);
		setDescuentoItemCarrito(descuentoItemCarrito);
	}

	public ItemCarrito(Producto producto, Integer cantidadItemCarrito) {
		setIdItemCarrito(producto.getIdProducto());
		setNombreItemCarrito(producto.getNombreProducto());
		setPrecioItemCarrito(producto.getPrecioProducto());
		setDescuentoItemCarrito(producto.getDescuentoProducto());
		setCantidadItemCarrito(cantidadItemCarrito);
	}

	public Long getIdItemCarrito() {
		return idItemCarrito;
	}

	public void setIdItemCarrito(Long idItemCarrito) {
		this.idItemCarrito = idItemCarrito;
	}

	public String getNombreItemCarrito() {
		return nombreItemCarrito;
	}

	public void setNombreItemCarrito(String nombreItemCarrito) {
		this.nombreItemCarrito = nombreItemCarrito;
	}

	public Double getPrecioItemCarrito() {
		return precioItemCarrito;
	}

	public void setPrecioItemCarrito(Double precioItemCarrito) {
		this.precioItemCarrito = precioItemCarrito;
	}

	public Integer getCantidadItemCarrito() {
		return cantidadItemCarrito;
	}

	public void setCantidadItemCarrito(Integer cantidadItemCarrito) {
		this.cantidadItemCarrito = cantidadItemCarrito;
	}

	public Double getDescuentoItemCarrito() {
		return descuentoItemCarrito;
	}

	public void setDescuentoItemCarrito(Double descuentoItemCarrito) {
		this.descuentoItemCarrito = descuentoItemCarrito;
	}

}

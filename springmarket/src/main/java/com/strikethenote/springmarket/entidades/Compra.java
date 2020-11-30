package com.strikethenote.springmarket.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "COMPRA")
/* POJO */

public class Compra implements Serializable {
	// Mapeo Hibernate

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COMPRA")
	private Long idCompra;
	
	@Column(name = "PRECIO_COMPRA")
	private Double precioCompra;
	
	@Column(name = "CARRITO")
	private List<ItemCarrito> carrito;

	// Relación OneToMany Usuario

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	// Relación ManyToMany Producto
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "LINEAS_DE_COMPRA", 
	joinColumns = @JoinColumn(name = "ID_COMPRA"), 
	inverseJoinColumns = @JoinColumn(name = "ID_PRODUCTO"))
	private Set<Producto> productos = new HashSet<>();
	
	public Compra() {
		super();
	}
	

	public Compra(Long idCompra, Double precioCompra, List<ItemCarrito> carrito, Usuario usuario) {
		super();
		this.idCompra = idCompra;
		this.precioCompra = precioCompra;
		this.carrito = carrito;
		this.usuario = usuario;
	}

	public Compra(Double precioCompra, List<ItemCarrito> carrito, Usuario usuario, Set<Producto> productos) {
		this.precioCompra = precioCompra;
		this.carrito = carrito;
		this.usuario = usuario;
		this.productos = productos;
	}

	public Compra(Long idCompra, Double precioCompra, List<ItemCarrito> carrito, Usuario usuario,
			Set<Producto> productos) {
		this.idCompra = idCompra;
		this.precioCompra = precioCompra;
		this.carrito = carrito;
		this.usuario = usuario;
		this.productos = productos;
	}
	
	public Double getPrecioCompra() {
		return precioCompra;
	}


	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}


	public List<ItemCarrito> getCarrito() {
		return carrito;
	}


	public void setCarrito(List<ItemCarrito> carrito) {
		this.carrito = carrito;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	//Métodos Producto (Entidad poseída)

	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}
	
	public boolean anadirProducto(Producto producto) {
		producto.anadirCompra(this);
		return getProductos().add(producto);
	}
	
	public void eliminarProducto(Producto producto) {
		this.productos.remove(producto);
		producto.getCompras().remove(this);
	}

}

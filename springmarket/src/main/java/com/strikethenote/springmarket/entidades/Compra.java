package com.strikethenote.springmarket.entidades;

import java.io.Serializable;
import java.util.HashSet;
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
	
	//Métodos Producto Entidad Poseída

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

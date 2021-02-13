package com.strikethenote.springmarket.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
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
@Table(name = "PRODUCTO")
/* POJO */
public class Producto implements Serializable {

	private static final long serialVersionUID = -4766316635642895356L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUCTO")
	private Long idProducto;

	@Column(name = "NOMBRE_PRODUCTO")
	private String nombreProducto;

	@Column(name = "DESCRIPCION_PRODUCTO")
	private String descripcionProducto;

	@Column(name = "PRECIO_PRODUCTO")
	private Double precioProducto;

	@Column(name = "CANTIDAD_PRODUCTO")
	private Integer cantidadProducto;

	@Column(name = "DESCUENTO_PRODUCTO")
	private Double descuentoProducto; // porcentaje

	// Relación ManyToMany Compra
	@ManyToMany(mappedBy = "productos")
	private Set<Compra> compras = new HashSet<>();

	// Relación OneToOne Imagen
	@OneToOne (mappedBy = "producto", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Imagen imagen;

	// Relación OneToMany Pregunta
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Pregunta> preguntas = new HashSet<>();

	// Constructores
	public Producto() {

	}

	public Producto(String nombreProducto, String descripcionProducto, Double precioProducto,
			Double descuentoProducto) {
		this.nombreProducto = nombreProducto;
		this.descripcionProducto = descripcionProducto;
		this.precioProducto = precioProducto;
		this.descuentoProducto = descuentoProducto;
	}

	public Producto(Long idProducto, String nombreProducto, String descripcionProducto, Double precioProducto,
			Double descuentoProducto) {
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.descripcionProducto = descripcionProducto;
		this.precioProducto = precioProducto;
		this.descuentoProducto = descuentoProducto;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public Double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(Double precioProducto) {
		this.precioProducto = precioProducto;
	}

	public Double getDescuentoProducto() {
		return descuentoProducto;
	}

	public void setDescuentoProducto(Double descuentoProducto) {
		this.descuentoProducto = descuentoProducto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	// Métodos Compra
	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public void anadirCompra(Compra c) {
		this.compras.add(c);

	}

	// Métodos Imagen
	public void addImagen(Imagen img) {
		this.imagen = img;
		img.setProducto(this);
	}

	public void removeImagen(Imagen img) {
		img.setProducto(null);
		this.imagen = null;

	}

	/*
	 * public void removeImagenes() { Iterator<Imagen> iterator =
	 * this.imagen.iterator(); while (iterator.hasNext()) { Imagen img =
	 * iterator.next(); img.setProducto(null); iterator.remove(); } }
	 */

	// Métodos de preguntas
	
	public Set<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	//Métodos imagen
	
	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", descripcionProducto="
				+ descripcionProducto + ", precioProducto=" + precioProducto + ", descuentoProducto="
				+ descuentoProducto + "]";
	}

}
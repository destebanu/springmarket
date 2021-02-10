package com.strikethenote.springmarket.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGEN")
/* POJO */
public class Imagen implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_IMAGEN")
	private Long idImagen;

	@Column(name = "NOMBRE")
	private String nombre;

	@Lob
	@Column(name = "IMAGEN")
	private byte[] imagen;

	// Relación OneToOne Producto
	@OneToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	public Imagen() {
		super();
	}

	public Imagen(String name, byte[] image) {
		super();
		this.nombre = name;
		this.imagen = image;
	}

	public Long getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(Long idImagen) {
		this.idImagen = idImagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	@Override
	public int hashCode() {
			return 2020;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
        
		
		return producto.getIdProducto() != null && producto.getIdProducto().equals(((Imagen) obj).producto.getIdProducto());

	}

}
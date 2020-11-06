package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(appliesTo = "PROFESOR")
/* POJO */

/* cuidado: Maven install no funciona, creo que es porque hay un problema con los paquetes.
 * El error es este: 
 * java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration,
 * you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test	*/
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
	
	@Column(name = "DESCUENTO_PRODUCTO")
	private Double descuentoProducto;	// porcentaje
	
		
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

}
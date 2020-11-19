package com.strikethenote.springmarket.entidades;

import java.io.Serializable;

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
@Table(name = "USUARIO")
/* POJO */
public class Usuario implements Serializable {
	
	
	private static final long serialVersionUID = -8548755844378572455L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	
	//Datos personales
	
	@Column(name = "NOMBRE_USUARIO")
	private String nombreUsuario;
	
	@Column(name = "APELLIDOS_USUARIO")
	private String apellidosUsuario;
	
	@Column(name = "CONTRASEÑA_USUARIO")
	private String contraseñaUsuario;
	
	@Column(name = "EMAIL_USUARIO")
	private String emailUsuario;
	
	@Column(name = "FECHANAC_USUARIO")
	private String fechanacUsuario;
	
	//Datos de pago
	
	@Column(name = "NUMTARJETA_USUARIO")
	private String numtarjetaUsuario;
	
	@Column(name = "TITULAR_USUARIO")
	private String titularUsuario;
	
	@Column(name = "CODSEG_USUARIO")
	private String codsegUsuario;
	
	@Column(name = "DIRECFACT_USUARIO")
	private String direcfactUsuario;
	
	public Usuario() {
		
	}
	
	public Usuario(Long idUsuario, String nombreUsuario, String apellidosUsuario, String contraseñaUsuario,
			String emailUsuario, String fechanacUsuario, String numtarjetaUsuario, String titularUsuario,
			String codsegUsuario, String direcfactUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellidosUsuario = apellidosUsuario;
		this.contraseñaUsuario = contraseñaUsuario;
		this.emailUsuario = emailUsuario;
		this.fechanacUsuario = fechanacUsuario;
		this.numtarjetaUsuario = numtarjetaUsuario;
		this.titularUsuario = titularUsuario;
		this.codsegUsuario = codsegUsuario;
		this.direcfactUsuario = direcfactUsuario;
	}

	public Usuario(String nombreUsuario, String apellidosUsuario, String contraseñaUsuario, String emailUsuario,
			String fechanacUsuario, String numtarjetaUsuario, String titularUsuario, String codsegUsuario,
			String direcfactUsuario) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.apellidosUsuario = apellidosUsuario;
		this.contraseñaUsuario = contraseñaUsuario;
		this.emailUsuario = emailUsuario;
		this.fechanacUsuario = fechanacUsuario;
		this.numtarjetaUsuario = numtarjetaUsuario;
		this.titularUsuario = titularUsuario;
		this.codsegUsuario = codsegUsuario;
		this.direcfactUsuario = direcfactUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidosUsuario() {
		return apellidosUsuario;
	}

	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}

	public String getContraseñaUsuario() {
		return contraseñaUsuario;
	}

	public void setContraseñaUsuario(String contraseñaUsuario) {
		this.contraseñaUsuario = contraseñaUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getFechanacUsuario() {
		return fechanacUsuario;
	}

	public void setFechanacUsuario(String fechanacUsuario) {
		this.fechanacUsuario = fechanacUsuario;
	}

	public String getNumtarjetaUsuario() {
		return numtarjetaUsuario;
	}

	public void setNumtarjetaUsuario(String numtarjetaUsuario) {
		this.numtarjetaUsuario = numtarjetaUsuario;
	}

	public String getTitularUsuario() {
		return titularUsuario;
	}

	public void setTitularUsuario(String titularUsuario) {
		this.titularUsuario = titularUsuario;
	}

	public String getCodsegUsuario() {
		return codsegUsuario;
	}

	public void setCodsegUsuario(String codsegUsuario) {
		this.codsegUsuario = codsegUsuario;
	}

	public String getDirecfactUsuario() {
		return direcfactUsuario;
	}

	public void setDirecfactUsuario(String direcfactUsuario) {
		this.direcfactUsuario = direcfactUsuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", apellidosUsuario="
				+ apellidosUsuario + ", contraseñaUsuario=" + contraseñaUsuario + ", emailUsuario=" + emailUsuario
				+ ", fechanacUsuario=" + fechanacUsuario + ", numtarjetaUsuario=" + numtarjetaUsuario
				+ ", titularUsuario=" + titularUsuario + ", codsegUsuario=" + codsegUsuario + ", direcfactUsuario="
				+ direcfactUsuario + "]";
	}
	
	
	
	
	
}


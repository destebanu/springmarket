package com.strikethenote.springmarket.servicios;

import java.util.List;

import com.strikethenote.springmarket.entidades.Compra;
import com.strikethenote.springmarket.entidades.Usuario;

public interface CompraServicio {
	
	public Compra crearCompra (Compra compra);

	public void eliminarCompra (long idCompra);

	public Compra obtenerCompra(long idCompra);

	public List<Compra> listarCompras();
	
	public List<Compra> buscarUsuario(Usuario usuario);
}

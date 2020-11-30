package com.strikethenote.springmarket.servicios;

import java.util.List;

import com.strikethenote.springmarket.entidades.Compra;

public interface CompraServicio {
	
	public Compra crearCompra (Compra compra);

	public void eliminarCompra (long idCompra);

	public Compra obtenerCompra(long idCompra);

	public List<Compra> listarCompras();
}

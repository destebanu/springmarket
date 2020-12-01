package com.strikethenote.springmarket.dao;

import java.util.List;

import com.strikethenote.springmarket.entidades.Compra;
import com.strikethenote.springmarket.entidades.Usuario;

public interface CompraDao extends DaoGenerico<Compra> {

	public List<Compra> listarCompras();
	
	public List<Compra> buscarPorUsuario (Usuario usuario);
}

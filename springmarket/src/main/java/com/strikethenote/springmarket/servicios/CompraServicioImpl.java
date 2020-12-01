package com.strikethenote.springmarket.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikethenote.springmarket.dao.CompraDao;
import com.strikethenote.springmarket.entidades.Compra;
import com.strikethenote.springmarket.entidades.Usuario;

@Transactional
@Service
public class CompraServicioImpl implements CompraServicio {

	@Autowired
	private CompraDao compraDao;

	@Override
	public Compra crearCompra(Compra compra) {
		return compraDao.crear(compra);
	}

	@Override
	public void eliminarCompra(long idCompra) {
		compraDao.borrar(idCompra);

	}

	@Override
	public Compra obtenerCompra(long idCompra) {
		return compraDao.buscar(idCompra);
	}

	@Override
	public List<Compra> listarCompras() {
		return compraDao.listarCompras();
	}

	@Override
	public List<Compra> buscarUsuario(Usuario usuario) {
		return compraDao.buscarPorUsuario(usuario);
	}

}

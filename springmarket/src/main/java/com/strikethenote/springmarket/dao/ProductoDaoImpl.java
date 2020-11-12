package com.strikethenote.springmarket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.strikethenote.springmarket.entidades.Producto;



@Repository
@Component("ProductoDao")
public class ProductoDaoImpl extends DaoGenericoImpl<Producto> implements ProductoDao {

	@Override
	public List<Producto> listarProductos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

package com.strikethenote.springmarket.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.servicios.ProductoServicio;

@Controller
public class IndexControlador {
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@GetMapping({ "/", "index" })
	public String index(Model model) {
		// Se añaden a una lista los productos de la bbdd a una lista
				List<Producto> productos = productoServicio.listarProductos();

				// Añadimos la lista al modelo para mostrarla en index

				int tamano = 8;
				if (tamano > productos.size()) {
					tamano = productos.size();
				}

				model.addAttribute("productos", productos.subList(0, tamano));

		return "index";

	}

}

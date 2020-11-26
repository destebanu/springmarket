package com.strikethenote.springmarket.controladores;

import org.springframework.stereotype.Controller;

import java.util.List;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.servicios.ProductoServicio;

@Controller
@RequestMapping(value = "/compra")
public class CompraControlador {
	@Autowired
	private ProductoServicio productoServicio;
/*
	@GetMapping("/carrocompra")
	public String product(Model model, HttpSession session) {

		// Se recoge el input de la búsqueda de la seesion y se usa el servicio para
		// buscar en la tabla
		Carrito carrito = new Carrito()
		String aux = (String) session.getAttribute("nombreproducto");

		// Recogemos el valor en una lista de productos.

		List<Carrito> listacarritos = productoServicio.buscarPorNombre(aux);

		// Añadimos la lista al modelo
		model.addAttribute("listacarritos", listacarritos);
		return "carrito";
	}
*/
}

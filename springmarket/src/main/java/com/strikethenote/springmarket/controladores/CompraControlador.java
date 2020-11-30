package com.strikethenote.springmarket.controladores;

import org.springframework.stereotype.Controller;

import java.util.ConcurrentModificationException;
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
import org.thymeleaf.exceptions.TemplateInputException;

import com.strikethenote.springmarket.entidades.Carrito;
import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.servicios.ProductoServicio;

@Controller
@RequestMapping(value = "/compra")
public class CompraControlador {
	@Autowired
	private ProductoServicio productoServicio;

	@GetMapping("/carrocompra")
	public String product(Model model, HttpSession session) {

		// Se recoge la lista de carritos de la session
		List<Carrito> listacarritos = (List<Carrito>) session.getAttribute("listacarritos");

		// Añadimos la lista al modelo
		model.addAttribute("listacarritos", listacarritos);
		return "carrocompra";
	}

	@PostMapping("/add/{idProducto}")
	public String buscarProducto(HttpServletRequest request, @PathVariable("idProducto") long idProducto) {

		// Se recoge la cantidad del producto
		Integer cantidadproducto = Integer.parseInt(request.getParameter("cantidadproducto"));

		// Crea carrito con el producto
		Carrito carrito = new Carrito(productoServicio.obtenerProducto(idProducto), cantidadproducto);

		// Meter el carrito en lista
		List<Carrito> listacarritos = (List<Carrito>) request.getSession().getAttribute("listacarritos");
		listacarritos.add(carrito);
		request.getSession().setAttribute("listacarritos", listacarritos);

		return "redirect:/index";
	}

	@PostMapping("/eliminar/{idProductoCarrito}")
	public String eliminarProducto(HttpServletRequest request,
			@PathVariable("idProductoCarrito") long idProductoCarrito) {
		// Se pasa por session el producto y se elimina del carrito a partir de su id
		List<Carrito> listacarritos = (List<Carrito>) request.getSession().getAttribute("listacarritos");
		// request.getSession().getAttribute("idProductoCarrito");
		try {
			for (Carrito carrito : listacarritos) {
				if (carrito.getIdProductoCarrito() == idProductoCarrito) {
					listacarritos.remove(carrito);
				}

			}
			request.getSession().setAttribute("listacarritos", listacarritos);
			return "redirect:/compra/carrocompra";
		} catch (ConcurrentModificationException e) {
			System.out.println("Fallo enorme");
			return null;
		}

	}

}

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

import com.strikethenote.springmarket.entidades.ItemCarrito;
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
		List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");

		// Añadimos la lista al modelo
		model.addAttribute("carrito", carrito);

		//
		Double totalConDescuento = 0.0;
		Double precioSegunCantidad = 0.0;

		for (ItemCarrito aux : carrito) {
			precioSegunCantidad = (aux.getPrecioItemCarrito() * aux.getCantidadItemCarrito());
			totalConDescuento += precioSegunCantidad - precioSegunCantidad * (aux.getDescuentoItemCarrito() / 100);
		}

		model.addAttribute("totalConDescuento", totalConDescuento);
		session.setAttribute("totalcondescuento",totalConDescuento);
		return "carrocompra";
	}

	@PostMapping("/add/{idProducto}")
	public String buscarProducto(HttpServletRequest request, @PathVariable("idProducto") long idProducto) {
		
		if (request.getSession().getAttribute("carrito")!=null) {

			// Se recoge la cantidad del producto
			Integer cantidadproducto = Integer.parseInt(request.getParameter("cantidadproducto"));

			// Crea carrito con el producto
			ItemCarrito item = new ItemCarrito(productoServicio.obtenerProducto(idProducto), cantidadproducto);

			// Meter el carrito en lista
			List<ItemCarrito> carrito = (List<ItemCarrito>) request.getSession().getAttribute("carrito");
			carrito.add(item);
			request.getSession().setAttribute("carrito", carrito);

		return "redirect:/index";
		} else
			return "redirect:/usuario/login";
			
	}

	@PostMapping("/eliminar/{idItemCarrito}")
	public String eliminarProducto(HttpServletRequest request,
			@PathVariable("idItemCarrito") long idItemCarrito) {
		// Se pasa por session el producto y se elimina del carrito a partir de su id
		List<ItemCarrito> carrito = (List<ItemCarrito>) request.getSession().getAttribute("carrito");
		try {
			for (ItemCarrito aux : carrito) {
				if (aux.getIdItemCarrito() == idItemCarrito) {
					carrito.remove(aux);
				}

			}
			request.getSession().setAttribute("carrito", carrito);
			return "redirect:/compra/carrocompra";
		} catch (ConcurrentModificationException e) {
			System.out.println("Fallo enorme");
			return null;
		}

	}
	
//	@PostMapping("/finalizar")
//	public String finalizarCompra(HttpServletRequest request) {
//		List<ItemCarrito> carrito = (List<ItemCarrito>) request.getSession().getAttribute("carrito");
//		
//		
//
//	}
	

}
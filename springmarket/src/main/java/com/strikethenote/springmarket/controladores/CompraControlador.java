package com.strikethenote.springmarket.controladores;

import org.springframework.stereotype.Controller;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

import com.strikethenote.springmarket.entidades.Compra;
import com.strikethenote.springmarket.entidades.ItemCarrito;
import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.entidades.Usuario;
import com.strikethenote.springmarket.servicios.CompraServicio;
import com.strikethenote.springmarket.servicios.ProductoServicio;
import com.strikethenote.springmarket.servicios.UsuarioServicio;

@Controller
@RequestMapping(value = "/compra")
public class CompraControlador {
	@Autowired
	private ProductoServicio productoServicio;
	@Autowired
	private CompraServicio compraServicio;
	@Autowired
	private UsuarioServicio usuarioServicio;

	@GetMapping("/carrocompra")
	public String product(Model model, HttpSession session) {
		if (session.getAttribute("carrito") != null) {
			// Se recoge la lista de carritos de la session
			Set<Producto> carrito = (Set<Producto>) session.getAttribute("carrito");

			// Añadimos la lista al modelo
			model.addAttribute("carrito", carrito);

			//
			Double totalConDescuento = 0.0;
			Double precioSegunCantidad = 0.0;
			Double total = 0.0;

			for (Producto aux : carrito) {
				precioSegunCantidad = (aux.getPrecioProducto() * aux.getCantidadProducto());
				total += precioSegunCantidad;
				totalConDescuento += precioSegunCantidad - precioSegunCantidad * (aux.getDescuentoProducto() / 100);
			}

			// Redondeamos a 2 decimales el descuento en %
			Double descuentoObtenido = Math.round(((total - totalConDescuento) * 100) / total * 100.0) / 100.0;

			// Redondeamos a 2 decimales el precio final
			totalConDescuento = Math.round(totalConDescuento * 100.0) / 100.0;

			model.addAttribute("totalConDescuento", totalConDescuento);
			session.setAttribute("totalcondescuento", totalConDescuento);
			session.setAttribute("carrito", carrito);
			session.setAttribute("descuentoObtenido", descuentoObtenido);
			return "carrocompra";
		} else
			return "redirect:/usuario/login";

	}

	@PostMapping("/add/{idProducto}")
	public String buscarProducto(HttpServletRequest request, @PathVariable("idProducto") long idProducto) {

		if (request.getSession().getAttribute("carrito") != null) {

			// Se recoge la cantidad del producto
			Integer cantidadproducto = Integer.parseInt(request.getParameter("cantidadproducto"));

			// Meter el carrito en lista
			Set<Producto> carrito = (Set<Producto>) request.getSession().getAttribute("carrito");

			// Se obtiene el producto, se le agrega la cantidad y al carrito
			Producto productoNuevo = productoServicio.obtenerProducto(idProducto);
			
			// Bucle para recorrer el carrito y comprobar si existe ya el producto
			// Si ya existe, actualiza la cantidad
			// Si no, lo añade
			// Mejor con equals? Seguramente
			
//			Integer cantidadtotal = 0;
//			for (int i=0; i<=carrito.size(); i++) {
//				if (carrito.contains(productoNuevo)) {
//					cantidadtotal += cantidadproducto;
//					productoNuevo.setCantidadProducto(cantidadtotal);
//				} else {
//					productoNuevo.setCantidadProducto(cantidadtotal);
//					carrito.add(productoNuevo);
//				}
//			}
			
			productoNuevo.setCantidadProducto(cantidadproducto);
			carrito.add(productoNuevo);
			request.getSession().setAttribute("carrito", carrito);

			return "redirect:/index";
		} else
			return "redirect:/usuario/login";

	}

	@PostMapping("/eliminar/{idProducto}")
	public String eliminarProducto(HttpServletRequest request, @PathVariable("idProducto") long idProducto) {
		// Se pasa por session el producto y se elimina del carrito a partir de su id
		Set<Producto> carrito = (Set<Producto>) request.getSession().getAttribute("carrito");
		// try {

		Set<Producto> carritoresultante = new HashSet<Producto>();
		for (Producto p : carrito) {
			// Recuperamos las cantidades de cada producto
			Producto aux = productoServicio.obtenerProducto(p.getIdProducto());
			aux.setCantidadProducto(p.getCantidadProducto());
			carritoresultante.add(aux);
		}

		// Se recorre el nuevo carrito para eliminar en él los productos y se sube a
		// session como carrito

		Iterator<Producto> it = carritoresultante.iterator();

		// Borramos los productos en el it
		while (it.hasNext()) {
			Producto aux = it.next();
			if (aux.getIdProducto() == idProducto)
				it.remove();
		}

		// Volvemos a pasar los productos actualizados de it al set

		while (it.hasNext()) {
			Producto aux = it.next();
			carritoresultante.add(aux);
		}

		/*
		 * for (int i = 0; i < carritoresultante.size(); ++i) { Producto aux = new
		 * Producto(); if (aux.getIdProducto() == idProducto)
		 * carritoresultante.remove(aux); }
		 */
		request.getSession().setAttribute("carrito", carritoresultante);
		return "redirect:/compra/carrocompra";

		// } catch (ConcurrentModificationException e) {
		// System.out.println("Fallo enorme");
		// return null;
		// }

	}

	@PostMapping("/finalizar")
	public String finalizarCompra(HttpServletRequest request) {
		Set<Producto> carrito = (Set<Producto>) request.getSession().getAttribute("carrito");
		Double totaldescuento = (Double) request.getSession().getAttribute("totalcondescuento");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		Double descuentoObtenido = (Double) request.getSession().getAttribute("descuentoObtenido");

		Set<Producto> carritofinal = new HashSet<Producto>();
		for (Producto p : carrito) {
			Producto aux = productoServicio.obtenerProducto(p.getIdProducto());
			// Recuperamos las cantidades de cada producto
			aux.setCantidadProducto(p.getCantidadProducto());
			carritofinal.add(aux);
		}

		Compra c = new Compra();
		c.setPrecioCompra(totaldescuento);
		c.setProductos(carritofinal);
		c.setUsuario(usuarioServicio.obtenerUsuario(usuario.getIdUsuario()));
		c.setDescuentoCompra(descuentoObtenido);

		Set<Producto> carritonuevo = new HashSet<Producto>();
		request.getSession().setAttribute("carrito", carritonuevo);
		Compra compra = compraServicio.crearCompra(c);

		return "redirect:/usuario/userid/" + usuario.getIdUsuario();
	}

	@GetMapping("/compraid/{idCompra}")
	public String compraid(Model model, HttpSession session, @PathVariable("idCompra") long idCompra) {
		Compra resultado = compraServicio.obtenerCompra(idCompra);
		// Añadimos la compra al objeto para mostrarla en detalle en su página
		model.addAttribute("compra", resultado);
		return "compraid";
	}

	@PostMapping("/devolver/{idCompra}")
	public String devolverCompra(HttpServletRequest request, @PathVariable("idCompra") long idCompra) {
		// Se pasa por session la compra y se elimina del carrito a partir de su id
		List<Compra> compras = (List<Compra>) request.getSession().getAttribute("compras");
		try {
			for (Compra aux : compras) {
				if (aux.getIdCompra() == idCompra) {
					compraServicio.eliminarCompra(idCompra);
				}
			}
			request.getSession().setAttribute("compras", compras);

			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

			return "redirect:/usuario/userid/" + usuario.getIdUsuario();
		} catch (ConcurrentModificationException e) {
			System.out.println("Fallo catastrófico");
			return null;
		}

	}
}
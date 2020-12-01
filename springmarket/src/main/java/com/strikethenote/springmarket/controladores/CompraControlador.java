package com.strikethenote.springmarket.controladores;

import org.springframework.stereotype.Controller;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
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
			Integer cantidad = (Integer) session.getAttribute("cantidadproducto");

			// Añadimos la lista al modelo
			model.addAttribute("carrito", carrito);
			model.addAttribute("cantidad", cantidad);

			//
			Double totalConDescuento = 0.0;
			Double precioSegunCantidad = 0.0;

			for (Producto aux : carrito) {
				precioSegunCantidad = (aux.getPrecioProducto() * cantidad);
				totalConDescuento += precioSegunCantidad - precioSegunCantidad * (aux.getDescuentoProducto() / 100);
			}
			// TODO
			// Double descuentoObtenido =
			// ((precioSegunCantidad-totalConDescuento)*100)/precioSegunCantidad;

			model.addAttribute("cantidad", cantidad);
			model.addAttribute("totalConDescuento", totalConDescuento);
			session.setAttribute("totalcondescuento", totalConDescuento);
			session.setAttribute("carrito", carrito);
			return "carrocompra";
		} else
			return "redirect:/usuario/login";

	}

	@PostMapping("/add/{idProducto}")
	public String buscarProducto(HttpServletRequest request, @PathVariable("idProducto") long idProducto) {

		if (request.getSession().getAttribute("carrito") != null) {

			// Se recoge la cantidad del producto
			Integer cantidadproducto = Integer.parseInt(request.getParameter("cantidadproducto"));
			request.getSession().setAttribute("cantidadproducto", cantidadproducto);

			// Crea carrito con el producto
			// ItemCarrito item = new
			// ItemCarrito(productoServicio.obtenerProducto(idProducto), cantidadproducto);

			// Meter el carrito en lista
			Set<Producto> carrito = (Set<Producto>) request.getSession().getAttribute("carrito");
			carrito.add(productoServicio.obtenerProducto(idProducto));
			request.getSession().setAttribute("carrito", carrito);

			return "redirect:/index";
		} else
			return "redirect:/usuario/login";

	}

	@PostMapping("/eliminar/{idProducto}")
	public String eliminarProducto(HttpServletRequest request, @PathVariable("idProducto") long idProducto) {
		// Se pasa por session el producto y se elimina del carrito a partir de su id
		Set<Producto> carrito = (Set<Producto>) request.getSession().getAttribute("carrito");
		try {
			for (Producto aux : carrito) {
				if (aux.getIdProducto() == idProducto) {
					carrito.remove(aux);
				}

			}

			Set<Producto> carritoresultante = new HashSet<Producto>();
			for (Producto p : carrito)
				carritoresultante.add(productoServicio.obtenerProducto(p.getIdProducto()));
			request.getSession().setAttribute("carrito", carritoresultante);
			return "redirect:/compra/carrocompra";

		} catch (ConcurrentModificationException e) {
			System.out.println("Fallo enorme");
			return null;
		}

	}

	@PostMapping("/finalizar")
	public String finalizarCompra(HttpServletRequest request) {
		Set<Producto> carrito = (Set<Producto>) request.getSession().getAttribute("carrito");
		Double totaldescuento = (Double) request.getSession().getAttribute("totalcondescuento");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

		Set<Producto> carritofinal = new HashSet<Producto>();
		for (Producto p : carrito)
			carritofinal.add(productoServicio.obtenerProducto(p.getIdProducto()));

		Compra c = new Compra();
		c.setPrecioCompra(totaldescuento);
		c.setProductos(carritofinal);
		c.setUsuario(usuarioServicio.obtenerUsuario(usuario.getIdUsuario()));

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
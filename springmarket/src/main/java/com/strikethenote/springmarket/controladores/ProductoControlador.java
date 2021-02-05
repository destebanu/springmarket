package com.strikethenote.springmarket.controladores;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.strikethenote.springmarket.entidades.Pregunta;
import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.entidades.Respuesta;
import com.strikethenote.springmarket.servicios.PreguntaServicio;
import com.strikethenote.springmarket.servicios.ProductoServicio;
import com.strikethenote.springmarket.servicios.RespuestaServicio;

@Controller
@RequestMapping(value = "/product")
public class ProductoControlador {

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private PreguntaServicio preguntaServicio;

	@Autowired
	private RespuestaServicio respuestaServicio;

	// Métodos get y post

	@GetMapping("/productcreate")
	public String product(Model model, HttpSession session, Authentication authentication) {
		boolean esAdmin = false;
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("admin")) {
				esAdmin = true;
				break;
			}
		}

		if (esAdmin)
			return "productcreate";
		else
			return "redirect:/index/";
	}

	@PostMapping("/productcreate")
	public String crearProducto(HttpServletRequest request, Authentication authentication) {
		boolean esAdmin = false;
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("admin")) {
				esAdmin = true;
				break;
			}
		}

		if (esAdmin) {
			String nombreProducto = request.getParameter("nombreproducto");
			String descripcionProducto = request.getParameter("descripcionproducto");
			Double precioProducto = Double.parseDouble(request.getParameter("precioproducto"));
			Double descuentoProducto = Double.parseDouble(request.getParameter("descuentoproducto"));

			// Pone la primera letra en mayúscula
			nombreProducto = nombreProducto.substring(0, 1).toUpperCase() + nombreProducto.substring(1);

			Producto p = new Producto();
			p.setNombreProducto(nombreProducto);
			p.setDescripcionProducto(descripcionProducto);
			p.setPrecioProducto(precioProducto);
			p.setDescuentoProducto(descuentoProducto);
			Producto producto = productoServicio.crearProducto(p);

			return "redirect:/product/productid/" + producto.getIdProducto();

		} else {
			return "redirect:/index/";
		}
	}

	@PostMapping("/productsearch")
	public String buscarProducto(HttpServletRequest request) {

		// Se recoge el nombre del input y se pasa a la session
		String nombreProducto = request.getParameter("nombreproducto");

		request.getSession().setAttribute("nombreproducto", nombreProducto);
		return "redirect:/product/results";
	}

	@GetMapping("/results")
	public String devolverResultados(Model model, HttpSession session) {
		// Se recoge el input de la búsqueda de la seesion y se usa el servicio para
		// buscar en la tabla
		String aux = (String) session.getAttribute("nombreproducto");

		// Recogemos el valor en una lista de productos.

		List<Producto> productos = productoServicio.buscarPorNombre(aux);

		// Añadimos la lista al modelo
		model.addAttribute("productos", productos);

		return "results";
	}

	// Métodos para mostrar producto con sus carácterísticas y borrarlo

	@GetMapping("/productid/{idProducto}")
	public String productid(Model model, HttpSession session, @PathVariable("idProducto") long idProducto) {
		// Se recoge el input de la búsqueda de la session y se usa el servicio para
		// buscar en la tabla
		Producto resultado = productoServicio.obtenerProducto(idProducto);
		// Añadimos el producto al objeto para mostrar su nombre en la página de
		// resultados de búsqueda
		model.addAttribute("producto", resultado);
		return "productid";
	}

	@PostMapping("/borrar/{idProducto}")
	public String borrarProducto(HttpServletRequest request, @PathVariable("idProducto") long idProducto) {
		// Se pasa por session el producto y se borra de la bbdd a partir de su id
		productoServicio.eliminarProducto(idProducto);
		return "redirect:/index";
	}

	// Métodos Q&A

	// Este método persiste preguntas
	@PostMapping("/pregunta/{idProducto}")
	@ResponseBody
	public String publicarPregunta(@RequestParam("pregunta") String pregunta,
			@PathVariable("idProducto") long idProducto, HttpServletRequest request) {
//		String pregunta = request.getParameter("pregunta");
//		String idUsuario = request.getParameter("idUsuario");

		// Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
		LocalDate fecha = LocalDate.now();
		Pregunta p = new Pregunta("pregunta", fecha);

		Boolean guardarPregunta = preguntaServicio.guardarPregunta(p);
		if (guardarPregunta) {
			return "redirect:/index";
		} else
			return "redirect:/product/productid/" + idProducto;
		/*
		 * if (guardarPregunta) { return "true"; } else { return "false"; }
		 */
	}

	// Este método persiste respuestas
	@RequestMapping(value = "/productid/{idProducto}", method = RequestMethod.POST)
	@ResponseBody
	public String publicarRespuesta(@PathVariable("respuesta") String respuesta) {
		LocalDate fecha = LocalDate.now();
		Respuesta r = new Respuesta(respuesta, fecha);

		Boolean guardarRespuesta = respuestaServicio.guardarRespuesta(r);

		if (guardarRespuesta) {
			return "true";
		} else {
			return "false";
		}
	}

}
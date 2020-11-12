package com.strikethenote.springmarket.controladores;

import java.util.ArrayList;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.strikethenote.springmarket.dao.ProductoDao;
import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.servicios.ProductoServicio;

@Controller
//@RequestMapping(value = "/product")  --> Error al buscar /pagina
	public class ProductoControlador {
		
		@Autowired
		private ProductoServicio productoServicio;
		
		// Métodos get y post
		
		@GetMapping("/index")
		public String index (Model model) {
			// Se añaden a una lista los productos de la bbdd a una lista
			List<Producto> productos = productoServicio.listarProductos();
			
			// Añadimos la lista al modelo para mostrarla en index
			model.addAttribute("productos", productos);
			
			return "index";
		}

/*	@PostMapping("/index")
		public String persistMessage (HttpServletRequest request) {
				return "product";
		}
*/		
		@GetMapping("/product")
		public String product (Model model, HttpSession session) {
			return "product";
		}
		
		@PostMapping("/productsearch")
		public String buscarProducto (HttpServletRequest request){
			String nombreProducto = request.getParameter("nombreproducto");
			request.getSession().setAttribute("nombreproducto", nombreProducto);
			return "redirect:results";
		}
		
		@PostMapping("/productcreate")
		public String crearProducto (HttpServletRequest request){
			String nombreProducto = request.getParameter("nombreproducto");
			String descripcionProducto = request.getParameter("descripcionproducto");
			Double precioProducto = Double.parseDouble(request.getParameter("precioproducto"));
			Double descuentoProducto = Double.parseDouble(request.getParameter("descuentoproducto"));
			
			Producto p = new Producto();
			p.setNombreProducto(nombreProducto);
			p.setDescripcionProducto(descripcionProducto);
			p.setPrecioProducto(precioProducto);
			p.setDescuentoProducto(descuentoProducto);
			Producto producto = productoServicio.crearProducto(p);
			
			return "redirect:/product";
		}
		
		@GetMapping("/results")
		public String devolverResultados (Model model, HttpSession session) {
			// Se recoge el input de la búsqueda de la seesion y se usa el servicio para buscar en la tabla
			String aux = (String) session.getAttribute("nombreproducto");
			
			// Nuestro método solo recibe un producto en lugar de una lista, por lo que recogemos el valor en un producto.
			Producto resultado = productoServicio.buscarPorNombre(aux);
			
			// Añadimos el producto al objeto para mostrar su nombre en la página de resultados de búsqueda
			model.addAttribute("resultado", resultado);
			
			
			return "results";
			
			
/*			ModelAndView mav = new ModelAndView();

			List<Producto> productos = productoServicio.listarProductos();

			mav.addObject("productos", productos);
			mav.setViewName("producto/results");
			return mav;
*/		}
		
		@PostMapping("/results")
		public String persistMessageResults (HttpServletRequest request){
			return "results";
		}
		
		// Métodos para mostrar producto con sus carácterísticas y borrarlo
		
		@GetMapping("/productid")
		public String productid (Model model, HttpSession session) {
			return "productid";
		}
		
		@PostMapping("/borrar")
		public String borrarproducto (HttpServletRequest request){
			// Se pasa por session el producto y se borra de la bbdd a partir de su id
			//productoServicio.eliminarProducto(session.getAttribute("producto").idProducto);
			
			return "reditect:/product";
		}
		
		
		// TODO método para la búsqueda usando contains de String o similar
		
/*		@PostMapping("/persistMessage")
		public String index (@RequestParam("producto") String name, HttpServletRequest request) {
					
			request.getSession().setAttribute("r0", name);
			return "redirect:/product";
		}
		
		// Los métodos para acceder a la página genérica de producto, donde se recibiría el producto seleccionado y/o se buscan
	
		@GetMapping("/product")
		public String product (Model model, HttpSession session) {
			
			return "index";
		}
		
		
		@PostMapping("/generalproduct")
		public String product (@RequestParam("producto") String name, HttpServletRequest request) {
							
			request.getSession().setAttribute("r0", name);
			return "redirect:/index";
		}
 
 */
}
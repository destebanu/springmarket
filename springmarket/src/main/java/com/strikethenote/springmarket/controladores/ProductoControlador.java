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
@RequestMapping(value = "/product")
	public class ProductoControlador {
		
		@Autowired
		private ProductoServicio productoServicio;
		
		// Métodos get y post
		
		@GetMapping("/results")
		public String results (Model model, HttpSession session) {
			return "results";
		}
		
		@GetMapping("/index")
		public String index (Model model) {
			return "index";
		}

		@PostMapping("/index")
		public String persistMessage (HttpServletRequest request) {
				return "product";
		}
		
		@GetMapping("/productsearch")
		public String productSearch (Model model, HttpSession session) {
			return "product";
		}
		
		@PostMapping("/productsearch")
		public String persistMessageProductSearch (HttpServletRequest request){
			
			return "results";
		}
		
		@GetMapping("/results")
		public ModelAndView devolverResultados () {
			ModelAndView mav = new ModelAndView();

			List<Producto> productos = productoServicio.listarProductos();

			mav.addObject("productos", productos);
			mav.setViewName("producto/results");
			return mav;
		}
		
		@PostMapping("/results")
		public String persistMessageResults (HttpServletRequest request){
			return "results";
		}
		
		@GetMapping("/productcreate")
		public String productCreate (Model model, HttpSession session) {
			return "product";
		}
		
		@PostMapping("/productcreate")
		public String crearProducto (HttpServletRequest request){
			String nombreProducto = request.getParameter("nombreproducto");
			String descripcionProducto = request.getParameter("descripcionproducto");
			Double precioProducto = Double.parseDouble(request.getParameter("precioproducto"));
			Double descuentoProducto = Double.parseDouble(request.getParameter("descuentoproducto"));
			
			Producto producto = new Producto();
			producto.setNombreProducto(nombreProducto);
			producto.setDescripcionProducto(descripcionProducto);
			producto.setPrecioProducto(precioProducto);
			producto.setDescuentoProducto(descuentoProducto);
			
			return "redirect:/product";
		}
		
		
		
		
		
		// TODO método para la búsqueda usando contains de String o similar
		
//		@PostMapping("/persistMessage")
//		public String index (@RequestParam("producto") String name, HttpServletRequest request) {
//					
//			request.getSession().setAttribute("r0", name);
//			return "redirect:/product";
//		}
//		
//		// Los métodos para acceder a la página genérica de producto, donde se recibiría el producto seleccionado y/o se buscan
//		
//		@GetMapping("/product")
//		public String product (Model model, HttpSession session) {
//			
//			return "index";
//		}
//		
//		
//		@PostMapping("/generalproduct")
//		public String product (@RequestParam("producto") String name, HttpServletRequest request) {
//							
//			request.getSession().setAttribute("r0", name);
//			return "redirect:/index";
//		}
}
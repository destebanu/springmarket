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
import org.springframework.web.bind.annotation.RequestParam;

import com.strikethenote.springmarket.dao.ProductoDao;
import com.strikethenote.springmarket.servicios.ProductoServicio;

@Controller
	public class ProductoControlador {
		
		@Autowired
		private ProductoServicio productoServicio;
		
		// Métodos get y post
		
		@GetMapping("/index")
		public String index (Model model, HttpSession session) {
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
			return "index";
		}
		
		@GetMapping("/productcreate")
		public String productCreate (Model model, HttpSession session) {
			return "product";
		}
		
		@PostMapping("/productcreate")
		public String persistMessageProductCreate (HttpServletRequest request){
			return "index";
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
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

	@Controller
	public class Controlador {
		
		// Los métodos para acceder al índice de "Strike a Note", la página principal
		
		@GetMapping("/index")
		public String index (Model model, HttpSession session) {
			
			return "index";
		}
		
		
		@PostMapping("/persistMessage")
		public String index (@RequestParam("producto") String name, HttpServletRequest request) {
					
			request.getSession().setAttribute("r0", name);
			return "redirect:/product";
		}
		
		// Los métodos para acceder a la página genérica de producto, donde se recibiría el producto seleccionao y/o se buscan
		
		@GetMapping("/product")
		public String product (Model model, HttpSession session) {
			
			return "index";
		}
		
		
		@PostMapping("/generalproduct")
		public String product (@RequestParam("producto") String name, HttpServletRequest request) {
							
			request.getSession().setAttribute("r0", name);
			return "redirect:/index";
		}
}
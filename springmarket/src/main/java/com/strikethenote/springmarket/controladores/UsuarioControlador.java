package com.strikethenote.springmarket.controladores;

import org.springframework.stereotype.Controller;

import java.util.List;

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
import com.strikethenote.springmarket.entidades.Usuario;
import com.strikethenote.springmarket.servicios.UsuarioServicio;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	// Métodos get y post
	
	@GetMapping("/signup")
	public String signup(Model model, HttpSession session) {
		return "signup";
	}

	@PostMapping("/signup")
	public String darseDeAlta(HttpServletRequest request) {
		return "redirect:/usuario/signup";
	}

	@GetMapping("/login")
	public String login(Model model, HttpSession session) {
		return "login";
	}

	@PostMapping("/login")
	public String iniciarSesion(HttpServletRequest request) {
		return "redirect:/usuario/login";
	}
	
	@PostMapping("/logout")
	public String cerrarSesion(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/index";
	}
	
	@GetMapping("/userid/{idUsuario}")
	public String usuarioid(Model model, HttpSession session, @PathVariable("idUsuario") long idUsuario) {
		// Se recoge el input de la búsqueda de la session y se usa el servicio para
		// buscar en la tabla
		Usuario resultado = usuarioServicio.obtenerUsuario(idUsuario);
		model.addAttribute("usuario", resultado);
		return "userid";
	}

}

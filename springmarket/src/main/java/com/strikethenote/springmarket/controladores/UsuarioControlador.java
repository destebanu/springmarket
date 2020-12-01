package com.strikethenote.springmarket.controladores;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
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

import com.strikethenote.springmarket.entidades.Compra;
import com.strikethenote.springmarket.entidades.ItemCarrito;
import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.entidades.Usuario;
import com.strikethenote.springmarket.servicios.CompraServicio;
import com.strikethenote.springmarket.servicios.UsuarioServicio;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;
	@Autowired
	private CompraServicio compraServicio;

	// Métodos get y post

	@GetMapping("/signup")
	public String signup(Model model, HttpSession session) {
		return "signup";
	}

	@PostMapping("/signup")
	public String darseDeAlta(HttpServletRequest request) {
		String nombreUsuario = request.getParameter("nombreusuario");
		String apellidosUsuario = request.getParameter("apellidosusuario");
		String passwordUsuario = request.getParameter("passwordusuario");
		String emailUsuario = request.getParameter("emailusuario");

		// Concatenamos la fecha en un solo String
		String diaNacimiento = request.getParameter("dianacimientousuario");
		String mesNacimiento = request.getParameter("mesnacimientousuario");
		String anioNacimiento = request.getParameter("anionacimientousuario");
		String concatenarFechaNac = diaNacimiento + "/" + mesNacimiento + "/" + anioNacimiento;

		String numtarjetaUsuario = request.getParameter("numerotarjetausuario");
		String titularUsuario = request.getParameter("titulartarjetausuario");
		String codsegUsuario = request.getParameter("codigoseguridadtarjetausuario");
		String direcfactUsuario = request.getParameter("direccionfacturacionusuario");

		Usuario u = new Usuario();
		u.setNombreUsuario(nombreUsuario);
		u.setApellidosUsuario(apellidosUsuario);
		u.setPasswordUsuario(passwordUsuario);
		u.setEmailUsuario(emailUsuario);
		u.setFechanacUsuario(concatenarFechaNac);
		u.setNumtarjetaUsuario(numtarjetaUsuario);
		u.setTitularUsuario(titularUsuario);
		u.setCodsegUsuario(codsegUsuario);
		u.setDirecfactUsuario(direcfactUsuario);
		Usuario usuario = usuarioServicio.crearUsuario(u);

		return "redirect:/usuario/userid/" + usuario.getIdUsuario();
	}

	@GetMapping("/login")
	public String login(Model model, HttpSession session) {
		Boolean error = false;

		model.addAttribute("error", error);

		return "login";
	}

	@PostMapping("/login")
	public String iniciarSesion(Model model, HttpServletRequest request, HttpSession session) {

		// Recogemos los valores del formulario
		String emailUsuario = request.getParameter("emailusuario");
		String passwordUsuario = request.getParameter("passwordusuario");

		Usuario buscado = usuarioServicio.buscarPorEmail(emailUsuario);

		// Comprobamos si el email y el password son correctos buscando el usuario
		if ((buscado != null)) {

			if (buscado.getPasswordUsuario().equals(passwordUsuario)) {
				Set<Producto> carrito = new HashSet<Producto>();
				session.setAttribute("carrito", carrito);
				session.setAttribute("usuario", buscado);
				return "redirect:/index";

			}
		}

		Boolean error = true;

		model.addAttribute("error", error);

		return "login";

		// Se debería dar feedback si el password es erróneo o si el email no existe
		// Si es correcto lleva a inicio, y si no a login
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

			// Recogemos las compras del usuario
			List<Compra> compras = compraServicio.buscarUsuario(resultado);
			session.setAttribute("compras", compras);
			model.addAttribute("compras", compras);

			return "userid";
	}

}

package com.strikethenote.springmarket.seguridad;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.strikethenote.springmarket.entidades.Usuario;
import com.strikethenote.springmarket.servicios.UsuarioServicio;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
  
	
	@Autowired
	private UsuarioServicio usuarioServicio;
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		HttpSession session = request.getSession();
		Usuario authUser = usuarioServicio.buscarPorNombre(userDetails.getUsername());
		//Esto de debajo no sé si lo necesitamos
		//session.setAttribute("username", authUser.getUsername());
		session.setAttribute("nombre", authUser.getNombreUsuario());
		session.setAttribute("idUsuario", authUser.getIdUsuario());

		boolean esRegistrado = false;
		boolean esAdmin = false;
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("registrado")) {
				esRegistrado = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("admin")) {
				esAdmin = true;
				break;
			}
		}

		String targetUrl;
		if (esRegistrado) {
			targetUrl = "/usuario/userid/" + authUser.getIdUsuario();
		} else if (esAdmin) {
			targetUrl = "/index";
		} else {
			throw new IllegalStateException();
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
    

}
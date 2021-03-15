package com.strikethenote.springmarket.servicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.strikethenote.springmarket.dao.RolRepository;
import com.strikethenote.springmarket.dao.UsuarioDao;
import com.strikethenote.springmarket.entidades.Rol;
import com.strikethenote.springmarket.entidades.Usuario;
import com.strikethenote.springmarket.servicios.UsuarioServicio;
import com.strikethenote.springmarket.servicios.UsuarioServicioImpl;

public class UsuarioServicioImplTest {

	UsuarioDao usuariodao = Mockito.mock(UsuarioDao.class);
	RolRepository rolrepository = Mockito.mock(RolRepository.class);
	BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
	
	@Autowired
	UsuarioServicio usuarioServicio = new UsuarioServicioImpl(usuariodao, rolrepository, bCryptPasswordEncoder);

	@BeforeEach
	void setUp() {

		Rol r1 = new Rol();
		r1.setIdRol(2);
		r1.setNombreRol("Mariliendres");
		Set<Usuario> usuarios = new HashSet<Usuario>();
		r1.setUsuarios(usuarios);

		Usuario u1 = new Usuario();
		u1.setIdUsuario(1L);
		u1.setNombreUsuario("Dolores");
		u1.setApellidosUsuario("Fuertes De Barriga");
		u1.setEmailUsuario("lalola@madriz.es");
		u1.setCodsegUsuario("47825798");
		u1.setDirecfactUsuario("Calle Desengaño");
		u1.setFechanacUsuario("11/03/1980");
		u1.setPasswordUsuario("1234");
		u1.setTitularUsuario("La Lola");
		u1.setNumtarjetaUsuario("XXXXXX");
		u1.setCompras(null);
		Set<Rol> roles = new HashSet<Rol>();		
		u1.setRoles(roles);
		u1.anadirRol(r1);

		List<Usuario> usuariosTesters = new ArrayList<Usuario>();

		usuariosTesters.add(u1);

		Mockito.when(usuarioServicio.obtenerUsuario(1L)).thenReturn(u1);
		Mockito.when(usuarioServicio.listarUsuarios()).thenReturn(usuariosTesters);
		
	}

	// ¡Este funciona!
	@Test
	void obtenerUsuarioTest() {
		Usuario t1 = usuarioServicio.obtenerUsuario(1L);

		Assertions.assertEquals(1L, t1.getIdUsuario());
		Assertions.assertEquals("Dolores", t1.getNombreUsuario());

	}

	// ¡Este funciona!
	@Test
	void listarUsuariosTest() {
		Iterable<Usuario> iterResultado = usuarioServicio.listarUsuarios();
		ArrayList<Usuario> listaTest = new ArrayList<Usuario>();
		iterResultado.forEach(listaTest::add);

		Assertions.assertEquals(1L, listaTest.get(0).getIdUsuario());
		Assertions.assertEquals("Dolores", listaTest.get(0).getNombreUsuario());
		Assertions.assertEquals("Fuertes De Barriga", listaTest.get(0).getApellidosUsuario());
		
	}

}

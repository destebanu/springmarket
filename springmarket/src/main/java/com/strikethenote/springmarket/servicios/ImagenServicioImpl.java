package com.strikethenote.springmarket.servicios;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.strikethenote.springmarket.dao.ImagenRepository;
import com.strikethenote.springmarket.dao.ProductoDao;
import com.strikethenote.springmarket.dao.ProductoDaoImpl;
import com.strikethenote.springmarket.dao.UsuarioDao;
import com.strikethenote.springmarket.entidades.Imagen;
import com.strikethenote.springmarket.entidades.Producto;
import com.strikethenote.springmarket.entidades.Usuario;

@Transactional
@Service
public class ImagenServicioImpl implements ImagenServicio {

	@Autowired
	private ImagenRepository imagenRepository;

	@Autowired
	private ProductoDaoImpl productoDaoImpl;

	public int guardarImagen(Imagen img) {
		try {
			imagenRepository.save(img);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public Imagen obtenerImagen(Long id) {
		Imagen findById = imagenRepository.findById(id).orElse(null);

		if (findById != null) {
			Imagen getImageDetails = findById;
			return findById;
		} else {
			return null;
		}
	}

	public Boolean actualizarImagen(long idProducto, MultipartFile file) {

		Producto p = productoDaoImpl.buscar(idProducto);

		if (p == null || file.isEmpty())
			return false;
		try {
			byte[] image = file.getBytes();
			if (p.getImagen() != null) {

				Imagen linkedimg = p.getImagen();				
				linkedimg.setImagen(image);
				p.setImagen(linkedimg);
				productoDaoImpl.actualizar(p);
				return true;
				

			} else {
				Imagen img = new Imagen("foto", image);
				p.addImagen(img);
				imagenRepository.save(img);
//				productoDaoImpl.actualizar(p);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
//	public Boolean actualizarImagen(long idProducto, MultipartFile file) {
//
//		Producto p= productoDaoImpl.buscar(idProducto);
//
//		if (p == null)
//			return false;
//		try {
//			byte[] image = file.getBytes();
//			if (!p.getImagen().isEmpty()) {
//
//				Set<Imagen> linkedimg = p.getImagen();
//				for (Imagen a : linkedimg) {
//					a.setImagen(image);
//					imagenRepository.save(a);
//					return true;
//				}
//				return null;
//
//			} else {
//				Imagen img = new Imagen("foto", image);
//				p.addImagen(img);
//				productoDaoImpl.actualizar(p);
//				return true;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}

}

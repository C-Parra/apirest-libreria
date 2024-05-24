package com.company.books.backend.service;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.model.Libro;
import com.company.books.backend.model.dao.ILibroDao;
import com.company.books.backend.response.LibroResponseRest;



@Service
public class LibroServiceImpl implements ILibroService{
	
	private static final Logger log = LoggerFactory.getLogger(LibroServiceImpl.class);
	
	@Autowired
	private ILibroDao libroDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> buscarLibros() {
		
		log.info("inicio metodo buscarLibros()");
		
		LibroResponseRest response = new LibroResponseRest();
		
		try {
			
			List<Libro> libro = (List<Libro>) libroDao.findAll();
			
			response.getLibroResponse().setLibro(libro);
			
			response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
			
		} catch (Exception e) {
			
			response.setMetada("Respuesta no ok", "-1", "Error al consultar libros");
			log.error("error al consultar libros: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //devuelve 500
			
		}
		
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); //devuelve 200
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> buscarPorId(Long id) {
		
		log.info("Inicio metodo buscarPorId");
		
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		
		try {
			
			Optional<Libro> libro = libroDao.findById(id);
			
			if (libro.isPresent()) {
				list.add(libro.get());
				response.getLibroResponse().setLibro(list);
			} else {
				log.error("Error al consultar libro");
				response.setMetada("Respuesta no ok", "-1", "Libro no encontrado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);//DEVUELVE 404
			}
			
		} catch (Exception e){
			
			log.error("Error al consultar libro");
			response.setMetada("Respuesta no ok", "-1", "Error al consultar libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //devuelve 500
			
		}
		
		response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); //devuelve 200
	}
	
	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> crear(Libro libro) {
		
		log.info("Inicio metodo crear");
		
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		
		try {
			
			Libro libroGuardado = libroDao.save(libro);
			
			if(libroGuardado != null) {
				
				list.add(libroGuardado);
				response.getLibroResponse().setLibro(list);
				
			} else {
				
				log.error("Error al crear nuevo libro");
				response.setMetada("Respuesta no ok", "-1", "Libro no creado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e){
			
			log.error("Error al crear libro");
			response.setMetada("Respuesta no ok", "-1", "Error al crear libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //devuelve 500
			
		}
		
		response.setMetada("Respuesta ok", "00", "Libro creado");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); //devuelve 200
	}
	
	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> update(Libro libro, Long id) {
		
		log.info("Inicio metodo actualizar");
		
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		
		try {
			
			Optional<Libro> libroBuscado = libroDao.findById(id);
			
			
			if(libroBuscado.isPresent()) {
				
				libroBuscado.get().setNombre(libro.getNombre());
				libroBuscado.get().setDescripcion(libro.getDescripcion());
				libroBuscado.get().setCategoria(libro.getCategoria());
				
				Libro libroActualizar = libroDao.save(libroBuscado.get());// actualizando
				
				if (libroActualizar != null) {
					
					response.setMetada("Respuesta ok", "00", "Libro actualizado");
					list.add(libroActualizar);
					response.getLibroResponse().setLibro(list);		
					
				} else {
					log.error("error al actualizar libro");
					response.setMetada("Respuesta no ok", "-1", "Libro no actualizado");
					return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				
				log.error("Error al actualizar libro");
				response.setMetada("Respuesta no ok", "-1", "Libro no actualizado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);//DEVUELVE 404
			}
			
		} catch (Exception e){
			
			log.error("Error al actualizar libro", e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta no ok", "-1", "Error al actualizar libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //devuelve 500
			
		}
	
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); //devuelve 200
	}
	
	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> delete(Long id) {
		
		log.info("Inicio metodo eliminar libro");
		
		LibroResponseRest response = new LibroResponseRest();
		
try {
			//eliminamos por id
	
			libroDao.deleteById(id);
			
			response.setMetada("Respuesta ok", "00", "Libro eliminado");
			
		} catch (Exception e){
			
			log.error("Error al eliminar libro", e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta no ok", "-1", "Error al eliminar libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //devuelve 500
			
		}
		
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); //devuelve 200
	}
}

package com.company.books.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICatergoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;

public class CategoriaServiceImplTest {
	
	@InjectMocks
	CategoriaServiceImpl service;
	
	@Mock
	ICatergoriaDao categoriaDao;
	
	List<Categoria> list = new ArrayList<Categoria>();
	
	@BeforeEach
	public void init() {
		
		MockitoAnnotations.openMocks(this);
		this.cargarCategorias();
		
	}
	
	@Test
	public void buscarCategoriasTest() {
		
		when(categoriaDao.findAll()).thenReturn(list);
		
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
		
		verify(categoriaDao, times(1)).findAll();
		
	}
	
	public void cargarCategorias() {
		
		Categoria catUno = new Categoria(Long.valueOf(1), "Comida", "Algo de comida");
		Categoria catDos = new Categoria(Long.valueOf(1), "Pollo", "Recetas de pollo");
		Categoria catTres = new Categoria(Long.valueOf(1), "Carne", "Cosas malas de la carne");
		Categoria catCuatro = new Categoria(Long.valueOf(1), "Aguardiente", "Licores");
		
		list.add(catUno);
		list.add(catDos);
		list.add(catTres);
		list.add(catCuatro);
		
	}

}

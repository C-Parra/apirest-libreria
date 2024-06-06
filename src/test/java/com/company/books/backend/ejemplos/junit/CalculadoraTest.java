package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	Calculadora calc;
	
	@BeforeAll
	public static void primero() {
		
		System.out.println("primero");
		
	}
	
	@AfterAll
	public static void despues() {
			
			System.out.println("ultimo");
			
		}
	
	@BeforeEach
	public void instanciaObjeto() {
		
		calc = new Calculadora();
		System.out.println("@BeforeEach");
		
	}
	
	@AfterEach
	public void despuesTest() {
		
		System.out.println("@AfterEach");
		
	}
	
	@Test
	@DisplayName("Prueba AssertEquals")
	@Disabled("Está prueba no se ejecutara")
	public void calculadoraAssertEqualTest() {
		
		assertEquals(2, calc.sumar(1, 1));
		assertEquals(0, calc.restar(1, 1));
		assertEquals(6, calc.multiplicar(3, 2));
		assertEquals(5, calc.dividir(10, 2));
		
		System.out.println("calculadoraAssertEqualTest");
	}
	
	@Test
	public void calculadoraTrueFalse() {
		
		assertTrue(calc.sumar(2, 6) == 8);
		assertTrue(calc.restar(8, 2) == 6);
		assertFalse(calc.multiplicar(5, 2) == 6);
		
		System.out.println("calculadoraTrueFalse");
		
	}

}

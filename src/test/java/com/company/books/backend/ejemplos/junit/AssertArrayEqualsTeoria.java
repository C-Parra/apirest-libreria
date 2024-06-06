package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssertArrayEqualsTeoria {
	
	@Test
	public void pruebaArregloIguales () {
		
		String [] arre1 = { "a", "b"};
		String [] arre2 = { "a", "b"};
		String [] arre3 = { "a", "b", "c"};
		
		//assertArrayEquals(arre1, arre3);
		assertArrayEquals(arre1, arre2);
		
	}

}

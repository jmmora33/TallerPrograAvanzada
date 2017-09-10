package Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import lalal.Contador;
/*
 * Arrange- Organizar
 * Act - Hacer Algo- Ej Contar
 * Assert -
 * Aniquilar- siempre volver a los objetos en un estado inicial
 * */
public class AppTest {

	Contador c;
	
	@Before
	public void setUp(){
		
	c = new Contador();	
	}
	
	@Test
	public void queInicieEnCero(){
		Assert.assertEquals(0, c.mostrar());
	
	}
	
	@Test
	public void queCuenteHastaUno()
	{  
			c.contar();
		
		Assert.assertEquals(1, c.mostrar());
	
	}
	@Test
	public void queCuenteHastaCien()
	{  
		for (int i = 0; i < 100; i++) {
			c.contar();
		}
		Assert.assertEquals(100, c.mostrar());
	
	}
	
	
	@Test
	public void queCuenteHastaUnMillon()
	{  
		for (int i = 0; i < 1000000; i++) {
			c.contar();
		}
		Assert.assertEquals(1000000, c.mostrar());
	
	}
	
	
	@Test 
	public void queReinicie(){
		c.contar();
		c.reiniciar();
		Assert.assertEquals(0,c.mostrar());

	}
}

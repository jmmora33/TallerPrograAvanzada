package transporteTest;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import transporte.BiciNene;
import transporte.Paquete;

public class BiciNeneTest {

private float volumen;
private float peso;
Paquete paquete;	
BiciNene nene;

	@Before
	public void setUp(){
		volumen= 20;
		peso = 100;
		paquete = new Paquete(peso,volumen);
		nene = new BiciNene(peso,volumen);
		
	}
	
	@Test
	public void quePuedaCargarUnPaquete(){
	
		Assert.assertTrue(nene.puedoCargar(paquete));
	}
	
	@Test
	public void  quePuedaAgregarCarga(){
		nene.setPesoLimite(peso*2);
		nene.setVolumenLimite(volumen*2);
	
		Assert.assertTrue(nene.cargo(paquete));
		
	}

}

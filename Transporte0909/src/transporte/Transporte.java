package transporte;



public abstract class Transporte {

	public final boolean cargar(Paquete paquete){
		
		if(!puedoCargar(paquete))
		return false;
		
			cargar(paquete);
		return true;	
		
	}
	public abstract boolean puedoCargar(Paquete paquete);
	public abstract boolean cargo(Paquete paquete);
}

package transporte;

public class BiciNene extends Transporte{


	
	@Override
	public boolean puedoCargar(Paquete paquete) {
		
		if(this.getPesoLimite() < paquete.getPeso() || this.getVolumenLimite() < paquete.getVolumen())
		return false;
		
		return true;
	}
	
	
	@Override
	public boolean cargo(Paquete paquete) {
			this.setVolumenDisponible(this.getVolumenDisponible() - paquete.getVolumen());
			this.setPesoDisponible(this.getPesoDisponible() - paquete.getPeso());
			return true;
			
	}
	
	
	public BiciNene(float pesoLimite, float volLimite){
		this.setPesoLimite(pesoLimite);
		this.setVolumenLimite(volLimite);
		this.setPesoDisponible(pesoLimite);
		this.setVolumenDisponible(volLimite);
	}
	
	public BiciNene(){
		
		this(0, 0);
	}
	
	public float getPesoLimite() {
		return pesoLimite;
	}
	public void setPesoLimite(float pesoLimite) {
		this.pesoLimite = pesoLimite;
	}
	public float getVolumenLimite() {
		return volumenLimite;
	}
	public void setVolumenLimite(float volumenLimite) {
		this.volumenLimite = volumenLimite;
	}
	


	
	public float getPesoDisponible() {
		return pesoDisponible;
	}


	public void setPesoDisponible(float pesoDisponible) {
		this.pesoDisponible = pesoDisponible;
	}


	public float getVolumenDisponible() {
		return volumenDisponible;
	}


	public void setVolumenDisponible(float volumenDisponible) {
		this.volumenDisponible = volumenDisponible;
	}




	private float pesoLimite;
	private float volumenLimite;
	private float pesoDisponible;
	private float volumenDisponible;
	
}

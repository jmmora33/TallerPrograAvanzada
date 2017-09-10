package transporte;

public class Abuela extends Transporte {

	@Override
	public boolean puedoCargar(Paquete paquete) {
		 return	this.getViajesRealizados() != 0 || this.getDistanciaLimite() < paquete.getDistancia() 
					|| this.getVolumenLimite() < paquete.getVolumen() ;
	}
	
	@Override
	public boolean cargo(Paquete paquete) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Abuela(){
		
		this(0,0);
	}
	
	public Abuela(float distLimite, float volLimite){
		
		this.setDistanciaLimite(distLimite);
		this.setViajesRealizados(0);
		this.setVolumenLimite(volLimite);
	}
	
	public void sumarViaje(){
		
		this.setViajesRealizados(this.getViajesRealizados() + 1);
	}
	
	public float getVolumenLimite() {
		return volumenLimite;
	}
	public void setVolumenLimite(float volumenLimite) {
		this.volumenLimite = volumenLimite;
	}
	public float getDistanciaLimite() {
		return distanciaLimite;
	}
	public void setDistanciaLimite(float distanciaLimite) {
		this.distanciaLimite = distanciaLimite;
	}
	public int getViajesRealizados() {
		return viajesRealizados;
	}
	public void setViajesRealizados(int viajesRealizados) {
		this.viajesRealizados = viajesRealizados;
	}
	
	private float volumenLimite;
	private float distanciaLimite;
	private int viajesRealizados;


	
}

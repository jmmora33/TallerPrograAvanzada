package transporte;

public class Paquete {
 
	
	private float peso;
	private float volumen;
	private float distancia;
	
	public Paquete(float peso, float volumen, float distancia){
	 this.setPeso(peso);
	 this.setVolumen(volumen);
	 this.setDistancia(distancia);
	}
	public Paquete(float peso, float volumen){
		 this.setPeso(peso);
		 this.setVolumen(volumen);
		 this.setDistancia(0);
		}
	
	public Paquete (){
		this(0,0,0);
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getVolumen() {
		return volumen;
	}
	public void setVolumen(float volumen) {
		this.volumen = volumen;
	}
	public float getDistancia() {
		return distancia;
	}
	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	
	
	
}

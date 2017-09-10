package transporte;

public class Aeroplano extends Transporte {

	

	@Override
	public boolean puedoCargar(Paquete paquete) {
		
		if(this.getViajesRealizados() < 2)
			return true;
		return false;
	}
	
	@Override
	public boolean cargo(Paquete paquete) {
		
		return true;
	}

	public void sumarViaje(){
		
		this.setViajesRealizados(this.getViajesRealizados() + 1);
	}
	
	private int viajesRealizados;

	public int getViajesRealizados() {
		return viajesRealizados;
	}

	public void setViajesRealizados(int viajesRealizados) {
		this.viajesRealizados = viajesRealizados;
	}


}

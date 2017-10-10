package unlam.ai;

public enum Resultado {

	GANADOR, PERDEDOR, EMPATE;
	
	public Resultado contraria() {
		if (this.equals(GANADOR)) return Resultado.PERDEDOR;
		if (this.equals(PERDEDOR)) return Resultado.GANADOR;
		return EMPATE;
	}
	
}

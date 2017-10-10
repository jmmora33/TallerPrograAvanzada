package unlam.ai;

public enum Forma {
	
	PIEDRA, PAPEL, TIJERA;
	
	public Resultado jugarCon(Forma otra) {
		
		if (this.equals(otra)) return Resultado.EMPATE;
		
		if (this.equals(PAPEL) && otra.equals(PIEDRA) || this.equals(PIEDRA) && otra.equals(TIJERA) || this.equals(TIJERA) && otra.equals(PAPEL)) {
			return Resultado.GANADOR;
		}
		
		return Resultado.PERDEDOR;
		
	}
	
}
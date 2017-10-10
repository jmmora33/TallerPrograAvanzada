package unlam.ai;

public interface Jugador {
	public Forma jugar();
	public void resultado(Resultado resultado, Forma formaDelOtro);
	public String getNombre();
}
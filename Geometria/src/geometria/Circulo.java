package geometria;

public class Circulo {
private double radio;
private Punto centro;



public Circulo(final double radio, final Punto centro) {
	super();
	this.radio = radio;
	this.centro = centro;
}



public double getRadio() {
	return radio;
}



public void setRadio(double radio) {
	this.radio = radio;
}



public Punto getCentro() {
	return centro;
}



public void setCentro(Punto centro) {
	this.centro = centro;
}



public boolean estaDentro(Punto p)
{
 
	return  p.distanciaCon(this.getCentro())<= this.getRadio();
}

public boolean intersectaCon(Circulo circulo)
{
 	
return this.getCentro().distanciaCon(circulo.getCentro())<= (this.getRadio() + circulo.getRadio());
}


}

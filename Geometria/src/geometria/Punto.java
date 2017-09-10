package geometria;

public class Punto {

private double x, y;

public Punto(final double x, final double y)
{
   this.x = x;	
   this.y = y;
}
	

public double getX() {
	return x;
}


public void setX(double x) {
	this.x = x;
}


public double getY() {
	return y;
}


public void setY(double y) {
	this.y = y;
}


public double distanciaCon(final Punto p)
{
	
return Math.sqrt(Math.pow(this.getX()-p.getX(), this.getY()-p.getY()));
}
	
}

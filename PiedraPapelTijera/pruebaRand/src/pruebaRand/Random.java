package pruebaRand;

public class Random {
	int n;
	
	public static void rand(int n){
		System.out.printf("%.2f", Math.random()*n);
	}

	public static void main(String [] args){
		Random.rand(10);
	}
}


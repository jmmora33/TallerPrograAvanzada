package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.util.Scanner;

public class Cliente implements Runnable {

	private static final int PUERTO = 9999;
	private static int id = 1;
	private String nombre;
	private int identificador;
	private Thread hiloCliente;
	private ObjectOutputStream salida;
	private Scanner sc;

	public Cliente(String nombre) {

		this.nombre = nombre;
		hiloCliente = new Thread(this);
		hiloCliente.start();		
		
	}
 
	
	public Cliente() {
		
		hiloCliente = new Thread(this);
		hiloCliente.start();		
		
	}
	
	
	public Scanner getTeclado() {
		return this.sc;
	}
	public static void main(String[] args) {
		//Scanner inicio = new Scanner(System.in);
		Cliente cliente = new Cliente("Juan");
//		System.out.println("Ingrese nombre : ");
//		cliente.abrirTeclado();
//		cliente.nombre = cliente.getTeclado().nextLine();
//	
//		cliente.cerrarTeclado();
		cliente.menu();
	}
    
	
	@Override
	public void run() {
		try {
			ServerSocket recibo = new ServerSocket(9090);
			Socket cliente;
			PaqueteEnvio paqueteRecibido;

			while (true) {
				cliente = recibo.accept();

				ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
				paqueteRecibido = (PaqueteEnvio) flujoEntrada.readObject();
				cliente.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error en el servidor:" + e.getMessage());
		}
	}
  
	public void menu() {
		int opcion = 0 ;
		System.out.println("1.Ver Conectados");
		System.out.println("2.Ver Msj privado");
		System.out.println("3.Sala");
		System.out.println("4.Escribir Mensaje");
		abrirTeclado();
		System.out.println("Elija una opcion");
		if(sc.hasNext())
		opcion = sc.nextInt();
		do {
			switch (opcion) {
			
			case 0 : System.out.println("Pasa por aca...");
			          break;
			case 1:
				break;
			case 2:

				break;
			case 3:

				break;
			case 4:
				escribirMensaje();
				break;
			case 5: System.out.println("Hasta luego!..");
			break;
			default:
				break;
			}
	
		}while(opcion != 5);
				cerrarTeclado();
	}

	public void escribirMensaje() {
		try {
			Socket socket = new Socket("localHost", PUERTO);
			PaqueteEnvio enviar = new PaqueteEnvio();
			enviar = escribir();
			salida = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Enviando info a servidor....");
			salida.writeObject(enviar);
			socket.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public PaqueteEnvio escribir() {
		PaqueteEnvio envio = new PaqueteEnvio();
		abrirTeclado();
		if(sc.hasNextLine()) sc.nextLine();
		System.out.println("Envia un mensaje");
		envio.setMensaje(sc.nextLine());
		System.out.println("Destinatario: ");
		envio.setDestinatario(sc.nextLine());
		if (envio.getDestinatario() == null || envio.getDestinatario().equals(""))
			envio.setDestinatario("Todos");
		cerrarTeclado();
		return envio;
	}
	
	public void abrirTeclado() {
		
		 this.sc = new Scanner(System.in);
	}
	
	public void cerrarTeclado() {
		sc.close();
	}
}

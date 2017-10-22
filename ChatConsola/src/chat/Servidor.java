package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor implements Runnable {

	private static final int PUERTO = 9999;
	private Thread hilo;
	private ArrayList<Cliente> conectados;
	private ServerSocket socketServer;
	private Socket atiendo;

	public Servidor() {

		hilo = new Thread(this);
		hilo.start();

	}

	@Override
	public void run() {
		try {
			socketServer = new ServerSocket(PUERTO,2);
			
			PaqueteEnvio recepcion;
			String nombre, destinatario, mensaje;

			while (true) {
				atiendo = socketServer.accept();
				System.out.println("Cliente con IP: " + atiendo.getInetAddress());
				ObjectInputStream flujoEntrada = new ObjectInputStream(atiendo.getInputStream());
				recepcion = (PaqueteEnvio) flujoEntrada.readObject();
				nombre = recepcion.getNombre();
				destinatario = recepcion.getDestinatario();
				mensaje = recepcion.getMensaje();
				if(destinatario.equalsIgnoreCase("Todos"))
				  System.out.println(nombre +" dice :" + mensaje);
				else {
					enviarADestinatario(recepcion);
				}
			}

		} catch (Exception e) {
			System.err.println("Error de comunicacion " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Servidor servidor = new Servidor();

	}

   public void enviarADestinatario(PaqueteEnvio paquete) {
		 Socket envioADestinatario;
		try {
		
			envioADestinatario = new Socket("localHost", 9090);
			ObjectOutputStream reenvio = new
					 ObjectOutputStream(envioADestinatario.getOutputStream());
					 reenvio.writeObject(paquete);
					 envioADestinatario.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
   }
}


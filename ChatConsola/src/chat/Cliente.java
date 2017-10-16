package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

public class Cliente implements Runnable {

	private static final int PUERTO = 9999;
	private static int id = 1;
	private String nombre;
	private int identificador;
	private Thread hiloCliente;
	private ObjectOutputStream salida;
	private Scanner sc = new Scanner(System.in);

	public Cliente(String nombre) {

		this.nombre = nombre;
		this.identificador = id++;
		hiloCliente = new Thread(this);
		hiloCliente.start();
	}

	public static void main(String[] args) {
		Cliente cliente = new Cliente("Juan");
		while (true)
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
		System.out.println("Elija una opcion");
		System.out.println("1.Ver Sala");
		System.out.println("2.Ver Msj privado");
		System.out.println("3.Ver conectados");
		System.out.println("4.Escribir Mensaje");
		int opcion = sc.nextInt();
		switch (opcion) {
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:
			escribirMensaje();
			break;

		default:
			break;
		}
	}

	public void escribirMensaje() {
		try {
			Socket socket = new Socket("localHost", PUERTO);
			PaqueteEnvio enviar = new PaqueteEnvio();
			enviar = escribir();
			salida = new ObjectOutputStream(socket.getOutputStream());
			salida.writeObject(enviar);
			socket.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public PaqueteEnvio escribir() {
		PaqueteEnvio envio = new PaqueteEnvio();
		
		System.out.println("Envia un mensaje");
		envio.setMensaje(sc.nextLine());
		System.out.println("Destinatario: ");
		envio.setDestinatario(sc.nextLine());
		if (envio.getDestinatario() == null || envio.getDestinatario() == " ")
			envio.setDestinatario("Todos");

		return envio;
	}
}

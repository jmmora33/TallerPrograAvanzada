package chat;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Servidor {

	public static void main(String[] args) {

		MarcoServidor mimarco = new MarcoServidor();

		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

class MarcoServidor extends JFrame implements Runnable {

	public MarcoServidor() {

		setBounds(1200, 300, 280, 350);

		JPanel milamina = new JPanel();

		milamina.setLayout(new BorderLayout());

		areatexto = new JTextArea();

		milamina.add(areatexto, BorderLayout.CENTER);

		add(milamina);

		setVisible(true);

		Thread hiloServer = new Thread(this);
		hiloServer.start();

	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(10100);
			PaqueteEnvio recepcion;
			String nick, ip, mensaje;
			ArrayList<String> conectados = new ArrayList<>();
			while (true) {
				Socket recibido = serverSocket.accept();
				
				/*****ONLINE!***/
				ObjectInputStream flujoEntrada = new ObjectInputStream(recibido.getInputStream());
				recepcion = (PaqueteEnvio) flujoEntrada.readObject();
				nick = recepcion.getNick();
				ip = recepcion.getIp();
				mensaje = recepcion.getMensaje();
				if(!mensaje.equals("online")) {
					areatexto.append("\n" + nick + " (All): " + mensaje);

					 Socket envioADestinatario = new Socket(recepcion.getIp(), 9090);
					
				     ObjectOutputStream reenvio = new
					 ObjectOutputStream(envioADestinatario.getOutputStream());
					 reenvio.writeObject(recepcion);
					 envioADestinatario.close();
					 recibido.close();
				}else {
					InetAddress direccion = recibido.getInetAddress();
					String ipRemota = direccion.getHostName();
					System.out.println("Online" +" "+ ipRemota);
					conectados.add(ipRemota);
					recepcion.setConectados(conectados);
					for(String direccionIp : conectados) {
						Socket envioADestinatario = new Socket(direccionIp, 9090);
						
					     ObjectOutputStream reenvio = new
						 ObjectOutputStream(envioADestinatario.getOutputStream());
						 reenvio.writeObject(recepcion);
						 envioADestinatario.close();
						 recibido.close();
					}
				}
								
			}

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JTextArea areatexto;
}

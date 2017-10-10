package chat;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}


}

class MarcoServidor extends JFrame implements Runnable  {
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		Thread hiloServer = new Thread(this);
		hiloServer.start();
		
		}
	
	@Override
	public void run() {
     try {
		ServerSocket serverSocket = new ServerSocket(9999);
		PaqueteEnvio recepcion;
		String nick, ip, mensaje;
		
		while(true){
			Socket recibido = serverSocket.accept();
			ObjectInputStream flujoEntrada = new ObjectInputStream(recibido.getInputStream());
			recepcion = (PaqueteEnvio) flujoEntrada.readObject();
			nick = recepcion.getNick();
			ip = recepcion.getIp();
			mensaje = recepcion.getMensaje();
			areatexto.append("\n" + nick + ": " + mensaje + "para "+ip);
			Socket envioADestinatario = new Socket(recepcion.getIp(),9090);
			
			ObjectOutputStream reenvio = new ObjectOutputStream(envioADestinatario.getOutputStream());
			reenvio.writeObject(recepcion);

			recibido.close();
		}
	
	} catch (IOException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	}	
	private	JTextArea areatexto;
}

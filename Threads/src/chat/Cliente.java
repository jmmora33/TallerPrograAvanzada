package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MarcoCliente mimarco = new MarcoCliente();

		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

class MarcoCliente extends JFrame {

	public MarcoCliente() {

		setBounds(600, 300, 280, 350);

		LaminaMarcoCliente milamina = new LaminaMarcoCliente();

		add(milamina);

		setVisible(true);
		addWindowListener(new EnvioOnline());
	}

}

class LaminaMarcoCliente extends JPanel implements Runnable{

	public LaminaMarcoCliente() {
		
		
		nombre = new JLabel("Nombre");
		add(nombre);
		
		String nick_user = JOptionPane.showInputDialog("Nick:");
		nick = new JLabel(nick_user);
		add(nick);
	
	
		JLabel texto = new JLabel("IP");
		add(texto);
		
		ip = new JComboBox();
		ip.addItem("1.1.1.1");
		ip.addItem("2.2.2.2");
		ip.addItem("3.3.3.3");
		add(ip);
		
		campoChat = new JTextArea(12,20);
		add(campoChat);
		
		mensaje = new JTextField(20);
		add(mensaje);

		miboton = new JButton("Enviar");
		miboton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(mensaje.getText() != " " && mensaje.getText() != null){
						campoChat.append("\n" + "Yo: "+ mensaje.getText() );
						Socket socket;
						socket = new Socket("localHost", 10100);
						PaqueteEnvio paquete = new PaqueteEnvio();
						paquete.setNick(nick.getText());
						paquete.setIp(ip.getSelectedItem().toString());
						paquete.setMensaje(mensaje.getText());
						ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
						salida.writeObject(paquete);
						socket.close();
						mensaje.setText(" ");
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		add(miboton);
		
	
		miHilo = new Thread(this);
		
	}
	
	@Override
	public void run() {
		try {
			ServerSocket recibo = new ServerSocket(9090);
			Socket cliente;
			PaqueteEnvio paqueteRecibido;
			
			while(true){
				cliente = recibo.accept();
				
				ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
				paqueteRecibido = (PaqueteEnvio)flujoEntrada.readObject();
				if(!paqueteRecibido.getMensaje().equals("online"))
				campoChat.append("\n" + paqueteRecibido.getNick() + ": " + paqueteRecibido.getMensaje());
				else
				campoChat.append("\n" + paqueteRecibido.getConectados());
				
				cliente.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error en el servidor:" + e.getMessage());
		}
	}
	private JTextField mensaje;
	private JTextArea	campoChat;
	private JButton miboton;

	private JLabel nick;
	private JComboBox  ip;
	private JLabel nombre;
	Thread miHilo ;

}

class EnvioOnline extends WindowAdapter{
	
	public void windowOpened(WindowEvent e) {
		
		try {
			
			Socket miSocket = new Socket("1.1.1.1", 10100);
			PaqueteEnvio datos = new PaqueteEnvio();
			datos.setMensaje("online");
			ObjectOutputStream paqueteDatos = new ObjectOutputStream(miSocket.getOutputStream());
			paqueteDatos.writeObject(datos);
			
			miSocket.close();
		}catch(Exception e2) {
			
		}
	}
}
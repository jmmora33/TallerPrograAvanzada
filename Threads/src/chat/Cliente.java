package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	}

}

class LaminaMarcoCliente extends JPanel {

	public LaminaMarcoCliente() {
		nick = new JTextField(5);
		add(nick);
		ip = new JTextField(8);
		add(ip);
		JLabel texto = new JLabel("CHAT JUANCI");
		campoChat = new JTextArea(12,20);
		add(campoChat);
		add(texto);
		
		mensaje = new JTextField(20);

		add(mensaje);

		miboton = new JButton("Enviar");

		miboton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Socket socket = new Socket("localHost", 9999);
					PaqueteEnvio paquete = new PaqueteEnvio();
					paquete.setNick(nick.getText());
					paquete.setIp(ip.getText());
					paquete.setMensaje(mensaje.getText());
					ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
					salida.writeObject(paquete);
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		add(miboton);

	}

	private JTextField mensaje;
	private JTextArea	campoChat;
	private JButton miboton;
	private JTextField nick;
	private JTextField  ip;

}
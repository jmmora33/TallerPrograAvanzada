package chat;

import java.io.Serializable;

public class PaqueteEnvio implements Serializable{
private String nombre;
private String destinatario;
private String mensaje;
private int id;



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getDestinatario() {
	return destinatario;
}
public void setDestinatario(String destinatario) {
	this.destinatario = destinatario;
}
public String getMensaje() {
	return mensaje;
}
public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
}


}

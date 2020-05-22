package Modelo;

public class Proveedor {
	
	private int IDProveedor;
	private String Nombre;
	private String Telefono;
	private String Correo;
	
	public String getNombre() {
		return Nombre;
	}
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public int getIDProveedor() {
		return IDProveedor;
	}
	
	public void setIDProveedor(int iDProveedor) {
		IDProveedor = iDProveedor;
	}
	
	public String getTelefono() {
		return Telefono;
	}
	
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	
	public String getCorreo() {
		return Correo;
	}
	
	public void setCorreo(String correo) {
		Correo = correo;
	}
	
}

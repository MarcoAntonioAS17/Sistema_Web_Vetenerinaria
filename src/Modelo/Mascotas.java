package Modelo;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Mascotas {
	
	private int Clave;
	private String Nombre;
	private Date Edad;
	private String Tipo;
	private String Raza;
	private String Descripcion;
	private int R_Cliente;
	private String SR_Cliente;
	
	public int getClave() {
		return Clave;
	}
	public void setClave(int clave) {
		Clave = clave;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public Date getEdad() {
		return Edad;
	}
	public void setEdad(Date edad) {
		Edad = edad;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getRaza() {
		return Raza;
	}
	public void setRaza(String raza) {
		Raza = raza;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public int getR_Cliente() {
		return R_Cliente;
	}
	public void setR_Cliente(int r_Cliente) {
		R_Cliente = r_Cliente;
	}
	public String getSR_Cliente() {
		return SR_Cliente;
	}
	public void setSR_Cliente(String string) {
		SR_Cliente = string;
	}
	
	public String crear_JSON() throws ParseException{
    	String lista = new String("{ \"IDMascota\": \""+String.format("%d", this.Clave)
    			+"\", \"Nombre\":\""+this.Nombre
    			+"\", \"Edad\":\"");
    	
    	Calendar startCalendar = new GregorianCalendar(); 
    	startCalendar.setTime(this.Edad); 
    	Calendar endCalendar = new GregorianCalendar(); 
    	endCalendar.setTime(new Date()); 

    	int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR); 
    	int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH); 
		
		lista+=diffMonth+" Meses ("+this.Edad+")";
    	
    	lista+=("\", \"Tipo\":\""+this.Tipo
    			+"\", \"Raza\":\""+this.Raza
    			+"\", \"Descripcion\":\""+this.Descripcion
    			+"\", \"R_Cliente\":\""+this.SR_Cliente
    			+"\"}");

    	
    	return lista;
    }
	
	public String crear_JSON2() {
    	String lista = new String("{ \"IDMascota\": \""+String.format("%d", this.Clave)
    			+"\", \"Nombre\":\""+this.Nombre
    			+"\", \"Edad\":\""+this.Edad
    			+"\", \"Tipo\":\""+this.Tipo
    			+"\", \"Raza\":\""+this.Raza
    			+"\", \"Descripcion\":\""+this.Descripcion
    			+"\", \"R_Cliente\":\""+this.R_Cliente
    			+"\"}");

    	
    	return lista;
    }
}

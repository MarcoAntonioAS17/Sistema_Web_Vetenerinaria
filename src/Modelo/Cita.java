package Modelo;

import java.util.Date;

public class Cita {

	private int ID;
	private Date Fecha;
	private Date Hora;
	private String Tipo;
	private int R_Cliente;
	private String S_Cliente;
	private int R_Mascota;
	private String S_Mascota;
	private String Notas;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public Date getHora() {
		return Hora;
	}
	public void setHora(Date hora) {
		Hora = hora;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public int getR_Cliente() {
		return R_Cliente;
	}
	public void setR_Cliente(int r_Cliente) {
		R_Cliente = r_Cliente;
	}
	public String getS_Cliente() {
		return S_Cliente;
	}
	public void setS_Cliente(String s_Cliente) {
		S_Cliente = s_Cliente;
	}
	public int getR_Mascota() {
		return R_Mascota;
	}
	public void setR_Mascota(int r_Mascota) {
		R_Mascota = r_Mascota;
	}
	public String getS_Mascota() {
		return S_Mascota;
	}
	public void setS_Mascota(String s_Mascota) {
		S_Mascota = s_Mascota;
	}
	public String getNotas() {
		return Notas;
	}
	public void setNotas(String notas) {
		Notas = notas;
	}
	
	public String crear_JSON() {
		String lista = new String("{ \"IDCita\": \""+this.ID
		+"\", \"Fecha\":\""+this.Fecha
		+"\", \"Hora\":\""+this.Hora
		+"\", \"Tipo\":\""+this.Tipo
		+"\", \"NombreC\":\""+this.S_Cliente
		+"\", \"NombreM\":\""+this.S_Mascota
		+"\", \"Notas\":\""+this.Notas
		+"\"}");

		return lista;
	}
	
	public String crear_JSON2() {
		String lista = new String("{ \"IDCita\": \""+this.ID
		+"\", \"Fecha\":\""+this.Fecha
		+"\", \"Hora\":\""+this.Hora
		+"\", \"Tipo\":\""+this.Tipo
		+"\", \"NombreC\":\""+this.R_Cliente
		+"\", \"NombreM\":\""+this.R_Mascota
		+"\", \"Notas\":\""+this.Notas
		+"\"}");

		return lista;
	}
}

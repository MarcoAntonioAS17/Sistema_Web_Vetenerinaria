package Modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ventas {
	
	private int IDVenta;
	private Date Fecha;
	private Date hora;
	private int R_Cliente;
	private String S_Cliente;
	private float Total;
	

	public int getIDVenta() {
		return IDVenta;
	}
	public void setIDVenta(int iDVenta) {
		IDVenta = iDVenta;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
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
	
	public float getTotal() {
		return Total;
	}
	public void setTotal(float total) {
		Total = total;
	}
	public String crear_JSON() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String lista = new String("{ "
				+ "\"Codigo\":\""+this.IDVenta
				+ "\",\"Nombre\":\""+this.S_Cliente
				+ "\",\"Fecha\":\""+this.Fecha
				+ "\",\"Hora\":\""+dateFormat.format(this.hora)
				+ "\",\"Total\":\""+this.Total
				+"\",");
		
		return lista;
	}
	
}

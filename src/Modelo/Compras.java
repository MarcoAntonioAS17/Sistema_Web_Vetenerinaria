package Modelo;

import java.util.Date;

public class Compras {
	
	private int IdCompra;
	private Date Fecha;
	private Date hora;
	private int R_Proveedor;
	private String S_Proveedor;
	
	public int getIdCompra() {
		return IdCompra;
	}
	public void setIdCompra(int idCompra) {
		IdCompra = idCompra;
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
	public int getR_Proveedor() {
		return R_Proveedor;
	}
	public void setR_Proveedor(int r_Proveedor) {
		R_Proveedor = r_Proveedor;
	}
	public String getS_Proveedor() {
		return S_Proveedor;
	}
	public void setS_Proveedor(String s_Proveedor) {
		S_Proveedor = s_Proveedor;
	}
	
	public String crear_JSON() {
		String lista = new String("{ ");
		
		return lista;
	}
	
}

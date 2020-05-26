package Modelo;

import java.util.Date;

public class Producto {
	
	private String IDProducto;
	private String Nombre;
	private int Cantidad;
	private float Precio_V;
	private float Precio_C;
	private Date Caducidad;
	private String Descripcion;
	private int R_Categoria;
	private int R_Proveedor;
	private String S_Categoria;
	private String S_Proveedor;
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getIDProducto() {
		return IDProducto;
	}
	public void setIDProducto(String iDProducto) {
		IDProducto = iDProducto;
	}
	public float getPrecio_V() {
		return Precio_V;
	}
	public void setPrecio_V(float precio_V) {
		Precio_V = precio_V;
	}
	public float getPrecio_C() {
		return Precio_C;
	}
	public void setPrecio_C(float precio_C) {
		Precio_C = precio_C;
	}
	public Date getCaducidad() {
		return Caducidad;
	}
	public void setCaducidad(Date caducidad) {
		Caducidad = caducidad;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public int getR_Categoria() {
		return R_Categoria;
	}
	public void setR_Categoria(int r_Categoria) {
		R_Categoria = r_Categoria;
	}
	public int getR_Proveedor() {
		return R_Proveedor;
	}
	public void setR_Proveedor(int r_Proveedor) {
		R_Proveedor = r_Proveedor;
	}
	public String getS_Categoria() {
		return S_Categoria;
	}
	public void setS_Categoria(String s_Categoria) {
		S_Categoria = s_Categoria;
	}
	public String getS_Proveedor() {
		return S_Proveedor;
	}
	public void setS_Proveedor(String s_Proveedor) {
		S_Proveedor = s_Proveedor;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	
	public String crear_JSON() {
		String lista = new String("{ \"IDProducto\": \""+this.IDProducto
				+"\", \"Nombre\":\""+this.Nombre
				+"\", \"Cantidad\":\""+this.Cantidad
				+"\", \"Precio_V\":\""+this.Precio_V
				+"\", \"Precio_C\":\""+this.Precio_C
				+"\", \"Caducidad\":\""+this.Caducidad
				+"\", \"Descripcion\":\""+this.Descripcion
				+"\", \"Categoria\":\""+String.format("%d", this.R_Categoria)
				+"\", \"Proveedor\":\""+String.format("%d", this.R_Proveedor)
				+"\", \"R_Proveedor\":\""+ this.S_Proveedor
				+"\", \"R_Categoria\":\""+ this.S_Categoria
				+"\"}");
		
		return lista;
	}
}

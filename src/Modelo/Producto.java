package Modelo;

import java.util.Date;

public class Producto {
	
	private int IDProducto;
	private String Nombre;
	private String Precio_V;
	private String Precio_C;
	private Date Caducidad;
	private String Descripcion;
	private int R_Categoria;
	private int R_Proveedor;
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getIDProducto() {
		return IDProducto;
	}
	public void setIDProducto(int iDProducto) {
		IDProducto = iDProducto;
	}
	public String getPrecio_V() {
		return Precio_V;
	}
	public void setPrecio_V(String precio_V) {
		Precio_V = precio_V;
	}
	public String getPrecio_C() {
		return Precio_C;
	}
	public void setPrecio_C(String precio_C) {
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
}

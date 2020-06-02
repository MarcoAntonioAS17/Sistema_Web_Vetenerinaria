package Modelo;

public class Detalle_Compras {
	
	private int R_Compra;
	private String R_Producto;
	private int Cantidad;
	private String 	Nombre_Producto;
	private float Precio;
	
	public int getR_Compra() {
		return R_Compra;
	}
	public void setR_Compra(int r_Compra) {
		R_Compra = r_Compra;
	}
	public String getR_Producto() {
		return R_Producto;
	}
	public void setR_Producto(String r_Producto) {
		R_Producto = r_Producto;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	public String getNombre_Producto() {
		return Nombre_Producto;
	}
	public void setNombre_Producto(String nombre_Producto) {
		Nombre_Producto = nombre_Producto;
	}
	public float getPrecio() {
		return Precio;
	}
	public void setPrecio(float precio) {
		Precio = precio;
	}
	
	public String crear_JSON() {
		String lista = new String("{\"Codigo\": \""+this.R_Producto
				+"\", \"Nombre\":\""+this.Nombre_Producto
				+"\", \"Cantidad\":\""+this.Cantidad
				+"\", \"Precio\":\""+this.Precio
				+"\"}");
		return lista;
	}

}

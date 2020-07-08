package Modelo;

public class Detalle_Ventas {
	
	private int R_Venta;
	private String R_Producto;
	private int Cantidad;
	private String 	Nombre_Producto;
	private float Precio;


	public int getR_Venta() {
		return R_Venta;
	}
	public void setR_Venta(int r_Venta) {
		R_Venta = r_Venta;
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
	
	public String crear_JSON_Ver_Ventas() {
		String lista = new String("[\""+this.R_Producto
				+"\", \""+this.Nombre_Producto
				+"\", \""+this.Cantidad
				+"\", \""+this.Precio
				+"\"]");
		return lista;
	}
}

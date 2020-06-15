package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import Config.Conexion;
import Modelo.Compras;
import Modelo.Detalle_Compras;

public class CompraDAO extends Conexion{
	
	PreparedStatement ps;
	ResultSet rs;
	Compras Compra = new Compras();
	String query;
	
	public CompraDAO() {
		
	}
	
	 public boolean add(Compras nueva) {

        try {
        	this.query = "INSERT INTO  compras (R_Proveedor, Fecha, Hora)"+ 
        				"values (?,?,?);";
        	ps = getConnection().prepareStatement(query);
            
            ps.setInt(1, nueva.getR_Proveedor());
            
            SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
            ps.setString(2,String.format(objSDF.format((nueva.getFecha()))));
            
            objSDF = new SimpleDateFormat("HH:mm");
            ps.setString(3,String.format(objSDF.format(nueva.getHora())));

            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
        return true;
	 }
	 
	 public boolean eliminar_compra(int CodCompra) {
			PreparedStatement ps=null;
			try {
	        	this.query = "delete from compras where idCompras = ?;";
	        	ps = getConnection().prepareStatement(query);
	            ps.setInt(1, CodCompra);
	            ps.executeUpdate();
	            
	        } catch (Exception var4) {
	            var4.printStackTrace();
	            return false;
	        }

			return true;
		}
	
	public void regresar_inventario(int IDCompra) {

		PreparedStatement ps=null;
		ResultSet rs=null;
		Detalle_Compras dcompra = new Detalle_Compras();
		Detalle_CompraDAO dao_detalle = new Detalle_CompraDAO();
		int inventario;
		
		this.query = "SELECT Cantidad, R_Producto FROM detalle_compras where R_Compra=?;";
    	
    	try {
            ps = getConnection().prepareStatement(query);
            ps.setInt(1,IDCompra);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	dcompra.setCantidad(rs.getInt("Cantidad"));
            	dcompra.setR_Producto(rs.getString("R_Producto"));
            	
            	inventario= dao_detalle.consultar_inventario(dcompra.getR_Producto());
    			
    			dao_detalle.modificar_inventario(dcompra.getR_Producto(), (inventario-dcompra.getCantidad()));

            }
	    } catch (Exception var4) {
	        var4.printStackTrace();
	    }
    	
	}
	
	public String mostrar_compras() {
		
		String retorno = new String("[");
		this.query = "select idCompras, Proveedor_Nombre, Fecha, Hora, round(sum(detalle_compras.Cantidad*productos.Precio_Compra),2) as Total from detalle_compras " + 
				"inner join compras on R_Compra=idCompras " + 
				"inner join proveedores on R_Proveedor=idProveedores " + 
				"inner join productos on R_Producto=idProductos " + 
				"group by idCompras " + 
				"order by idCompras;";
		
		try {
            ps = getConnection().prepareStatement(query);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()) {
            	Compras new_comp = new Compras();
            	Detalle_CompraDAO dao_detalles = new Detalle_CompraDAO();
            	new_comp.setIdCompra(this.rs.getInt("idCompras"));
            	new_comp.setS_Proveedor(this.rs.getString("Proveedor_Nombre"));
            	new_comp.setFecha(this.rs.getDate("Fecha"));
            	new_comp.setHora(this.rs.getTime("Hora"));
            	new_comp.setTotal(this.rs.getFloat("Total"));
            	
            	retorno+= new_comp.crear_JSON();
            	retorno+="\"Compras\":";
            	retorno+=dao_detalles.Listar_JSON_Ver_Compras(new_comp.getIdCompra());
            	retorno+="}";
            	if(!this.rs.isLast())
            		retorno+= ",";
            }
    	
	    } catch (Exception var4) {
	        var4.printStackTrace();
	    }
    	retorno+="]";
    	return retorno;
	}
	
	
}

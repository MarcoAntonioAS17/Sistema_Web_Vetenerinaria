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
}

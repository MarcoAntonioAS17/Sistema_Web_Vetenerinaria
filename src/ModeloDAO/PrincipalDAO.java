package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.Conexion;


public class PrincipalDAO extends Conexion{
	
	PreparedStatement ps;
    ResultSet rs;
    
    String query;
	
	public int Producto_Totales() {
		int retorno = 0;
	    	this.query = "SELECT count(Nombre) as Productos FROM veterinaria.productos;";
	    	
	    	try {
	            ps = getConnection().prepareStatement(query);
	            this.rs = this.ps.executeQuery();
	            
	            while(this.rs.next()) {
		        	retorno=this.rs.getInt("Productos");
	            }
	    	
		    } catch (Exception var4) {
		        var4.printStackTrace();
		    }
		
		return retorno;
		
	}
	
	public int Productos_Agotarse() {
		int retorno = 0;
	    	this.query = "SELECT count(Nombre) as Productos FROM veterinaria.productos where Cantidad < 3;";
	    	
	    	try {
	            ps = getConnection().prepareStatement(query);
	            this.rs = this.ps.executeQuery();
	            
	            while(this.rs.next()) {
		        	retorno=this.rs.getInt("Productos");
	            }
	    	
		    } catch (Exception var4) {
		        var4.printStackTrace();
		    }
		
		return retorno;
		
	}
	
	public int Productos_Caducar() {
		int retorno = 0;
	    	this.query = "SELECT count(DATEDIFF(Caducidad, now())) as Dias FROM veterinaria.productos where DATEDIFF(Caducidad, now()) < 30;";
	    	
	    	try {
	            ps = getConnection().prepareStatement(query);
	            this.rs = this.ps.executeQuery();
	            
	            while(this.rs.next()) {
		        	retorno=this.rs.getInt(1);
	            }
	    	
		    } catch (Exception var4) {
		        var4.printStackTrace();
		    }
		
		return retorno;
		
	}
	
	

}

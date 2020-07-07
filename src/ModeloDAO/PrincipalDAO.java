package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.Conexion;


public class PrincipalDAO {
	
	PreparedStatement ps;
    ResultSet rs;
    
    String query;
    
    public int Clientes_Totales() {
		Conexion conect = new Conexion();
		int retorno = 0;
	    	this.query = "SELECT count(idClientes) as Productos FROM veterinaria.clientes;";
	    	
	    	try {
	            ps = conect.getConnection().prepareStatement(query);
	            this.rs = this.ps.executeQuery();
	            
	            while(this.rs.next()) {
		        	retorno=this.rs.getInt(1);
	            }
	    	
		    } catch (Exception var4) {
		        var4.printStackTrace();
		    }
		
		return retorno;
		
	}
	
	public int Producto_Totales() {
		Conexion conect = new Conexion();
		int retorno = 0;
	    	this.query = "SELECT count(Nombre) as Productos FROM veterinaria.productos where R_Categoria like \"%\";";
	    	
	    	try {
	            ps = conect.getConnection().prepareStatement(query);
	            this.rs = this.ps.executeQuery();
	            
	            while(this.rs.next()) {
		        	retorno=this.rs.getInt(1);
	            }
	    	
		    } catch (Exception var4) {
		        var4.printStackTrace();
		    }
		
		return retorno;
		
	}
	
	public int Citas_Vigentes() {
		Conexion conect = new Conexion();
		int retorno = 0;
	    	this.query = "SELECT count(idCitas) as Citas FROM veterinaria.citas " + 
	    			"where datediff(Fecha,now()) >=0;";
	    	
	    	try {
	            ps = conect.getConnection().prepareStatement(query);
	            this.rs = this.ps.executeQuery();
	            
	            while(this.rs.next()) {
		        	retorno=this.rs.getInt(1);
	            }
	    	
		    } catch (Exception var4) {
		        var4.printStackTrace();
		    }
		
		return retorno;
		
	}
	
	public int Productos_Agotarse() {
		Conexion conect = new Conexion();
		int retorno = 0;
	    	this.query = "SELECT count(Nombre) as Productos FROM veterinaria.productos where Cantidad < 3;";
	    	
	    	try {
	            ps = conect.getConnection().prepareStatement(query);
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
		Conexion conect = new Conexion();
		int retorno = 0;
	    	this.query = "SELECT count(DATEDIFF(Caducidad, now())) as Dias FROM veterinaria.productos where DATEDIFF(Caducidad, now()) < 30;";
	    	
	    	try {
	            ps = conect.getConnection().prepareStatement(query);
	            this.rs = this.ps.executeQuery();
	            
	            while(this.rs.next()) {
		        	retorno=this.rs.getInt(1);
	            }
	    	
		    } catch (Exception var4) {
		        var4.printStackTrace();
		    }
		
		return retorno;
		
	}
	
	public float Ventas_Semana() {
		Conexion conect = new Conexion();
		float retorno = 0;
	    	this.query = "select round(sum(detalle_ventas.Cantidad*productos.Precio_Venta),2) as Total " + 
	    			"from detalle_ventas " + 
	    			"inner join ventas on R_Venta=idVentas " + 
	    			"inner join productos on R_Producto=idProductos " + 
	    			"where datediff(now(),ventas.fecha) between 0 and 7;";
	    	
	    	try {
	            ps = conect.getConnection().prepareStatement(query);
	            this.rs = this.ps.executeQuery();
	            
	            while(this.rs.next()) {
		        	retorno=this.rs.getFloat(1);
	            }
	    	
		    } catch (Exception var4) {
		        var4.printStackTrace();
		    }
		
		return retorno;
		
	}
	
	

}

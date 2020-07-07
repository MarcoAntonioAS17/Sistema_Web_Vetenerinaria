package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Config.Conexion;
import Modelo.Compras;
import Modelo.Detalle_Compras;

public class CompraDAO {
	
	PreparedStatement ps;
	ResultSet rs;
	Compras Compra = new Compras();
	String query;
	
	public CompraDAO() {
		
	}
	
	 public boolean add(Compras nueva) {
		 Conexion conect = new Conexion();
        try {
        	this.query = "INSERT INTO  compras (R_Proveedor, Fecha, Hora)"+ 
        				"values (?,?,?);";
        	ps = conect.getConnection().prepareStatement(query);
            
            ps.setInt(1, nueva.getR_Proveedor());
            
            SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
            ps.setString(2,String.format(objSDF.format((nueva.getFecha()))));
            
            objSDF = new SimpleDateFormat("HH:mm");
            ps.setString(3,String.format(objSDF.format(nueva.getHora())));

            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        } finally {
			try {
				if(conect.getConnection() != null)
					conect.getConnection().close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return true;
	 }
	 
	 public boolean eliminar_compra(int CodCompra) {
		 Conexion conect = new Conexion();
			PreparedStatement ps=null;
			try {
	        	this.query = "delete from compras where idCompras = ?;";
	        	ps = conect.getConnection().prepareStatement(query);
	            ps.setInt(1, CodCompra);
	            ps.executeUpdate();
	            
	        } catch (Exception var4) {
	            var4.printStackTrace();
	            return false;
	        } finally {
				try {
					if(conect.getConnection() != null)
						conect.getConnection().close();
					if(ps != null)
						ps.close();
					if(rs != null)
						rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return true;
		}
	
	public void regresar_inventario(int IDCompra) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Detalle_Compras dcompra = new Detalle_Compras();
		Detalle_CompraDAO dao_detalle = new Detalle_CompraDAO();
		int inventario;
		
		this.query = "SELECT Cantidad, R_Producto FROM detalle_compras where R_Compra=?;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
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
	    } finally {
			try {
				if(conect.getConnection() != null)
					conect.getConnection().close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
	}
	
	public String mostrar_compras(int key, String busq) {
		
		Conexion conect = new Conexion();
		
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		String retorno = new String("[");
		this.query = "select idCompras, Proveedor_Nombre, Fecha, date_add(hora, Interval 1 hour) as Horas, round(sum(detalle_compras.Cantidad*productos.Precio_Compra),2) as Total from detalle_compras " + 
				"inner join compras on R_Compra=idCompras " + 
				"inner join proveedores on R_Proveedor=idProveedores " + 
				"inner join productos on R_Producto=idProductos " ;
		switch (key) {
			case 1:
					this.query+="where idCompras like ? ";
				break;
			case 2:
				this.query+="where Proveedor_Nombre like ? ";
				break;	
			case 3:
				this.query+="where Fecha like ? ";
				break;
			case 4:
				this.query+="where Hora like ? ";
				break;
		
			default:
				this.query+="";
				break;
		}
		
		this.query+="group by idCompras order by idCompras;";
		try {
            ps2 = conect.getConnection().prepareStatement(query);
            ps2.setString(1,"%"+busq+"%");
            rs2 = ps2.executeQuery();
            
            while(rs2.next()) {
            	Compras new_comp = new Compras();
            	Detalle_CompraDAO dao_detalles = new Detalle_CompraDAO();
            	new_comp.setIdCompra(rs2.getInt("idCompras"));
            	new_comp.setS_Proveedor(rs2.getString("Proveedor_Nombre"));
            	new_comp.setFecha(rs2.getDate("Fecha"));
            	new_comp.setHora(rs2.getTime("Horas"));
            	new_comp.setTotal(rs2.getFloat("Total"));
            	
            	retorno+= new_comp.crear_JSON();
            	retorno+="\"Compras\":";
            	retorno+=dao_detalles.Listar_JSON_Ver_Compras(new_comp.getIdCompra());
            	retorno+="}";
            	if(!rs2.isLast())
            		retorno+= ",";
            }
    	
	    } catch (Exception var4) {
	        var4.printStackTrace();
	    }finally {
			try {
				if(conect.getConnection() != null)
					conect.getConnection().close();
				if(ps2 != null)
					ps2.close();
				if(rs2 != null)
					rs2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
    	retorno+="]";
    	return retorno;
	}
	
	
	
	
}

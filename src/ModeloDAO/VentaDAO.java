package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Config.Conexion;
import Modelo.Detalle_Ventas;
import Modelo.Ventas;

public class VentaDAO {
	
	PreparedStatement ps;
	ResultSet rs;
	Ventas Venta = new Ventas();
	String query;
	
	public VentaDAO() {
		
	}
	
	 public boolean add(Ventas nueva) {
		 Conexion conect = new Conexion();
        try {
        	this.query = "INSERT INTO  ventas (R_Cliente, Fecha, Hora)"+ 
        				"values (?,?,?);";
        	ps = conect.getConnection().prepareStatement(query);
            
            ps.setInt(1, nueva.getR_Cliente());
            
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
	 
	 public boolean eliminar_venta(int CodVenta) {
		 Conexion conect = new Conexion();
			PreparedStatement ps=null;
			try {
	        	this.query = "delete from ventas where idVentas = ?;";
	        	ps = conect.getConnection().prepareStatement(query);
	            ps.setInt(1, CodVenta);
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
		
		Detalle_Ventas dventa = new Detalle_Ventas();
		Detalle_VentaDAO dao_detalle = new Detalle_VentaDAO();
		int inventario;
		
		this.query = "SELECT Cantidad, R_Producto FROM detalle_ventas where R_Venta=?;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1,IDCompra);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	dventa.setCantidad(rs.getInt("Cantidad"));
            	dventa.setR_Producto(rs.getString("R_Producto"));
            	
            	inventario= dao_detalle.consultar_inventario(dventa.getR_Producto());
    			
    			dao_detalle.modificar_inventario(dventa.getR_Producto(), (inventario+dventa.getCantidad()));

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
	
	public String mostrar_ventas(int key, String busq) {
		
		Conexion conect = new Conexion();
		
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		String retorno = new String("[");
		this.query = "select idVentas, clientes.Nombre, Fecha, date_add(hora, Interval 1 hour) as Horas, round(sum(detalle_ventas.Cantidad*productos.Precio_Venta),2) as Total from detalle_ventas " + 
				"inner join Ventas on R_Venta=idVentas " + 
				"inner join clientes on R_Cliente=idClientes " + 
				"inner join productos on R_Producto=idProductos " ;
		switch (key) {
			case 1:
					this.query+="where idVentas like ? ";
				break;
			case 2:
				this.query+="where clientes.Nombre like ? ";
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
		
		this.query+="group by idVentas order by idVentas;";
		try {
            ps2 = conect.getConnection().prepareStatement(query);
            ps2.setString(1,"%"+busq+"%");
            rs2 = ps2.executeQuery();
            
            while(rs2.next()) {
            	Ventas new_venta = new Ventas();
            	Detalle_VentaDAO dao_detalles = new Detalle_VentaDAO();
            	new_venta.setIDVenta(rs2.getInt(1));
            	new_venta.setS_Cliente(rs2.getString(2));
            	new_venta.setFecha(rs2.getDate(3));
            	new_venta.setHora(rs2.getTime(4));
            	new_venta.setTotal(rs2.getFloat(5));
            	
            	retorno+= new_venta.crear_JSON();
            	retorno+="\"Ventas\":";
            	retorno+=dao_detalles.Listar_JSON_Ver_Ventas(new_venta.getIDVenta());
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
	
	public int buscar_servicio(String Nombre, float Precio) {
		
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int codigo=0;
		
		this.query = "SELECT idProductos FROM veterinaria.productos where Nombre = ? and Precio_Venta = ? ;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setString(1,Nombre);
            ps.setFloat(2, Precio);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	codigo = rs.getInt(1);

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
    	return codigo;
	}
	
	public int ultimo_codigo() {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int retorno=0;
		
		this.query = "SELECT idProductos FROM veterinaria.productos where "
				+ "Nombre like \"Consulta\" or Nombre like \"Est%tica\" or Nombre like \"Operaci%n\" "
				+ "order by idProductos desc limit 1;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	retorno = rs.getInt(1);

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
    	return retorno;
		
		
	}
	
	public boolean add_servicio(int codigo, String Servicio, float precio) {
		 	Conexion conect = new Conexion();
	       try {
	    	   this.query = "insert into productos (idProductos, Nombre, Precio_Venta)"
	       			+ " values (?,?,?);";
	    	   ps = conect.getConnection().prepareStatement(query);
	           
	           ps.setInt(1, codigo);
	           ps.setString(2, Servicio);
	           ps.setFloat(3, precio);
	           
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
}

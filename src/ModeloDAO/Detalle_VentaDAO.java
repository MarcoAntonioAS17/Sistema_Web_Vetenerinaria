package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Config.Conexion;
import Modelo.Detalle_Ventas;

public class Detalle_VentaDAO {
	
	String query;
	
	public Detalle_VentaDAO() {
		
	}
	
	public String Listar_JSON(int IDVenta) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String retorno = new String("[");
		
		this.query = "SELECT R_Producto, productos.Nombre, detalle_ventas.Cantidad, productos.Precio_Venta "
				+ "FROM veterinaria.detalle_ventas " + 
				"INNER JOIN productos ON detalle_ventas.R_Producto=productos.idProductos where R_Venta=?;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1,IDVenta);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Detalle_Ventas dcompra = new Detalle_Ventas();
            	dcompra.setR_Producto(rs.getString(1));
            	dcompra.setNombre_Producto(rs.getString(2));
            	dcompra.setCantidad(rs.getInt(3));
            	dcompra.setPrecio(rs.getFloat(4));
            	
            	retorno+= dcompra.crear_JSON();
            	if(!rs.isLast())
            		retorno+= ",";
            }
	    } catch (Exception var4) {
	        var4.printStackTrace();
	    }finally {
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
    	retorno+="]";
		return retorno;
	}
	
	public String Listar_JSON_Ver_Ventas(int IDVenta) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String retorno = new String("[");
		
		this.query = "SELECT R_Producto, productos.Nombre, detalle_ventas.Cantidad, productos.Precio_Venta "
				+ "FROM veterinaria.detalle_ventas " + 
				"INNER JOIN productos ON detalle_ventas.R_Producto=productos.idProductos where R_Venta=?;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1,IDVenta);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Detalle_Ventas dventa = new Detalle_Ventas();
            	dventa.setR_Producto(rs.getString(1));
            	dventa.setNombre_Producto(rs.getString(2));
            	dventa.setCantidad(rs.getInt(3));
            	dventa.setPrecio(rs.getFloat(4));
            	
            	retorno+= dventa.crear_JSON_Ver_Ventas();
            	if(!rs.isLast())
            		retorno+= ",";
            }
	    } catch (Exception var4) {
	        var4.printStackTrace();
	    }finally {
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
    	retorno+="]";
		return retorno;
	}
	
	public boolean add(Detalle_Ventas Dventa) {
		PreparedStatement ps=null;
		Conexion conect = new Conexion();
		
		this.query = "INSERT INTO detalle_ventas (R_Venta,R_Producto,Cantidad) VALUES (?,?,?);";
		
		try {
        	ps = conect.getConnection().prepareStatement(query);
            
            ps.setInt(1,Dventa.getR_Venta());
            ps.setString(2,Dventa.getR_Producto());
            ps.setInt(3,Dventa.getCantidad());
            
            ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }finally {
			try {
				if(conect.getConnection() != null)
					conect.getConnection().close();
				if(ps != null)
					ps.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return true;
    }
	 
	public int buscar_venta(Date Fecha, Date Hora) {

		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int ID=0;

		this.query = "SELECT idVentas FROM ventas WHERE Fecha = ? and Hora = ?;";
		
		try {
			ps=conect.getConnection().prepareStatement(this.query);
			
			SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
			ps.setString(1,String.format(objSDF.format(Fecha)));
			
			objSDF = new SimpleDateFormat("HH:mm");
            ps.setString(2,String.format(objSDF.format(Hora)));
            
            rs = ps.executeQuery();
            while(rs.next()) {
            	ID=rs.getInt(1);
            }
            
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
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
		return ID;
	}
	
	public int consultar_inventario(String Codigo) {
			
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int cant=0;

		this.query = "SELECT cantidad from productos where idProductos = ?;";
		
		try {
			ps=conect.getConnection().prepareStatement(this.query);
            ps.setString(1,Codigo);
            rs = ps.executeQuery();
            while(rs.next()) {
            	cant=rs.getInt("cantidad");
            }
            
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
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
		return cant;
	}
	
	public boolean modificar_inventario(String Codigo, int Cantidad) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;

		this.query = "UPDATE productos set cantidad = ? where idProductos = ?;";
		
		try {
			ps=conect.getConnection().prepareStatement(this.query);
            ps.setInt(1, Cantidad);
			ps.setString(2,Codigo);
            ps.executeUpdate();
            
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(conect.getConnection() != null)
					conect.getConnection().close();
				if(ps != null)
					ps.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean eliminar_producto(String producto, int CodVenta) {
		
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		try {
        	this.query = "delete from detalle_ventas where R_Producto = ? and R_Venta = ?;";
        	ps = conect.getConnection().prepareStatement(query);
            ps.setString(1, producto);
            ps.setInt(2, CodVenta);
            ps.executeUpdate();
            
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }finally {
			try {
				if(conect.getConnection() != null)
					conect.getConnection().close();
				if(ps != null)
					ps.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}
	
	public void modificar_cantidad(String Codigo, int Venta, int Cantidad) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;

		this.query = "UPDATE detalle_ventas SET cantidad=? where R_Producto = ? and R_Venta = ?;";
		
		try {
			ps = conect.getConnection().prepareStatement(this.query);
            ps.setInt(1, Cantidad);
			ps.setString(2,Codigo);
            ps.setInt(3, Venta);
            ps.executeUpdate();
            
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}finally {
			try {
				if(conect.getConnection() != null)
					conect.getConnection().close();
				if(ps != null)
					ps.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}

	public int consultar_cantidad(String Codigo, int Venta) {
		
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int cant=0;

		this.query = "SELECT cantidad from detalle_ventas where R_Producto = ? and R_Venta = ?;";
		
		try {
			ps=conect.getConnection().prepareStatement(this.query);
            ps.setString(1,Codigo);
            ps.setInt(2, Venta);
            rs = ps.executeQuery();
            while(rs.next()) {
            	cant=rs.getInt("cantidad");
            }
            
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
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
		return cant;
	}

	public boolean eliminar_productos(int CodVenta) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		try {
        	this.query = "delete from detalle_ventas where R_Venta = ?;";
        	ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1, CodVenta);
            ps.executeUpdate();
            
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }finally {
			try {
				if(conect.getConnection() != null)
					conect.getConnection().close();
				if(ps != null)
					ps.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}
	
	public String cargar_productos() {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String retorno = new String("[");
		
		this.query = "SELECT idProductos, Nombre FROM veterinaria.productos where R_Categoria like \"%\";";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	
            	String lista = new String("{\"Codigo\": \""+rs.getInt("idProductos")
        				+"\", \"Nombre\":\""+rs.getString("Nombre")
        				+"\"}");
            	retorno+=lista;
            	if(!rs.isLast())
            		retorno+= ",";
            }
	    } catch (Exception var4) {
	        var4.printStackTrace();
	    }finally {
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
    	retorno+="]";
		return retorno;
	}

}



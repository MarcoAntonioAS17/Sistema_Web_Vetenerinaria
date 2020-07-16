package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Config.Conexion;
import Modelo.Detalle_Compras;

public class Detalle_CompraDAO {
	
	String query;
	
	public Detalle_CompraDAO() {
		
	}
	
	public String Listar_JSON(int IDCompra) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String retorno = new String("[");
		
		this.query = "SELECT R_Producto, productos.Nombre, detalle_compras.Cantidad, productos.Precio_Compra "
				+ "FROM veterinaria.detalle_compras " + 
				"INNER JOIN productos ON detalle_compras.R_Producto=productos.idProductos where R_Compra=?;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1,IDCompra);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Detalle_Compras dcompra = new Detalle_Compras();
            	dcompra.setR_Producto(rs.getString("R_Producto"));
            	dcompra.setNombre_Producto(rs.getString("Nombre"));
            	dcompra.setCantidad(rs.getInt("Cantidad"));
            	dcompra.setPrecio(rs.getFloat("Precio_Compra"));
            	
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
	
	public String Listar_JSON_Ver_Compras(int IDCompra) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String retorno = new String("[");
		
		this.query = "SELECT R_Producto, productos.Nombre, detalle_compras.Cantidad, productos.Precio_Compra "
				+ "FROM veterinaria.detalle_compras " + 
				"INNER JOIN productos ON detalle_compras.R_Producto=productos.idProductos where R_Compra=?;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1,IDCompra);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Detalle_Compras dcompra = new Detalle_Compras();
            	dcompra.setR_Producto(rs.getString("R_Producto"));
            	dcompra.setNombre_Producto(rs.getString("Nombre"));
            	dcompra.setCantidad(rs.getInt("Cantidad"));
            	dcompra.setPrecio(rs.getFloat("Precio_Compra"));
            	
            	retorno+= dcompra.crear_JSON_Ver_Compras();
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
	
	
	
	public boolean add(Detalle_Compras Dcompra) {
		PreparedStatement ps=null;
		Conexion conect = new Conexion();
		
		this.query = "INSERT INTO detalle_compras (R_Compra,R_Producto,Cantidad) VALUES (?,?,?);";
		
		try {
        	ps = conect.getConnection().prepareStatement(query);
            
            ps.setInt(1,Dcompra.getR_Compra());
            ps.setString(2,Dcompra.getR_Producto());
            ps.setInt(3,Dcompra.getCantidad());
            
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
	 
	public int buscar_compra(Date Fecha, Date Hora) {

		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int ID=0;

		this.query = "SELECT idCompras FROM compras WHERE Fecha = ? and Hora = ?;";
		
		try {
			ps=conect.getConnection().prepareStatement(this.query);
			
			SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
			ps.setString(1,String.format(objSDF.format(Fecha)));
			
			objSDF = new SimpleDateFormat("HH:mm");
            ps.setString(2,String.format(objSDF.format(Hora)));
            
            rs = ps.executeQuery();
            while(rs.next()) {
            	ID=rs.getInt("idCompras");
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
        	this.query = "delete from detalle_compras where R_Producto = ? and R_Compra = ?;";
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
	
	public void modificar_cantidad(String Codigo, int Compra, int Cantidad) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;

		this.query = "UPDATE detalle_compras SET cantidad=? where R_Producto = ? and R_Compra = ?;";
		
		try {
			ps = conect.getConnection().prepareStatement(this.query);
            ps.setInt(1, Cantidad);
			ps.setString(2,Codigo);
            ps.setInt(3, Compra);
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

	public int consultar_cantidad(String Codigo, int Compra) {
		
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int cant=0;

		this.query = "SELECT cantidad from detalle_compras where R_Producto = ? and R_Compra = ?;";
		
		try {
			ps=conect.getConnection().prepareStatement(this.query);
            ps.setString(1,Codigo);
            ps.setInt(2, Compra);
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
        	this.query = "delete from detalle_compras where R_Compra = ?;";
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
	
	public String producto_x_proveedor(int IDProveedor) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String retorno = new String("[");
		
		this.query = "SELECT idProductos, Nombre FROM veterinaria.productos WHERE R_Proveedor=?";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1,IDProveedor);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	
            	String lista = new String("{\"Codigo\": \""+rs.getString("idProductos")
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



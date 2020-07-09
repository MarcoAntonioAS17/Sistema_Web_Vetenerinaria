package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.Conexion;
import Modelo.Administrador;

public class AdministradorDAO {
	
	public int autenticacion(Administrador admin) {
		PreparedStatement ps = null;
		ResultSet rs= null;
		Conexion c = new Conexion();
		try {
			String query = "SELECT * FROM administradores WHERE BINARY UserName = ? and BINARY Password = ?;";
			ps =  c.getConnection().prepareStatement(query);
			ps.setString(1, admin.getUserName());
			ps.setString(2, admin.getPassword());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt(3);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			try {
				if(c.getConnection() != null)
					c.getConnection().close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
	public int autenticacion_admin(String Name, String Pass) {
		PreparedStatement ps = null;
		ResultSet rs= null;
		Conexion c = new Conexion();
		try {
			String query = "SELECT * FROM administradores WHERE BINARY UserName = ? and BINARY Password = ?;";
			ps =  c.getConnection().prepareStatement(query);
			ps.setString(1, Name);
			ps.setString(2, Pass);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt(3);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			try {
				if(c.getConnection() != null)
					c.getConnection().close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
	public boolean add(Administrador admin) {
		Conexion c = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs= null;

        try {
        	String query = "insert into administradores (UserName, Password, Nivel) values (?, ?,?);";
        	ps = c.getConnection().prepareStatement(query);
            
            ps.setString(1, admin.getUserName());
            ps.setString(2, admin.getPassword());
            ps.setInt(3, admin.getTipo());
            
            ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }finally {
			try {
				if(c.getConnection() != null)
					c.getConnection().close();
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
	
	public List<Administrador> listar(){
		PreparedStatement ps = null;
		ResultSet rs= null;
    	Conexion c = new Conexion();
    	List<Administrador> datos = new ArrayList<Administrador>();
    	
    	String query = "SELECT * FROM administradores;";
    	
    	try {
            ps = c.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Administrador user = new Administrador();
            	user.setUserName(rs.getString(1));
            	user.setTipo(rs.getInt(3));
            	datos.add(user);
            }
    	
	    } catch (Exception var4) {
	        var4.printStackTrace();
	    }finally {
			try {
				if(c.getConnection() != null)
					c.getConnection().close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	return datos;
    }
	
	public boolean delete(String user) {
		PreparedStatement ps = null;
		ResultSet rs= null;
    	Conexion c = new Conexion();

        try {
        	String query = "DELETE FROM administradores WHERE UserName = ?;";
        	ps = c.getConnection().prepareStatement(query);
            ps.setString(1, user);
            ps.executeUpdate();
            
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }finally {
			try {
				if(c.getConnection() != null)
					c.getConnection().close();
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
	
	public boolean edit(Administrador admin) {
		PreparedStatement ps = null;
		ResultSet rs= null;
    	Conexion conect = new Conexion();
        try {
        	String query = "UPDATE administradores SET Password = ? "
        			+ "WHERE UserName = ?";
        	ps = conect.getConnection().prepareStatement(query);
            
            ps.setString(2, admin.getUserName());
            ps.setString(1, admin.getPassword());
        	
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

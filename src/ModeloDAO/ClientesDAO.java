package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.Conexion;
import Modelo.Clientes;

public class ClientesDAO  {
	
	PreparedStatement ps;
    ResultSet rs;
    
    String query;
    
    public ClientesDAO() {
    }
    
    public Clientes select_one(int IDClient) {
    	Clientes new_Cte = new Clientes();
    	Conexion c = new Conexion();
    	this.query = "SELECT * FROM Clientes WHERE idClientes=?;";
    
    	try {
            ps = c.getConnection().prepareStatement(query);
            ps.setInt(1,IDClient);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()) {
        	new_Cte.setIDClient(this.rs.getInt("idClientes"));
        	new_Cte.setNombreC(this.rs.getString("Nombre"));
        	new_Cte.setTelefonoC(this.rs.getString("Telefono"));
        	new_Cte.setEmailC(this.rs.getString("Correo"));
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
    	return new_Cte;      
    }
    
    public List<Clientes> listar(){
    	List<Clientes> datos = new ArrayList<Clientes>();
    	Conexion c = new Conexion();
    	this.query = "SELECT * FROM Clientes;";
    	
    	try {
            ps = c.getConnection().prepareStatement(query);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()) {
            	Clientes new_Cte = new Clientes();
            	new_Cte.setIDClient(this.rs.getInt("idClientes"));
            	new_Cte.setNombreC(this.rs.getString("Nombre"));
            	new_Cte.setTelefonoC(this.rs.getString("Telefono"));
            	new_Cte.setEmailC(this.rs.getString("Correo"));
            	datos.add(new_Cte);
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
    
    public boolean edit(Clientes clients) {
    	Conexion c = new Conexion();
        try {
        	this.query = "UPDATE Clientes SET Nombre = ?, Telefono=?, Correo= ?"
        			+ "WHERE idClientes = ?";
        	ps = c.getConnection().prepareStatement(query);
            ps.setInt(4, clients.getIDClient());
            ps.setString(1, clients.getNombreC());
            ps.setString(2, clients.getTelefonoC());
            ps.setString(3, clients.getEmailC());
            
            this.ps.executeUpdate();
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
    
    public boolean add(Clientes New_Ctes) {
    	Conexion c = new Conexion();
        try {
        	this.query = "INSERT INTO Clientes (Nombre, Telefono, Correo) "
        			+ "VALUES ( ?, ?, ?);";
        	ps = c.getConnection().prepareStatement(query);
            ps.setString(1, New_Ctes.getNombreC());
            ps.setString(2, New_Ctes.getTelefonoC());
            ps.setString(3, New_Ctes.getEmailC());
            
            this.ps.executeUpdate();
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
    
    public boolean delete(int ID_C) {
    	Conexion c = new Conexion();
        try {
        	this.query = "DELETE FROM Clientes WHERE idClientes = ?;";
        	ps = c.getConnection().prepareStatement(query);
            ps.setInt(1, ID_C);
            this.ps.executeUpdate();
            
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
}

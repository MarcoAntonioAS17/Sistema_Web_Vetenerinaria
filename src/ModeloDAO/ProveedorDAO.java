package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.Conexion;
import Modelo.Proveedor;

public class ProveedorDAO {
	
	PreparedStatement ps;
    ResultSet rs;
    Proveedor E_Cat = new Proveedor();
    String query;
    
    public ProveedorDAO() {
    	
    }
    
    public Proveedor select_one(int IDProveedor){
    	Conexion conect = new Conexion();
    	Proveedor new_pro = new Proveedor();
    	this.query = "SELECT * FROM Proveedores WHERE idProveedores=?;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1,IDProveedor);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()) {
        	new_pro.setIDProveedor(this.rs.getInt("idProveedores"));
        	new_pro.setNombre(this.rs.getString("Proveedor_Nombre"));
        	new_pro.setTelefono(this.rs.getString("Telefono"));
        	new_pro.setCorreo(this.rs.getString("Correo"));
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
    	return new_pro;
    }
    
    public List<Proveedor> listar(){
    	List<Proveedor> datos = new ArrayList<Proveedor>();
    	Conexion conect = new Conexion();
    	this.query = "SELECT * FROM Proveedores;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()) {
            	Proveedor new_pro = new Proveedor();
            	new_pro.setIDProveedor(this.rs.getInt("idProveedores"));
            	new_pro.setNombre(this.rs.getString("Proveedor_Nombre"));
            	new_pro.setTelefono(this.rs.getString("Telefono"));
            	new_pro.setCorreo(this.rs.getString("Correo"));
            	datos.add(new_pro);
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
    	return datos;
    }

    public boolean edit(Proveedor proveed) {
    	Conexion conect = new Conexion();
        try {
        	this.query = "UPDATE Proveedores SET Proveedor_Nombre = ?, Telefono=?, Correo= ?"
        			+ "WHERE idProveedores = ?";
        	ps = conect.getConnection().prepareStatement(query);
            
            ps.setInt(4, proveed.getIDProveedor());
            ps.setString(1,proveed.getNombre());
            ps.setString(2, proveed.getTelefono());
            ps.setString(3,proveed.getCorreo());
            
            this.ps.executeUpdate();
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
    
    public boolean add(Proveedor nuevo_pro) {
    	Conexion conect = new Conexion();

        try {
        	this.query = "INSERT INTO Proveedores (idProveedores, Proveedor_Nombre, Telefono, Correo) "
        			+ "VALUES ( ?, ?, ?, ?);";
        	ps = conect.getConnection().prepareStatement(query);
            
            ps.setInt(1, nuevo_pro.getIDProveedor());
            ps.setString(2,nuevo_pro.getNombre());
            ps.setString(3, nuevo_pro.getTelefono());
            ps.setString(4,nuevo_pro.getCorreo());
            
            this.ps.executeUpdate();
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

    public boolean delete(int ID) {
    	Conexion conect = new Conexion();

        try {
        	this.query = "DELETE FROM Proveedores WHERE idProveedores = ?;";
        	ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1, ID);
            this.ps.executeUpdate();
            
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



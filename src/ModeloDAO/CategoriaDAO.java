package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.Conexion;
import Modelo.Categoria;

public class CategoriaDAO {
	
    PreparedStatement ps;
    ResultSet rs;
    Categoria E_Cat = new Categoria();
    String query;
    
    public CategoriaDAO() {
    	
    }
    
    public List<Categoria> listar(){
    	Conexion c = new Conexion();
    	List<Categoria> datos = new ArrayList<Categoria>();
    	
    	this.query = "SELECT * FROM categorias;";
    	
    	try {
            ps = c.getConnection().prepareStatement(query);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()) {
            	Categoria new_cat = new Categoria();
            	new_cat.setIDCategoria(this.rs.getInt("idCategorias"));
            	new_cat.setNombre(this.rs.getString("Nombre"));
            	datos.add(new_cat);
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

    public boolean add(String nombre) {
    	Conexion c = new Conexion();

        try {
        	this.query = "INSERT INTO categorias(Nombre) VALUES ( ?);";
        	ps = c.getConnection().prepareStatement(query);
            
            ps.setString(1, nombre);
            
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

    public boolean delete(int ID) {
    	Conexion c = new Conexion();

        try {
        	this.query = "DELETE FROM categorias WHERE idCategorias = ?;";
        	ps = c.getConnection().prepareStatement(query);
            ps.setInt(1, ID);
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

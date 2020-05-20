package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Config.Conexion;
import Modelo.Categoria;

public class CategoriaDAO extends Conexion{
	
    PreparedStatement ps;
    ResultSet rs;
    Categoria E_Cat = new Categoria();
    String query;
    
    public CategoriaDAO() {
    	
    }
    
    public List<Categoria> listar(){
    	List<Categoria> datos = new ArrayList<Categoria>();
    	
    	this.query = "SELECT * FROM categorias;";
    	
    	try {
            ps = getConnection().prepareStatement(query);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()) {
            	Categoria new_cat = new Categoria();
            	new_cat.setIDCategoria(this.rs.getInt("idCategorias"));
            	new_cat.setNombre(this.rs.getString("Nombre"));
            	datos.add(new_cat);
            }
    	
	    } catch (Exception var4) {
	        var4.printStackTrace();
	    }
    	return datos;
    }

    public boolean add(String nombre) {
        

        try {
        	this.query = "INSERT INTO categorias(Nombre) VALUES ( ?);";
        	ps = getConnection().prepareStatement(query);
            
            ps.setString(1, nombre);
            
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean edit(Categoria cate) {
        

        try {
        	this.query = "UPDATE categorias SET Nombre= ? WHERE IDProveedor= ?;";
        	ps = getConnection().prepareStatement(query);
            ps.setInt(2, cate.getIDCategoria());
            ps.setString(1, cate.getNombre());
            
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean delete(int ID) {
       

        try {
        	this.query = "DELETE FROM categorias WHERE idCategorias = ?;";
        	ps = getConnection().prepareStatement(query);
            ps.setInt(1, ID);
            
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }
    
    
}

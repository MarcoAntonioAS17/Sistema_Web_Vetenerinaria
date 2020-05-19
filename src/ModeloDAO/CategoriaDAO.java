package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Config.Conexion;
import Modelo.Categoria;

public class CategoriaDAO {
	Conexion conex = new Conexion();
	 Connection con;
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
    		this.con = this.conex.getConnection();
            this.ps = this.con.prepareStatement(query);
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

    public boolean add(Categoria new_cat) {
        this.query = "INSERT INTO categorias(idCategorias, Nombre) VALUES ('" + 
        				new_cat.getIDCategoria()+ "','" + 
        				new_cat.getNombre() + "');";

        try {
            this.con = this.conex.getConnection();
            this.ps = this.con.prepareStatement(query);
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean edit(Categoria cate) {
        this.query = "UPDATE categorias SET Nombre='" + cate.getNombre() + "' WHERE IDProveedor=" + cate.getIDCategoria() + ";";

        try {
            this.con = this.conex.getConnection();
            this.ps = this.con.prepareStatement(query);
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean delete(int ID) {
        this.query = "DELETE FROM categorias WHERE idCategorias = " + ID;

        try {
            this.con = this.conex.getConnection();
            this.ps = this.con.prepareStatement(query);
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }
    
    
}

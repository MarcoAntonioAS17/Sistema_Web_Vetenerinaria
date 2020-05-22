package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Config.Conexion;
import Modelo.Proveedor;

public class ProveedorDAO extends Conexion{
	
	PreparedStatement ps;
    ResultSet rs;
    Proveedor E_Cat = new Proveedor();
    String query;
    
    public ProveedorDAO() {
    	
    }
    
    public List<Proveedor> listar(){
    	List<Proveedor> datos = new ArrayList<Proveedor>();
    	
    	this.query = "SELECT * FROM Proveedores;";
    	
    	try {
            ps = getConnection().prepareStatement(query);
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
	    }
    	return datos;
    }

    public boolean edit(Proveedor proveed) {
        
        try {
        	this.query = "UPDATE Proveedores SET Proveedor_Nombre = ?, Telefono=?, Correo= ?"
        			+ "WHERE idProveedores = ?";
        	ps = getConnection().prepareStatement(query);
            
            ps.setInt(4, proveed.getIDProveedor());
            ps.setString(1,proveed.getNombre());
            ps.setString(2, proveed.getTelefono());
            ps.setString(3,proveed.getCorreo());
            
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }
    
    public boolean add(Proveedor nuevo_pro) {
        

        try {
        	this.query = "INSERT INTO Proveedores (idProveedores, Proveedor_Nombre, Telefono, Correo) "
        			+ "VALUES ( ?, ?, ?, ?);";
        	ps = getConnection().prepareStatement(query);
            
            ps.setInt(1, nuevo_pro.getIDProveedor());
            ps.setString(2,nuevo_pro.getNombre());
            ps.setString(3, nuevo_pro.getTelefono());
            ps.setString(4,nuevo_pro.getCorreo());
            
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean delete(int ID) {
       

        try {
        	this.query = "DELETE FROM Proveedores WHERE idProveedores = ?;";
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

package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Config.Conexion;
import Modelo.Clientes;

public class ClientesDAO extends Conexion {
	
	PreparedStatement ps;
    ResultSet rs;
    Clientes E_Cat = new Clientes();
    String query;
    
    public ClientesDAO() {
    }
    
    public Clientes select_one(int IDClient) {
    	Clientes new_Cte = new Clientes();
    	this.query = "SELECT * FROM Clientes WHERE idClientes=?;";
    
    	try {
            ps = getConnection().prepareStatement(query);
            ps.setInt(1,IDClient);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()) {
        	new_Cte.setIDClient(this.rs.getInt("idClientes"));
        	new_Cte.setNombreC(this.rs.getString("Nombre"));
        	new_Cte.setTelefonoC(this.rs.getString("Telefono"));
        	new_Cte.setCorreoC(this.rs.getString("Correo"));
            }
            
    	} catch (Exception var4) {
	        var4.printStackTrace();
	    }
    	return new_Cte;      
    }
    
    public List<Clientes> listar(){
    	List<Clientes> datos = new ArrayList<Clientes>();
    	
    	this.query = "SELECT * FROM Clientes;";
    	
    	try {
            ps = getConnection().prepareStatement(query);
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
	    }
    	return datos;
    }
    
    public boolean edit(Clientes clients) {
        
        try {
        	this.query = "UPDATE Clientes SET Nombre = ?, Telefono=?, Correo= ?"
        			+ "WHERE idClientes = ?";
        	ps = getConnection().prepareStatement(query);
            ps.setInt(4, clients.getIDClient());
            ps.setString(1, clients.getNombreC());
            ps.setString(2, clients.getTelefonoC());
            ps.setString(3, clients.getEmailC());
            
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean add(Clientes New_Ctes) {
       
        try {
        	this.query = "INSERT INTO Clientes (idClientes, Nombre, Telefono, Correo) "
        			+ "VALUES ( ?, ?, ?, ?);";
        	ps = getConnection().prepareStatement(query);
            ps.setInt(1, New_Ctes.getIDClient());
            ps.setString(2, New_Ctes.getNombreC());
            ps.setString(3, New_Ctes.getTelefonoC());
            ps.setString(4, New_Ctes.getEmailC());
            
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean delete(int ID_C) {
        
        try {
        	this.query = "DELETE FROM Clientes WHERE idClientes = ?;";
        	ps = getConnection().prepareStatement(query);
            ps.setInt(1, ID_C);
            this.ps.executeUpdate();
            
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
        return true;
    }
}

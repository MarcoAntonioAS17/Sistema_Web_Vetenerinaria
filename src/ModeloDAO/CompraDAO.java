package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Config.Conexion;
import Modelo.Compras;

public class CompraDAO extends Conexion{
	
	PreparedStatement ps;
	ResultSet rs;
	Compras Compra = new Compras();
	String query;
	
	public CompraDAO() {
		
	}
	
	 public boolean add(Compras nueva) {

        try {
        	this.query = "INSERT INTO  compras (idCompras, R_Proveedor, Fecha, Hora)"+ 
        				"values (?,?,?,?);";
        	ps = getConnection().prepareStatement(query);
            
            ps.setInt(1,nueva.getIdCompra());
            ps.setInt(2, nueva.getR_Proveedor());
            
            SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
            ps.setString(3,String.format(objSDF.format((nueva.getFecha()))));
            
            objSDF = new SimpleDateFormat("HH:mm");
            ps.setString(4,String.format(objSDF.format(nueva.getHora())));

            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }finally {
			try {
				if(getConnection() != null)
					getConnection().close();
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

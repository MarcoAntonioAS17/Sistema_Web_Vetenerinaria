package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Config.Conexion;

public class ConfiguracionDAO {
	
	public int Dias_Caducidad() {
		
		Conexion c = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs= null;
		
		int Dias = 0;
		
		try {
			String query = "SELECT Dias_Caducidad FROM veterinaria.configuracion;";
			ps =  c.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Dias = rs.getInt(1);
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
		
		return Dias;
	}
	
	public int Cantidad_Aviso() {
		
		Conexion c = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs= null;
		
		int Cantidad = 0;
		
		try {
			String query = "SELECT Cantidad_Inventario FROM veterinaria.configuracion;";
			ps =  c.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Cantidad = rs.getInt(1);
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
		
		return Cantidad;
	}
	
	public boolean Modificar_Datos(int Dias, int Cantidad) {
		PreparedStatement ps = null;
		ResultSet rs= null;
		Conexion conect = new Conexion();
		
        try {
        	
        	String query = "update veterinaria.configuracion "
        			+ "set Cantidad_Inventario=?, Dias_Caducidad=? where Clave=1;";
        		
        	ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1, Cantidad);
            ps.setInt(2, Dias);
            
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

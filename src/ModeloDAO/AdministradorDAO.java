package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Config.Conexion;
import Modelo.Administrador;

public class AdministradorDAO {
	
	public boolean autenticacion(Administrador admin) {
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
				return true;
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
		
		return false;
	}
	
	
}

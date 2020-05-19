package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Config.Conexion;

public class AdministradorDAO extends Conexion{
	
	public boolean autenticacion(String user, String password) {
		PreparedStatement ps = null;
		ResultSet rs= null;
		
		try {
			String query = "SELECT * FROM administradores WHERE BINARY UserName = ? and BINARY Password = ?;";
			ps =  getConnection().prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
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
		
		return false;
	}
	
	
}

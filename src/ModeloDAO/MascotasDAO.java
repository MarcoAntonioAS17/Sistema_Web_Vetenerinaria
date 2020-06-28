package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Config.Conexion;
import Modelo.Mascotas;

public class MascotasDAO {
	
    String query;
    
    public MascotasDAO() {
    	
    }
    
    public Mascotas select_one(int IDMascota){
    	PreparedStatement ps=null;
        ResultSet rs=null;
    	Conexion conect = new Conexion();
    	Mascotas new_mas = new Mascotas();
    	this.query = "SELECT * FROM Mascotas WHERE idMascotas=?;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1,IDMascota);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            new_mas.setClave(rs.getInt("idMascotas"));
            new_mas.setNombre(rs.getString("Nombre"));
            new_mas.setEdad(rs.getDate("Edad"));
            new_mas.setTipo(rs.getString("Tipo"));
            new_mas.setRaza(rs.getString("Raza"));
            new_mas.setDescripcion(rs.getString("Descripcion"));
            new_mas.setR_Cliente(rs.getInt("R_Cliente"));
        	
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
    	
    	return new_mas;
    }

    public boolean edit(Mascotas masco) {
    	PreparedStatement ps=null;
        ResultSet rs=null;
    	Conexion conect = new Conexion();
    	
        try {
        	this.query = "UPDATE Mascotas SET Nombre = ?, Edad=?, Tipo= ?, Raza= ?, Descripcion= ?, R_Cliente= ? "
        			+ "WHERE idMascotas = ?;";
        	ps = conect.getConnection().prepareStatement(query);
            
            ps.setInt(7, masco.getClave());
            ps.setString(1,masco.getNombre());
            SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
            ps.setString(2,String.format(objSDF.format((masco.getEdad()))));
            ps.setString(3,masco.getTipo());
            ps.setString(4, masco.getRaza());
            ps.setString(5, masco.getDescripcion());
            ps.setInt(6, masco.getR_Cliente());
            
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
    
    public boolean add(Mascotas nueva_masco) {
    	PreparedStatement ps=null;
        ResultSet rs=null;
    	Conexion conect = new Conexion();
    	

        try {
        	this.query = "INSERT INTO Mascotas (Nombre, Edad, Tipo, Raza, Descripcion, R_Cliente) " + 
        			"VALUES (?,?,?,?,?,?);";
        	ps = conect.getConnection().prepareStatement(query);
            
        	 ps.setString(1,nueva_masco.getNombre());
        	 SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
             ps.setString(2,String.format(objSDF.format((nueva_masco.getEdad()))));
             
             ps.setString(3,nueva_masco.getTipo());
             ps.setString(4, nueva_masco.getRaza());
             ps.setString(5, nueva_masco.getDescripcion());
             ps.setInt(6, nueva_masco.getR_Cliente());
            
            ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        } finally {
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
    	PreparedStatement ps=null;
        ResultSet rs=null;
    	Conexion conect = new Conexion();

        try {
        	this.query = "DELETE FROM Mascotas WHERE idMascotas = ?;";
        	ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1, ID);
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
    
    public String mostrar_mascotas(int key, String busq) {
		
		Conexion conect = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String retorno = new String("[");
		this.query = "SELECT idMascotas, mascotas.Nombre, Edad, Tipo, Raza, Descripcion, clientes.Nombre as Dueno, clientes.Nombre as Dueno, round(datediff(now(),Edad)/30,0) as Meses FROM veterinaria.mascotas " + 
				"inner join clientes on R_Cliente=idClientes ";
		switch (key) {
		case 1:
				this.query+="WHERE idMascotas like ? ORDER BY idMascotas;";
			break;
		case 2:
			this.query+="WHERE mascotas.Nombre like ? ORDER BY mascotas.Nombre;";
			break;	
		case 3:
			this.query+="WHERE round(datediff(now(),Edad)/30,0) like ? ORDER BY Meses;";
			break;
		case 4:
			this.query+="WHERE Tipo like ? ORDER BY Tipo;";
			break;
		case 5:
			this.query+="WHERE Raza like ? ORDER BY Raza;";
			break;
		case 6:
			this.query+="WHERE Descripcion like ? ORDER BY Descripcion;";
			break;
		case 7:
			this.query+="WHERE Dueno like ? ORDER BY Dueno;";
			break;
		default:
			this.query+=";";
			break;
		}
		try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setString(1, "%"+busq+"%");
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Mascotas new_masco = new Mascotas();
            	
            	new_masco.setClave(rs.getInt(1));
            	new_masco.setNombre(rs.getString(2));
            	new_masco.setEdad(rs.getDate(3));
            	new_masco.setTipo(rs.getString(4));
            	new_masco.setRaza(rs.getString(5));
            	new_masco.setDescripcion(rs.getString(6));
            	new_masco.setSR_Cliente(rs.getString(7));
            	
            	retorno+= new_masco.crear_JSON();
            	
            	if(!rs.isLast())
            		retorno+= ",";
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
		
    	retorno+="]";
    	return retorno;
	}

}

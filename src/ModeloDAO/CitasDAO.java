package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Config.Conexion;
import Modelo.Cita;

public class CitasDAO {
	
    String query;
    
    public CitasDAO() {
    	
    }
    
    public boolean edit(Cita cita, boolean conHora) {
    	PreparedStatement ps=null;
        ResultSet rs=null;
    	Conexion conect = new Conexion();
    	
        try {
        	
        	if(conHora)
        		this.query = "UPDATE Citas SET R_Cliente= ?, Fecha=?, Tipo=?, R_Mascota=?, Notas=?, Hora=? "
        			+ "WHERE idCitas = ?;";
        	else
        		this.query = "UPDATE Citas SET R_Cliente= ?, Fecha=?, Tipo=?, R_Mascota=?, Notas=? "
            			+ "WHERE idCitas = ?;";
        	
        	ps = conect.getConnection().prepareStatement(query);
            
            
            ps.setInt(1,cita.getR_Cliente());
            SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
            
            ps.setString(2,String.format(objSDF.format(cita.getFecha())));
            ps.setString(3,cita.getTipo());
            ps.setInt(4, cita.getR_Mascota());
            ps.setString(5, cita.getNotas());
            
            if(conHora) {
            	objSDF = new SimpleDateFormat("HH:mm");
            	ps.setString(6,String.format(objSDF.format(cita.getHora())));             
                ps.setInt(7, cita.getID());
            }else {
            	ps.setInt(6, cita.getID());
            }
            
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
    
    public Cita select_one(int IDCita){
    	PreparedStatement ps=null;
        ResultSet rs=null;
    	Conexion conect = new Conexion();
    	Cita cita = new Cita();
    	this.query = "SELECT * FROM citas WHERE idCitas=?;";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1,IDCita);
            rs = ps.executeQuery();
            
            while(rs.next()) {
	            cita.setID(rs.getInt(1));
	            cita.setR_Cliente(rs.getInt(2));
	            cita.setFecha(rs.getDate(3));
	            cita.setHora(rs.getTime(4));
	            cita.setTipo(rs.getString(5));
	            cita.setR_Mascota(rs.getInt(6));
	            cita.setNotas(rs.getString(7));
	        	
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
    	
    	return cita;
    }
    
    public boolean delete(int ID) {
    	PreparedStatement ps=null;
        ResultSet rs=null;
    	Conexion conect = new Conexion();

        try {
        	this.query = "DELETE FROM citas WHERE idCitas = ?;";
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
    
    public boolean add(Cita nueva_cita, boolean conHora) {
    	PreparedStatement ps=null;
        ResultSet rs=null;
    	Conexion conect = new Conexion();
    	
        try {
        	if(conHora) {
        		this.query = "INSERT INTO citas (R_Cliente, Fecha, Tipo, R_Mascota, Notas, Hora) " + 
            			"VALUES (?,?,?,?,?,?);";
        	}else {
        		this.query = "INSERT INTO citas (R_Cliente, Fecha, Tipo, R_Mascota, Notas) " + 
            			"VALUES (?,?,?,?,?);";
        	}
        	
        	ps = conect.getConnection().prepareStatement(query);
            
        	 ps.setInt(1,nueva_cita.getR_Cliente());
        	 SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
             ps.setString(2,String.format(objSDF.format((nueva_cita.getFecha()))));
             
             ps.setString(3,nueva_cita.getTipo());
             ps.setInt(4, nueva_cita.getR_Mascota());
             ps.setString(5, nueva_cita.getNotas());
             
             if(conHora) {
                 objSDF = new SimpleDateFormat("HH:mm");
                 ps.setString(6,String.format(objSDF.format(nueva_cita.getHora())));
             }
             
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
    
    public String mostrar_citas() {
		
		Conexion conect = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String retorno = new String("[");
		this.query = "SELECT idCitas, clientes.Nombre, Fecha, date_add(Hora, Interval 1 hour) as Horas, citas.Tipo, mascotas.Nombre, Notas FROM veterinaria.citas " + 
				"inner join clientes on R_Cliente =idClientes " + 
				"inner join mascotas on  R_Mascota=idMascotas " + 
				"where datediff(Fecha,now()) >= 0 " +
				"order by fecha desc; ";
		
		try {
            ps = conect.getConnection().prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Cita new_cita = new Cita();
            	
            	new_cita.setID(rs.getInt(1));
            	new_cita.setS_Cliente(rs.getString(2));
            	new_cita.setFecha(rs.getDate(3));
            	new_cita.setHora(rs.getTime(4));
            	new_cita.setTipo(rs.getString(5));
            	new_cita.setS_Mascota(rs.getString(6));
            	new_cita.setNotas(rs.getString(7));
            	
            	retorno+= new_cita.crear_JSON();
            	
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
    
public String mostrar_citas_todas_de(String nombre, String mascota) {
		
		Conexion conect = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String retorno = new String("[");
		this.query = "SELECT idCitas, clientes.Nombre, Fecha, date_add(Hora, Interval 1 hour) as Horas, citas.Tipo, mascotas.Nombre, Notas FROM veterinaria.citas " + 
				"inner join clientes on R_Cliente =idClientes " + 
				"inner join mascotas on  R_Mascota=idMascotas " + 
				"where clientes.Nombre = ? and mascotas.Nombre = ? " +
				"order by fecha desc; ";
		
		try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, mascota);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Cita new_cita = new Cita();
            	
            	new_cita.setID(rs.getInt(1));
            	new_cita.setS_Cliente(rs.getString(2));
            	new_cita.setFecha(rs.getDate(3));
            	new_cita.setHora(rs.getTime(4));
            	new_cita.setTipo(rs.getString(5));
            	new_cita.setS_Mascota(rs.getString(6));
            	new_cita.setNotas(rs.getString(7));
            	
            	retorno+= new_cita.crear_JSON();
            	
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
    
    public String mostrar_citas_todas() {
		
		Conexion conect = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String retorno = new String("[");
		this.query = "SELECT idCitas, clientes.Nombre, Fecha, date_add(Hora, Interval 1 hour) as Horas, citas.Tipo, mascotas.Nombre, Notas FROM veterinaria.citas " + 
				"inner join clientes on R_Cliente =idClientes " + 
				"inner join mascotas on  R_Mascota=idMascotas " + 
				"order by fecha desc; ";
		
		try {
            ps = conect.getConnection().prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Cita new_cita = new Cita();
            	
            	new_cita.setID(rs.getInt(1));
            	new_cita.setS_Cliente(rs.getString(2));
            	new_cita.setFecha(rs.getDate(3));
            	new_cita.setHora(rs.getTime(4));
            	new_cita.setTipo(rs.getString(5));
            	new_cita.setS_Mascota(rs.getString(6));
            	new_cita.setNotas(rs.getString(7));
            	
            	retorno+= new_cita.crear_JSON();
            	
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
    
    public String mostrar_mascotas_de(int ID) {
    	Conexion conect = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String retorno = new String("[");
		this.query = "SELECT Nombre, idMascotas FROM veterinaria.mascotas where R_Cliente=?; ";
		
		try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            
            	retorno+= "{\"NombreM\":\"" +rs.getString(1)+"\",";
            	retorno+= "\"IDM\":\"" +rs.getInt(2)+"\"}";
            	
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
    
    public String mostrar_citas_hoy(int opc) {
		
		Conexion conect = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String retorno = new String("[");
		this.query = "SELECT idCitas, clientes.Nombre, Fecha, date_add(Hora, Interval 1 hour) as Horas, citas.Tipo, mascotas.Nombre, Notas FROM veterinaria.citas " + 
				"inner join clientes on R_Cliente =idClientes " + 
				"inner join mascotas on  R_Mascota=idMascotas " + 
				"where  (datediff(Fecha,now()) = 0) ";
		switch (opc) {
		case 1:
			this.query+=" and citas.Tipo like '%Esteti%' ";
			break;
		case 2:
			this.query+=" and citas.Tipo like '%Consul%' ";
			break;
		case 3:
			this.query+=" and citas.Tipo like '%Vacuna%' ";
			break;
		case 4:
			this.query+=" and citas.Tipo like '%Opera%' ";
			break;
		case 5:
			this.query+=" and citas.Tipo like '%Otro%' ";
			break;
		default:
			break;
		}
		this.query+="order by fecha;";
		
		try {
            ps = conect.getConnection().prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Cita new_cita = new Cita();
            	
            	new_cita.setID(rs.getInt(1));
            	new_cita.setS_Cliente(rs.getString(2));
            	new_cita.setFecha(rs.getDate(3));
            	new_cita.setHora(rs.getTime(4));
            	new_cita.setTipo(rs.getString(5));
            	new_cita.setS_Mascota(rs.getString(6));
            	new_cita.setNotas(rs.getString(7));
            	
            	retorno+= new_cita.crear_JSON();
            	
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
    	
    public String mostrar_citas_semana(int opc) {
		
		Conexion conect = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String retorno = new String("[");
		this.query = "SELECT idCitas, clientes.Nombre, Fecha, date_add(Hora, Interval 1 hour) as Horas, citas.Tipo, mascotas.Nombre, Notas FROM veterinaria.citas " + 
				"inner join clientes on R_Cliente =idClientes " + 
				"inner join mascotas on  R_Mascota=idMascotas " + 
				"where  (datediff(Fecha,now())  between 0 and 7) "; 
		switch (opc) {
		case 1:
			this.query+=" and citas.Tipo like '%Esteti%' ";
			break;
		case 2:
			this.query+=" and citas.Tipo like '%Consul%' ";
			break;
		case 3:
			this.query+=" and citas.Tipo like '%Vacuna%' ";
			break;
		case 4:
			this.query+=" and citas.Tipo like '%Opera%' ";
			break;
		case 5:
			this.query+=" and citas.Tipo like '%Otro%' ";
			break;
		default:
			break;
		}
		this.query+=" order by fecha;";
		
		try {
            ps = conect.getConnection().prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Cita new_cita = new Cita();
            	
            	new_cita.setID(rs.getInt(1));
            	new_cita.setS_Cliente(rs.getString(2));
            	new_cita.setFecha(rs.getDate(3));
            	new_cita.setHora(rs.getTime(4));
            	new_cita.setTipo(rs.getString(5));
            	new_cita.setS_Mascota(rs.getString(6));
            	new_cita.setNotas(rs.getString(7));
            	
            	retorno+= new_cita.crear_JSON();
            	
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
    
    public String mostrar_citas_mes(int opc) {

		Conexion conect = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String retorno = new String("[");
		this.query = "SELECT idCitas, clientes.Nombre, Fecha, date_add(Hora, Interval 1 hour) as Horas, citas.Tipo, mascotas.Nombre, Notas FROM veterinaria.citas " + 
				"inner join clientes on R_Cliente =idClientes " + 
				"inner join mascotas on  R_Mascota=idMascotas " + 
				"where  (datediff(Fecha,now())  between 0 and 30) ";
		switch (opc) {
		case 1:
			this.query+=" and citas.Tipo like '%Esteti%' ";
			break;
		case 2:
			this.query+=" and citas.Tipo like '%Consul%' ";
			break;
		case 3:
			this.query+=" and citas.Tipo like '%Vacuna%' ";
			break;
		case 4:
			this.query+=" and citas.Tipo like '%Opera%' ";
			break;
		case 5:
			this.query+=" and citas.Tipo like '%Otro%' ";
			break;
		default:
			break;
		}
		this.query+="order by fecha;";
		try {
            ps = conect.getConnection().prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	Cita new_cita = new Cita();
            	
            	new_cita.setID(rs.getInt(1));
            	new_cita.setS_Cliente(rs.getString(2));
            	new_cita.setFecha(rs.getDate(3));
            	new_cita.setHora(rs.getTime(4));
            	new_cita.setTipo(rs.getString(5));
            	new_cita.setS_Mascota(rs.getString(6));
            	new_cita.setNotas(rs.getString(7));
            	
            	retorno+= new_cita.crear_JSON();
            	
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
    
    /*
     * Metodos para las graficas
     *
     */
    public int total_mes(int mes, int year) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int retorno=0;
		
		this.query = "SELECT count(idCitas) from citas " + 
				"where Fecha like ?; ";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setString(1, "%"+year+"-"+String.format("%02d",mes)+"-%");
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	retorno = rs.getInt(1);

            }
	    } catch (Exception var4) {
	        var4.printStackTrace();
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
    	return retorno;
	}
    
    public int estetica_mes(int mes, int year) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int retorno=0;
		
		this.query = "SELECT count(idCitas) from citas " + 
				"where Fecha like ? and Tipo like \"Est%tica\"; ";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setString(1, "%"+year+"-"+String.format("%02d",mes)+"-%");
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	retorno = rs.getInt(1);

            }
	    } catch (Exception var4) {
	        var4.printStackTrace();
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
    	return retorno;
	}
    
    public int consulta_mes(int mes, int year) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int retorno=0;
		
		this.query = "SELECT count(idCitas) from citas " + 
				"where Fecha like ? and Tipo like \"Consulta\"; ";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setString(1, "%"+year+"-"+String.format("%02d",mes)+"-%");
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	retorno = rs.getInt(1);

            }
	    } catch (Exception var4) {
	        var4.printStackTrace();
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
    	return retorno;
	}
    
    public int vacunacion_mes(int mes, int year) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int retorno=0;
		
		this.query = "SELECT count(idCitas) from citas " + 
				"where Fecha like ? and Tipo like \"Vacuna%\"; ";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setString(1, "%"+year+"-"+String.format("%02d",mes)+"-%");
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	retorno = rs.getInt(1);

            }
	    } catch (Exception var4) {
	        var4.printStackTrace();
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
    	return retorno;
	}
    
    public int operacion_mes(int mes, int year) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int retorno=0;
		
		this.query = "SELECT count(idCitas) from citas " + 
				"where Fecha like ? and Tipo like \"Opera%\"; ";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setString(1, "%"+year+"-"+String.format("%02d",mes)+"-%");
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	retorno = rs.getInt(1);

            }
	    } catch (Exception var4) {
	        var4.printStackTrace();
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
    	return retorno;
	}
    
    public int otr_mes(int mes, int year) {
		Conexion conect = new Conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int retorno=0;
		
		this.query = "SELECT count(idCitas) from citas " + 
				"where Fecha like ? and Tipo like \"Otro\"; ";
    	
    	try {
            ps = conect.getConnection().prepareStatement(query);
            ps.setString(1, "%"+year+"-"+String.format("%02d",mes)+"-%");
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	retorno = rs.getInt(1);

            }
	    } catch (Exception var4) {
	        var4.printStackTrace();
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
    	return retorno;
	}
}

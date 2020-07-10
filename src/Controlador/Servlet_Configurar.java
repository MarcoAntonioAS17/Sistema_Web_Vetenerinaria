package Controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Administrador;
import ModeloDAO.AdministradorDAO;
import ModeloDAO.ConfiguracionDAO;


@WebServlet("/Configurar")
public class Servlet_Configurar extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
    public Servlet_Configurar() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		ConfiguracionDAO dao = new ConfiguracionDAO();
		
		if(accion.equals("modificar_dias")) {
			String Dias = request.getParameter("Dias");
			String Cantidad = request.getParameter("Cantidad");
			
			response.setContentType("text/html");
    		response.setCharacterEncoding("UTF-8");
			
			if(dao.Modificar_Datos(Integer.parseInt(Dias), Integer.parseInt(Cantidad))) {
				response.getWriter().write("true");
			}else {
				response.getWriter().write("false");
			}
			return;
		}else {
			AdministradorDAO admin_dao = new AdministradorDAO();
			if(accion.equals("eliminar")) {
				String user = request.getParameter("User");

				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				
				if(admin_dao.delete(user)) {
					response.getWriter().write("true");
				}else {
					response.getWriter().write("false");
				}
				return;
			}else {
				
				if(accion.equals("validar_admin")) {
					
					String NombreA = request.getParameter("NombreA");
					String PasswordA = request.getParameter("PassA");
					
		    		if(admin_dao.autenticacion_admin(NombreA, PasswordA)==1) {
		    			System.out.println("Es 1");
		    			Administrador admin = new Administrador();
						admin.setUserName(request.getParameter("Nombre"));
						admin.setPassword(request.getParameter("Pass"));
						admin.setTipo(Integer.parseInt(request.getParameter("Tipo")));
						
						response.setContentType("text/html");
			    		response.setCharacterEncoding("UTF-8");
						
			    		if(admin_dao.add(admin))
			    			response.getWriter().write("true");
			    		else
			    			response.getWriter().write("2");
		    		}else {
		    			response.getWriter().write("1");
		    		}
					return;
				}else {
					if(accion.equals("modificar")) {
						
						Administrador admin = new Administrador();
						admin.setUserName(request.getParameter("User"));
						admin.setPassword(request.getParameter("PassA"));
						
						response.setContentType("text/html");
			    		response.setCharacterEncoding("UTF-8");
			    		
			    		if(admin_dao.autenticacion(admin)!=0) {
			    			admin.setPassword(request.getParameter("PassN"));
							
				    		if(admin_dao.edit(admin))
				    			response.getWriter().write("true");
				    		else
				    			response.getWriter().write("2");
			    		}else {
			    			response.getWriter().write("1");
			    		}
						return;
					}
				}
			}
			
		}
	
    	
	}

}

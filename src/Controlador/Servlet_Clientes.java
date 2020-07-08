package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Clientes;
import ModeloDAO.ClientesDAO;

@WebServlet("/Clientes")
public class Servlet_Clientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClientesDAO dao = new ClientesDAO();
	Clientes cliente = new Clientes();
	
	public Servlet_Clientes() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		if(accion.equals("agregar")) {
			cliente.setNombreC(request.getParameter("Nom_Cte"));
			cliente.setTelefonoC(request.getParameter("Telefono"));
			cliente.setEmailC(request.getParameter("Email"));
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			if(dao.add(cliente)) {
				response.getWriter().write("true");
			}else {
				response.getWriter().write("false");
			}
		}else {
			if(accion.equals("eliminar")) {
				int idCte = Integer.parseInt(request.getParameter("IDClient"));

				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				
				if(dao.delete(idCte)) {
					response.getWriter().write("true");
				}else {
					response.getWriter().write("false");
				}
			}else {
				if(accion.equals("editar1")) {
					int idCte = Integer.parseInt(request.getParameter("IDClient"));
					cliente = dao.select_one(idCte);
					
					response.setContentType("text/html");
					response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(cliente.crear_JSON());
				    return;
					
				}else {
					if(accion.equals("editar")) {
						cliente.setIDClient(Integer.parseInt(request.getParameter("Clientes")));
						cliente.setNombreC(request.getParameter("Nom_Cte"));
						cliente.setTelefonoC(request.getParameter("Telefono"));
						cliente.setEmailC(request.getParameter("Email"));
						
						response.setContentType("text/html");
						response.setCharacterEncoding("UTF-8");
						
						if(dao.edit(cliente)) 
							response.getWriter().write("true");
						else 
							response.getWriter().write("false");	
					}
				}
			}
			
		}
	}
}
	

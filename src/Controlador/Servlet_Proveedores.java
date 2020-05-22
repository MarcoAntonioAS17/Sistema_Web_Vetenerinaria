package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Proveedor;
import ModeloDAO.CategoriaDAO;
import ModeloDAO.ProveedorDAO;

/**
 * Servlet implementation class Servlet_Proveedores
 */
@WebServlet("/Proveedores")
public class Servlet_Proveedores extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	ProveedorDAO dao = new ProveedorDAO();
	Proveedor proveedor = new Proveedor();
    public Servlet_Proveedores() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		if(accion.equals("agregar")) {
			proveedor.setIDProveedor(Integer.parseInt(request.getParameter("Proveedor")));
			proveedor.setNombre(request.getParameter("Nombre_Pro"));
			proveedor.setTelefono(request.getParameter("Telefono"));
			proveedor.setCorreo(request.getParameter("Email"));

			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			if(dao.add(proveedor)) {
				response.getWriter().write("true");
			}else {
				response.getWriter().write("false");
			}
		}else {
			if(accion.equals("eliminar")) {
				int idpro = Integer.parseInt(request.getParameter("IDProveedor"));

				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				
				if(dao.delete(idpro)) {
					response.getWriter().write("true");
				}else {
					response.getWriter().write("false");
				}
			}
			
			
		}
	}

}

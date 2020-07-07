package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ModeloDAO.CitasDAO;
import ModeloDAO.PrincipalDAO;

@WebServlet("/Principal")
public class Servlet_Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	PrincipalDAO dao = new PrincipalDAO();
	CitasDAO cita_dao = new CitasDAO();
	
    public Servlet_Principal() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		
		if(accion.equals("H_Cliente")) {
			
			HttpSession sesion_user = request.getSession(true);
			
			String mascota = request.getParameter("Mascota");
			String cliente = request.getParameter("Cliente");
			
			sesion_user.setAttribute("Mascota",mascota);
			sesion_user.setAttribute("Cliente", cliente);
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write("true");
			
			return;
		}
		
		if(accion.equals("C_Datos")) {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			response.getWriter().write("{\"PTotales\": \""+dao.Producto_Totales()
					+"\", \"Clientes_Tot\": \""+dao.Clientes_Totales()
					+"\", \"Citas\": \""+dao.Citas_Vigentes()
					+"\", \"Agotarse\": \""+dao.Productos_Agotarse(dao.Cantidad_N())
					+"\", \"Caducar\": \""+dao.Productos_Caducar(dao.Cantidad_Dias())
					+"\", \"VentasSem\": \""+dao.Ventas_Semana()
					+"\"}");
			return;
		}
		
		int Accion = Integer.parseInt(accion);
		int opcion = Integer.parseInt(request.getParameter("Opcion"));
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		switch (Accion) {
		case 1:
				response.getWriter().write(cita_dao.mostrar_citas_hoy(opcion));
			break;
		case 2:
			response.getWriter().write(cita_dao.mostrar_citas_semana(opcion));
			break;
		case 3:
			response.getWriter().write(cita_dao.mostrar_citas_mes(opcion));
			break;
		default:
			break;
		}
	}

}

package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		if(accion.equals("C_Datos")) {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			response.getWriter().write("{\"Totales\": \""+dao.Producto_Totales()
					+"\", \"Agotarse\": \""+dao.Productos_Agotarse()
					+"\", \"Caducar\": \""+dao.Productos_Caducar()
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

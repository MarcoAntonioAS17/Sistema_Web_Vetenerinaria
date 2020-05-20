package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.CategoriaDAO;

/**
 * Servlet implementation class Servlet_Categorias
 */
@WebServlet("/Categorias")
public class Servlet_Categorias extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servlet_Categorias() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String accion = request.getParameter("accion");
		
		if(accion.equals("Agregar")) {
			String nombre = request.getParameter("Nombre_cat");
			
			CategoriaDAO cat = new CategoriaDAO();
			
			if(cat.add(nombre)) {
				response.sendRedirect("Vistas/views/Categorias.jsp");
			}else {
				response.sendRedirect("Vistas/views/Categorias.jsp");
			}
		}
		 
	}

}

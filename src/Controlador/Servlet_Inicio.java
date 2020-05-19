package Controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ModeloDAO.AdministradorDAO;

@WebServlet("/SInicio")
public class Servlet_Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Servlet_Inicio() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String usuario = request.getParameter("Usuario");
    	String password = request.getParameter("Password");
    	
    	AdministradorDAO admin = new AdministradorDAO();
    	HttpSession sesion_user = request.getSession(true);
    	
    	if(admin.autenticacion(usuario, password)) {
    		
    		sesion_user.setAttribute("usuario", usuario);
    		sesion_user.setAttribute("mensaje", "");
    		response.sendRedirect("Vistas/views/Inicio.jsp");
    		
    	}else {
    		sesion_user.setAttribute("mensaje", "Usuario o contraseña incorrectos.");
    		response.sendRedirect("index.jsp");
    	}
    	
	}

}

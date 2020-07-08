package Controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Mascotas;
import ModeloDAO.MascotasDAO;

@WebServlet("/SMascotas")
public class Servlet_Mascotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MascotasDAO dao = new MascotasDAO();
	Mascotas mascotas = new Mascotas();
	
	public Servlet_Mascotas() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		if(accion.equals("mostrar")) {
			String busq=request.getParameter("search");
			int valor = Integer.parseInt(request.getParameter("valor"));
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			response.getWriter().write(dao.mostrar_mascotas(valor,busq));
			return;
		}else {	if(accion.equals("agregar")) {
			mascotas.setR_Cliente(Integer.parseInt(request.getParameter("Cliente")));
			mascotas.setNombre(request.getParameter("Nombre"));
			try {
				SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
				mascotas.setEdad(objSDF.parse(request.getParameter("Edad")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mascotas.setTipo(request.getParameter("Tipo"));
			mascotas.setRaza(request.getParameter("Raza"));
			mascotas.setDescripcion(request.getParameter("Descripcion"));
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			if(dao.add(mascotas)) {
				response.getWriter().write("true");
			}else {
				response.getWriter().write("false");
			}
		}else {	if(accion.equals("eliminar")) {
			int idMas = Integer.parseInt(request.getParameter("IDMascota"));

			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			if(dao.delete(idMas)) {
				response.getWriter().write("true");
			}else {
				response.getWriter().write("false");
			}
		}else { if(accion.equals("editar1")) {
			int idMas = Integer.parseInt(request.getParameter("IDMascota"));
			mascotas = dao.select_one(idMas);
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(mascotas.crear_JSON2());
		    return;
			
		}else {if(accion.equals("editar")) {
			mascotas.setClave(Integer.parseInt(request.getParameter("IDMascota")));
			mascotas.setR_Cliente(Integer.parseInt(request.getParameter("Cliente")));
			mascotas.setNombre(request.getParameter("Nombre"));
			try {
				SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
				mascotas.setEdad(objSDF.parse(request.getParameter("Edad")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mascotas.setTipo(request.getParameter("Tipo"));
			mascotas.setRaza(request.getParameter("Raza"));
			mascotas.setDescripcion(request.getParameter("Descripcion"));
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			if(dao.edit(mascotas)) 
				response.getWriter().write("true");
			else 
				response.getWriter().write("false");	
		}
		}
		}
		}
		}
	}
}
	

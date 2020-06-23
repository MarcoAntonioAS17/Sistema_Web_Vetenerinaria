package Controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Producto;
import ModeloDAO.ProductoDAO;

@WebServlet("/Productos")
public class Servlet_Productos extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	ProductoDAO dao = new ProductoDAO();
	Producto producto = new Producto();
    public Servlet_Productos() {
        super();
        // TODO Auto-generated constructor stub
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
			
			response.getWriter().write(dao.Listar_JSON(valor, busq));
			return;
		}
		
		if(accion.equals("agregar")) {
			producto.setIDProducto(request.getParameter("idProducto"));
			producto.setNombre(request.getParameter("Nombre"));
			producto.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
			producto.setR_Categoria(Integer.parseInt(request.getParameter("Categoria")));
			producto.setPrecio_C(Float.parseFloat(request.getParameter("Precio_C")));
			producto.setPrecio_V(Float.parseFloat(request.getParameter("Precio_V")));
			producto.setR_Proveedor(Integer.parseInt(request.getParameter("Proveedor")));
			producto.setDescripcion(request.getParameter("Descripcion"));
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			String fecha = request.getParameter("Fecha");
			
			if(!fecha.equals("")) {
				//Viene con fecha
				SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
				try {
					producto.setCaducidad(objSDF.parse(fecha));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(dao.add(producto,true)) {
					response.getWriter().write("true");
				}else {
					response.getWriter().write("false");
				}
				return;
			}
			
			if(dao.add(producto,false)) {
				response.getWriter().write("true");
			}else {
				response.getWriter().write("false");
			}
		}else {
			if(accion.equals("eliminar")) {
				String idpro = request.getParameter("IDProductos");

				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				
				if(dao.delete(idpro)) {
					response.getWriter().write("true");
				}else {
					response.getWriter().write("false");
				}
			}else{
				if(accion.equals("editar1")) {
					String idpro = request.getParameter("IDProducto");
					producto = dao.select_one(idpro);
					
					response.setContentType("text/html");
					response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(producto.crear_JSON());
				    return;
					
				}else {
					if(accion.equals("editar")) {
						producto.setIDProducto(request.getParameter("idProducto"));
						producto.setNombre(request.getParameter("Nombre"));
						producto.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
						producto.setR_Categoria(Integer.parseInt(request.getParameter("Categoria")));
						producto.setPrecio_C(Float.parseFloat(request.getParameter("Precio_C")));
						producto.setPrecio_V(Float.parseFloat(request.getParameter("Precio_V")));
						producto.setR_Proveedor(Integer.parseInt(request.getParameter("Proveedor")));
						producto.setDescripcion(request.getParameter("Descripcion"));
						
						String fecha = request.getParameter("Fecha");
						
						response.setContentType("text/html");
						response.setCharacterEncoding("UTF-8");
						
						if(!fecha.equals("")) {
							//Viene con fecha
							SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
							try {
								producto.setCaducidad(objSDF.parse(request.getParameter("Fecha")));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							if(dao.edit(producto,true)) 
								response.getWriter().write("true");
							else 
								response.getWriter().write("false");
							return;
						}
						
						if(dao.edit(producto,false)) 
							response.getWriter().write("true");
						else 
							response.getWriter().write("false");
						
					
						
						
					}
				}
			}
		}
	}
}

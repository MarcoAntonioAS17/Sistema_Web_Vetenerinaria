package Controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Compras;
import Modelo.Detalle_Compras;
import ModeloDAO.CompraDAO;
import ModeloDAO.Detalle_CompraDAO;

@WebServlet("/Ventas")
public class Servlet_Ventas extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	CompraDAO dao = new CompraDAO();
	Detalle_CompraDAO dao_detalle = new Detalle_CompraDAO();
	Compras compra = new Compras();
	Detalle_Compras Dcompra = new Detalle_Compras();
    public Servlet_Ventas() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		if(accion.equals("agregar_producto")) {

			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			//Guardamos la compra general
			compra.setR_Proveedor(Integer.parseInt(request.getParameter("Proveedor")));
			
			try {
				SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
				compra.setFecha(objSDF.parse(request.getParameter("Fecha")));
				objSDF = new SimpleDateFormat("hh:mm");
				compra.setHora(objSDF.parse(request.getParameter("Hora")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if(!dao.add(compra)) {
				response.getWriter().write("");
				return;
			}
			
			
			compra.setIdCompra(dao_detalle.buscar_compra(compra.getFecha(),compra.getHora()));
			
			
			Dcompra.setR_Producto(request.getParameter("Producto"));
			Dcompra.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
			Dcompra.setR_Compra(compra.getIdCompra());
			
			int inventario = dao_detalle.consultar_inventario(Dcompra.getR_Producto());
			
			if( (inventario-Dcompra.getCantidad()) < 0) {
				response.getWriter().write("false");
				return;
			}

			if(!dao_detalle.modificar_inventario(Dcompra.getR_Producto(), (inventario-Dcompra.getCantidad())))
			{
				response.getWriter().write("false");
				return;
			}
			
			if(dao_detalle.add(Dcompra)) {
				response.getWriter().write(dao_detalle.Listar_JSON(compra.getIdCompra()));
			}else {
				response.getWriter().write("false");
			}
		}else {
			if(accion.equals("agregar_producto2")) {
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				
				Dcompra.setR_Producto(request.getParameter("Producto"));
				Dcompra.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
				
				int inventario = dao_detalle.consultar_inventario(Dcompra.getR_Producto());
				
				if( (inventario-Dcompra.getCantidad()) < 0) {
					response.getWriter().write("false");
					return;
				}
				
				if(!dao_detalle.modificar_inventario(Dcompra.getR_Producto(), (inventario-Dcompra.getCantidad())))
				{
					return;
				}
				
				if(dao_detalle.add(Dcompra)) {
					response.getWriter().write(dao_detalle.Listar_JSON(compra.getIdCompra()));
				}else {
					response.getWriter().write("false");
				}
			}else {
			}
		}
		
		/*if(accion.equals("mostrar")) {
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
			SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
			try {
				producto.setCaducidad(objSDF.parse(request.getParameter("Fecha")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			producto.setR_Proveedor(Integer.parseInt(request.getParameter("Proveedor")));
			producto.setDescripcion(request.getParameter("Descripcion"));
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			if(dao.add(producto)) {
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
						SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
						try {
							producto.setCaducidad(objSDF.parse(request.getParameter("Fecha")));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						producto.setR_Proveedor(Integer.parseInt(request.getParameter("Proveedor")));
						producto.setDescripcion(request.getParameter("Descripcion"));
						
						response.setContentType("text/html");
						response.setCharacterEncoding("UTF-8");
						
						if(dao.edit(producto)) 
							response.getWriter().write("true");
						else 
							response.getWriter().write("false");
						
						
					}
				}
			}
		}*/
	}
}

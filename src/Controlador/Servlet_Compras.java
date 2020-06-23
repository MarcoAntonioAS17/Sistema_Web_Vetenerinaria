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

@WebServlet("/Compras")
public class Servlet_Compras extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	CompraDAO dao = new CompraDAO();
	Detalle_CompraDAO dao_detalle = new Detalle_CompraDAO();
	Compras compra = new Compras();
	Detalle_Compras Dcompra = new Detalle_Compras();
    public Servlet_Compras() {
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

			if(!dao_detalle.modificar_inventario(Dcompra.getR_Producto(), (inventario+Dcompra.getCantidad())))
			{
				response.getWriter().write("false");
				return;
			}
			
			if(dao_detalle.add(Dcompra)) {
				response.getWriter().write(dao_detalle.Listar_JSON(compra.getIdCompra()));
			}else {
				response.getWriter().write("false");
			}
		}else { if(accion.equals("agregar_producto2")) {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			Dcompra.setR_Producto(request.getParameter("Producto"));
			Dcompra.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
			
			int inventario = dao_detalle.consultar_inventario(Dcompra.getR_Producto());
			
			if(!dao_detalle.modificar_inventario(Dcompra.getR_Producto(), (inventario+Dcompra.getCantidad())))
			{
				return;
			}
			int cantidad = dao_detalle.consultar_cantidad(Dcompra.getR_Producto(),Dcompra.getR_Compra());
			
			if(cantidad >0) {
				dao_detalle.modificar_cantidad(Dcompra.getR_Producto(),Dcompra.getR_Compra(),Dcompra.getCantidad()+cantidad);
				response.getWriter().write(dao_detalle.Listar_JSON(compra.getIdCompra()));
			}else {
				if(dao_detalle.add(Dcompra)) {
					response.getWriter().write(dao_detalle.Listar_JSON(compra.getIdCompra()));
				}else {
					response.getWriter().write("false");
				}
			}
			
			
		}else { if(accion.equals("borrar_producto")) {
			Dcompra.setR_Producto( request.getParameter("Producto"));
			Dcompra.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			if(!dao_detalle.eliminar_producto(Dcompra.getR_Producto(),Dcompra.getR_Compra())) {
				response.getWriter().write("false");
				return;
			}
			int inventario = dao_detalle.consultar_inventario(Dcompra.getR_Producto());
			
			if(!dao_detalle.modificar_inventario(Dcompra.getR_Producto(), (inventario-Dcompra.getCantidad())))
			{
				response.getWriter().write("false");
				return;
			}
			
			response.getWriter().write(dao_detalle.Listar_JSON(compra.getIdCompra()));
		}else { if(accion.equals("cancelar_compra")) {
			
			boolean retorno=true;
			dao.regresar_inventario(Dcompra.getR_Compra());
			retorno=dao_detalle.eliminar_productos(Dcompra.getR_Compra());
			if(retorno)
				retorno=dao.eliminar_compra(Dcompra.getR_Compra());
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(""+retorno);
			
		}else {if(accion.equals("cargar_productos")) {
			
			int Cod_Pro = Integer.parseInt(request.getParameter("Proveedor"));
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(dao_detalle.producto_x_proveedor(Cod_Pro));
				
		}else{
			if(accion.equals("mostrar_compras")) {
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				int opcion = Integer.parseInt(request.getParameter("valor"));
				String busq = request.getParameter("search");
				
				response.getWriter().write(dao.mostrar_compras(opcion,busq));
		}
			
		}
		}
		}
		}
		}
		
		
	}
}

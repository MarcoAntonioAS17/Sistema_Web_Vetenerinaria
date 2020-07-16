package Controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Detalle_Ventas;
import Modelo.Ventas;
import ModeloDAO.Detalle_VentaDAO;
import ModeloDAO.VentaDAO;

@WebServlet("/Ventas")
public class Servlet_Ventas extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	VentaDAO dao = new VentaDAO();
	Detalle_VentaDAO dao_detalle = new Detalle_VentaDAO();
	Ventas venta = new Ventas();
	Detalle_Ventas Dventa = new Detalle_Ventas();
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
		
		if(accion.equals("agregar_servicio")) {

			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			//Guardamos la venta general
			venta.setR_Cliente(Integer.parseInt(request.getParameter("Cliente")));
			
			try {
				SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
				venta.setFecha(objSDF.parse(request.getParameter("Fecha")));
				objSDF = new SimpleDateFormat("HH:mm");
				venta.setHora(objSDF.parse(request.getParameter("Hora")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if(!dao.add(venta)) {
				response.getWriter().write("false");
				return;
			}
			
			venta.setIDVenta(dao_detalle.buscar_venta(venta.getFecha(),venta.getHora()));
			
			String servicio = request.getParameter("Servicio");
			float precio = Integer.parseInt(request.getParameter("Precio"));
			
			//Buscamos si ya existe el servicio con el precio
			int codigo = dao.buscar_servicio(servicio, precio);
			//Si no, lo creamos
			if(codigo==0) {
				codigo = dao.ultimo_codigo()+1;
				if(!dao.add_servicio(codigo, servicio, precio)) {
					response.getWriter().write("false");
					return;
				}
			}
			
			Dventa.setR_Producto(codigo+"");
			Dventa.setCantidad(1);
			Dventa.setR_Venta(venta.getIDVenta());
			
			if(dao_detalle.add(Dventa)) {
				response.getWriter().write(dao_detalle.Listar_JSON(venta.getIDVenta()));
			}else {
				response.getWriter().write("false");
			}
			return;
		}
		
		if(accion.equals("agregar_servicio2")) {

			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			String servicio = request.getParameter("Servicio");
			float precio = Integer.parseInt(request.getParameter("Precio"));
			
			//Buscamos si ya existe el servicio con el precio
			int codigo = dao.buscar_servicio(servicio, precio);
			//Si no, lo creamos
			if(codigo==0) {
				codigo = dao.ultimo_codigo()+1;
				if(!dao.add_servicio(codigo, servicio, precio)) {
					response.getWriter().write("false");
					return;
				}
			}
			
			Dventa.setR_Producto(codigo+"");
			Dventa.setCantidad(1);
			Dventa.setR_Venta(venta.getIDVenta());
			
			int cantidad2 = dao_detalle.consultar_cantidad(Dventa.getR_Producto(),Dventa.getR_Venta());
			
			if(cantidad2 >0) {
				dao_detalle.modificar_cantidad(Dventa.getR_Producto(),Dventa.getR_Venta(),Dventa.getCantidad()+cantidad2);
				response.getWriter().write(dao_detalle.Listar_JSON(venta.getIDVenta()));
			}else {
				if(dao_detalle.add(Dventa)) {
					response.getWriter().write(dao_detalle.Listar_JSON(venta.getIDVenta()));
				}else {
					response.getWriter().write("false");
				}
			
				return;
			}
		}
		
		
		if(accion.equals("agregar_producto")) {

			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			//Guardamos la venta general
			venta.setR_Cliente(Integer.parseInt(request.getParameter("Cliente")));
			
			try {
				SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
				venta.setFecha(objSDF.parse(request.getParameter("Fecha")));
				objSDF = new SimpleDateFormat("HH:mm");
				venta.setHora(objSDF.parse(request.getParameter("Hora")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if(!dao.add(venta)) {
				response.getWriter().write("false");
				return;
			}
			
			
			venta.setIDVenta(dao_detalle.buscar_venta(venta.getFecha(),venta.getHora()));
			
			
			Dventa.setR_Producto(request.getParameter("Producto"));
			Dventa.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
			Dventa.setR_Venta(venta.getIDVenta());
			
			int inventario = dao_detalle.consultar_inventario(Dventa.getR_Producto());
			
			if( (inventario-Dventa.getCantidad()) < 0) {
				response.getWriter().write("false");
				return;
			}

			if(!dao_detalle.modificar_inventario(Dventa.getR_Producto(), (inventario-Dventa.getCantidad())))
			{
				response.getWriter().write("false");
				return;
			}
			
			if(dao_detalle.add(Dventa)) {
				response.getWriter().write(dao_detalle.Listar_JSON(venta.getIDVenta()));
			}else {
				response.getWriter().write("false");
			}
		}else {
			
			if(accion.equals("agregar_producto2")) {
				
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				
				Dventa.setR_Producto(request.getParameter("Producto"));
				Dventa.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
				
				int inventario = dao_detalle.consultar_inventario(Dventa.getR_Producto());
				
				if( (inventario-Dventa.getCantidad()) < 0) {
					response.getWriter().write("false");
					return;
				}
				
				if(!dao_detalle.modificar_inventario(Dventa.getR_Producto(), (inventario-Dventa.getCantidad())))
				{
					return;
				}
				
				int cantidad = dao_detalle.consultar_cantidad(Dventa.getR_Producto(),Dventa.getR_Venta());
				
				if(cantidad >0) {
					dao_detalle.modificar_cantidad(Dventa.getR_Producto(),Dventa.getR_Venta(),Dventa.getCantidad()+cantidad);
					response.getWriter().write(dao_detalle.Listar_JSON(venta.getIDVenta()));
				}else {
					if(dao_detalle.add(Dventa)) {
						response.getWriter().write(dao_detalle.Listar_JSON(venta.getIDVenta()));
					}else {
						response.getWriter().write("false");
					}
				}
				
			}else { if(accion.equals("borrar_producto")) {
				Dventa.setR_Producto( request.getParameter("Producto"));
				Dventa.setCantidad(Integer.parseInt(request.getParameter("Cantidad")));
				
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				
				if(!dao_detalle.eliminar_producto(Dventa.getR_Producto(),Dventa.getR_Venta())) {
					response.getWriter().write("false");
					return;
				}
				int inventario = dao_detalle.consultar_inventario(Dventa.getR_Producto());
				
				if(!dao_detalle.modificar_inventario(Dventa.getR_Producto(), (inventario+Dventa.getCantidad())))
				{
					response.getWriter().write("false");
					return;
				}
				
				response.getWriter().write(dao_detalle.Listar_JSON(venta.getIDVenta()));
			}else { 
				
				if(accion.equals("cancelar_venta")) {
				
				boolean retorno=true;
				dao.regresar_inventario(Dventa.getR_Venta());
				retorno=dao_detalle.eliminar_productos(Dventa.getR_Venta());
				if(retorno)
					retorno=dao.eliminar_venta(Dventa.getR_Venta());
				
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(""+retorno);
				
			}else {
				
				if(accion.equals("cargar_productos")) {
				
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(dao_detalle.cargar_productos());
					
			}else{
				if(accion.equals("mostrar_ventas")) {
					response.setContentType("text/html");
					response.setCharacterEncoding("UTF-8");
					int opcion = Integer.parseInt(request.getParameter("valor"));
					String busq = request.getParameter("search");
					
					String conFecha = request.getParameter("marcado");
					int ver = Integer.parseInt(request.getParameter("Ver"));
					
					
					if(conFecha.equals("Si")) {
						String fecha_men = request.getParameter("fecha_men");
						String fecha_may = request.getParameter("fecha_may");
						
						response.getWriter().write(dao.mostrar_ventas(opcion,busq,true,fecha_men,fecha_may,ver));
					}else {
						response.getWriter().write(dao.mostrar_ventas(opcion,busq,false,null,null,ver));
					}
					
					
			}
				
			}
			}
			}
			}
			}
			
		}
	}

package Controlador;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.CitasDAO;
import ModeloDAO.CompraDAO;
import ModeloDAO.VentaDAO;

@WebServlet("/Grafica")
public class Servlet_Grafica extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	public Servlet_Grafica() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		Calendar c2 = new GregorianCalendar();
        
		int Mes=c2.get(Calendar.MONTH)+1;
		int year=c2.get(Calendar.YEAR);
		
		if(accion.equals("grafica_ventas")) {
			VentaDAO dao = new VentaDAO();
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			String Retorno= new String("{\"VentasM\": [");
			String Meses = "\"Meses\":[";
			String Ventas_Pro = "\"V_Prod\":[";
			String Ventas_Ser = "\"V_Ser\":[";
			
			for(int i=0;i<12;i++) {
				
				if(i<11) {
					Retorno+=dao.total_mes(Mes,year)+", ";
					Ventas_Pro+=dao.productos_mes(Mes, year)+", ";
					Ventas_Ser+=dao.servicios_mes(Mes, year)+", ";
					Meses +=Mes+", ";
				}else {
					Ventas_Pro+=dao.productos_mes(Mes, year);
					Ventas_Ser+=dao.servicios_mes(Mes, year);
					Retorno+=dao.total_mes(Mes,year);
					Meses +=Mes;
				}
				Mes--;
				if(Mes==0) {
					year--;
					Mes=12;
				}
			}
			Meses+="],";
			Ventas_Ser+="],";
			Ventas_Pro+="]";
			Retorno+="],";
			Retorno+=Meses+Ventas_Ser+Ventas_Pro;
			
			Retorno+="}";
			
			response.getWriter().write(Retorno);
			
			return;
			
		}
		
		if(accion.equals("grafica_compras")) {
			CompraDAO dao = new CompraDAO();
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			String Retorno= new String("{\"ComprasM\": [");
			String Meses = "\"Meses\":[";
			
			for(int i=0;i<12;i++) {
				
				if(i<11) {
					Retorno+=dao.total_mes(Mes,year)+", ";
					Meses +=Mes+", ";
				}else {
					Retorno+=dao.total_mes(Mes,year);
					Meses +=Mes;
				}
				Mes--;
				if(Mes==0) {
					year--;
					Mes=12;
				}
			}
			Meses+="]";
			Retorno+="],";
			Retorno+=Meses;
			
			Retorno+="}";
			
			response.getWriter().write(Retorno);
			
			return;
			
		}
		
		if(accion.equals("grafica_citas")) {
			CitasDAO dao = new CitasDAO();
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			String Retorno= new String("{\"CitasM\": [");
			String Meses = "\"Meses\":[";
			String Citas_E = "\"C_Est\":[";
			String Citas_C = "\"C_Con\":[";
			String Citas_V = "\"C_Vac\":[";
			String Citas_Op = "\"C_Ope\":[";
			String Citas_Ot = "\"C_Otr\":[";
			
			for(int i=0;i<12;i++) {
				
				if(i<11) {
					Retorno+=dao.total_mes(Mes,year)+", ";
					Citas_E+=dao.estetica_mes(Mes, year)+", ";
					Citas_C+=dao.consulta_mes(Mes, year)+", ";
					Citas_V+=dao.vacunacion_mes(Mes, year)+", ";
					Citas_Op+=dao.operacion_mes(Mes, year)+", ";
					Citas_Ot+=dao.otr_mes(Mes, year)+", ";
					Meses +=Mes+", ";
				}else {
					Citas_E+=dao.estetica_mes(Mes, year);
					Citas_C+=dao.consulta_mes(Mes, year);
					Citas_V+=dao.vacunacion_mes(Mes, year);
					Citas_Op+=dao.operacion_mes(Mes, year);
					Citas_Ot+=dao.otr_mes(Mes, year);
					Retorno+=dao.total_mes(Mes,year);
					Meses +=Mes;
				}
				Mes--;
				if(Mes==0) {
					year--;
					Mes=12;
				}
			}
			Meses+="],";
			Citas_E+="],";
			Citas_V+="],";
			Citas_Op+="],";
			Citas_Ot+="],";
			Citas_C+="]";
			Retorno+="],";
			Retorno+=Meses+Citas_E+Citas_V+Citas_Op+Citas_Ot+Citas_C;
			
			
			Retorno+="}";
			
			response.getWriter().write(Retorno);
			
			return;
		}

	}
}
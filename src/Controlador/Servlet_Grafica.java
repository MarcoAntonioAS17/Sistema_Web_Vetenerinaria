package Controlador;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.VentaDAO;

@WebServlet("/Grafica")
public class Servlet_Grafica extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	VentaDAO dao = new VentaDAO();
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
		
		if(accion.equals("grafica_ventas")) {

			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			Calendar c2 = new GregorianCalendar();
            
			int Mes=c2.get(Calendar.MONTH)+1;
			int year=c2.get(Calendar.YEAR);
			String Retorno="{\"VentasM\": [";
			String Meses = "\"Meses\":[";
			
			for(int i=0;i<12;i++) {
				
				if(i<11) {
					Retorno+=dao.total_mes(Mes,year)+", ";
					Meses +=Mes+", ";
				}else {
					Retorno+=dao.total_mes(Mes,year)+"";
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
			Retorno+=Meses+"}";
			
			response.getWriter().write(Retorno);
			
			return;
			
		}

	}
}
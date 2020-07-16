package Controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Cita;
import ModeloDAO.CitasDAO;

@WebServlet("/SCitas")
public class Servlet_Citas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Cita cita = new Cita();
	CitasDAO dao = new CitasDAO();
	
	public Servlet_Citas() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		if(accion.equals("mostrar_todas")) {
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			String conFecha = request.getParameter("marcado");
			
			if(conFecha.equals("Si")) {
				String fecha_men = request.getParameter("fecha_men");
				String fecha_may = request.getParameter("fecha_may");
				
				response.getWriter().write(dao.mostrar_citas_todas(true,fecha_men,fecha_may));
			}else {
				response.getWriter().write(dao.mostrar_citas_todas(false,null,null));
			}
			
			return;
		}
		if(accion.equals("mostrar_todas_de")) {
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			String mascota = request.getParameter("Mascota");
			String cliente = request.getParameter("Cliente");
			
			String conFecha = request.getParameter("marcado");
			
			if(conFecha.equals("Si")) {
				String fecha_men = request.getParameter("fecha_men");
				String fecha_may = request.getParameter("fecha_may");
				response.getWriter().write(dao.mostrar_citas_todas_de(cliente, mascota,true,fecha_men,fecha_may));
			}else {
				response.getWriter().write(dao.mostrar_citas_todas_de(cliente,mascota,false,null,null));
			}
			
			return;
		}
		if(accion.equals("mostrar")) {
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			response.getWriter().write(dao.mostrar_citas());
			return;
		}else {
			if(accion.equals("MascotasDe")) {
				
				int IDClient = Integer.parseInt(request.getParameter("Cliente"));
				
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				
				response.getWriter().write(dao.mostrar_mascotas_de(IDClient));
				return;
			}else {
				if(accion.equals("agregar")) {
					boolean conHora=false;
					cita.setTipo(request.getParameter("Tipo"));
					try {
						SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
						cita.setFecha(objSDF.parse(request.getParameter("Fecha")));
						
						String hora = request.getParameter("Hora");
						if(!hora.equals("")) {
							conHora=true;
							objSDF = new SimpleDateFormat("HH:mm");
							cita.setHora(objSDF.parse(request.getParameter("Hora")));
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					cita.setR_Cliente(Integer.parseInt(request.getParameter("Cliente")));
					cita.setR_Mascota(Integer.parseInt(request.getParameter("Mascota")));
					cita.setNotas(request.getParameter("Notas"));
					
					response.setContentType("text/html");
					response.setCharacterEncoding("UTF-8");
					
					if(dao.add(cita,conHora)) {
						response.getWriter().write("true");
					}else {
						response.getWriter().write("false");
					}
					return;
				}else {
					if(accion.equals("eliminar")) {
						int idMas = Integer.parseInt(request.getParameter("IDCita"));

						response.setContentType("text/html");
						response.setCharacterEncoding("UTF-8");
						
						if(dao.delete(idMas)) {
							response.getWriter().write("true");
						}else {
							response.getWriter().write("false");
						}
					}else {
						if(accion.equals("editar1")) {
							int idCita = Integer.parseInt(request.getParameter("IDCita"));
							cita = dao.select_one(idCita);
							
							response.setContentType("text/html");
							response.setCharacterEncoding("UTF-8");
						    response.getWriter().write(cita.crear_JSON2());
						    return;
						}else {
							if(accion.equals("editar")) {
								boolean conHora=false;
								cita.setTipo(request.getParameter("Tipo"));
								try {
									SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
									cita.setFecha(objSDF.parse(request.getParameter("Fecha")));
									
									String hora = request.getParameter("Hora");
									if(!hora.equals("")) {
										conHora=true;
										objSDF = new SimpleDateFormat("HH:mm");
										cita.setHora(objSDF.parse(request.getParameter("Hora")));
									}
								} catch (ParseException e) {
									e.printStackTrace();
								}
								
								cita.setR_Cliente(Integer.parseInt(request.getParameter("Cliente")));
								cita.setR_Mascota(Integer.parseInt(request.getParameter("Mascota")));
								cita.setNotas(request.getParameter("Notas"));
								
								response.setContentType("text/html");
								response.setCharacterEncoding("UTF-8");
								
								if(dao.edit(cita,conHora)) 
									response.getWriter().write("true");
								else 
									response.getWriter().write("false");
								
								return;
							}
						}
					}
				}
			}
		}
		/*
		
		}
		}
		}
		}
		*/
	}
}
	

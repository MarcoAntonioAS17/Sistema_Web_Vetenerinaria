<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<%@page import="Modelo.Clientes"%>
<%@page import="ModeloDAO.ClientesDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	HttpSession user_session = request.getSession(false);
	String usuario = (String) user_session.getAttribute("usuario");
	String ST = (String) user_session.getAttribute("tipo");
	if(usuario == null){
		response.sendRedirect("../../index.jsp");
		return;
	}
	
	int Tipo = Integer.parseInt(ST);
	
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="imagen/png" href="../img/Logo.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Agenda</title>

    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../icons/style.css">
    <link rel="stylesheet" href="../CSS/ver_citas.css">
</head>
    
<body>
	
	<input type="checkbox" id="abrir-cerrar" name="abrir-cerrar" value="" >
    <div id="label_sup">
        <label id="hamburger" for="abrir-cerrar">
            <div class="button">
                <div class="menu_button"></div>
                <div class="menu_button"></div>
                <div class="menu_button"></div>
            </div>
        </label>
        <img id="logo-extend" src="../img/Logo-Extend-Extend.svg" alt="logo-extendido">
    </div>
	
	 <%
    	switch(Tipo){
    		 case 1:%>
    			<jsp:include page="Includes/Menu_Principal.jsp"></jsp:include>		 
    			 <%break;
    		 case 2:%>
    		 	<jsp:include page="Includes/Menu_Principal2.jsp"></jsp:include>
    			 <%break;
    		 case 3:%>
    		 	<jsp:include page="Includes/Menu_Principal3.jsp"></jsp:include>
    			 <%break;
    	}
    %>
    <br><br><br><br>
    
    <div class="contenido"  >
        <h1>Citas</h1>
        
            <table>
                <thead>
                    <tr>
                        <th>Clave</th>
                        <th>Tipo</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Cliente</th>
                        <th>Mascota</th>
                        <th>Notas</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    
                </tbody>
            </table>
        
    </div>
    <br>
    <%if(Tipo!=3){ %>
    <form id="formulario" action="" class="formulario">
        <h1>Nueva Cita</h1><br><br>
            
            
            
	        <select id="tipo">	        		
	               <option value="Estetica">Estetica</option>
	               <option value="Consulta">Consulta</option>
	               <option value="Vacunacion">Vacunación</option>
	               <option value="Operacion">Operación</option>
	               <option value="Otro">Otro</option>
	        </select>
	        <label for="tipo" class="formulario__label estatic ">Tipo de Cita</label>
            
            <input id="clv" type="hidden" value="">
            
            <input id="Fecha" type="date" class="formulario__input" required="required"> 
            <label for="Fecha" class="formulario__label estatic">Fecha</label>
            
            <input id="Hora" type="time" class="formulario__input" > 
            <label for="Hora" class="formulario__label estatic">Hora</label>
            
	        <select id="Client">
	        	<%
	        		ClientesDAO daocat = new ClientesDAO();
	           		List<Clientes> listcat = daocat.listar();
	           		Iterator<Clientes> itercat = listcat.iterator();
	           		Clientes clie=null;
	           		while(itercat.hasNext()){
	           			clie=itercat.next();
	           	%>
	               <option value="<%=clie.getIDClient() %>"><%=clie.getNombreC() %></option>
	               
	           <%} %>	
	        </select>
	        <label for="Client" class="formulario__label estatic">Nombre Cliente</label>
	        
	        <select id="Masc">
	        	
	        </select>
	        <label for="Masc" class="formulario__label estatic">Mascota</label>
	        
	        <input id="Notas" type="text" class="formulario__input" > 
            <label for="Notas" class="formulario__label estatic">Notas</label>
            
            
        <div>
        	<button id="actualizar" type="button" class="actualizar">Actualizar</button>
            <button id="guardar" type="button" class="guardar">Guardar</button>
            <button id="cancelar" type="reset" class="cancelar">Cancelar</button>
        </div>
    </form>
    <%} %>


    <script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/Citas.js"></script>
    
</body>
</html>
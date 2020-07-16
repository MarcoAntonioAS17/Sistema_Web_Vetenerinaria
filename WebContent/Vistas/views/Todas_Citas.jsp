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
	
	switch(Tipo){
		 case 3:
			 response.sendRedirect("Inicio.jsp");
			break;
	}
	
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
    <div id="grafica" class="contenido">
    <canvas id="MiGrafica" ></canvas>
    </div>
    
    <br><br>
    <div class="contenido">
        <h1>Citas</h1>
        
        <div id="fecha-bus" >
        	<input id="busc_calendar" type="checkbox">
            <label id="fbusc_calendar" for="busc_calendar">Buscar entre fechas</label>
            
            <label class="busq_fecha" for="Fecha_Men">Desde: </label>
            <input class="busq_fecha" id="Fecha_Men" type="date">
            
            <label class="busq_fecha" for="Fecha_May">Hasta: </label>
            <input class="busq_fecha" id="Fecha_May" type="date">
        </div>
        <button class="but-descargar" id="crearPDF">Guardar PDF</button>
        
	    <table id="reporte">
	        <thead>
	            <tr>
	                <th>Clave</th>
	                <th>Tipo</th>
	                <th>Fecha</th>
	                <th>Hora</th>
	                <th>Cliente</th>
	                <th>Mascota</th>
	                <th>Notas</th>
	            </tr>
	        </thead>
	        <tbody>
	            
	        </tbody>
	    </table>
        
    </div>
    <br>
    <script type="text/javascript" src="../scripts/jspdf.min.js"></script>
	<script type="text/javascript" src="../scripts/jspdf.plugin.autotable.min.js"></script>
    <script type="text/javascript" src="../scripts/Chart.js"></script>

    <script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/Todas_Citas.js"></script>
    
</body>
</html>
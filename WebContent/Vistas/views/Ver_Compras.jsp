<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		case 2:
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
    <title>Compras</title>

    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../icons/style.css">
    <link rel="stylesheet" href="../CSS/Ver_Compras.css">
</head>
    
<body>
	
	<input type="checkbox" id="abrir-cerrar" name="abrir-cerrar" value="">
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
    <br><br>
   	<button id="create_pdf" type="button" class="create_pdf">Descargar PDF</button>
    <br><br>
    
    <form class="form" style="max-width: none;"> 
    <div id="contenido1" class="contenido"  >
        <h1>Historial de compras</h1>
            <form id="busqueda">
              <label>Buscar</label>
              <select id="opciones" name="opciones">
                   <option value="1">Codigo Compra</option>
                  <option value="2">Proveedor</option>
                  <option value="3">Fecha</option>
                  <option value="4">Hora</option>
              </select>
            
               <input id="busqueda_input" type="search" placeholder="Busqueda"> 
            </form>
            
         
            <table id="reporte">
                <thead>
                    <tr>
                    	<th>Código</th>
                    	<th>Proveedor</th>
                    	<th>Fecha</th>
                    	<th>Hora</th>
                        <th>Total</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="tbody1">
				</tbody>
            </table>
        
		</div>
    </form>

    <script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/Tabla_Extendible.js"></script>
   	<script type="text/javascript" src="../scripts/ver_compras.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>  
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script> 
    
</body>
</html>
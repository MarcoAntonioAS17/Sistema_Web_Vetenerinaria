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
	String Cliente = (String) user_session.getAttribute("Cliente");
	String Mascota = (String) user_session.getAttribute("Mascota");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="imagen/png" href="../img/Logo.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Historial Ventas</title>

    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../icons/style.css">
    <link rel="stylesheet" href="../CSS/historial_cliente.css">
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
    
    <br><br><br><br>
    
    <div id="contenido1" class="contenido"  >
        <h1>Historial de citas (<%=Cliente%>)</h1>
        	<input id="cliente" type="hidden" value="<%=Cliente%>">
        	<input id="mascota" type="hidden" value="<%=Mascota%>">
			
			<button class="but-descargar" id="crearPDF">Guardar PDF</button>
            <button class="but-descargar" id="crearPDF-detall">Guardar PDF Detallado</button>
                  
             <table id="my-table">
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
                <tbody id="body_citas">
                    
                </tbody>
            </table>
        
	</div>
    
   <div id="contenido1" class="contenido"  >
        <h1>Historial de ventas (<%=Cliente%>)</h1>
        	
            <table id="reporte">
                <thead>
                    <tr>
                    	<th>Código</th>
                    	<th>Cliente</th>
                    	<th>Fecha</th>
                    	<th>Hora</th>
                        <th>Total</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="tbody1">
				</tbody>
            </table>
            
            <table id="reporte2">
                <thead>
                    <tr>
                        <th>Código Venta</th>
                    	<th>Cliente</th>
                    	<th>Fecha</th>
                    	<th>Hora</th>
                        <th>Código Producto</th>
                        <th>Producto</th>
                        <th>Precio Unit.</th>
                        <th>Cantidad</th>
                        <th>Precio Tot.</th>
                        <th>Total Venta</th>
                    </tr>
                </thead>
                <tbody id="tbody2">
				</tbody>
            </table>
        
	</div>
    
    <script type="text/javascript" src="../scripts/jspdf.min.js"></script>
	<script type="text/javascript" src="../scripts/jspdf.plugin.autotable.min.js"></script>
    
    <script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/Tabla_Extendible.js"></script>
   	<script type="text/javascript" src="../scripts/Historial_Clientes.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    
</body>
</html>
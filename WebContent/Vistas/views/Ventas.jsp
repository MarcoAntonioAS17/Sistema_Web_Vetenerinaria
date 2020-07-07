
<%@page import="Modelo.Clientes"%>
<%@page import="ModeloDAO.ClientesDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

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
    <title>Ventas</title>

    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../icons/style.css">
    <link rel="stylesheet" href="../CSS/ventas.css">
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
    
    <form action="" class="formulario">
        <h1>Nueva Venta</h1>
        <div id="date-time">
	        <label for="Fec_C" class="label-input-40">Fecha</label>
	        <label for="Hra_C" class="label-input-40">Hora</label>
	        
	        <input id="Fec_C" name="Fec_C" type="date" class="input-40">  
	        <input id="Hra_C" name="Hra_C" type="time" class="input-40">  
        </div>
        
        <label class="formulario__label_fija">Cliente</label>
        <select id="Clv_Client" name="opciones" class="formulario__input">
        	<%
	        	ClientesDAO provedao = new ClientesDAO();
	    		List<Clientes> listprove = provedao.listar();
	    		Iterator<Clientes> iterprove = listprove.iterator();
	    		Clientes client=null;
	    		while(iterprove.hasNext()){
    			client=iterprove.next();
           	%>
               <option value="<%=client.getIDClient() %>"><%=client.getNombreC()%></option>
               
           <%} %>
        </select>
        
        <label class="formulario__label_fija">Opciones</label>
       		<select id="otro_opc" class="formulario__input">
       			<option value="1">Producto</option>
       			<option value="2">Servicio Veterinario</option>
       		</select>
        
       <div id="cont-producto">
       		
       		<label for="Clv_Prod" class="label-input-20">Código del producto</label>
	        <input  id="Clv_Prod" list="list_prod" class="input-20" placeholder="Código">
	        <datalist id="list_prod">
	        	
	        </datalist>
	        
	        <label for="Cantidad" class="label-input-20">Cantidad</label>
	        <input id="Cantidad" type="number" class="input-20" placeholder="Cantidad">  
        
        	<button id="agregar" type="button" class="agregar">Agregar Producto</button>
        	
        	
        	<label for="servicio" class="label-input-20 hide">Concepto</label>
	        
	        <select id="servicio" class="input-20 hide">
	        	<option value="Consulta">Consulta</option>
	        	<option value="Operación">Operación</option>
	        	<option value="Estética">Estetica</option>
	        </select>
	        
	        <label for="Otro_Precio" class="label-input-20 hide">Precio</label>
	        <input id="Otro_Precio" type="text" class="input-20 hide" placeholder="Precio">  
        
        	<button id="Otro_Agregar" type="button" class="agregar hide">Agregar Servicio</button>
        
        </div>
        
         <div id="tabla">
	         <h1>Carrito de Compra</h1>
	            <table>
	                <thead>
	                    <tr>
	                        <th>Código del Producto</th>
	                        <th>Nombre</th>
	                        <th>Cantidad</th>
	                        <th>Precio Unitario</th>
	                        <th>Precio Total</th>
	                        <th></th>
	                    </tr>
	                </thead>
	                <tbody>
	                   
	                </tbody>
	            </table>
           	<br>
           	<div id="div-cambio">
           		 <label for="Efectivo" class="label-input-20">Efectivo : </label>
	        	<input  id="Efectivo" type="text" class="input-20" placeholder="Efectivo">
	        	
	        	<label for="Cambio" class="label-input-20">Cambio : </label>
	        	<input  id="Cambio" type="text" class="input-20" placeholder="Efectivo" disabled="disabled">
           	</div>
           	<br>
	            
	        <div >
	            <button id="guardar" type="button"  class="guardar">Finalizar Venta</button>
	            <button id="cancelar" type="button" class="cancelar">Cancelar</button>
	        </div>
        </div>
        
    </form>
    

    <script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    <script type="text/javascript" src="../scripts/fecha-tiempo.js"></script>
    <script type="text/javascript" src="../scripts/Ventas.js"></script>
    
</body>
</html>
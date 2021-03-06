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
    <title>Mascotas</title>

    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../icons/style.css">
    <link rel="stylesheet" href="../CSS/mascotas.css">
    <%if(Tipo==3){ %>
    
    	<style type="text/css">
    		td button{
    			display: none;
    		}
    	</style>
    	
    <%} %>
    
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
        <h1>Mascotas Registradas</h1>
            <form id="busqueda">
              <label>Buscar</label>
              <select id="opciones" name="opciones">
                  <option value="1">Clave</option>
                  <option value="2">Nombre</option>
                  <option value="3">Edad</option>
                  <option value="4">Tipo</option>
                  <option value="5">Raza</option>
                  <option value="6">Descripcion</option>
                  <option value="7">Dueño</option>
              </select>
            
               <input id="busqueda-input" type="search" placeholder="Busqueda">
               <button class="but-descargar" id="crearPDF">Guardar PDF</button>
             
            </form>
            <table id="tables">
                <thead>
                    <tr>
                        <th>Clave de la Mascota</th>
                        <th>Dueño</th>
                        <th>Nombre de la Mascota</th>
                        <th>Edad</th>
                        <th>Tipo de Mascota</th>
                        <th>Raza de la Mascota</th>
                        <th>Descripción</th>
                    </tr>
                </thead>
                <tbody>
                   
                </tbody>
            </table>
        
    </div>
    <br>
    <%if(Tipo !=3){ %>
    <form id="formulario-id" action="" class="formulario">
        <h1 id="title_form">Registrar Nueva Mascota</h1><br><br>
            
            
            <input  id="Clv_Clie" list="list_prod" class="formulario__input ">
	        <datalist id="list_prod">
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
	        </datalist>
	        <input id="clv" type="hidden" value="">
	        <label for="Clv_Clie" class="formulario__label estatic">Clave del Cliente</label>
            
            <input id="Nom_Mas" name="Nom_Mas" type="text" class="formulario__input" required="required" maxlength="45"> 
            <label for="Nom_Mas" class="formulario__label">Nombre de la Mascota</label>
            
            <input id="Edad_Mas" name="Edad_Mas" type="date" class="formulario__input" required="required"> 
            <label for="Edad_Mas" class="formulario__label estatic">Nacimiento</label>
            
            <input id="Tipo_Mas" name="Tipo_Mas" type="text" class="formulario__input" required="required" maxlength="20"> 
            <label for="Tipo_Mas" class="formulario__label">Tipo de Mascota</label>
            
            <input id="Raz_Mas" name="Raz_Mas" type="text" class="formulario__input" required="required" maxlength="45"> 
            <label for="Raz_Mas" class="formulario__label">Raza de la Mascota</label>
            
            <input id="Des_Mas" name="Des_Mas" type="text" class="formulario__input" required="required" maxlength="50"> 
            <label for="Des_Mas" class="formulario__label">Descripción</label>
        
        <div >
        	<button id="actualizar" type="button" class="actualizar">Actualizar</button>
            <button id="guardar" type="button" class="guardar">Guardar</button>
            <button id="cancelar" type="reset" class="cancelar">Cancelar</button>
        </div>
    </form>
    <%} %>

	<script type="text/javascript" src="../scripts/jspdf.min.js"></script>
	<script type="text/javascript" src="../scripts/jspdf.plugin.autotable.min.js"></script>

    <script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="../scripts/Mascotas.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    <%if(Tipo==3){ %>
    
    	<script type="text/javascript">
    	 	$(document).ready(function(){
    	 		
    	 		$("tr").hover(function(){
        	 		$(".editar_pro").remove();
        	 		$(".eliminar_pro").remove();
    	 		});
    	 		
    	 	});
    	</script>
    	
    <%} %>
</body>
</html>
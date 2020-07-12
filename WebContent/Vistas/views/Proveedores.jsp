<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Proveedor"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.ProveedorDAO"%>
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
    <title>Proveedores</title>

    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../icons/style.css">
    <link rel="stylesheet" href="../CSS/Proveedores.css">
   	<%if(Tipo!=1){ %>
    
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
   	<button id="create_pdf" type="button" class="create_pdf">Descargar PDF</button>
    <br>
    
    <div class="contenido"  >
        <h1>Proveedores</h1>
           
            <table>
                <thead>
                    <tr>
                        <th>Clave del Proveedor</th>
                        <th>Nombre</th>
                        <th>Telefono</th>
                        <th>Correo Electronico</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<%
                		ProveedorDAO prove = new ProveedorDAO();
                		List<Proveedor> list = prove.listar();
                		Iterator<Proveedor> iter = list.iterator();
                		Proveedor pro=null;
                		while(iter.hasNext()){
                			pro=iter.next();
                	%>
                   <tr>
                        <td><%=pro.getIDProveedor() %></td>
                        <td><%=pro.getNombre() %></td>
                        <td><%=pro.getTelefono() %></td>
                        <td><%=pro.getCorreo() %></td>
                        <td>
                        	<button class="editar_pro" value="<%=pro.getIDProveedor()%>"> <img width="25px"  alt="icono-editar" src="../img/editar-icono.svg"></button>
                        	<button class="eliminar_pro" value="<%=pro.getIDProveedor()%>"> <img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></button>
                       </td>
                    </tr>
                    <%} %>
                   
                </tbody>
            </table>
        
    </div>
    <br>
    <%if(Tipo==1){ %>
    <form action="" class="formulario" >
        <h5 id="title_form">AGREGAR PROVEEDOR</h5>
      <input id="Clv_Pro" name="Clv_Pro" type="text" class="formulario__input" required="required">  
        <label for="Clv_Pro" class="formulario__label">Clave de Proveedor</label>
        
        <input id="Nombre_Pro" name="Nombre_Pro" type="text" class="formulario__input" required="required">  
        <label for="Nombre_Pro" class="formulario__label">Nombre del Proveedor</label>
        
        <input id="Tel_Pro" name="Tel_Pro" type="number" class="formulario__input">  
        <label for="Tel_Pro" class="formulario__label">Telefono</label>
        
        <input id="Email_Pro" name="Email_Pro" type="email" class="formulario__input">   
        <label for="Email_Pro" class="formulario__label">Correo</label>
        
        <div >
        	<button id="actualizar" type="button" class="actualizar">Actualizar</button>
            <button id="guardar" type="button" class="guardar">Guardar</button>
            <button id="cancelar" type="reset" onclick="limpiar_campos()" class="cancelar">Cancelar</button>
        </div>
    </form>
	<%} %>
	
    <script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    <script type="text/javascript" src="../scripts/Proveedores.js"></script>
     
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>  
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>  
    
     <%if(Tipo!=1){ %>
    
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
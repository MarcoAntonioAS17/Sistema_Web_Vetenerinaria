<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Proveedor"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.ProveedorDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
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
	
	
	<jsp:include page="Includes/Menu_Principal.jsp"></jsp:include>
    
    <br><br><br><br>
    
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
    <form action="" class="formulario">
        <h5>AGREGAR PROVEEDOR</h5>
      <input id="Clv_Pro" name="Clv_Pro" type="text" class="formulario__input" required="required">  
        <label for="Clv_Pro" class="formulario__label">Clave de Proveedor</label>
        
        <input id="Nombre_Pro" name="Nombre_Pro" type="text" class="formulario__input" required="required">  
        <label for="Nombre_Pro" class="formulario__label">Nombre del Proveedor</label>
        
        <input id="Tel_Pro" name="Tel_Pro" type="number" class="formulario__input">  
        <label for="Tel_Pro" class="formulario__label">Telefono</label>
        
        <input id="Email_Pro" name="Email_Pro" type="email" class="formulario__input">   
        <label for="Email_Pro" class="formulario__label">Correo</label>
        
        <div >
            <button id="guardar" type="button" class="guardar">Guardar</button>
            <button id="cancelar" type="reset" class="cancelar">Cancelar</button>
        </div>
    </form>

	

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    
     <script type="text/javascript">
    
    	//Guardar datos en BD
	   $(document).ready(function(){
		   
	       $('#guardar').click(function(e){
	    	   var idProveedor = $("#Clv_Pro").val();
	    	   var Nombre_Pro = $("#Nombre_Pro").val();
	    	   var Tel_Pro = $("#Tel_Pro").val();
	    	   var Email_Pro = $("#Email_Pro").val();
	    	   
	    	   if(idProveedor == "" || Nombre_Pro ==""){
	    		   if(idProveedor == ""){
						$("#Clv_Pro").css({"border-color":"red","color":"red"});
					}
					if(Nombre_Pro==""){
						$("#Nombre_Pro").css({"border-color":"red","color":"red"});
					}
					alert("Faltan campos por acompletar");
	    		   return;
	    	   }
	          $.post("../../Proveedores",{
	        	  accion : "agregar",
	        	  Proveedor : idProveedor,
	        	  Nombre_Pro : Nombre_Pro,
	        	  Telefono : Tel_Pro,
	        	  Email: Email_Pro
	          },function(responseText){
	        	  
	        	  if(responseText == "true"){
	        		  alert("Proveedor agregado con exito");
	        	  }else{
	        		  alert("Error al agregar");
	        	  }
	        	  location.reload();
	        	  limpiar_campos();
	          });
	       });
	       
	       $('.eliminar_pro').click(function(e){
	    	   var codvar = $(this).val();
	    	   
	    	   $.post("../../Proveedores",{
	    		   accion : "eliminar",
	    		   IDProveedor : codvar
	    	   },function(responseText){
	    		   if(responseText == "true"){
		        		  alert("Proveedor elimanado con exito");
		        	  }else{
		        		  alert("Error al elimar");
		        	  }
		        	  location.reload();
	    	   });
	       });
	       
    	});
    	
    	function limpiar_campos(){
    		$("#Clv_Pro").val("");
    	   	$("#Nombre_Pro").val("");
    	   	$("#Tel_Pro").val("");
    	   	$("#Email_Pro").val("");
    	}
    	
    </script>
    
</body>
</html>
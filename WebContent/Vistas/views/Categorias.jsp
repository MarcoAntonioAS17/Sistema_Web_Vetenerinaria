<%@ page import="java.util.List" %>
<%@ page import="Modelo.Categoria" %>
<%@page import="ModeloDAO.CategoriaDAO"%>
<%@ page import="java.util.Iterator" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="imagen/png" href="../img/Logo.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Categorias</title>

    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../icons/style.css">
    <link rel="stylesheet" href="../CSS/Categorias.css">
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
        <h1>Categorias</h1>
           
            <table>
                <thead>
                    <tr>
                        <th>Codigo Categoria</th>
                        <th>Categoria</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<%
                		CategoriaDAO dao = new CategoriaDAO();
                		List<Categoria> list = dao.listar();
                		Iterator<Categoria> iter = list.iterator();
                		Categoria cat=null;
                		while(iter.hasNext()){
                			cat=iter.next();
                	%>
                   <tr>
                        <td><%=cat.getIDCategoria() %></td>
                        <td><%=cat.getNombre()%></td>
                        <td>
                        	<button class="button_cat" value="<%=cat.getIDCategoria()%>"> <img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></button>
                       </td>
                    </tr>
                    <%} %>
                  
                </tbody>
            </table>
        
    </div>
    <br>
    <form class="formulario" method="post" >

        <h5>Agregar Categoria</h5>
        
        <input id="Nombre_cat" name="Nombre_cat" type="text" class="formulario__input" required="required">  
        <label for="Nombre_cat" class="formulario__label">Nombre de la Categoria</label>
        
        <div >
            <button id="guardar" type="button" name="accion" value="Agregar" class="guardar">Agregar</button>
            <button id="cancelar" type="reset" class="cancelar">Cancelar</button>
        </div>
    </form>
    

   <script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    
    <script type="text/javascript">
    
    	//Guardar datos en BD
	   $(document).ready(function(){
		   
	       $('#guardar').click(function(e){
	    	   var categoriavar = $("#Nombre_cat").val();
	    	   $("#Nombre_cat").css({"border-color":"white","color":"white"});
	    	   if(categoriavar == ""){
	    		   $("#Nombre_cat").css({"border-color":"red","color":"red"});
	    		   return;
	    	   }
	          $.post("../../Categorias",{
	        	  accion : "agregar",
	        	  Nombre_cat : categoriavar
	          },function(responseText){
	        	  
	        	  if(responseText == "true"){
	        		  alert("Categoria agregado con exito");
	        	  }else{
	        		  alert("Error al agregar");
	        	  }
	        	  limpiar_campos();
	        	  location.reload();
	          });
	       });
	       
	       $('.button_cat').click(function(e){
	    	   var codvar = $(this).val();
	    	   var confirma = confirm("¿Estás seguro que deseas eliminar el registro?");
	    	   
	    	   if(confirma==true) {
		    	   $.post("../../Categorias",{
		    		   accion : "eliminar",
		    		   IDCategoria : codvar
		    	   },function(responseText){
		    		   if(responseText == "true"){
			        		  alert("Categoria elimanada con exito");
			        	  }else{
			        		  alert("Error al elimar");
			        	  }
			        	  location.reload();
		    	   });
	    	   }
	       });
    	});
    	
	   function limpiar_campos(){
		   $("#Nombre_cat").val("");
	   	}
    	
    	
	   
    </script>
</body>
</html>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<%@ page import="Modelo.Categoria" %>
<%@page import="ModeloDAO.CategoriaDAO"%>

<%@page import="Modelo.Proveedor"%>
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
    
    <title>Inventario</title>
    <link rel="stylesheet" href="../CSS/inventario.css">
     <%if(Tipo!=1){ %>
    
    	<style type="text/css">
    		td button{
    			display: none;
    		}
    	</style>
    	
    <%} %>
    
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
   
    <div id="contenido1" class="contenido"  >
        <h1>Inventario</h1>
            <form id="busqueda">
              <label>Buscar</label>
              <select id="opciones" name="opciones">
                   <option value="1">Codigo</option>
                  <option value="2">Nombre</option>
                  <option value="9">Cantidad</option>
                  <option value="3">Precio Venta</option>
                  <option value="4">Precio Compra</option>
                  <option value="7">Categoria</option>
                  <option value="8">Proveedor</option>
                  <option value="5">Caducidad</option>
                  <option value="6">Descripcion</option>
              </select>
            
               <input id="busqueda_input" type="search" placeholder="Busqueda">
               
               <button class="but-descargar" id="crearPDF">Guardar PDF</button> 
            </form>
            
            <table id="my-table">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                        <th>Precio Ven.</th>
                        <th>Precio Com.</th>
                        <th>Categoría</th>
                        <th>Proveedor</th>
                        <th>Caducidad</th>
                        <th>Descripción</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	
                     
                </tbody>
            </table>
        
    </div>
    <% if(Tipo==1){%>
    <form action="" class="formulario">
        <h5 id="title_form">Agregar Producto</h5>
      <input id="Clv_Pro" name="Clv_Pro" type="number" class="formulario__input" required="required" maxlength="15">  
        <label for="Clv_Pro" class="formulario__label">Código del producto</label>
        
        <input id="Nombre_Pro" name="Nombre_Pro" type="text" class="formulario__input" required="required" maxlength="45">  
        <label for="Nombre_Pro" class="formulario__label">Nombre del producto</label>
        
        <label class="formulario__label_fija">Categoria</label>
        <select id="Categorias" name="opciones" class="formulario__input">
        	<%
           		CategoriaDAO daocat = new CategoriaDAO();
           		List<Categoria> listcat = daocat.listar();
           		Iterator<Categoria> itercat = listcat.iterator();
           		Categoria cat=null;
           		while(itercat.hasNext()){
           			cat=itercat.next();
           	%>
               <option value="<%=cat.getIDCategoria() %>"><%=cat.getNombre()%></option>
               
           <%} %>
        </select>
        <input id="Cantidad" name="Cantidad" type="number" class="formulario__input" required="required">   
        <label for="Cantidad" class="formulario__label">Cantidad</label>
        
        <input id="Precio_V" name="Precio_V" type="number" class="formulario__input" required="required">   
        <label for="Precio_V" class="formulario__label">Precio de Venta</label>
		
		<input id="Precio_C" name="Precio_C" type="number" class="formulario__input" required="required">   
        <label for="Precio_C" class="formulario__label">Precio de Compra</label>
        
        <label for="Fecha_C" class="formulario__label_fija">Fecha de Caducidad</label>
        <input id="Fecha_C" name="Fecha_C" type="date" class="formulario__input">   
        
        <label class="formulario__label_fija">Proveedor</label>
        <select id="Proveedores" name="opciones" class="formulario__input">
        	<%
	        	ProveedorDAO provedao = new ProveedorDAO();
	    		List<Proveedor> listprove = provedao.listar();
	    		Iterator<Proveedor> iterprove = listprove.iterator();
	    		Proveedor prove=null;
	    		while(iterprove.hasNext()){
    			prove=iterprove.next();
           	%>
               <option value="<%=prove.getIDProveedor() %>"><%=prove.getNombre()%></option>
               
           <%} %>
        </select>
        
        <input id="Descrip" name="Descrip" type="text" class="formulario__input" maxlength="60">   
        <label for="Descrip" class="formulario__label">Descripción</label>
                
        <div >
            <button id="actualizar" type="button" class="actualizar">Actualizar</button>
            <button id="guardar" type="button" class="guardar">Guardar</button>
            <button id="cancelar" type="reset" onclick="limpiar_campos()" class="cancelar">Cancelar</button>
        </div>
    </form>
	<%} %>
	
	
    <script type="text/javascript" src="../scripts/jspdf.min.js"></script>
	<script type="text/javascript" src="../scripts/jspdf.plugin.autotable.min.js"></script>
	
   	<script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    <script type="text/javascript" src="../scripts/Productos.js"></script>
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
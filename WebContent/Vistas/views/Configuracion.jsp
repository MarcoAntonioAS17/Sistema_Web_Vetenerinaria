<%@page import="Modelo.Administrador"%>
<%@page import="ModeloDAO.AdministradorDAO"%>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.Categoria" %>
<%@page import="ModeloDAO.CategoriaDAO"%>
<%@ page import="java.util.Iterator" %>

<%@page import="ModeloDAO.ConfiguracionDAO"%>
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
    <title>Configuracion</title>

    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../icons/style.css">
    <link rel="stylesheet" href="../CSS/Configuracion.css">
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
        <h1>Configuración</h1>
    	
    	<p>Hola <%=usuario %></p>
    	<%
    	switch(Tipo){
    		 case 1:%>
    			<p>Tipo de Usuario: Administrador</p>		 
    			 <%break;
    		 case 2:%>
    		 	<p>Tipo de Usuario: Empleado</p>
    			 <%break;
    		 case 3:%>
    		 	<p>Tipo de Usuario: Otro</p>
    			 <%break;
    	}
    	%>
        
        <%
        ConfiguracionDAO dao = new ConfiguracionDAO();
        %>
        <p>Aviso de productos próximos a caducar</p>
        <label for="dias-caducar">Días próximos</label>    
        <input id="dias-caducar" type="number" placeholder="Días" value="<%=dao.Dias_Caducidad()%>">
        
        <p>Aviso de productos próximos a agotarse</p>
        <label for="cantidad-a">Cantidad de aviso</label>    
        <input id="cantidad-a" type="number" placeholder="Cantidad" value="<%=dao.Cantidad_Aviso()%>">
        <br>
        <button id="guardar_cant" class="agregar_u" type="button" >Guardar</button>
        
        
        
        <br><br>
        <h1>Tipos de Usuarios</h1>
        <div id="tipos">
        	<div>
        		
        		<h2>Administrador</h2>
        		Crear Nuevos Usarios
        		Visualizar:
        		<ul>
        			<li>Ventas</li>
        			<li>Compras</li>
        			<li>Clientes</li>
        			<li>Mascotas</li>
        			<li>Proveedores</li>
        			<li>Inventario</li>
        			<li>Citas</li>
        		</ul>
        		Modificar:
        		<ul>
        			<li>Ventas</li>
        			<li>Compras</li>
        			<li>Clientes</li>
        			<li>Mascotas</li>
        			<li>Inventario</li>
        			<li>Citas</li>
        		</ul>
        		
        	</div>
        	<div>
        		<h2>Empleado</h2>
        		Realizar:
        		<ul>
        			<li>Ventas</li>
        			<li>Citas</li>
        		</ul>
        		Visualizar:
        		<ul>
        			<li>Clientes</li>
        			<li>Mascotas</li>
        			<li>Proveedores</li>
        			<li>Inventario</li>
        			<li>Citas</li>
        		</ul>
        		Modificar:
        		<ul>
        			<li>Citas</li>
        		</ul>
        	</div>
        	<div>
        		<h2>Otro</h2>
        		Visualizar:
        		<ul>
        			<li>Clientes</li>
        			<li>Mascotas</li>
        			<li>Proveedores</li>
        			<li>Inventario</li>
        			<li>Citas</li>
        		</ul>
        		No se permite Modificar
        	</div>
        	
        </div>
        <button id="new_user" class="agregar_u" type="button">Nuevo Usuario</button>
    </div>
    <%if(Tipo==1) {%>>
    <br><br><br>
    <div class="contenido"  >
        <h1>Usuarios Registrados</h1>
           
            <table>
                <thead>
                    <tr>
                        <th>Nombre de Usuario</th>
                        <th>Tipo de Usuario</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<%
                		AdministradorDAO dao2 = new AdministradorDAO();
                		List<Administrador> list = dao2.listar();
                		Iterator<Administrador> iter = list.iterator();
                		Administrador user=null;
                		while(iter.hasNext()){
                			user=iter.next();
                	%>
                   <tr>
                        <td><%=user.getUserName() %></td>
                        <%
				    	switch(user.getTipo()){
				    		 case 1:%>
				    			<td>Administrador</td>		 
				    			 <%break;
				    		 case 2:%>
				    		 	<td>Empleado</td>
				    			 <%break;
				    		 case 3:%>
				    		 	<td>Otro</td>
				    			 <%break;
				    	}
				    	%>
                        
                        <td>
                        	<button class="button_cat" type="button" onclick="eliminar('<%=user.getUserName()%>')"> <img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></button>
                       </td>
                    </tr>
                    <%} %>
                  
                </tbody>
            </table>
        
    </div>
    <%} %>
    <br>
    <form class="formulario" method="post" >

        <h1>Modificar Contraseña</h1>
        
        <input id="ContraAc" type="password" class="formulario__input" required="required">  
        <label for="ContraAc" class="formulario__label">Contraseña actual</label>
        
        <input id="ContraN" type="password" class="formulario__input" required="required">  
        <label for="ContraN" class="formulario__label">Nueva Contraseña</label>
        
        <input id="ContraN2" type="password" class="formulario__input" required="required">  
        <label for="ContraN2" class="formulario__label">Repite la Contraseña</label>
        
		<div >
            <button onclick="modificar('<%=usuario%>')" id="mod_pass" type="button" class="guardar">Actualizar</button>
        </div>
    </form>
    
    <br>
    <form id="formulario-new" class="formulario" method="post" >

        <h1>Nuevo Usuario</h1>
        
        <input id="Nombre" type="text" class="formulario__input" required="required">  
        <label for="Nombre" class="formulario__label">Nombre de Usuario</label>
        
        <input id="Contra" type="password" class="formulario__input" required="required">  
        <label for="Contra" class="formulario__label">Contraseña</label>
        
        <input id="Contra2" type="password" class="formulario__input" required="required">  
        <label for="Contra2" class="formulario__label">Repite la Contraseña</label>
        
        <select id="Tipo" class="formulario__input">
        	<option value="1">Administrador</option>
        	<option value="2">Empleado</option>
        	<option value="3">Otro</option>
        </select>
        <label for="Tipo" class="formulario__label estatic ">Tipo de usuario</label>
       
		<h2>Permiso de Administrador</h2>       
        <input id="NombreA" type="text" class="formulario__input" required="required">  
        <label for="NombreA" class="formulario__label">Nombre de Usuario (Admin)</label>
        
        <input id="ContraA" type="password" class="formulario__input" required="required">  
        <label for="ContraA" class="formulario__label">Contraseña (Admin)</label>
        <div >
            <button id="guardar" type="button" name="accion" value="Agregar" class="guardar">Agregar</button>
            <button id="cancelar" type="reset" class="cancelar">Cancelar</button>
        </div>
    </form>
    

   <script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    <script type="text/javascript" src="../scripts/Config.js"></script>
</body>
</html>
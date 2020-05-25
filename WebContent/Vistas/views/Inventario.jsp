<%@page import="ModeloDAO.ProductoDAO"%>
<%@page import="Modelo.Producto"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<%@ page import="Modelo.Categoria" %>
<%@page import="ModeloDAO.CategoriaDAO"%>

<%@page import="Modelo.Proveedor"%>
<%@page import="ModeloDAO.ProveedorDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="imagen/png" href="../img/Logo.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    
    <title>Inventario</title>
    <link rel="stylesheet" href="../CSS/inventario.css">
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
   
	<jsp:include page="Includes/Menu_Principal.jsp"></jsp:include>
	
   
    <div id="contenido1" class="contenido"  >
        <h1>Inventario</h1>
            <form id="busqueda">
              <label>Buscar</label>
              <select id="opciones" name="opciones">
                   <option value="Codigo">Codigo</option>
                  <option value="Nombre">Nombre</option>
                  <option value="Precio Venta">Precio Venta</option>
                  <option value="Precio Compra">Precio Compra</option>
                  <option value="Categoria">Categoria</option>
                  <option value="Proveedor">Proveedor</option>
                  <option value="Caducidad">Caducidad</option>
                  <option value="Descripcion">Descripcion</option>
              </select>
            
               <input id="busqueda" type="search" placeholder="Busqueda"> 
            </form>
            <table>
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
                	<%
                		ProductoDAO dao = new ProductoDAO();
                		List<Producto> list = dao.listar();
                		Iterator<Producto> iter = list.iterator();
                		Producto pro=null;
                		while(iter.hasNext()){
                			pro=iter.next();
                	%>
                   <tr>
                        <td><%=pro.getIDProducto() %></td>
                        <td><%=pro.getNombre() %></td>
                        <td><%=pro.getCantidad() %></td>
                        <td> $<%=pro.getPrecio_V() %></td>
                        <td> $<%=pro.getPrecio_C() %></td>
                        <td><%=pro.getS_Categoria() %></td>
                        <td><%=pro.getS_Proveedor() %></td>
                        <td><%=pro.getCaducidad() %></td>
                        <td><%=pro.getDescripcion() %></td>
                        <td>
                        	<button class="editar_pro" value="<%=pro.getIDProducto() %>"> <img width="25px"  alt="icono-editar" src="../img/editar-icono.svg"></button>
                        	<button class="eliminar_pro" value="<%=pro.getIDProducto()%>"> <img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></button>
                       </td>
                    </tr>
                    <%} %>
                </tbody>
            </table>
        
    </div>
    
    <form action="" class="formulario">
        <h5 id="title_form">AGREGAR PRODUCTO</h5>
      <input id="Clv_Pro" name="Clv_Pro" type="number" class="formulario__input" required="required">  
        <label for="Clv_Pro" class="formulario__label">Código del producto</label>
        
        <input id="Nombre_Pro" name="Nombre_Pro" type="text" class="formulario__input" required="required">  
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
        
        <input id="Descrip" name="Descrip" type="text" class="formulario__input">   
        <label for="Descrip" class="formulario__label">Descripción</label>
                
        <div >
            <button id="actualizar" type="button" class="actualizar">Actualizar</button>
            <button id="guardar" type="button" class="guardar">Guardar</button>
            <button id="cancelar" type="reset" onclick="limpiar_campos()" class="cancelar">Cancelar</button>
        </div>
    </form>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    <script type="text/javascript" src="../scripts/Productos.js"></script>
    
    
</body>
</html>
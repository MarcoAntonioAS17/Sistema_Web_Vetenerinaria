<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

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
    <title>Compras</title>

    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../icons/style.css">
    <link rel="stylesheet" href="../CSS/compras.css">
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

    <br><br><br><br>
    
    <form class="formulario">
        <h1>Nueva Compra</h1>
        <div id="date-time">
	        <label for="Fec_C" class="label-input-40">Fecha</label>
	        <label for="Hra_C" class="label-input-40">Hora</label>
	        
	        <input id="Fec_C" name="Fec_C" type="date" class="input-40">  
	        <input id="Hra_C" name="Hra_C" type="time" class="input-40">  
        </div>
        
        <label class="formulario__label_fija">Proveedor</label>
        <select id="Clv_Pro" name="opciones" class="formulario__input">
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
        
        <div id="cont-producto">
	        <label for="Clv_Prod" class="label-input-20">Código del producto</label>
	        
	        <input  id="Clv_Prod" list="list_prod" class="input-20" placeholder="Código">
	        <datalist id="list_prod">
	        	
	        </datalist>
	        
	        <label for="Cantidad" class="label-input-20">Cantidad</label>
	        <input id="Cantidad" type="number" class="input-20" placeholder="Cantidad">  
        
        	<button id="agregar" type="button" class="agregar">Agregar Producto</button>
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
	        <div >
	            <button id="guardar" type="button"  class="guardar">Finalizar Compra</button>
	            <button id="cancelar" type="button" class="cancelar">Cancelar</button>
	        </div>
        </div>
    </form>
    

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    <script type="text/javascript" src="../scripts/fecha-tiempo.js"></script>
    <script type="text/javascript" src="../scripts/Compras.js"></script>
    
</body>
</html>
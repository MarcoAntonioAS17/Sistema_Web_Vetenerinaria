<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="../CSS/historial_ventas.css">
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
    
   <div id="contenido1" class="contenido"  >
        <h1>Historial de ventas</h1>
            <form id="busqueda">
              <label>Buscar</label>
              <select id="opciones" name="opciones">
                   <option value="Codigo">Codigo Venta</option>
                  <option value="Cliente">Cliente</option>
                  <option value="Fecha">Fecha</option>
                  <option value="Hora">Hora</option>
                  <option value="Producto">Producto</option>
                  <option value="Cantidad">Cantidad</option>
                  <option value="Precio">Precio</option>
              </select>
            
               <input id="busqueda" type="search" placeholder="Busqueda"> 
            </form>
            
         
            <table>
                <thead>
                    <tr>
                    	<th>Código de Venta</th>
                    	<th>Cliente</th>
                    	<th>Fecha</th>
                    	<th>Hora</th>
                        <th>Código del Producto</th>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                        <th>Precio Unitario</th>
                        <th>Precio Total</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                   <tr>
                   		<td>001</td>
                   		<td>Felipe Ramirez</td>
                   		<td>10/02/2020</td>
                   		<td>12:00</td>
                        <td>72931038</td>
                        <td>Croqueta Proplan Cordero</td>
                        <td>1</td>
                        <td>1200</td>
                        <td>1200</td>
                        <td>
                        	<a  href="#"><img width="25px" alt="ico-editar" src="../img/editar-icono.svg"></a>
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                    <tr>
                    	<td>001</td>
                   		<td>Felipe Ramirez</td>
                   		<td>10/02/2020</td>
                   		<td>12:00</td>
                        <td>72931038</td>
                        <td>Croqueta Proplan Cordero</td>
                        <td>1</td>
                        <td>1200</td>
                        <td>1200</td>
                        <td>
                        	<a  href="#"><img width="25px" alt="ico-editar" src="../img/editar-icono.svg"></a>
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                    <tr>
                    	<td>001</td>
                   		<td>Felipe Ramirez</td>
                   		<td>10/02/2020</td>
                   		<td>12:00</td>
                        <td>72931038</td>
                        <td>Croqueta Proplan Cordero</td>
                        <td>1</td>
                        <td>1200</td>
                        <td>1200</td>
                        <td>
                        	<a  href="#"><img width="25px" alt="ico-editar" src="../img/editar-icono.svg"></a>
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                    
                    <tr>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td>Total</td>
                    	<td>$3,600</td>
                    	<td></td>
                    </tr>
                </tbody>
            </table>
        
	</div>
    

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    
</body>
</html>
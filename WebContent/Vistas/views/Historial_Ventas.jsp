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
	
	<div class="sidebar">
        
        <div class="logo">
           <img src="../img/Logo-Extend.svg">
        </div>
            <ul class="menu select">
                <li class="title">MENU</li>
                
                <li class="active">
                    <a href="Inicio.jsp">
                    <i class="icon-home icon"></i>Inicio</a>
                </li>
                
                <li class="title">ADMINISTRAR VENTAS</li>
                <li>
                   <a href="">Ventas<i class="icon-chevron-small-right icon-right icon"></i>
                   </a>
                    <ul>
                        <li>
                            <a href="Historial_Ventas.jsp">
                                <i class="sub_menu"></i>
                                Ventas Realizadas
                            </a>
                            <a href="Ventas.jsp">
                                <i class="sub_menu"></i>
                                Nueva Venta
                            </a>
                        </li>
                    </ul>
                </li>
                
                <li>
                   <a href="">Citas<i class="icon-chevron-small-right icon-right icon"></i>
                   </a>
                    <ul>
                        <li>
                            <a href="#">
                                <i class="sub_menu"></i>
                                Ver Citas
                            </a>
                            <a href="#">
                                <i class="sub_menu"></i>
                                Agendar Cita
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                   <a href="">Clientes<i class="icon-chevron-small-right icon-right icon"></i>
                   </a>
                    <ul>
                        <li>
                            <a href="#">
                                <i class="sub_menu"></i>
                                Ver Clientes
                            </a>
                            <a href="#">
                                <i class="sub_menu"></i>
                                Mascotas
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="title">ADMINISTRACI�N DE INVENTARIO</li>
                <li>
                   <a href="">Compras<i class="icon-chevron-small-right icon-right icon"></i>
                   </a>
                    <ul>
                        <li>
                            <a href="Compras.jsp">
                                <i class="sub_menu"></i>
                                Ver Compras
                            </a>
                            <a href="Proveedores.jsp">
                                <i class="sub_menu"></i>
                                Proveedores
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                   <a href="">Inventario<i class="icon-chevron-small-right icon-right icon"></i>
                   </a>
                    <ul>
                        <li>
                            <a href="Inventario.jsp">
                                <i class="sub_menu"></i>
                                Consultar Inventario
                            </a>
                            <a href="#">
                                <i class="sub_menu"></i>
                                Categorias
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="title">SISTEMA</li>
                <li ><a href="">Acceso al sistema</a></li>
            </ul>
    </div>

    <br><br><br><br>
    
   <div id="contenido1" class="contenido"  >
        <h1>Historial de ventas</h1>
            <form id="busqueda">
              <label>Buscar</label>
              <select id="opciones" name="opciones">
                   <option value="Codigo">Codigo Compra</option>
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
                    	<th>Código de Compra</th>
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
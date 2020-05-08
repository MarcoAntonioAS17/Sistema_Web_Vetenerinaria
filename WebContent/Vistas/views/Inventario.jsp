<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
                <li class="title">ADMINISTRACIÓN DE INVENTARIO</li>
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
                   <tr>
                        <td>7283018392</td>
                        <td>Proplan Adulto</td>
                        <td>$1200</td>
                        <td>$1000</td>
                        <td>Alimento Premium</td>
                        <td>Purina</td>
                        <td>2/10/2021</td>
                        <td>Alimento para perros, bolsa de 12 kg.</td>
                        <td>
                        	<a  href="#"><img width="25px"  alt="icono-editar" src="../img/editar-icono.svg"></a>	
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                    <tr>
                        <td>7283018392</td>
                        <td>Proplan Adulto</td>
                        <td>$1200</td>
                        <td>$1000</td>
                        <td>Alimento Premium</td>
                        <td>Purina</td>
                        <td>2/10/2021</td>
                        <td>Alimento para perros, bolsa de 12 kg.</td>
                        <td>
                        	<a  href="#"><img width="25px"  alt="icono-editar" src="../img/editar-icono.svg"></a>	
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                    <tr>
                        <td>7283018392</td>
                        <td>Proplan Adulto</td>
                        <td>$1200</td>
                        <td>$1000</td>
                        <td>Alimento Premium</td>
                        <td>Purina</td>
                        <td>2/10/2021</td>
                        <td>Alimento para perros, bolsa de 12 kg.</td>
                        <td>
                        	<a  href="#"><img width="25px"  alt="icono-editar" src="../img/editar-icono.svg"></a>	
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                </tbody>
            </table>
        
    </div>
    
    <form action="" class="formulario">
        <h5>AGREGAR PRODUCTO</h5>
      <input id="Clv_Pro" name="Clv_Pro" type="number" class="formulario__input" required="required">  
        <label for="Clv_Pro" class="formulario__label">Código del producto</label>
        
        <input id="Nombre_Pro" name="Nombre_Pro" type="text" class="formulario__input" required="required">  
        <label for="Nombre_Pro" class="formulario__label">Nombre del producto</label>
        
        <input id="Categoria" name="Categoria" type="text" class="formulario__input">  
        <label for="Categoria" class="formulario__label">Categoria</label>
        
        <input id="Precio_V" name="Precio_V" type="number" class="formulario__input" required="required">   
        <label for="Precio_V" class="formulario__label">Previo de Venta</label>
		
		<input id="Precio_C" name="Precio_C" type="number" class="formulario__input" required="required">   
        <label for="Precio_C" class="formulario__label">Previo de Compra</label>
        
        <input id="Fecha_C" name="Fecha_C" type="date" class="formulario__input">   
        <label for="Fecha_C" class="formulario__label">Fecha de Caducidad</label>
        
        <input id="Descrip" name="Descrip" type="text" class="formulario__input">   
        <label for="Descrip" class="formulario__label">Descripción</label>
        
        <input id="Proveedor" name="Proveedor" type="text" class="formulario__input">   
        <label for="Proveedor" class="formulario__label">Proveedor</label>
                
        <div >
            <button id="guardar" type="submit" class="guardar">Guardar</button>
            <button id="cancelar" type="reset" class="cancelar">Cancelar</button>
        </div>
    </form>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    
</body>
</html>
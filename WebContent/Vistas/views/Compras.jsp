<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
                            <a href="#">
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

    <br><br><br><br>
    
    <form action="" class="formulario">
        <h5>REGISTRAR COMPRAS</h5>
      <input id="Clv_Com" name="Clv_Com" type="text" class="formulario__input" required="required">  
        <label for="Clv_Com" class="formulario__label">Clave de la Compra</label>
        
        <input id="Clv_Pro" name="Clv_Pro" type="text" class="formulario__input" required="required">  
        <label for="Clv_Pro" class="formulario__label">Clave del Proveedor</label>
        
        <input id="Fec_C" name="Fec_C" type="number" class="formulario__input">  
        <label for="Fec_C" class="formulario__label">Fecha</label>
        
        <input id="Hra_C" name="Hra_C" type="number" class="formulario__input">  
        <label for="Hra_C" class="formulario__label">Hora</label>
        
        <div >
            <button id="guardar" type="submit" class="guardar">Guardar</button>
            <button id="cancelar" type="reset" class="cancelar">Cancelar</button>
        </div>
    </form>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    
</body>
</html>
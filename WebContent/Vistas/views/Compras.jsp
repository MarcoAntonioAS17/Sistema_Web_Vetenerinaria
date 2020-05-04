<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="imagen/png" href="../img/Logo.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Compras</title>

    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../CSS/style.css">
    <link rel="stylesheet" href="../icons/style.css">
    <link rel="stylesheet" href="../CSS/compras.css">
    <link rel="stylesheet" href="../CSS/form.css">
</head>
    
<body>
    <input type="checkbox" id="abrir-cerrar" name="abrir-cerrar" value="">
    <label id="hamburger" for="abrir-cerrar">
        <div class="button">
            <div class="menu_button"></div>
            <div class="menu_button"></div>
            <div class="menu_button"></div>
        </div>
    </label>
 
    <div id="sidebar" class="sidebar">
        <div class="logo">
           <img src="../img/Logo-Extend.svg">
        </div>
        <ul class="menu select">
                <li class="title">INICIO</li>
                <li class="active"><a href="Inicio.jsp"><i class="icon-home icon"></i>Inicio</a></li>
                <li class="title">INFORMACIÓN</li>
                <li><a href="#">Acerca de... <i class="icon-chevron-small-right icon-right icon"></i></a>
                    <ul>
                        <li>
                            <a href="#">
                                <i class="sub_menu"></i>
                                Pacientes
                            </a>
                            <a href="#">
                                <i class="sub_menu"></i>
                                Proveedores
                            </a>
                        </li>
                    </ul>
                </li>
                <li ><a href="#">Citas </a></li>
                <li><a href="#">Productos<i class="icon-chevron-small-right icon-right icon"></i></a>
                    <ul>
                        <li>
                            <a href="#">
                                <i class="sub_menu"></i>
                                Alimentos
                            </a>
                            <a href="#">
                                <i class="sub_menu"></i>
                                Artículos
                            </a>
                        </li>
                    </ul>
                </li>
                <li ><a href="Compras.jsp">Compras</a></li>
                <li ><a href="ventas.jsp">Ventas</a></li>
            </ul>
    </div>
    
    <div class="container-fluid">
        <br><br><br><br>
        <div class="card">
            <h5>AGREGAR COMPRAS</h5>
            <form class="row">
                <div class="col-md-6">
                    <label for="clave" class="info_label">Clave de la Compra</label>
                    <input name="clave" id="claveproduc" placeholder="Ingresar..." type="text">
                </div>
                <div class="col-md-6">
                    <label for="examplePassword11" class="info_label">Clave del Proveedor</label>
                    <input name="provee" id="claveprovee" placeholder="Ingresar..." type="text">
                </div>
                <div class="col-12">
                    <label for="exampleAddress" class="info_label">Fecha</label>
                    <input name="date" id="fecha" placeholder="Ingresar..." type="text">
                </div>
                <div class="col-12">
                    <label for="exampleAddress" class="info_label">Hora</label>
                    <input name="Hrs" id="Hora" placeholder="Ingresar..." type="text">
                </div>
                <div class="col-18">
                <button id="guardar" type="submit" class="guardar">Guardar</button>
                <button id="cancelar" type="reset" class="cancelar">Cancelar</button>
                </div>
            </form>
        </div>

    </div>  

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    
</body>
</html>
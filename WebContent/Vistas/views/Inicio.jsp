<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="imagen/png" href="../img/Logo.svg">
    
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    
    <title>Inicio</title>

    
    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../CSS/inicio.css">
    <link rel="stylesheet" href="../CSS/opciones.css">
    <link rel="stylesheet" href="../CSS/style.css">
    

    
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

    <div class="sidebar">
        
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
    <div id="contenido">
        <div id="contenedor-inicio" class="contenedor">
            <div id="opc1" class="opcion">
                <img class="icono" src="../img/Contactos.png" alt="icono-contacto">
                <h1>20</h1>
                <p>Clientes Totales</p>
            </div>
            <div id="opc2" class="opcion">
                <img class="icono" src="../img/producto-icono.png" alt="icono-contacto">
                <h1>20</h1>
                <p>Productos</p>
            </div>
            <div id="opc3" class="opcion">
                <img class="icono" src="../img/cita-icono.png" alt="icono-contacto">
                <h1>2</h1>
                <p>Citas</p>
            </div>
            <div id="opc4" class="opcion">
                <img class="icono" src="../img/caducar-icono.png" alt="icono-contacto">
                <h1>0</h1>
                <p>Productos a caducar</p>
            </div>
            <div id="opc5" class="opcion">
                <img class="icono" src="../img/agotarse-icono.png" alt="icono-contacto">
                <h1>20</h1>
                <p>Proximos a agotarse</p>
            </div>
            <div id="opc6" class="opcion">
                <img class="icono" src="../img/venta-icono.svg" alt="icono-contacto">
                <h1>20</h1>
                <p>Ventas de la semana</p>
            </div>
        </div>

        <div class="agenda-div">
            <h1>Agenda</h1>

            <div> 
                <p>Tipo de cita</p>
                <select name="tipo" >
                    <option value="Todas">Todas</option>
                    <option value="Estetica">Estetica</option>
                    <option value="Vacuna">Vacuna</option>
                    <option value="Consulta">Consulta</option>
                    <option value="Otros">Otros</option>
                </select>
            </div>

            <div>
                <input id="hoy" type="radio" name="opcion">
                <label for="hoy">Hoy</label>
                <input id="semana" type="radio" name="opcion">
                <label for="semana">Semana</label>
                <input id="mes" type="radio" name="opcion">
                <label for="mes">Mes</label>
            </div>

            <div id="agenda-contenido">
                <div>

                    <img src="../img/estetica-icono.png" alt="icono-estetica">
                    <div>
                        <p id="fecha">30/04/2020 10:00 hrs</p>
                        <p>Luis Hernandez</p>
                        <p>Tommy</p>
                    </div>
                </div>
                <div>

                    <img src="../img/estetica-icono.png" alt="icono-estetica">
                    <div>
                        <p id="fecha">30/04/2020 10:00 hrs</p>
                        <p>Luis Hernandez</p>
                        <p>Tommy</p>
                    </div>
                </div>
            </div>

        </div>
    </div>
    
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</body>
</html>
    
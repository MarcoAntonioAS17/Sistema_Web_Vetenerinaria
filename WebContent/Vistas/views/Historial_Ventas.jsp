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
                  <option value="1">Codigo Venta</option>
                  <option value="2">Cliente</option>
                  <option value="3">Fecha</option>
                  <option value="4">Hora</option>
              </select>
            
              <input id="busqueda_input" type="search" placeholder="Busqueda">
            </form>
            
         
            <table id="reporte">
                <thead>
                    <tr>
                    	<th>Código</th>
                    	<th>Cliente</th>
                    	<th>Fecha</th>
                    	<th>Hora</th>
                        <th>Total</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="tbody1">
				</tbody>
            </table>
        
	</div>
    

    <script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/Tabla_Extendible.js"></script>
   	<script type="text/javascript" src="../scripts/ver_ventas.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    
</body>
</html>
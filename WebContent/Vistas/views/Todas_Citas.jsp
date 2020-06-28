<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<%@page import="Modelo.Clientes"%>
<%@page import="ModeloDAO.ClientesDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="imagen/png" href="../img/Logo.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Agenda</title>

    <!--- Custom CSS for this page --->
    <link rel="stylesheet" href="../icons/style.css">
    <link rel="stylesheet" href="../CSS/ver_citas.css">
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
	
	
	<jsp:include page="Includes/Menu_Principal.jsp"></jsp:include>
    
    <br><br><br><br>
    
    <div class="contenido"  >
        <h1>Citas</h1>
        
            <table>
                <thead>
                    <tr>
                        <th>Clave</th>
                        <th>Tipo</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Cliente</th>
                        <th>Mascota</th>
                        <th>Notas</th>
                    </tr>
                </thead>
                <tbody>
                    
                </tbody>
            </table>
        
    </div>
    <br>
    

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/Todas_Citas.js"></script>
    
</body>
</html>
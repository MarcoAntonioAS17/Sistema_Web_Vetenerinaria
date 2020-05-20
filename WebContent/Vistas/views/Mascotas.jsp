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
    <link rel="stylesheet" href="../CSS/mascotas.css">
    
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
    
    <form action="" class="formulario">
        <h1>Buscar Mascota</h1>
        <br><tbr>
         <div id="cont-producto">
	        <label for="Clv_Cte" class="label-input-30">Código del Cliente</label>
	        <input id="Clv_Cte" name="Clv_Cte" type="text" class="input-30" placeholder="Código">  
        
        	<button id="buscar" type="button" class="buscar">Buscar Clientes</button>
        </div>
        
        <h1>Registrar Nueva Mascota</h1>
        <br><tbr>
        <div>
            <input id="Clv_Mas" name="Clv_Mas" type="text" class="formulario__input" required="required">  
            <label for="Clv_Mas" class="formulario__label">Clave de la Mascota</label>
            
            <input id="Nom_Mas" name="Nom_Mas" type="text" class="formulario__input" required="required"> 
            <label for="Nom_Mas" class="formulario__label">Nombre de la Mascota</label>
            
            <input id="Edad_Mas" name="Edad_Mas" type="number" class="formulario__input" required="required"> 
            <label for="Edad_Mas" class="formulario__label">Edad</label>
            
            <input id="Tipo_Mas" name="Tipo_Mas" type="text" class="formulario__input" required="required"> 
            <label for="Tipo_Mas" class="formulario__label">Tipo de Mascota</label>
            
            <input id="Raz_Mas" name="Raz_Mas" type="text" class="formulario__input" required="required"> 
            <label for="Raz_Mas" class="formulario__label">Raza de la Mascota</label>
            
            <input id="Des_Mas" name="Des_Mas" type="text" class="formulario__input" required="required"> 
            <label for="Des_Mas" class="formulario__label">Descripción</label>
            
            <button id="guard" type="button" class="guard">Registrar Mascota</button>
        </div>
        
        <br><tbr>
         <h1>Mascotas Registradas</h1>
            <table>
                <thead>
                    <tr>
                        <th>Clave de la Mascota</th>
                        <th>Nombre de la Mascota</th>
                        <th>Edad</th>
                        <th>Tipo de Mascota</th>
                        <th>Raza de la Mascota</th>
                        <th>Descripción</th>
                    </tr>
                </thead>
                <tbody>
                   <tr>
                        <td>72931038</td>
                        <td>Scarlett</td>
                        <td>2 años</td>
                        <td>Perro</td>
                        <td>Chihuahua</td>
                        <td>Color cafe, ojos negros, cola corta y trompa chata</td>
                        <td>
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                    <tr>
                        <td>72931038</td>
                        <td>Cheese</td>
                        <td>2 años</td>
                        <td>Perro</td>
                        <td>Salchicha</td>
                        <td>Color blanco, ojos cafes, cola larga y trompa alargada</td>
                        <td>
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                </tbody>
            </table>

        <div >
            <button id="guardar" type="submit" class="guardar">Editar Datos</button>
            <button id="cancelar" type="reset" class="cancelar">Cancelar</button>
        </div>   
    </form>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    <script type="text/javascript" src="../scripts/fecha-tiempo.js"></script>
    
</body>
</html>
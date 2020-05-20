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
    <link rel="stylesheet" href="../CSS/clientes.css">
    
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
        <h1>Buscar Cliente</h1>
        <br><tbr>
         <div id="cont-producto">
	        <label for="Clv_Cte" class="label-input-30">Código del Cliente</label>
	        <input id="Clv_Cte" name="Clv_Cte" type="text" class="input-30" placeholder="Código">  
        
        	<button id="buscar" type="button" class="buscar">Buscar Cliente</button>
        </div>
        
        <h1>Registrar Cliente Nuevo</h1>
        <br><tbr>
        <div>
            <input id="Clv_C" name="Clv_C" type="text" class="formulario__input" required="required">  
            <label for="Clv_C" class="formulario__label">Clave del Cliente</label>
            
            <input id="Nom_Cte" name="Nom_Cte" type="text" class="formulario__input" required="required"> 
            <label for="Nom_Cte" class="formulario__label">Nombre del Cliente</label>
            
            <input id="Tel_Cte" name="Tel_Cte" type="number" class="formulario__input" required="required"> 
            <label for="Tel_Cte" class="formulario__label">Teléfono</label>
            
            <input id="Mail_Cte" name="Mail_Cte" type="email" class="formulario__input" required="required"> 
            <label for="Mail_Cte" class="formulario__label">Correo Eléctronico</label>
            
            <button id="guard" type="button" class="guard">Guardar Cliente</button>
        </div>
        
        <br><tbr>
         <h1>Clientes Registrados</h1>
            <table>
                <thead>
                    <tr>
                        <th>Clave Cliente</th>
                        <th>Nombre</th>
                        <th>Teléfono</th>
                        <th>E-mail</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                   <tr>
                        <td>72931038</td>
                        <td>Javier Duran</td>
                        <td>2942942942</td>
                        <td>javii111@gmail.com</td>
                        <td>
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                    <tr>
                        <td>72931038</td>
                        <td>Jonh Rodriguez</td>
                        <td>2942942942</td>
                        <td>jonh@gmail.com</td>
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
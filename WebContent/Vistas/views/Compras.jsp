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
	
	<jsp:include page="Includes/Menu_Principal.jsp"></jsp:include>

    <br><br><br><br>
    
    <form action="" class="formulario">
        <h1>Nueva Compra</h1>
        <div id="date-time">
	        <label for="Fec_C" class="label-input-40">Fecha</label>
	        <label for="Hra_C" class="label-input-40">Hora</label>
	        
	        <input id="Fec_C" name="Fec_C" type="date" class="input-40">  
	        <input id="Hra_C" name="Hra_C" type="time" class="input-40">  
        </div>
        
        <input id="Clv_Cte" name="Clv_Cte" type="text" class="formulario__input" required="required">  
        <label for="Clv_Cte" class="formulario__label">Clave de Proveedor</label>
        
        <div id="cont-producto">
	        <label for="Clv_Pro" class="label-input-30">Código del producto</label>
	        <input id="Clv_Pro" name="Clv_Pro" type="text" class="input-30" placeholder="Código">  
        
        	<button id="agregar" type="button" class="agregar">Agregar Producto</button>
        </div>
         <h1>Carrito de Compra</h1>
            <table>
                <thead>
                    <tr>
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
                        <td>72931038</td>
                        <td>Croqueta Proplan Cordero</td>
                        <td>3</td>
                        <td>1000</td>
                        <td>3000</td>
                        <td>
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                    <tr>
                        <td>72931038</td>
                        <td>Croqueta Proplan Cordero</td>
                        <td>3</td>
                        <td>1000</td>
                        <td>3000</td>
                        <td>
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                    <tr>
                        <td>72931038</td>
                        <td>Croqueta Proplan Cordero</td>
                        <td>3</td>
                        <td>1000</td>
                        <td>3000</td>
                        <td>
                            <a  href="#"><img width="25px" alt="ico-eliminar" src="../img/eliminar-icono.svg"></a>
                       </td>
                    </tr>
                    
                    <tr>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td>Total</td>
                    	<td>$9,000</td>
                    	<td></td>
                    </tr>
                </tbody>
            </table>
        
        <div >
            <button id="guardar" type="submit" class="guardar">Finalizar Compra</button>
            <button id="cancelar" type="reset" class="cancelar">Cancelar</button>
        </div>
        
    </form>
    

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../scripts/menu.js"></script>
    <script type="text/javascript" src="../scripts/script.js"></script>
    <script type="text/javascript" src="../scripts/fecha-tiempo.js"></script>
    
    
</body>
</html>
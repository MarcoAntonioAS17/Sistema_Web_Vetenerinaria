<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<%
	HttpSession user_session = request.getSession(false);
	String mensaje = (String) user_session.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	
	<link rel="icon" type="imagen/png" href="Vistas/img/Logo.svg">
	
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1">
	
    <link rel="stylesheet" type="text/css" href="Vistas/CSS/login.css">
    
    <script type="text/javascript" src="Vistas/scripts/jquery.min.js"></script>

	<title>Login</title>

</head>
<body>
	<main>
        <div class="login">
           <section id="left">
                <img id="logo" src="Vistas/img/Logo-Extend.svg">
            </section>
           
            <section id="right">
                  
                <div id="opcion-login">
                    <h1>BIENVENIDO</h1>
                    <h2>Ingrese su cuenta y contraseña</h2>
                    <form action="SInicio" method="post"  >
                        <p>USUARIO</p>
                        <input id="Usuario" type="text" name="Usuario" placeholder="Usuario" required="required">
                        
                        <p>CONTRASEÑA</p>
                        <input id="Password" type="password" name="Password" placeholder="Contraseña" required="required">
                        
                       <!-- <button id="guardar" type="button" name="accion" value="Agregar" class="guardar">Agregar</button>
                        <input id="Ingresar" type="submit" name="Ingresar" value="Entrar" >-->
                        <button id="Ingresar" type="button" name="Ingresar" >Ingresar</button>
                    </form>
                </div>
               
            </section>
            
            
            
        </div>
    </main>
    
    <script>
	    
    	
        $(document).ready(function(){
   
			$('#Ingresar').click(function(e){
				var usuariovar = $("#Usuario").val();
				var passwordvar = $("#Password").val();
				$("#Password").css({"border-color":"white","color":"white"});
				$("#Usuario").css({"border-color":"white","color":"white"});
				if(passwordvar == "" || usuariovar==""){
					if(passwordvar == ""){
						$("#Password").css({"border-color":"red","color":"red"});
					}
					if(usuariovar==""){
						$("#Usuario").css({"border-color":"red","color":"red"});
					}
					return;
				}
				
				$.post("SInicio",{
				 Usuario: usuariovar,
				 Password: passwordvar
				},function(responseText){
				 	if(responseText == "true"){
				 		$(location).attr('href',"http://localhost:8080/SistemaWebVeterinaria/Vistas/views/Inicio.jsp");
				 	}else{
				 		alert("Usuario o contraseña incorrecto");
				 	}
				});
			});
        	
           
            
        });
    </script>
    
</body>
</html>
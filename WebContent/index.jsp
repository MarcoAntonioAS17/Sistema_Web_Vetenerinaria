<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	
	<link rel="icon" type="imagen/png" href="Vistas/img/Logo.svg">
	
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1">
	
    <link rel="stylesheet" type="text/css" href="Vistas/CSS/login.css">
    
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

	<title>Login</title>

</head>
<body>
	<main>
        <div class="login">
           <section id="left">
                <img id="logo" src="Vistas/img/Logo-Extend.svg">
            </section>
           
            <section id="right">
                  <input  id="login"  name="opcion" type="radio" checked> <label id="f-login" for="login">Ingresar</label>
                  <input id="new"  name="opcion" type="radio"> <label id="f-new" for="new">Registrar</label>
                  
                <div id="opcion-login">
                    <h1>BIENVENIDO</h1>
                    <h2>Ingrese su cuenta y contraseña</h2>
                    <form action="views/Inicio.jsp"  >
                        <p>USUARIO</p>
                        <input type="text" name="Usuario" placeholder="Usuario">
                        
                        <p>CONTRASEÑA</p>
                        <input type="password" name="Password" placeholder="Contraseña">
                        
                        <input type="submit" name="Ingresar" value="Entrar" >
                    </form>
                </div>
                <div id="opcion-new">
                    <h1>Nuevo Usuario</h1>
                    <h2>Ingrese los datos de la nueva cuenta</h2>
                    <form action="" method="post" >
                        <p>CUENTA DE USUARIO</p>
                        <input type="text" name="Usuario_Nuevo" placeholder="Usuario">
                        
                        <p>CONTRASEÑA</p>
                        <input type="password" name="Password_Nuevo" placeholder="Contraseña">
                        
                        <input type="submit" name="Guardar" value="Guardar">
                    </form>
                </div>
            </section>
            
            
            
        </div>
    </main>
    
    <script>
        $(document).ready(function(){
            
            /*Clic en Ingresar*/
            $("#login").change(function(){

                $("#f-new").css({ "color": "#3a4955",
                    "border": "1px solid #808d97",
                    "background": "#808d97"});
                
                $("#f-login").css({ "color": "white",
                    "border": "1px solid #70D6C1",
                    "background": "#70D6C1"});
                
                
                $("#opcion-login").toggle(1000);
                $("#opcion-new").toggle(1000);
 
            });
            
            //Clic en Registrar
            $("#new").change(function(){
                
                 $("#f-login").css({ "color": "#3a4955",
                    "border": "1px solid #808d97",
                    "background": "#808d97"});
                
                $("#f-new").css({ "color": "white",
                    "border": "1px solid #70D6C1",
                    "background": "#70D6C1"});
                
                $("#opcion-login").toggle(1000);
                $("#opcion-new").toggle(1000);
            });
            
        });
    </script>
    
</body>
</html>
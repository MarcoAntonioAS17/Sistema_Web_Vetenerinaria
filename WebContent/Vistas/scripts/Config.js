	//Guardar datos en BD
   $(document).ready(function(){
	   
	   $("#new_user").click(function(e){
		   $(".formulario").show(500);
		   $('html, body').animate({
			   scrollTop: $("#formulario-new").offset().top
		   }, 1000);
	   });
	   
	   $(".contenido > input[type=number]").keyup(function(e){
		   $("#guardar_cant").show(500);
	   });
	   
       $('#guardar_cant').click(function(e){
    	   var varDias = $("#dias-caducar").val();
    	   var varCantidad = $("#cantidad-a").val();
    	   
    	   $("#dias-caducar").css({"border-color":"white","color":"#000"});
    	   $("#cantidad-a").css({"border-color":"white","color":"#000"});
    	   
    	   if(varDias == "" || varCantidad == ""){
    		   if(varCantidad == "")
    			   $("#cantidad-a").css({"border-color":"red","color":"red"});
    		   if(varDias == "")
    			   $("#dias-caducar").css({"border-color":"red","color":"red"});
    		   
    		   return;
    	   }
          $.post("../../Configurar",{
        	  accion : "modificar_dias",
        	  Dias : varDias,
        	  Cantidad : varCantidad
          },function(responseText){
        	  
        	  if(responseText == "true"){
        		  alert("Modificado con exito");
        	  }else{
        		  alert("Error al modificar");
        	  }
        	  
        	  location.reload();
        	  limpiar_campos();
          });
       });
       
       
       $('#guardar').click(function(e){
    	   var varNombre = $("#Nombre").val();
    	   var varPass = $("#Contra").val();
    	   var varPass2 = $("#Contra2").val();
    	   
    	   if(varPass != varPass2){
    		   alert("Las contraseñas no coinciden");
    		   return;
    	   }
    	   
    		
    	   var varTipo = $("#Tipo").val();
    	   var varNombreA = $("#NombreA").val(); 
    	   var varPassA = $("#ContraA").val();
    	   
    	   if(varNombre == "" || varPass == ""){
    		   if(varNombre == "")
    			   $("#Nombre").css({"border-color":"red","color":"red"});
    		   if(varPass == "")
    			   $("#Contra").css({"border-color":"red","color":"red"});
    		   return;
    	   }
    	   alert(varNombreA+" "+ varPassA);
          $.post("../../Configurar",{
        	  accion : "validar_admin",
        	  NombreA : varNombreA,
        	  PassA : varPassA,
        	  Nombre : varNombre,
        	  Pass : varPass,
        	  Tipo :varTipo
          },function(responseText){
        	  
        	  if(responseText == "true"){
        		  alert("Usuario creado con exito");
        	  }else{
        		  if(responseText=='1')
        			  alert("Administrador incorrecto");
        		  else
        			  alert("Error al crear Usuario");
        	  }
        	  location.reload();
        	  limpiar_campos();
        	  
          });
       });
       
	});
	
function eliminar(Usuario){
   var confirma = confirm("¿Estás seguro que deseas eliminar el usuario?");
   
   if(confirma==true) {
	   $.post("../../Configurar",{
    		   accion : "eliminar",
    		   User : Usuario
    	   },function(responseText){
    		   if(responseText == "true"){
        		  alert("Usuario eliminado");
        	  }else{
        		  alert("Error al eliminar usuario");
        	  }
        	  location.reload();
    	   });
   }
}

function modificar(Usuario){
	
	var varPassA = $("#ContraAc").val();
	var varPass = $("#ContraN").val();
	var varPass2 = $("#ContraN2").val();
	
	if(varPass != varPass2){
		alert("Las contraseñas no coinciden");
		return;
	}
	
	$.post("../../Configurar",{
		   accion : "modificar",
		   User : Usuario,
		   PassN: varPass,
		   PassA: varPassA
	},function(responseText){
		if(responseText == "true"){
  		  alert("Contraseña actualizada exito");
  	  }else{
  		  if(responseText=='1')
  			  alert("Contraseña incorrecta");
  		  else
  			  alert("Error al modificar contraseña");
  	  }
  	  location.reload();
  	  limpiar_campos();
  	  
    });
}

   
function limpiar_campos(){
		
	$("#Nombre").val("");
	$("#Contra").val("");
	$("#Contra2").val("");
		
	$("#Tipo").val(1);
	$("#NombreA").val(""); 
	$("#ContraA").val("");
	
	$("#ContraA").val("");
	$("#ContraN").val("");
	$("#ContraN2").val("");
}
  
	
	
	   
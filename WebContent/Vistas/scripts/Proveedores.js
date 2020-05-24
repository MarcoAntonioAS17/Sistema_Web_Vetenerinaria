	//Guardar datos en BD
	   $(document).ready(function(){
		   
	       $('#guardar').click(function(e){
	    	   var idProveedor = $("#Clv_Pro").val();
	    	   var Nombre_Pro = $("#Nombre_Pro").val();
	    	   var Tel_Pro = $("#Tel_Pro").val();
	    	   var Email_Pro = $("#Email_Pro").val();
	    	   
	    	   if(idProveedor == "" || Nombre_Pro ==""){
	    		   if(idProveedor == ""){
						$("#Clv_Pro").css({"border-color":"red","color":"red"});
					}
					if(Nombre_Pro==""){
						$("#Nombre_Pro").css({"border-color":"red","color":"red"});
					}
					alert("Faltan campos por acompletar");
	    		   return;
	    	   }
	          $.post("../../Proveedores",{
	        	  accion : "agregar",
	        	  Proveedor : idProveedor,
	        	  Nombre_Pro : Nombre_Pro,
	        	  Telefono : Tel_Pro,
	        	  Email: Email_Pro
	          },function(responseText){
	        	  
	        	  if(responseText == "true"){
	        		  alert("Proveedor agregado con exito");
	        	  }else{
	        		  alert("Error al agregar");
	        	  }
	        	  location.reload();
	        	  limpiar_campos();
	          });
	       });
	       
	       $('.eliminar_pro').click(function(e){
	    	   var codvar = $(this).val();
	    	   
	    	   $.post("../../Proveedores",{
	    		   accion : "eliminar",
	    		   IDProveedor : codvar
	    	   },function(responseText){
	    		   if(responseText == "true"){
		        		  alert("Proveedor elimanado con exito");
		        	  }else{
		        		  alert("Error al elimar");
		        	  }
		        	  location.reload();
	    	   });
	       });
	       
	       $('.editar_pro').click(function(e){
	    	   var codvar = $(this).val();
	    	   $.post("../../Proveedores",{
		        	  accion : "editar1",
		        	  IDProveedor : codvar,
		          },function(responseJson){
		        	  	$(".formulario__label").addClass("fijar");
						var datos = JSON.parse(responseJson);
		        	  
						$("#Clv_Pro").val(datos.IDProveedor);
						$("#Nombre_Pro").val(datos.Nombre);
						$("#Tel_Pro").val(datos.Telefono);
						$("#Email_Pro").val(datos.Correo);
			    	   
						$("#title_form").text("EDITAR PROVEEDOR");
		        	  alert("Baja para modificar los datos");
		        	  $('#guardar').hide(500);
		        	  $('#actualizar').show(500);
		        	  $("#Clv_Pro").prop("readonly", true);
		          });
	    	   
	       });
	       
    	});
	   
	   $('#actualizar').click(function(e){
    	   var idProveedor = $("#Clv_Pro").val();
    	   var Nombre_Pro = $("#Nombre_Pro").val();
    	   var Tel_Pro = $("#Tel_Pro").val();
    	   var Email_Pro = $("#Email_Pro").val();
    	   
    	   if(idProveedor == "" || Nombre_Pro ==""){
    		   if(idProveedor == ""){
					$("#Clv_Pro").css({"border-color":"red","color":"red"});
				}
				if(Nombre_Pro==""){
					$("#Nombre_Pro").css({"border-color":"red","color":"red"});
				}
				alert("Faltan campos por acompletar");
    		   return;
    	   }
          $.post("../../Proveedores",{
        	  accion : "editar",
        	  Proveedor : idProveedor,
        	  Nombre_Pro : Nombre_Pro,
        	  Telefono : Tel_Pro,
        	  Email: Email_Pro
          },function(responseText){
        	  
        	  if(responseText == "true"){
        		  alert("Proveedor actualizado con exito");
        	  }else{
        		  alert("Error al actualizar");
        	  }
        	  location.reload();
        	  limpiar_campos();
          });
       });
    	
    	function limpiar_campos(){
    		$("#title_form").text("AGREGAR PROVEEDOR");
    		$("#Clv_Pro").prop("readonly", false);
    		  $('#guardar').show(500);
        	  $('#actualizar').hide(500);
    		$(".formulario__label").removeClass("fijar");
    		$("#Clv_Pro").val("");
    	   	$("#Nombre_Pro").val("");
    	   	$("#Tel_Pro").val("");
    	   	$("#Email_Pro").val("");
    	}
    	
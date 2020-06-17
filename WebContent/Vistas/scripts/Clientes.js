//Clientes en BD

 $(document).ready(function(){
		   
	       $('#guardar').click(function(e){
	    	   var idCliente = $("#Clv_C").val();
	    	   var Nom_Cte = $("#Nom_Cte").val();
	    	   var Tel_Cte = $("#Tel_Cte").val();
	    	   var Mail_Cte = $("#Mail_Cte").val();
	    	   
	    	   if(idCliente == "" || Nom_Cte ==""){
	    		   if(idCliente == ""){
						$("#Clv_C").css({"border-color":"red","color":"red"});
					}
					if(Nom_Cte==""){
						$("#Nom_Cte").css({"border-color":"red","color":"red"});
					}
					alert("Faltan campos por ser llenados");
	    		   return;
	    	   }
	    	   
	          $.post("../../Clientes",{
	        	  accion : "agregar",
	        	  Clientes : idCliente,
	        	  Nom_Cte : Nom_Cte,
	        	  Telefono : Tel_Cte,
	        	  Email: Mail_Cte
	          },function(responseText){
	        	  
	        	  if(responseText == "true"){
	        		  alert("Cliente agregado con exito");
	        	  }else{
	        		  alert("Error al agregar los datos del cliente");
	        	  }
	        	  location.reload();
	        	  limpiar_campos();
	          });
	       });
	       
	       
	       $('.eliminar_cte').click(function(e){
	    	   var codvar = $(this).val();
	    	   var confirma = confirm("¿Estás seguro que deseas eliminar el registro?");
	    	   
	    	   if(confirma==true) {
		    	   $.post("../../Clientes",{
		    		   accion : "eliminar",
		    		   IDClient : codvar
		    	   },function(responseText){
		    		   if(responseText == "true"){
			        		  alert("Cliente eliminado con exito");
			        	  }else{
			        		  alert("Error al eliminar datos");
			        	  }
			        	  location.reload();
		    	   });
	    	   }
	       });
	       
	       
	       $('.editar_cte').click(function(e){
	    	   var codvar = $(this).val();
	    	   $.post("../../Clientes",{
		        	  accion : "editar1",
		        	  IDClient : codvar,
		          },function(responseJson){
		        	  	$(".formulario__label").addClass("fijar");
						var datos = JSON.parse(responseJson);
		        	  
						$("#Clv_C").val(datos.IDClient);
						$("#Nom_Cte").val(datos.NombreC);
						$("#Tel_Cte").val(datos.TelefonoC);
						$("#Mail_Cte").val(datos.CorreoC);
			    	   
						$("#title_form").text("EDITAR PROVEEDOR");
		        	  alert("Baja para modificar los datos");
		        	  $('#guardar').hide(500);
		        	  $('#actualizar').show(500);
		        	  $("#Clv_C").prop("readonly", true);
		          });
	       });
	       
    	});
 
 
 $('#actualizar').click(function(e){
	   var idCliente = $("#Clv_C").val();
	   var Nom_Cte = $("#Nom_Cte").val();
	   var Tel_Cte = $("#Tel_Cte").val();
	   var Mail_Cte = $("#Mail_Cte").val();
	   
	   if(idCliente == "" || Nom_Cte ==""){
		   if(idCliente == ""){
				$("#Clv_C").css({"border-color":"red","color":"red"});
			}
			if(Nom_Cte==""){
				$("#Nom_Cte").css({"border-color":"red","color":"red"});
			}
			alert("Faltan campos por ser llenados");
		   return;
	   }
    $.post("../../Clientes",{
  	  accion : "editar",
  	  Clientes : idCliente,
  	  Nom_Cte : Nom_Cte,
  	  Telefono : Tel_Cte,
  	  Email: Mail_Cte
    },function(responseText){
  	  
  	  if(responseText == "true"){
  		  alert("Cliente actualizado con exito");
  	  }else{
  		  alert("Error al actualizar los datos");
  	  }
  	  location.reload();
  	  limpiar_campos();
    });
 });
 
 
 function limpiar_campos(){
		$("#title_form").text("AGREGAR CLIENTE");
		$("#Clv_C").prop("readonly", false);
		$('#guardar').show(500);
		$('#actualizar').hide(500);
		$(".formulario__label").removeClass("fijar");
		$("#Clv_C").val("");
	   	$("#Nom_Cte").val("");
	   	$("#Tel_Cte").val("");
	   	$("#Mail_Cte").val("");
	}
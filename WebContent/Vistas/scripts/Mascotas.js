	//Guardar datos en BD
   $(document).ready(function(){
		   
		   mostrar_registros(1,"");
		   limpiar_campos();
		   
		   $("#opciones").change(function(){
			   mostrar_registros($(this).val(),"");
		   });
		   
		   $("#busqueda-input").keyup(function(){
			   mostrar_registros($("#opciones").val(),$(this).val());
		   });
		   
	       $('#guardar').click(function(e){
	    	   var varCliente = $("#Clv_Clie").val();
	    	   var varMascota = $("#Nom_Mas").val();
	    	   var varEdad = $("#Edad_Mas").val();
	    	   var varTipo = $("#Tipo_Mas").val();
	    	   var varRaza = $("#Raz_Mas").val();
	    	   var varDes = $("#Des_Mas").val();
	    	   
	    	   if(varMascota == "" || varTipo =="" || varCliente ==""){
	    		   if(varCliente == ""){
						$("#Clv_Clie").css({"border-color":"red","color":"red"});
	    		   }
	    		   if(varMascota == ""){
						$("#Nom_Mas").css({"border-color":"red","color":"red"});
					}
					if(varTipo==""){
						$("#Tipo_Mas").css({"border-color":"red","color":"red"});
					}
					alert("Faltan campos por acompletar");
	    		   return;
	    	   }
	          $.post("../../SMascotas",{
	        	  accion : "agregar",
	        	  Cliente: varCliente,
	        	  Nombre: varMascota,
		    	  Edad: varEdad,
		    	  Tipo: varTipo,
		    	  Raza: varRaza,
		    	  Descripcion: varDes
		    	 
	          },function(responseText){
	        	  
	        	  if(responseText == "true"){
	        		  alert("Mascota agregado con exito");
	        	  }else{
	        		  alert("Error al agregar");
	        	  }
	        	  location.reload();
	        	  limpiar_campos();
	          });
	       });
	       
	       $('#actualizar').click(function(e){
	    	   var varCliente = $("#Clv_Clie").val();
	    	   var varMascota = $("#Nom_Mas").val();
	    	   var varEdad = $("#Edad_Mas").val();
	    	   var varTipo = $("#Tipo_Mas").val();
	    	   var varRaza = $("#Raz_Mas").val();
	    	   var varDes = $("#Des_Mas").val();
	    	   var Clv_Mas = $("#clv").val();
	    	   
	    	   if(varMascota == "" || varTipo =="" || varCliente ==""){
	    		   if(varCliente == ""){
						$("#Clv_Clie").css({"border-color":"red","color":"red"});
	    		   }
	    		   if(varMascota == ""){
						$("#Nom_Mas").css({"border-color":"red","color":"red"});
					}
					if(varTipo==""){
						$("#Tipo_Mas").css({"border-color":"red","color":"red"});
					}
					alert("Faltan campos por acompletar");
	    		   return;
	    	   }
	          $.post("../../SMascotas",{
	        	  accion : "editar",
	        	  IDMascota:Clv_Mas,
	        	  Cliente: varCliente,
	        	  Nombre: varMascota,
		    	  Edad: varEdad,
		    	  Tipo: varTipo,
		    	  Raza: varRaza,
		    	  Descripcion: varDes
	          },function(responseText){
	        	  
	        	  if(responseText == "true"){
	        		  alert("Mascota actualizada con exito");
	        	  }else{
	        		  alert("Error al actualizar");
	        	  }
	        	  location.reload();
	        	  limpiar_campos();
	          });
	       });
       });
	   
function mostrar_registros(opcion,busqueda){
	$("tbody").empty();
	$.post("../../SMascotas",{
		accion : "mostrar",
		valor: opcion,
		search: busqueda
	},function(responseJson){
		
		var datos = JSON.parse(responseJson);

		for(var i in datos){
			$("tbody").append("<tr>" +
					"<td>"+datos[i].IDMascota+"</td>" +
					"<td>"+datos[i].R_Cliente+"</td>" +
					"<td>"+datos[i].Nombre+"</td>" +
					"<td> "+datos[i].Edad+"</td>" +
					"<td> "+datos[i].Tipo+"</td>" +
					"<td>"+datos[i].Raza+"</td>" +
					"<td>"+datos[i].Descripcion+"</td>" +
					"<td>"+
					"<button onclick=\"editar_inicio("+datos[i].IDMascota+")\" class=\"editar_pro\"> <img width=\"25px\"  alt=\"icono-editar\" src=\"../img/editar-icono.svg\"></button>"+
					"<button onclick=\"eliminar("+datos[i].IDMascota+")\" class=\"eliminar_pro\"> <img width=\"25px\"  alt=\"icono-eliminar\" src=\"../img/eliminar-icono.svg\"></button>"+
					
					"</td>"+
                    "</tr>");
		}		
	}
	);
   
}



function eliminar(codmas){
 	   var confirma = confirm("¿Estás seguro que deseas eliminar el registro?");
 	   
 	   if(confirma==true) {
 		   $.post("../../SMascotas",{
	    		   accion : "eliminar",
	    		   IDMascota : codmas
	    	   },function(responseText){
	    		   if(responseText == "true"){
	        		  alert("Producto eliminado con exito");
	        	  }else{
	        		  alert("Error al elimar");
	        	  }
	        	  location.reload();
	    	   });
 	   }
}

function editar_inicio(codmas){
	   
	   $.post("../../SMascotas",{
	        	  accion : "editar1",
	        	  IDMascota : codmas,
	          },function(responseJson){
	        	  	$(".formulario__label").addClass("fijar");
					var datos = JSON.parse(responseJson);
					$("#clv").val(codmas);
					$("#Clv_Clie").val(datos.R_Cliente);
			    	$("#Nom_Mas").val(datos.Nombre);
			    	$("#Edad_Mas").val(datos.Edad);
			    	$("#Tipo_Mas").val(datos.Tipo);
			    	$("#Raz_Mas").val(datos.Raza);
			    	$("#Des_Mas").val(datos.Descripcion);
					
		    	   
			    	$("#title_form").text("Editar Mascota");
	        	  alert("Baja para modificar los datos");
	        	  $('#guardar').hide(500);
	        	  $('#actualizar').show(500);
	          });
}


function limpiar_campos(){
	$("#busqueda-input").val("");
	$("#Clv_Clie").val("");
	$("#Nom_Mas").val("");
	$("#Edad_Mas").val("");
	$("#Tipo_Mas").val("");
	$("#Raz_Mas").val("");
	$("#Des_Mas").val("");
}
    	
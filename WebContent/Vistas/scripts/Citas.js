	//Guardar datos en BD
   $(document).ready(function(){
		   
		   mostrar_registros();
		   cargar_mascotas($("#Client").val());
		   
		   $("#Client").change(function(){			   
			   cargar_mascotas($(this).val());
		   });
		   
		   $('#guardar').click(function(e){
			   var varTipo = $("#tipo").val();
			   var varFecha = $("#Fecha").val();
			   var varHora = $("#Hora").val();
	    	   var varCliente = $("#Client").val();
	    	   var varMascota = $("#Masc").val();
	    	   var varNotas = $("#Notas").val();
	    	   
	    	   if(varFecha == "" ){
					$("#Fecha").css({"border-color":"red","color":"red"});
					alert("Faltan campos por acompletar");
	    		   return;
	    	   }
	          $.post("../../SCitas",{
	        	  accion : "agregar",
	        	  Tipo: varTipo,
	        	  Fecha: varFecha,
		    	  Hora: varHora,
		    	  Cliente: varCliente,
		    	  Mascota: varMascota,
		    	  Notas: varNotas
	          },function(responseText){
	        	  
	        	  if(responseText == "true"){
	        		  alert("Cita registrada");
	        	  }else{
	        		  alert("Error al agendar");
	        	  }
	        	  location.reload();
	        	  limpiar_campos();
	          });
	       });
		   
		   $('#actualizar').click(function(e){
			   var varTipo = $("#tipo").val();
			   var varFecha = $("#Fecha").val();
			   var varHora = $("#Hora").val();
	    	   var varCliente = $("#Client").val();
	    	   var varMascota = $("#Masc").val();
	    	   var varNotas = $("#Notas").val();
	    	   
	    	   if(varFecha == "" ){
					$("#Fecha").css({"border-color":"red","color":"red"});
					alert("Faltan campos por acompletar");
	    		   return;
	    	   }
	          $.post("../../SCitas",{
	        	  accion : "editar",
	        	  Tipo: varTipo,
	        	  Fecha: varFecha,
		    	  Hora: varHora,
		    	  Cliente: varCliente,
		    	  Mascota: varMascota,
		    	  Notas: varNotas
	          },function(responseText){
	        	  
	        	  if(responseText == "true"){
	        		  alert("Cita actualizadda");
	        	  }else{
	        		  alert("Error al actualizar");
	        	  }
	        	  location.reload();
	        	  limpiar_campos();
	          });
	       });
	       
       });
	   
function mostrar_registros(){
	$("tbody").empty();
	$.post("../../SCitas",{
		accion : "mostrar"
	},function(responseJson){
		
		var datos = JSON.parse(responseJson);
		var date;
		var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
		for(var i in datos){
			 date = new Date(datos[i].Fecha); 
			 date.setDate(date.getDate() + 1);
			 if(datos[i].Hora == "null"){
				 $("tbody").append("<tr>" +
							"<td>"+datos[i].IDCita+"</td>" +
							"<td>"+datos[i].Tipo+"</td>" +
							"<td>"+date.toLocaleDateString("es-ES", options)+"</td>" +
							"<td>"+datos[i].Hora+"</td>" +
							"<td> "+datos[i].NombreC+"</td>" +
							"<td>"+datos[i].NombreM+"</td>" +
							"<td>"+datos[i].Notas+"</td>" +
							"<td>"+
							"<button onclick=\"editar_inicio("+datos[i].IDCita+")\" class=\"editar_pro\"> <img width=\"25px\"  alt=\"icono-editar\" src=\"../img/editar-icono.svg\"></button>"+
							"<button onclick=\"eliminar("+datos[i].IDCita+")\" class=\"eliminar_pro\"> <img width=\"25px\"  alt=\"icono-eliminar\" src=\"../img/eliminar-icono.svg\"></button>"+
							
							"</td>"+
		                    "</tr>");
			 }else{
				 $("tbody").append("<tr>" +
							"<td>"+datos[i].IDCita+"</td>" +
							"<td>"+datos[i].Tipo+"</td>" +
							"<td>"+date.toLocaleDateString("es-ES", options)+"</td>" +
							"<td>"+datos[i].Hora[0]+datos[i].Hora[1]+datos[i].Hora[2]+datos[i].Hora[3]+datos[i].Hora[4]+"</td>" +
							"<td> "+datos[i].NombreC+"</td>" +
							"<td>"+datos[i].NombreM+"</td>" +
							"<td>"+datos[i].Notas+"</td>" +
							"<td>"+
							"<button onclick=\"editar_inicio("+datos[i].IDCita+")\" class=\"editar_pro\"> <img width=\"25px\"  alt=\"icono-editar\" src=\"../img/editar-icono.svg\"></button>"+
							"<button onclick=\"eliminar("+datos[i].IDCita+")\" class=\"eliminar_pro\"> <img width=\"25px\"  alt=\"icono-eliminar\" src=\"../img/eliminar-icono.svg\"></button>"+
							
							"</td>"+
		                    "</tr>");
			 }
			
		}		
	}
	);
}

function eliminar(codcita){
	   var confirma = confirm("¿Estás seguro que deseas cancelar la cita?");
	   
	   if(confirma==true) {
		   $.post("../../SCitas",{
	    		   accion : "eliminar",
	    		   IDCita : codcita
	    	   },function(responseText){
	    		   if(responseText == "true"){
	        		  alert("Cita cancelada con exito");
	        	  }else{
	        		  alert("Error al cancelar");
	        	  }
	        	  location.reload();
	    	   });
	   }
}

function editar_inicio(codcita){
	   
   $.post("../../SCitas",{
        	  accion : "editar1",
        	  IDCita : codcita,
          },function(responseJson){
        	  	$(".formulario__label").addClass("fijar");
				var datos = JSON.parse(responseJson);

		    	
				$("#tipo").val(datos.Tipo);
				$("#Fecha").val(datos.Fecha);
				$("#Hora").val(datos.Hora);
				$("#Client").val(datos.NombreC);
				cargar_mascotas(datos.NombreC);
				$("#Masc").val(datos.NombreM);
				$("#Notas").val(datos.Notas);
	    	   
		    	$("#title_form").text("Editar Cita");
        	  alert("Baja para modificar los datos");
        	  $('#guardar').hide(500);
        	  $('#actualizar').show(500);
          });
}

function cargar_mascotas(varCliente){
	$("#Masc").empty();
	$.post("../../SCitas",{
		accion : "MascotasDe",
		Cliente: varCliente
	},function(responseJson){
		
		var datos = JSON.parse(responseJson);
		
		for(var i in datos){
			$("#Masc").append(
					"<option value=\""+datos[i].IDM+"\" >"+datos[i].NombreM+"</option>");
		}		
	}
	);
}

function limpiar_campos() {
	$("#tipo").val(1);
	$("#Client").val(1);
	$("#Masc").val(1);
	$("#Notas").val("");
	$("#Hora").val("");
}


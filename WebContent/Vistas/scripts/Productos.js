//Guardar datos en BD
   $(document).ready(function(){
	   
	   mostrar_registros(1,"");
	   $("#opciones").val(1);
	   $("#busqueda_input").val("");
	   
	   $("#opciones").change(function(){
		   $("tbody").empty();
		   mostrar_registros($(this).val(),"");
	   });
	   
	   $("#busqueda_input").keyup(function(){
		   $("tbody").empty();
		   mostrar_registros($("#opciones").val(),$(this).val());
	   });
	   
       $('#guardar').click(function(e){
    	   var idPro = $("#Clv_Pro").val();
    	   var varNombre = $("#Nombre_Pro").val();
    	   var varCategoria = $("#Categorias").val();
    	   var varCantidad =$("#Cantidad").val();
    	   var varPrecio_V = $("#Precio_V").val();
    	   var varPrecio_C = $("#Precio_C").val();
    	   var varFecha = $("#Fecha_C").val();
    	   var varProveedor =$("#Proveedores").val();
    	   var varDescr =$("#Descrip").val();
    	   
    	   if(idPro == "" || varNombre ==""){
    		   if(idPro == ""){
					$("#Clv_Pro").css({"border-color":"red","color":"red"});
				}
				if(varNombre==""){
					$("#Nombre_Pro").css({"border-color":"red","color":"red"});
				}
				alert("Faltan campos por acompletar");
    		   return;
    	   }
    	  $.post("../../Productos",{
				accion : "agregar",
				idProducto : idPro,	        	  
				Nombre : varNombre,
				Cantidad: varCantidad,
				Categoria: varCategoria,
				Precio_V: varPrecio_V,
				Precio_C: varPrecio_C,
				Fecha: varFecha,
				Proveedor: varProveedor,
				Descripcion: varDescr 
  
				},function(responseText){
				  
				if(responseText == "true"){
					alert("Producto agregado con exito");
				}else{
					  alert("Error al agregar");
				}
				location.reload();
				limpiar_campos();
		});
       });
 
	   $('#actualizar').click(function(e){
		   var idPro = $("#Clv_Pro").val();
    	   var varNombre = $("#Nombre_Pro").val();
    	   var varCategoria = $("#Categorias").val();
    	   var varCantidad =$("#Cantidad").val();
    	   var varPrecio_V = $("#Precio_V").val();
    	   var varPrecio_C = $("#Precio_C").val();
    	   var varFecha = $("#Fecha_C").val();
    	   var varProveedor =$("#Proveedores").val();
    	   var varDescr =$("#Descrip").val();
    	   
    	   if(idPro == "" || varNombre ==""){
    		   if(idPro == ""){
					$("#Clv_Pro").css({"border-color":"red","color":"red"});
				}
				if(varNombre==""){
					$("#Nombre_Pro").css({"border-color":"red","color":"red"});
				}
				alert("Faltan campos por acompletar");
    		   return;
    	  }
    	   
          $.post("../../Productos",{
        	  accion : "editar",
        	  idProducto : idPro,	        	  
        	  Nombre : varNombre,
        	  Cantidad: varCantidad,
        	  Categoria: varCategoria,
        	  Precio_V: varPrecio_V,
        	  Precio_C: varPrecio_C,
        	  Fecha: varFecha,
        	  Proveedor: varProveedor,
        	  Descripcion: varDescr 
          },function(responseText){
        	  
        	  if(responseText == "true"){
        		  alert("Producto actualizado con exito");
        	  }else{
        		  alert("Error al actualizar");
        		  return;
        	  }
        	  location.reload();
        	  limpiar_campos();
          });
   	});
});
   
function limpiar_campos(){
	$("#title_form").text("AGREGAR PRODUCTO");
	$("#Clv_Pro").prop("readonly", false);
	  $('#guardar').show(500);
	  $('#actualizar').hide(500);
	$(".formulario__label").removeClass("fijar");

   $("#Clv_Pro").val("");
   $("#Nombre_Pro").val("");
   $("#Cantidad").val("");
   $("#Precio_V").val("");
   $("#Precio_C").val("");
   $("#Descrip").val("");
}

function eliminar(codpro){
 	   var confirma = confirm("¿Estás seguro que deseas eliminar el registro?");
 	   
 	   if(confirma==true) {
 		   $.post("../../Productos",{
	    		   accion : "eliminar",
	    		   IDProductos : codpro
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

function editar_inicio(codvar){
 	   
 	   $.post("../../Productos",{
	        	  accion : "editar1",
	        	  IDProducto : codvar,
	          },function(responseJson){
	        	  	$(".formulario__label").addClass("fijar");
					var datos = JSON.parse(responseJson);
	        	  
					$("#Clv_Pro").val(datos.IDProducto);
			    	$("#Nombre_Pro").val(datos.Nombre);
			    	$("#Categorias").val(datos.Categoria);
			    	$("#Cantidad").val(datos.Cantidad);
			    	$("#Precio_V").val(datos.Precio_V);
			    	$("#Precio_C").val(datos.Precio_C);
			    	$("#Fecha_C").val(datos.Caducidad);
			    	$("#Proveedores").val(datos.Proveedor);
			    	$("#Descrip").val(datos.Descripcion);
		    	   
					$("#title_form").text("EDITAR PRODUCTO");
	        	  alert("Baja para modificar los datos");
	        	  $('#guardar').hide(500);
	        	  $('#actualizar').show(500);
	        	  $("#Clv_Pro").prop("readonly", true);
	          });
}

function funcion(){
	$("tbody").empty();
}

function mostrar_registros(opcion,busqueda){

	$.post("../../Productos",{
		accion : "mostrar",
		valor : opcion,
		search: busqueda
	},function(responseJson){
		
		var datos = JSON.parse(responseJson);

		for(var i in datos){
			$("tbody").append("<tr>" +
					"<td>"+datos[i].IDProducto+"</td>" +
					"<td>"+datos[i].Nombre+"</td>" +
					"<td>"+datos[i].Cantidad+"</td>" +
					"<td> $"+datos[i].Precio_V+"</td>" +
					"<td> $"+datos[i].Precio_C+"</td>" +
					"<td>"+datos[i].R_Categoria+"</td>" +
					"<td>"+datos[i].R_Proveedor+"</td>" +
					"<td>"+datos[i].Caducidad+"</td>" +
					"<td>"+datos[i].Descripcion+"</td>" +
					"<td>"+
					"<button onclick=\"editar_inicio("+datos[i].IDProducto+")\" class=\"editar_pro\"> <img width=\"25px\"  alt=\"icono-editar\" src=\"../img/editar-icono.svg\"></button>"+
					"<button onclick=\"eliminar("+datos[i].IDProducto+")\" class=\"eliminar_pro\"> <img width=\"25px\"  alt=\"icono-eliminar\" src=\"../img/eliminar-icono.svg\"></button>"+
					"</td>"+
                    "</tr>");
		}		
	}
	);
   
}
	
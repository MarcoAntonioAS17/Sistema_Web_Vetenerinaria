//Guardar datos en BD
   $(document).ready(function(){
	   
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
       
       $('.eliminar_pro').click(function(e){
    	   var codpro = $(this).val();
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
    	   
       });
       
       $('.editar_pro').click(function(e){
    	   var codvar = $(this).val();
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
	
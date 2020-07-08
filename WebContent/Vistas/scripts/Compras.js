
var contador =true;

$(document).ready(function(){
	
	contador=true;
	var Total;
	$("#Cantidad").val("1");
	cargar_productos($("#Clv_Pro").val());
	
	ocultar_elementos();
	$('#guardar').click(function(e){
		var confirma = confirm("¿Estás seguro que deseas finalizar la compra?");
		
		if(confirma == true){
			limpiar_campos();
			alert("Compra guardada");
			location.reload();
			
		}else{
			return;
		}
    });
	
	$('#cancelar').click(function(e){
		var confirma = confirm("¿Estás seguro que deseas CANCELAR la compra?");
		
		if(confirma == true){
			limpiar_campos();
		$.post("../../Compras",{
	 		accion : "cancelar_compra"
		},function(responseText){
			if(responseText != "false"){
				alert("La compra ha sido cancelada");
				location.reload();
			}else{
				  alert("Error al cancelar compra");
				  return;
			}
		});
			
		}else{
			return;
		}
	});
	
	$('#agregar').click(function(e){
	 	
		var falso=false;
		var varFecha = $("#Fec_C").val();
	   	var varHora = $("#Hra_C").val();
	   
	   	var varProveedor = $("#Clv_Pro").val();
	   	var varProducto = $("#Clv_Prod").val();
	   	var varCantidad = $("#Cantidad").val();
	   	
	   	$("#Cantidad").css({"border-color":"black","color":"black"});
		$("#Clv_Prod").css({"border-color":"black","color":"black"});
	   	
		if(varCantidad == "" || varCantidad <= 0){
	   		$("#Cantidad").css({"border-color":"red","color":"red"});
	   		falso=true;
	   	}
	   	if(varProducto == ""){
	   		$("#Clv_Prod").css({"border-color":"red","color":"red"});
	   		falso="true";
	   	}
	   	if(falso){
	   		alert("Faltan registros por acompletar");
	   		return;
	   	}
	   	
	 	if(contador){
	 		$.post("../../Compras",{
		 		accion : "agregar_producto",
				Fecha : varFecha,
				Hora : varHora,
				Producto: varProducto,
				Cantidad: varCantidad,
				Proveedor: varProveedor
			},function(responseText){
				
				if(responseText != "false"){
					Total=mostrar_registros(responseText);
					$('#tabla').show(500);
					limpiar_campos();
				}else{
					  alert("Error al agregar producto");
					  return;
				}
			});
	 		contador=false;
	 	}else{
	 		$.post("../../Compras",{
		 		accion : "agregar_producto2",
				Producto: varProducto,
				Cantidad: varCantidad
			},function(responseText){
				if(responseText != "false"){
					Total=mostrar_registros(responseText);
					limpiar_campos();
				}else{
					  alert("Error al agregar producto");
					  return;
				}
			});
	 	}
	   	
	});
	
	$("#Clv_Pro").change(function(e){
		cargar_productos($(this).val());
		
	});
});

function mostrar_registros(responseJson){
	$("tbody").empty();
	var suma=0;
	var datos = JSON.parse(responseJson);
	var i=0;
	for(i in datos){

		$("tbody").append("<tr>" +
				"<td>"+datos[i].Codigo+"</td>" +
				"<td>"+datos[i].Nombre+"</td>" +
				"<td>"+datos[i].Cantidad+"</td>" +
				"<td> $"+datos[i].Precio+"</td>" +
				"<td> $"+(datos[i].Precio*datos[i].Cantidad).toFixed(2)+"</td>" +
				"<td>"+
				"<button onclick=\"eliminar("+datos[i].Codigo+","+datos[i].Cantidad+")\" type=\"button\" class=\"eliminar_pro\"> <img width=\"25px\"  alt=\"icono-eliminar\" src=\"../img/eliminar-icono.svg\"></button>"+
				"</td>"+
                "</tr>");
		
		suma+=(datos[i].Precio*datos[i].Cantidad);
	}
	$("tbody").append("<tr>" +
			"<td></td><td></td>" +
			"<td></td>" +
			"<td> Total </td>" +
			"<td> $"+suma.toFixed(2)+"</td>" +
			"<td></td>"+
            "</tr>");
	return suma;
}

function cargar_productos(vProveedor){
	$("datalist").empty();
	$.post("../../Compras",{
		accion : "cargar_productos",
		Proveedor: vProveedor
	},function(responseJson){
		var datos = JSON.parse(responseJson);

		for(var i in datos){
			$("datalist").append(
				"<option value="+datos[i].Codigo+">"+datos[i].Codigo+", "+datos[i].Nombre+"</option>"
				);
		}
	}
	);
}

function eliminar(varProducto, varCantidad){

	$.post("../../Compras",{
 		accion : "borrar_producto",
		Producto: varProducto,
		Cantidad: varCantidad
	},function(responseText){
		if(responseText != "false"){
			mostrar_registros(responseText);
		}else{
			  alert("Error al agregar producto");
			  return;
		}
	});
}

function limpiar_campos(){
	$("#Clv_Prod").val("");	
	$("#Cantidad").val(1);	
}

function ocultar_elementos(){

	$('#tabla').hide(500);

}


